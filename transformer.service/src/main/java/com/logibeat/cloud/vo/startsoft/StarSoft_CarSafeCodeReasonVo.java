package com.logibeat.cloud.vo.startsoft;

public class StarSoft_CarSafeCodeReasonVo {
	
	private Integer safeCode;//安全码1红色 2黄色 3绿色
	
	private String handleTime;//处理时间
	
	private String handlePerson;//处理人
	
	private String remark;//备注
	
	private Integer redCodeType;//红码类型1-超速,2-离线限速,3-设备未安装
	
	private String orgName;//处罚顶级机构
	
	private String startTime;//开始时间
	
	private String endTime;//结束时间
	
	private String stopRunMeta;//停运原因

	public Integer getSafeCode() {
		return safeCode;
	}

	public void setSafeCode(Integer safeCode) {
		this.safeCode = safeCode;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRedCodeType() {
		return redCodeType;
	}

	public void setRedCodeType(Integer redCodeType) {
		this.redCodeType = redCodeType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStopRunMeta() {
		return stopRunMeta;
	}

	public void setStopRunMeta(String stopRunMeta) {
		this.stopRunMeta = stopRunMeta;
	}
	
	
	
	
}
