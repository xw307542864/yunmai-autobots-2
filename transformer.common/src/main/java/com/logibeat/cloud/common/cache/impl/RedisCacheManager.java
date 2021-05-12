package com.logibeat.cloud.common.cache.impl;

import com.logibeat.cloud.common.cache.RedisCache;
import com.logibeat.cloud.common.cache.enumType.CacheDBType;
import com.logibeat.cloud.common.cache.util.StringUtils;
import com.logibeat.cloud.util.tools.ObjectUtils;
import com.logibeat.cloud.util.tools.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RedisCacheManager implements RedisCache {

	private Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private ShardedJedisPool shardedJedisPool;
	// 设置缓存，始终有效的非强制缓存
	public static final int NO_EXPIRE = -1;

	public synchronized Jedis acquireConnection(CacheDBType DBType) {
		Jedis jedis = jedisPool.getResource();
		jedis.select(DBType.getValue());

//		logger.info(" --> redis open resource ..");
		return jedis;
	}
	/**
	 * 获取资源
	 * @return
	 * @throws JedisException
	 */
	public synchronized ShardedJedis getResource() throws JedisException {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
//			logger.debug("getResource.", jedis);
		} catch (JedisException e) {
			//logger.warn("getResource.", e);
			returnBrokenResource(jedis);
			throw e;
		}
		return jedis;
	}
	public <T> boolean setRedisCache(String key, T value, int expireSeconds, CacheDBType cacheDBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(cacheDBType);

			if (jedis.exists(key.getBytes())) {
				jedis.del(key.getBytes());
			}

			jedis.set(key.getBytes(), SerializeUtil.serialize(value));

			if (expireSeconds > 0) {
				jedis.expire(key.getBytes(), expireSeconds);
			}
			logger.info(" --> add redisCache = " + key + ", expireSeconds = " + expireSeconds);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
		return false;
	}

	public <T> T getRedisCache(String key, CacheDBType cacheDBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(cacheDBType);

			byte[] bytes = jedis.get(key.getBytes());
			if (bytes == null) {
				return null;
			}

			logger.info(" --> get redisCache = " + key);
			return (T) SerializeUtil.unserialize(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
		return null;
	}

	public <T> boolean setRedisCache(String key, T value, CacheDBType cacheDBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(cacheDBType);

			if (jedis.exists(key.getBytes())) {
				jedis.del(key.getBytes());
			}

			jedis.set(key.getBytes(), SerializeUtil.serialize(value));

			logger.info(" --> add redisCache = " + key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
		return false;
	}

	public <T> boolean delRedisCache(String key, CacheDBType cacheDBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(cacheDBType);
			jedis.del(key.getBytes());
			logger.info(" --> delete redisCache = " + key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
		return false;
	}

	public <T> boolean setRedisCacheList(String key, T value, CacheDBType cacheDBType) {
		Jedis jedis = null;
		try {
			List<Object> objList;
			if (value instanceof List) {
				objList = (ArrayList<Object>) value;
			} else {
				objList = new ArrayList<Object>(Arrays.asList(value));
			}

			jedis = acquireConnection(cacheDBType);
			for (Object o : objList) {
//				delRedisCacheList(key, o, cacheDBType);
				jedis.rpush(key.getBytes(), SerializeUtil.serialize(o));
				logger.info(" --> set redisCache list = " + key + ":" + o.toString());
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}

		return false;
	}

	public <T> List<T> getRedisCacheList(String key, CacheDBType cacheDBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(cacheDBType);
			List<byte[]> list = jedis.lrange(key.getBytes(), 0, 9999);

			List<T> resultList = list.parallelStream().map(p -> (T) SerializeUtil.unserialize(p)).collect(Collectors.toList());
			logger.info(" --> get redisCache list = " + key);
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}

		return null;
	}

	public <T> boolean delRedisCacheList(String key, T value, CacheDBType cacheDBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(cacheDBType);
			jedis.lrem(key.getBytes(), 0, SerializeUtil.serialize(value));

			logger.info(" --> delete redisCacheList = " + key + ":" + value.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
		return false;
	}
	
	public <T> List<T> hvals(String key, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
			List<T> list = (List<T>) jedis.hvals(key);
			
//			List<T> resultList = list.parallelStream().map(p -> (T)p).collect(Collectors.toList());
//			logger.info(" --> hvals redisCache = " + key);
			return list; 
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}

	public void hmset(String key, Map<String, String> dataMap, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
			jedis.hmset(key, dataMap);
//			logger.info(" --> hmset redisCache = " + key);
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}

	public List<String> hmget(String key, String value, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
//			logger.info(" --> hmget redisCache = " + key);
			return jedis.hmget(key, value);
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}
	
	public void hdel(String key, String value, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
//			logger.info(" --> hdel redisCache = " + key);
			jedis.hdel(key, value); 
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}

	public Map<String, String> hgetAll(String key, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
			logger.info(" --> hgetAll redisCache = " + key);
			return jedis.hgetAll(key);
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}

	public String setex(final String key, int seconds, String value, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
			logger.info(" --> setex redisCache = " + key);
			return jedis.setex(key, seconds, value);
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}

	public String get(final String key, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
			return jedis.get(key);
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}

	public long del(final String key, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
			return jedis.del(key);
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}

	public void expire(String key, int i, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
			jedis.expire(key, i);
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}
	
	@Override
	public void zadd(String key, String value, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
			jedis.zadd(key, 0, value);
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}

	@Override
	public void zrem(String key, String value, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
			jedis.zrem(key, value);
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}

	@Override
	public boolean zscore(String key, String value, CacheDBType DBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(DBType);
			Double count = jedis.zscore(key, value) == null ? 0d : jedis.zscore(key, value);
			return count > 0 ? true : false;
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
	}

	public boolean exists(String key, CacheDBType cacheDBType) {
		Jedis jedis = null;
		try {
			jedis = acquireConnection(cacheDBType);
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != jedis) {
				jedis.resetState();
				jedis.close();
			}
		}
		return false;
	}
	
	/**
	 * 归还资源
	 * @param jedis
	 * @param isBroken
	 */
	public  void returnBrokenResource(ShardedJedis jedis) {
		if (jedis != null) {
		    shardedJedisPool.returnBrokenResource(jedis);
		}
	}
	/**
	 * 释放资源
	 * @param jedis
	 * @param isBroken
	 */
	public  void returnResource(ShardedJedis jedis) {
		if (jedis != null) {
		    shardedJedisPool.returnResource(jedis);
		}
	}
	/**
	 * SortedSet缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */ 
	@Override
	public long zaddObject(String key, Object value) {
	    long result = 0;
		ShardedJedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.zadd(getBytesKey(key),0, toBytes(value));
			logger.info("执行动态缓存插入操作成功or失败{}，zaddObject {} = {}",result, key, value);
		} catch (Exception e) {
			logger.info("执行动态缓存插入操作失败，zaddObject {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}
	/**
	 * 从Set集合中移除元素
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	@Override
	public long zrem(String key, Object value) {
	    long result = 0;
		ShardedJedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.zrem(getBytesKey(key), toBytes(value));
			logger.debug("移除是否成功{},zrem {} = {}", result, key, value);
		} catch (Exception e) {
			logger.warn("zrem {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}
	/**
	 * 获取缓存
	 * @param key 键
	 * @return 值
	 */ 
	@Override
	public SortedSet<Object> getObjectSortedSet(String key) {
	    SortedSet<Object> value = null;
		ShardedJedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(getBytesKey(key))) {
				value =new TreeSet<Object>();
				Set<byte[]> set = jedis.zrange(getBytesKey(key), 0, -1);
				if(set!=null&&set.size()>0){
					for (byte[] bs : set){
						value.add(toObject(bs));
					}
					logger.debug("getObjectSortedSet {} = {}", key, value);
				}
			}
		} catch (Exception e) {
			logger.warn("getObjectSortedSet {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}
	/**
	 * 获取byte[]类型Key
	 * @param key
	 * @return
	 */
	public static byte[] getBytesKey(Object object){
		if(object instanceof String){
    		return StringUtils.getBytes((String)object);
    	}else{
    		return ObjectUtils.serialize(object);
    	}
	}
	
	/**
	 * Object转换byte[]类型
	 * @param key
	 * @return
	 */
	public static byte[] toBytes(Object object){
    	return ObjectUtils.serialize(object);
	}

	/**
	 * byte[]型转换Object
	 * @param key
	 * @return
	 */
	public static Object toObject(byte[] bytes){
		return ObjectUtils.unserialize(bytes);
	}



}
