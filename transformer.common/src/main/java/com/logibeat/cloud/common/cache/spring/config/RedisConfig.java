package com.logibeat.cloud.common.cache.spring.config;

import com.logibeat.cloud.common.cache.queue.thread.CacheOperateConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangf on 15/5/3. 理论上来说 com.logibeat.cloud
 * 在war层会声明，并且配置文件已读取，故此类无需在web.xml声明 "classpath:redisConfig.properties",
 */
@Configuration
@ComponentScan(value = { "com.logibeat.cloud.common.cache" })
public class RedisConfig {
//
//	@Resource
//	private Environment env;
//
//	private String eval(String prop) {
//		String result = env.getRequiredProperty(prop);
//		if (result != null) {
//			result = result.trim();
//		}
//		return result;
//	}
//
//	private Integer evalInt(String prop) {
//		return Integer.valueOf(env.getRequiredProperty(prop));
//	}
//
//	@Bean
//	public RedisCacheManager getRedisCacheManager() {
//		RedisCacheManager redisCacheManager = new RedisCacheManager(getRedisTemplate());
//		return redisCacheManager;
//	}
//
//	@Bean
//	public RedisTemplate getRedisTemplate() {
//		RedisTemplate redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(getJedisConnectionFactory());
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//		return redisTemplate;
//	}
//
//	@Bean
//	public JedisConnectionFactory getJedisConnectionFactory() {
//		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//		jedisConnectionFactory.setHostName(eval("redis.host"));
//		jedisConnectionFactory.setPort(evalInt("redis.port"));
//		String password = eval("redis.pass");
//		if (password != null && !password.equals("")) {
//			jedisConnectionFactory.setPassword(password);
//		}
//		jedisConnectionFactory.setPoolConfig(getJedisPoolConfig());
//		return jedisConnectionFactory;
//	}
//
//	@Bean
//	public JedisPoolConfig getJedisPoolConfig() {
//		JedisPoolConfig poolConfig = new JedisPoolConfig();
//		poolConfig.setMaxTotal(evalInt("redis.maxtotal"));
//		poolConfig.setMaxIdle(evalInt("redis.maxidle"));
//		poolConfig.setMaxWaitMillis(evalInt("redis.maxwait"));
//		poolConfig.setBlockWhenExhausted(true);
//		poolConfig.setTestOnBorrow(true);
//		poolConfig.setTestWhileIdle(true);
//		return poolConfig;
//	}
//
//	@Bean
//	public JedisPool getJedisPool() {
//		String password = eval("redis.pass");
//		JedisPool jedisPool = null;
//		if (password != null && !password.equals("")) {
//			jedisPool = new JedisPool(getJedisPoolConfig(), eval("redis.host"), evalInt("redis.port"),
//					evalInt("redis.timeout"), password);
//		} else {
//			jedisPool = new JedisPool(getJedisPoolConfig(), eval("redis.host"), evalInt("redis.port"),
//					evalInt("redis.timeout"));
//		}
//		return jedisPool;
//	}

	@Bean
	public CacheOperateConsumer createCacheOperateConsumer() {
		CacheOperateConsumer cacheOperateConsumer = new CacheOperateConsumer();
		new Thread(cacheOperateConsumer).start();
		return cacheOperateConsumer;
	}
}
