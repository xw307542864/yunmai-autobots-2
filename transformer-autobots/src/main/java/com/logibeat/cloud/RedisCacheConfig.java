package com.logibeat.cloud;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangf on 15/5/3. 理论上来说 com.logibeat.cloud
 * 在war层会声明，并且配置文件已读取，故此类无需在web.xml声明 "classpath:redisConfig.properties",
 */
@Configuration
@EnableCaching
@RefreshScope
@ComponentScan(value = { "com.logibeat.cloud" })
public class RedisCacheConfig {

	@NacosValue(value = "${redis.host}", autoRefreshed = true)
	private String hostName;
	@NacosValue(value = "${redis.port}", autoRefreshed = true)
	private int hostPort;
	@NacosValue(value = "${redis.maxtotal}", autoRefreshed = true)
	private int maxtotal;
	@NacosValue(value = "${redis.maxidle}", autoRefreshed = true)
	private int maxidle;
	@NacosValue(value = "${redis.maxwait}", autoRefreshed = true)
	private int maxwait;
	@NacosValue(value = "${redis.pass}", autoRefreshed = true)
	private String pass;
	@NacosValue(value = "${redis.timeout}", autoRefreshed = true)
	private int timeout;
	@NacosValue(value = "${redis.datebase}", autoRefreshed = true)
	private String datebase;


	@Bean
	@Primary
	public RedisCacheManager getRedisCacheManager() {
		System.out.println("hostName========================================" + hostName);
		RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(getJedisConnectionFactory());
		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
		RedisCacheManager redisCacheManager = new RedisCacheManager(cacheWriter, configuration);
		return redisCacheManager;
	}

	@Bean
	public RedisTemplate getRedisTemplate() {
		RedisTemplate redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(getJedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return redisTemplate;
	}

	@Bean
	public JedisConnectionFactory getJedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(hostName);
		jedisConnectionFactory.setPort(hostPort);
		String password = pass;
		if (password != null && !password.equals("")) {
			jedisConnectionFactory.setPassword(password);
		}
		jedisConnectionFactory.setPoolConfig(getJedisPoolConfig());
		return jedisConnectionFactory;
	}

	@Bean
	public JedisPoolConfig getJedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxtotal);
		poolConfig.setMaxIdle(maxidle);
		poolConfig.setMaxWaitMillis(maxwait);
		poolConfig.setBlockWhenExhausted(true);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestWhileIdle(true);
		return poolConfig;
	}

	@Bean
	public GenericObjectPoolConfig getShardedJedisPoolConfig() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(maxtotal);
		poolConfig.setMaxIdle(maxidle);
		poolConfig.setMaxWaitMillis(maxwait);
		poolConfig.setBlockWhenExhausted(true);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestWhileIdle(true);
		return poolConfig;
	}

	@Bean
	public JedisPool getJedisPool() {
		String password = pass;
		JedisPool jedisPool = null;
		if (password != null && !password.equals("")) {
			jedisPool = new JedisPool(getJedisPoolConfig(), hostName, hostPort,
					timeout, password);
		} else {
			jedisPool = new JedisPool(getJedisPoolConfig(), hostName, hostPort,
					timeout);
		}
		return jedisPool;
	}

	@Bean
	public ShardedJedisPool getShardedJedisPool() {
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo jedisShardInfo = new JedisShardInfo(datebase);
		jedisShardInfo.setConnectionTimeout(timeout);
		String password = pass;
		if (password != null && !password.equals("")) {
			jedisShardInfo.setPassword(password);
		} else {
			jedisShardInfo.setPassword(null);
		}
		jedisShardInfo.setSoTimeout(0);
		shards.add(jedisShardInfo);
		ShardedJedisPool jedisPool = new ShardedJedisPool(getShardedJedisPoolConfig(), shards);
		return jedisPool;
	}


	/*@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

		StringRedisTemplate template = new StringRedisTemplate(factory);

		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(

				Object.class);

		ObjectMapper om = new ObjectMapper();

		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		jackson2JsonRedisSerializer.setObjectMapper(om);

		//序列化 值时使用此序列化方法

		template.setValueSerializer(jackson2JsonRedisSerializer);

		template.afterPropertiesSet();

		return template;

	}

	*//**
	 * 往容器中添加RedisCacheManager容器，并设置序列化方式
	 *
	 * @param redisConnectionFactory
	 * @return
	 *//*
	@Bean
	@Primary
	public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));
		redisCacheConfiguration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
		return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);


	}

	*//**
	 * 使用Jackson序列化器
	 *
	 * @return
	 *//*
	private RedisSerializer<Object> valueSerializer() {
		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		serializer.setObjectMapper(objectMapper);
		return serializer;
	}
*/
}