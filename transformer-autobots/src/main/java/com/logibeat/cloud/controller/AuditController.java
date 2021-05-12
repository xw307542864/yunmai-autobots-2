package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.common.vo.AuditStatusVo;
import com.logibeat.cloud.common.vo.DrivingLicenseAuditVo;
import com.logibeat.cloud.common.vo.GetDriverIdentityVo;
import com.logibeat.cloud.core.dto.AuditQualityDto;
import com.logibeat.cloud.core.dto.AuditVehicleDto;
import com.logibeat.cloud.core.dto.DrivingLicenseDto;
import com.logibeat.cloud.services.AuditLogService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证
 *
 */
@Controller
@RequestMapping(value = "ent/Audit/api")
@Scope("prototype")
public class AuditController extends BaseController {
	@Autowired
	private AuditLogService auditLogService;
	
	/**
	 * 获得企业认证状态
	 * @param
	 * @return
     */
	@RequestMapping(value = "/User/GetEntAuditStatus")
	@ResponseBody
	@NotLogin
	public JSONPrompt<AuditStatusVo> getEntAuditStatus(String entId) {
		AuditStatusVo auditStatusVo = auditLogService.getAuditStatus(entId);
		return new JSONPrompt<>(auditStatusVo);
	}
	
	/**
	 * 驾驶证认证
	 * @param drivingLicenseDto
	 * @return
	 */
	@RequestMapping(value = "/User/auditDrivingLicense")
	@ResponseBody
	@NotLogin
	public JSONPrompt auditDrivingLicense(DrivingLicenseDto drivingLicenseDto) {
		JSONPrompt jsonResult = new JSONPrompt();
		auditLogService.auditDrivingLicense(drivingLicenseDto);
		return jsonResult;
	}


	/**
	 * 从业资格认证
	 * @param auditQualityDto
	 * @return
	 */
	@RequestMapping(value = "/User/auditQuality")
	@ResponseBody
	@NotLogin
	public JSONPrompt auditQuality(AuditQualityDto auditQualityDto){
		auditLogService.auditQuality(auditQualityDto);
		return  new JSONPrompt();
	}


	/**
	 * 车辆认证
	 *
	 * @param auditVehicleDto
	 * @return
	 */
	@RequestMapping(value = "/User/auditVehicle")
	@ResponseBody
	@NotLogin
	public JSONPrompt auditVehicle(@RequestBody AuditVehicleDto auditVehicleDto){
		if(StringUtils.isBlank(auditVehicleDto.getSourceType())) {
			auditVehicleDto.setSourceType(baseRequestDTO.getClientType());
		}
		return  new JSONPrompt(auditLogService.auditVehicle(auditVehicleDto));
	}


	/**
	 * 获取司机从业资格认证
	 * @param personId
	 * @return
	 */
	@RequestMapping(value = "/User/getQualityAuditList")
	@ResponseBody
	public JSONPrompt getQualityAuditList(String personId){
		return  new JSONPrompt(auditLogService.getQualityAuditList(personId));
	}


	/**
	 * 最新驾驶证认证信息
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/User/getDrivingLicenseAudit")
	@ResponseBody
	@NotLogin
	public JSONPrompt<DrivingLicenseAuditVo> getDrivingLicenseAudit(String baseUserId) {
		DrivingLicenseAuditVo driverCertificationVo=auditLogService.getDrivingLicenseAudit(baseUserId);
		return new JSONPrompt(driverCertificationVo);
	}


	/**
	 * 获取审核信息
	 * @param auditId
	 * @param objectId
	 * @param objectType
	 * @param objectItem
	 * @return
	 */
	@RequestMapping(value = "/User/getAuditFailInfo")
	@ResponseBody
	@NotLogin
	public JSONPrompt getAuditFailInfo(String auditId,String objectId,Integer objectType,String objectItem){
		return new JSONPrompt(auditLogService.getAuditFailInfo(auditId,objectId,objectType,objectItem ));
	}


	/**
	 * 实名认证推送（即将到期、过期）
	 * @return
	 */
	@RequestMapping(value = "/User/authenticationExpirePush")
	@ResponseBody
	@NotLogin
	public JSONPrompt authenticationExpirePush(){
		auditLogService.authenticationExpirePush();
		return  new JSONPrompt();
	}


	/**
	 * 我的认证过期情况
	 * @param personId
	 * @return
	 */
	@RequestMapping(value = "/User/myAuthenticationExpire")
	@ResponseBody
	@NotLogin
	public JSONPrompt myAuthenticationExpire(String personId){
		return  new JSONPrompt(auditLogService.myAuthenticationExpire(personId));
	}


	/**
	 * 认证结果推送
	 * @param auditId
	 * @param auditStatus
	 * @return
	 */
	@RequestMapping(value = "/User/pushAuditResult")
	@ResponseBody
	@NotLogin
	public JSONPrompt pushAuditResult(String auditId,Integer auditStatus){
		auditLogService.pushAuditResult(auditId,auditStatus);
		return  new JSONPrompt();
	}


	/**
	 * 删除认证相关信息
	 * @param objectId
	 * @param auditType
	 * @return
	 */
	@RequestMapping(value = "/User/delete")
	@ResponseBody
	@NotLogin
	public JSONPrompt delete(String objectId,Integer auditType){
		auditLogService.delete(objectId,auditType);
		return  new JSONPrompt();
	}




	@RequestMapping(value = "/User/initEx")
    @ResponseBody
    @NotLogin
	public JSONPrompt initEx(){
        auditLogService.initEx();
        return  new JSONPrompt();

    }
	
	/**
	 * 获取司机身份
	 * @param personId
	 * @return
	 */
	@RequestMapping(value = "/User/getDriverIdentity")
    @ResponseBody
    @NotLogin
	public JSONPrompt<GetDriverIdentityVo> getDriverIdentity(String personId){
		if(StringUtils.isBlank(personId)) {
			personId = baseRequestDTO.getBaseUserId();
		}
		return  new JSONPrompt(auditLogService.getDriverIdentity(personId));
	}



}
