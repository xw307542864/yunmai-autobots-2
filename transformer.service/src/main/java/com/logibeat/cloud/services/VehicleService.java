package com.logibeat.cloud.services;


import com.logibeat.cloud.vo.CarShortInfoVo;
import com.logibeat.cloud.core.dto.AuditVehicleDto;
import com.logibeat.cloud.persistent.entity.SystemPersonEntity;

public interface VehicleService {

	/**
	 * 添加车辆
	 * @param auditVehicleDto
	 */
	CarShortInfoVo addOrUpdateVehicle(AuditVehicleDto auditVehicleDto, SystemPersonEntity person);



}
