package com.logibeat.cloud.redis;

import com.logibeat.cloud.common.cache.enumType.CacheDBType;
import com.logibeat.cloud.common.cache.impl.RedisCacheManager;
import com.logibeat.cloud.persistent.entity.EnterpriseCooperateCarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 企业车辆组织关系，缓存策略,ent_cooperatecar
 * @author yg
 *
 */
@Service
public class CoopcarListTac {
	@Autowired
	private RedisCacheManager redisCacheManager;

	public List<EnterpriseCooperateCarEntity> getCoopCarListCache(String key) {

		return redisCacheManager.getRedisCache(key, CacheDBType.QueueDB);
	}

	public void setCoopCarListReisCach(String key, List<EnterpriseCooperateCarEntity> carList) {
		if(null!=carList&&carList.size()>0){
			redisCacheManager.setRedisCache(key, carList, CacheDBType.QueueDB);
		}
		
	}

	public void setCarListReisCachByEntity(String key, EnterpriseCooperateCarEntity enterpriseCooperateCarEntity) {

		List<EnterpriseCooperateCarEntity> carsList = redisCacheManager.getRedisCache(key, CacheDBType.QueueDB);
		if (null != carsList && carsList.size() > 0) {
			carsList = carsList.parallelStream()
					.filter(p ->null!=enterpriseCooperateCarEntity.getGuid()&& !enterpriseCooperateCarEntity.getGuid().equals(p.getGuid()))
					.collect(Collectors.toList());
			carsList.add(enterpriseCooperateCarEntity);
			redisCacheManager.setRedisCache(key, carsList, CacheDBType.QueueDB);
		} 
		
	}
	public void deleteCarListReisCachByEntity(String key, EnterpriseCooperateCarEntity enterpriseCooperateCarEntity) {
		List<EnterpriseCooperateCarEntity> carsList = redisCacheManager.getRedisCache(key, CacheDBType.QueueDB);
		if(null!=carsList&&carsList.size()>0){
			
			carsList=carsList.parallelStream().filter(p -> null!=enterpriseCooperateCarEntity.getGuid()&&!enterpriseCooperateCarEntity.getGuid().equals(p.getGuid()))
					.collect(Collectors.toList());
			redisCacheManager.setRedisCache(key, carsList, CacheDBType.QueueDB);
		}
	}
 
 
}
