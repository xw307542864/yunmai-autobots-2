package com.logibeat.cloud.common.cache.aspect;

import com.logibeat.cloud.common.cache.RedisCache;
import com.logibeat.cloud.common.cache.annotation.CacheList;
import com.logibeat.cloud.common.cache.annotation.CacheList.CacheListType;
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
import java.util.List;
import java.util.Map;

//@Component
//@Aspect
public class CacheListAspect {
	
	private Logger logger = LoggerFactory.getLogger(CacheListAspect.class);
	
	private final static String DEFAULT_RANDOM = "currentThread";
	
	@Autowired
	private RedisCache redisCache;

	@Pointcut("execution (* com.logibeat.cloud.*.dao..*.*(..))")
	public void daoExpandMethod() {
	};

	@Pointcut("execution (* com.logibeat.cloud.*.dao..*.*(..))")
	public void daoMethod() {
	};

	@Around("(daoExpandMethod() || daoMethod()) && @annotation(cacheList)")
	public Object aroundMethod(ProceedingJoinPoint joinpoint, CacheList cacheList) throws Throwable {

		boolean caching = redisCache.exists("caching", CacheDBType.BaseDB);
		if(caching){
			Object object = joinpoint.proceed();
			return object;
		}
		
		// 获取参数值
		Object[] args = joinpoint.getArgs();
		
		// 获取cacheList配置属性
		String key = cacheList.key();
		Class classType = cacheList.classType();
		
		// 允许用多个参数组装一个key.
		String[] addition = cacheList.addition();
		
		// get rediskey. 
		String cacheKey = addition == null || addition.length <= 0 ? key : CacheAspectUtil.getCacheKey(key, addition, args);
		if(StringUtils.isBlank(cacheKey)){
			Object object = joinpoint.proceed();
			return object;
		}
		
		// cacheList select策略
		if (cacheList.cacheListType().equals(CacheListType.SELECT)) {
			List<String> jsonList = redisCache.hvals(cacheKey, CacheDBType.StrategyDB);
			
			if (jsonList != null && jsonList.size() > 0) {
//				List<Object> objectList = jsonList.parallelStream().map(p->JSON.parseObject(p.toString(), classType)).collect(Collectors.toList());
				List<Object> objectList = new ArrayList<>();
				for(String o: jsonList){
					objectList.add(JsonMapper.buildNonDefaultMapper().fromJson(o.toString(), classType));
				}
				
				logger.info(" [缓存策略] >>>>>> 命中缓存KEY:{}, 获取数据{}条..", cacheKey, jsonList.size());
				return objectList;
			}
		}

		Object object = joinpoint.proceed();

		if (object == null) {
			return object;
		}
		
		if ((object instanceof ArrayList)) {
			if (((List) object).size() <= 0) {
				return object;
			}
		} 

		String id = cacheList.id();
		
		if(cacheList.cacheListType().equals(CacheListType.SELECT)){
			Map<String, String> inputMap = CacheAspectUtil.getCacheMap(id, object);
			redisCache.hmset(cacheKey, inputMap, CacheDBType.StrategyDB);
			logger.info(" [缓存操作] >>>>>> 批量缓存KEY:{}, 共{}条数据..", cacheKey, inputMap.size());
			return object;
		}
		
		HttpServletRequest request = HttpServletUtil.getRequest();
		Object random = request == null ? "notThread" : request.getSession().getAttribute(DEFAULT_RANDOM);
		if(random == null || random.toString().equals("")){
			return object;
		}
		
		CacheOperateEntity cacheOperateEntity = CacheAspectUtil.getCacheOperateEntity(cacheKey, id, object, classType, cacheList.cacheListType());
		if(cacheOperateEntity != null){
			redisCache.setRedisCacheList(random.toString(), cacheOperateEntity, CacheDBType.QueueDB);
		}
		
		return object;
	}
}
