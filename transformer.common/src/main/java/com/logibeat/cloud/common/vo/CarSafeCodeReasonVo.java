package com.logibeat.cloud.common.vo;

public class CarSafeCodeReasonVo {
	
	private Integer safeCode;//安全码1红色 2黄色 3绿色
	
	private String safeCodeText;
	
	private Integer reason;//原因1-超速,2-离线限速,3-设备未安装
	
	private String reasonText;
	
	private String explain;//说明
	
	private String orgSource;//来源
	
	private String dealTime;//处理时间
	
	private String startTime;//开始时间
	
	private String endTime;//结束时间
	
	private String stopRunMeta;//停运原因
	
	private String effective;//时效
	
	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
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

	public Integer getSafeCode() {
		return safeCode;
	}

	public void setSafeCode(Integer safeCode) {
		this.safeCode = safeCode;
	}

	public String getSafeCodeText() {
		return safeCodeText;
	}

	public void setSafeCodeText(String safeCodeText) {
		this.safeCodeText = safeCodeText;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public String getReasonText() {
		return reasonText;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getOrgSource() {
		return orgSource;
	}

	public void setOrgSource(String orgSource) {
		this.orgSource = orgSource;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	
	

}
