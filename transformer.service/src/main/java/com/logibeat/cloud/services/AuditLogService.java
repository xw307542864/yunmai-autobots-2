package com.logibeat.cloud.services;


import com.logibeat.cloud.common.vo.*;
import com.logibeat.cloud.core.dto.AuditQualityDto;
import com.logibeat.cloud.core.dto.AuditVehicleDto;
import com.logibeat.cloud.core.dto.DrivingLicenseDto;
import com.logibeat.cloud.vo.CarShortInfoVo;

import java.util.List;

public interface AuditLogService {
	/**
	 * 获得认证状态和认证完成进度
	 * @param entId
	 * @return
	 */
	 AuditStatusVo getAuditStatus(String entId);
	
	/**
	 * 驾驶证认证
	 * @param drivingLicenseDto
	 */
	 void auditDrivingLicense(DrivingLicenseDto drivingLicenseDto);

	/**
	 * 获取驾驶证认证信息
	 * @param baseUserId
	 * @return
	 */
	 DrivingLicenseAuditVo getDrivingLicenseAudit(String baseUserId);

	/**
	 * 司机从业资格认证
	 * @param auditQualityDto
	 */
	 void auditQuality(AuditQualityDto auditQualityDto);

	/**
	 * 车辆认证
	 * @param auditVehicleDto
	 */
	CarShortInfoVo auditVehicle(AuditVehicleDto auditVehicleDto);


	/**
	 * 获取司机从业资格认证列表
	 * @param personiD
	 * @return
	 */
	List<QualityAuditVo> getQualityAuditList(String personiD);


	/**
	 * 认证失败信息
	 * @param auditId
	 * @param objectId
	 * @param objectType
	 * @param objectItem
	 * @return
	 */
	AuditFailInfo getAuditFailInfo(String auditId, String objectId, Integer objectType, String objectItem);


	void authenticationExpirePush();

	void pushAuditResult(String auditId,Integer auditStatus);


	AuditExpireVo myAuthenticationExpire(String personId);


	void delete(String objectId,Integer auditType);


	void initEx();
	
	GetDriverIdentityVo getDriverIdentity(String personId);


}
