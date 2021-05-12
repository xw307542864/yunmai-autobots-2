package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.CarOperationTimeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarOperationTimeDao {
    int delete(String guid);

    int insert(CarOperationTimeEntity entity);

    CarOperationTimeEntity select(String guid);

    int update(CarOperationTimeEntity entity);

    List<CarOperationTimeEntity> getAllCarOperationTime(@Param("carId") String carId);

    List<CarOperationTimeEntity> getCarOperationTimeNoPerson(@Param("carId")String carId);
    
    /**
	 * 获取车辆运行时段
	 * @Title: getCarOperationTimeList  
	 * @param carId
	 * @return 
	 * List<CarOperationTimeEntity>
	 * @date 2017年8月3日 上午9:55:55
	 *
	 */
	public List<CarOperationTimeEntity> getCarOperationTimeList(@Param("carId")String carId);
	
    
}