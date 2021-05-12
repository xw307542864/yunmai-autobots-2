package com.logibeat.cloud.common.vo;

import java.util.List;

public class AuditStatusVo {
	private String entId;
	private String entName;
	private Integer auditProgress;
	private Integer entLargeType;
	private Integer certificationStatus;
	private List<AuditTypeVo> auditList;
	
	public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public Integer getAuditProgress() {
		return auditProgress;
	}
	public void setAuditProgress(Integer auditProgress) {
		this.auditProgress = auditProgress;
	}
	public List<AuditTypeVo> getAuditList() {
		return auditList;
	}
	public void setAuditList(List<AuditTypeVo> auditList) {
		this.auditList = auditList;
	}
	public Integer getEntLargeType() {
		return entLargeType;
	}
	public void setEntLargeType(Integer entLargeType) {
		this.entLargeType = entLargeType;
	}
	public Integer getCertificationStatus() {
		return certificationStatus;
	}
	public void setCertificationStatus(Integer certificationStatus) {
		this.certificationStatus = certificationStatus;
	}
	
}
