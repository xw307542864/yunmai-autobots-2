package com.logibeat.cloud.common.cache.queue.thread;

import com.logibeat.cloud.common.cache.RedisCache;
import com.logibeat.cloud.common.cache.annotation.CacheList.CacheListType;
import com.logibeat.cloud.common.cache.annotation.CacheOperate.CacheOperateType;
import com.logibeat.cloud.common.cache.entity.CacheOperateEntity;
import com.logibeat.cloud.common.cache.entity.CacheOperateListEntity;
import com.logibeat.cloud.common.cache.entity.CacheOperateObjectEntity;
import com.logibeat.cloud.common.cache.entity.CacheOperateQueueEntity;
import com.logibeat.cloud.common.cache.enumType.CacheDBType;
import com.logibeat.cloud.common.cache.queue.CacheOperateQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

//@Component
public class CacheOperateConsumer implements Runnable {

	private Logger logger = LoggerFactory.getLogger(CacheOperateConsumer.class);

	@Autowired
	private RedisCache redisCache;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("[缓存操作队列] >>> start ... ");
		while (true) {
			CacheOperateQueueEntity cacheOperateQueueEntity = CacheOperateQueue.take();
			if (cacheOperateQueueEntity == null || cacheOperateQueueEntity.getCacheOperateList().isEmpty()) {
				continue;
			}

			try {
				doOperate(cacheOperateQueueEntity);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	private void doOperate(CacheOperateQueueEntity cacheOperateQueueEntity) throws Exception {
		// 获取操作集合
		List<CacheOperateEntity> operateList = cacheOperateQueueEntity.getCacheOperateList();

		// 循环这个混蛋
		for (CacheOperateEntity operate : operateList) {
			if(operate instanceof CacheOperateObjectEntity){
				run((CacheOperateObjectEntity) operate);
			} else if(operate instanceof CacheOperateListEntity){
				run((CacheOperateListEntity) operate);
			}
		}

		// 删除备份
		redisCache.del(cacheOperateQueueEntity.getRandom(), CacheDBType.QueueDB);
	}
	
	/**
	 * 单表对象缓存
	 * @param operate
	 */
	private void run(CacheOperateObjectEntity operate){
//		String zsetKey = operate.getValue() + "~keys";
//		if(!redisCache.zscore(zsetKey, operate.getKey(), CacheDBType.DataDB)){
//			logger.info(" [缓存操作] >>>>>> 缓存KEY:{}不存在，强制不命中缓存..", operate.getKey());
//			continue;
//		}

		if (operate.getCacheOperateType().equals(CacheOperateType.ADD)) {
			redisCache.setex(operate.getKey(), 3600, operate.getEntity().toString(), CacheDBType.DataDB);
//			redisCache.zadd(zsetKey, operate.getKey(), CacheDBType.DataDB);
			logger.info(" [缓存对象] >>>>>> 存入缓存KEY:{}..", operate.getKey());
		} else if (operate.getCacheOperateType().equals(CacheOperateType.UPDATE)) {
			redisCache.setex(operate.getKey(), 3600, operate.getEntity().toString(), CacheDBType.DataDB);
//			redisCache.zadd(zsetKey, operate.getKey(), CacheDBType.DataDB);
			logger.info(" [缓存对象] >>>>>> 修改缓存KEY:{}", operate.getKey());
		} else if (operate.getCacheOperateType().equals(CacheOperateType.DELETE)) {
			redisCache.del(operate.getKey(), CacheDBType.DataDB);
//			redisCache.zrem(zsetKey, operate.getKey(), CacheDBType.DataDB);
			logger.info(" [缓存对象] >>>>>> 删除缓存KEY:{}", operate.getKey());
		}
	}
	
	/**
	 * 缓存策略
	 * @param cacheList
	 */
	private void run(CacheOperateListEntity cacheList){
		String id = cacheList.getId();
		String cacheKey = cacheList.getKey();
		Object object = cacheList.getEntity();
		
		if(!redisCache.exists(cacheKey, CacheDBType.StrategyDB)){
			logger.info(" [缓存操作] >>>>>> 缓存KEY:{}不存在，强制不命中缓存..", cacheKey);
			return ;
		}
		
		if (cacheList.getCacheListType().equals(CacheListType.ADD)) {
			Map<String, String> inputMap = CacheConsumerUtil.getCacheMap(id, object);
			redisCache.hmset(cacheKey, inputMap, CacheDBType.StrategyDB);
			logger.info(" [缓存列表] >>>>>> 存入缓存KEY:{}, 共{}条数据..", cacheKey, inputMap.size());
		} else if (cacheList.getCacheListType().equals(CacheListType.DELETE)) {
			String field = CacheConsumerUtil.getFieldValue(id, object);
			redisCache.hdel(cacheKey, field, CacheDBType.StrategyDB);
			logger.info(" [缓存列表] >>>>>> 删除缓存KEY:{}, FIELD:{}..", cacheKey, field);
		} else if (cacheList.getCacheListType().equals(CacheListType.UPDATE)) {
			Map<String, String> inputMap = CacheConsumerUtil.getCacheMap(id, object);
			String field = CacheConsumerUtil.getFieldValue(id, object);
			redisCache.hdel(cacheKey, field, CacheDBType.StrategyDB);
			redisCache.hmset(cacheKey, inputMap, CacheDBType.StrategyDB);
			logger.info(" [缓存列表] >>>>>> 修改缓存KEY:{}, FIELD:{}..", cacheKey, field);
		}
	}
}
