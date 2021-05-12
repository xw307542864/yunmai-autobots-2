package com.logibeat.cloud.common.cache.aspect;

import com.logibeat.cloud.common.cache.RedisCache;
import com.logibeat.cloud.common.cache.annotation.CacheOperate;
import com.logibeat.cloud.common.cache.annotation.CacheOperate.CacheOperateType;
import com.logibeat.cloud.common.cache.entity.CacheOperateEntity;
import com.logibeat.cloud.common.cache.enumType.CacheDBType;
import com.logibeat.cloud.common.cache.util.HttpServletUtil;
import com.logibeat.cloud.common.cache.util.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

//@Component
//@Aspect
public class CacheOperateAspect {
	
	private Logger logger = LoggerFactory.getLogger(CacheOperateAspect.class);
	
	private final static String DEFAULT_RANDOM = "currentThread";
	
	@Autowired
	private RedisCache redisCache;

	@Pointcut("execution (* com.logibeat.cloud.*.dao..*.*(..))")
	public void daoMethod() {
	};

	@Around("daoMethod() && @annotation(cacheOperate)")
	public Object aroundMethod(ProceedingJoinPoint joinpoint, CacheOperate cacheOperate) throws Throwable {
		
		boolean caching = redisCache.exists("caching", CacheDBType.BaseDB);
		if(caching){
			Object object = joinpoint.proceed();
			return object;
		}
		
		// 获取cacheList配置属性
		String key = cacheOperate.key();
		Class classType = cacheOperate.classType();

		// 允许用多个参数组装一个key.
		String[] addition = cacheOperate.addition();

		// 获取参数值
		Object[] args = joinpoint.getArgs();

		// get rediskey.
		String cacheKey = addition == null || addition.length <= 0 ? key : CacheAspectUtil.getCacheKey(key, addition, args);
		if(StringUtils.isBlank(cacheKey)){
			Object object = joinpoint.proceed();
			return object;
		}
		
		if(cacheOperate.cacheOperateType().equals(CacheOperateType.SELECT)){
			String json = redisCache.get(cacheKey, CacheDBType.DataDB);
			if(StringUtils.isNotBlank(json)){
				Object object = JsonMapper.buildNonDefaultMapper().fromJson(json.toString(), classType);
				return object;
			}
		}
		
		// -----------------------------------------------------------------
		Object object = joinpoint.proceed();
		
		if(object == null){
			return object;
		}
		
		if ((object instanceof ArrayList)) {
			return object;
		}
		// -----------------------------------------------------------------

		if(cacheOperate.cacheOperateType().equals(CacheOperateType.SELECT)){
			String json =  JsonMapper.toNonDefaultJson(object);
			redisCache.setex(cacheKey, 432000, json, CacheDBType.DataDB);
			return object;
		}
		
		HttpServletRequest request = HttpServletUtil.getRequest();
		Object random = request == null ? "notThread" : request.getSession().getAttribute(DEFAULT_RANDOM);
		if(random == null || random.toString().equals("")){
			return object;
		}
		
		CacheOperateEntity cacheOperateEntity = CacheAspectUtil.getCacheOperateEntity(cacheOperate.value(), cacheKey, object, classType, cacheOperate.cacheOperateType());
		if(cacheOperateEntity != null){
			redisCache.setRedisCacheList(random.toString(), cacheOperateEntity, CacheDBType.QueueDB);
		}
		return object;
	}

}
