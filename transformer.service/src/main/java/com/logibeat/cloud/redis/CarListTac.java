package com.logibeat.cloud.redis;

import com.logibeat.cloud.common.cache.enumType.CacheDBType;
import com.logibeat.cloud.common.cache.impl.RedisCacheManager;
import com.logibeat.cloud.persistent.entity.CarsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 车辆信息缓存策略,carcars
 * 
 * @author yg
 *
 */
@Service
public class CarListTac {
	@Autowired
	private RedisCacheManager redisCacheManager;

	public List<CarsEntity> getCarListCache(String key) {

		return redisCacheManager.getRedisCache(key, CacheDBType.QueueDB);
	}

	public void setCarListReisCach(String key, List<CarsEntity> carList) {
		if(null!=carList&&carList.size()>0){
			redisCacheManager.setRedisCache(key, carList, CacheDBType.QueueDB);
		}
		
	}

	public void setCarListReisCachByEntity(String key, CarsEntity carsEntity) {

		List<CarsEntity> carsList = redisCacheManager.getRedisCache(key, CacheDBType.QueueDB);
		if (null != carsList && carsList.size() > 0) {
			carsList = carsList.parallelStream().filter(p -> null!=carsEntity.getCarID()&&!carsEntity.getCarID().equals(p.getCarID()))
					.collect(Collectors.toList());
			carsList.add(carsEntity);
			redisCacheManager.setRedisCache(key, carsList, CacheDBType.QueueDB);
		}
		
	}

	public void deleteCarListReisCachByEntity(String key, CarsEntity carsEntity) {
		List<CarsEntity> carsList = redisCacheManager.getRedisCache(key, CacheDBType.QueueDB);
		if(null!=carsList&&carsList.size()>0){
			
			carsList=carsList.parallelStream().filter(p ->null!=carsEntity.getCarID()&& !carsEntity.getCarID().equals(p.getCarID())).collect(Collectors.toList());
			redisCacheManager.setRedisCache(key, carsList, CacheDBType.QueueDB);
		}
	}
}
