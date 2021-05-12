package com.logibeat.cloud.common.cache.aspect;

import com.logibeat.cloud.common.cache.RedisCache;
import com.logibeat.cloud.common.cache.entity.CacheOperateEntity;
import com.logibeat.cloud.common.cache.enumType.CacheDBType;
import com.logibeat.cloud.common.cache.queue.CacheOperateQueue;
import com.logibeat.cloud.common.cache.util.HttpServletUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Component
//@Aspect
public class CacheTransactionAspect {
	
	private Logger logger = LoggerFactory.getLogger(CacheTransactionAspect.class);
	
	private final static String DEFAULT_RANDOM = "currentThread";
	
	@Autowired
	private RedisCache redisCache;

	@Pointcut("execution (* com.logibeat.cloud.*.controller..*.*(..))")
	public void CtrlMethod() {
	};

	@AfterReturning("CtrlMethod()")
	public void afterReturningMethod(JoinPoint jp) throws Throwable {
		
		boolean caching = redisCache.exists("caching", CacheDBType.BaseDB);
		if(caching){
			return ;
		}
		
		Object random = HttpServletUtil.getRequest().getSession().getAttribute(DEFAULT_RANDOM);
		if(random == null || random.toString().equals("")){
			return ;
		}
		
		List<CacheOperateEntity> objectList = redisCache.getRedisCacheList(random.toString(), CacheDBType.QueueDB);
		if(!objectList.isEmpty()){
			CacheOperateQueue.putList(random.toString(), objectList);
			logger.info(" [缓存事务] >>>>>> 当前操作{}执行成功，缓存本次操作，共{}条。", random, objectList.size());
		}
	}
	
	@AfterThrowing("CtrlMethod()")
	public void afterThrowingMethod(JoinPoint jp) throws Throwable {
		boolean caching = redisCache.exists("caching", CacheDBType.BaseDB);
		if(caching){
			return ;
		}
		
		Object random = HttpServletUtil.getRequest().getSession().getAttribute(DEFAULT_RANDOM);
		if(random == null || random.toString().equals("")){
			return ;
		}
		
		List<CacheOperateEntity> objectList = redisCache.getRedisCacheList(random.toString(), CacheDBType.QueueDB);
		logger.info(" [缓存事务] >>>>>> 当前操作{}执行失败，取消缓存 ，共{}条。", random, objectList.size());
		
		redisCache.del(random.toString(), CacheDBType.QueueDB);
	}
	
}
