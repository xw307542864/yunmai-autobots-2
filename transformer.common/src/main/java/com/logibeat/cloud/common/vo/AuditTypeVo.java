package com.logibeat.cloud.common.vo;

public class AuditTypeVo {

	private Integer auditEventType;
	private Integer auditStatus;
	private String failMessage;
	
	public Integer getAuditEventType() {
		return auditEventType;
	}
	public void setAuditEventType(Integer auditEventType) {
		this.auditEventType = auditEventType;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getFailMessage() {
		return failMessage;
	}
	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}
	

}
