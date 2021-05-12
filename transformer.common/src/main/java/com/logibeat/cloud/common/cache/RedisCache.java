package com.logibeat.cloud.common.cache;

import com.logibeat.cloud.common.cache.enumType.CacheDBType;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public interface RedisCache {

	// --------------------- object -----------------------------------

	public <T> T getRedisCache(String key, CacheDBType cacheDBType);

	public <T> boolean setRedisCache(String key, T value, CacheDBType cacheDBType);

	public <T> boolean setRedisCache(String key, T value, int expireSeconds, CacheDBType cacheDBType);

	public <T> boolean delRedisCache(String key, CacheDBType cacheDBType);

	// --------------------- list -----------------------------------

	public <T> boolean setRedisCacheList(String key, T value, CacheDBType cacheDBType);

	public <T> List<T> getRedisCacheList(String key, CacheDBType cacheDBType);

	public <T> boolean delRedisCacheList(String key, T value, CacheDBType cacheDBType);

	// -------------------- hmap -----------------------------------------

	public <T> List<T> hvals(String key, CacheDBType DBType);
	
	public void hmset(String key, Map<String, String> dataMap, CacheDBType DBType);

	public List<String> hmget(String key, String value, CacheDBType DBType);
	
	public void hdel(String key, String value, CacheDBType DBType);

	public Map<String, String> hgetAll(String key, CacheDBType DBType);
	
	// -------------------- String -----------------------------------------

	public String setex(final String key, int seconds, String value, CacheDBType DBType);

	public String get(final String key, CacheDBType DBType);

	public long del(final String key, CacheDBType DBType);
	
	public void expire(String key, int i, CacheDBType DBType);
	
	// -------------------- String -----------------------------------------

	public void zadd(final String key, String value, CacheDBType DBType);

	public void zrem(final String key, String value, CacheDBType DBType);

	public boolean zscore(final String key, String value, CacheDBType DBType);
	
	// -------------------- all -----------------------------------------
	
	public boolean exists(String key, CacheDBType cacheDBType);
	/**
	 * SortedSet缓存中添加值
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public  long zaddObject(String key, Object value);
	/**
	 * 从Set集合中移除元素
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public  long zrem(String key, Object value);
	/**
	 * 获取缓存
	 * @param key 键
	 * @return 值
	 */
	public  SortedSet<Object> getObjectSortedSet(String key);
}
