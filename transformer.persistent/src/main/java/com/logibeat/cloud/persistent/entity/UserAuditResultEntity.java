package com.logibeat.cloud.persistent.entity;


import java.sql.Timestamp;

public class UserAuditResultEntity{

	private String guid;
	
	//对象ID
	private String objectId;
	
	//对象类型�?0：企业认证，1：资质认证）
	private Integer objectType;
	
	//认证结果：初始化�?0
	private Integer auditResult;
	
	//申请人名�?
	private String applyPersonName;
	
	//申请人ID
	private String applyPersonId;
	
	//申请时间
	private Timestamp applyTime;
	
	//认证人名�?
	private String auditPersonName;
	
	//认证人ID
	private String auditPersonId;
	
	//认证时间
	private String auditTime;
	
	//类型�?0：初审，1：复�?
	private Integer type;
	
	//认证来源
	private Integer source;
	
	//是否审核：企业认证初始化�?0，资质认证初始化根据企业认证的auditResult做判�?
	private Integer isAudit;
	
	private Integer auditStatus;
	
	private String applyPersonPhone;//申请人电�?
	
	private String applyPersonSocialLic;//身份�?
	
	private String enterpriseName;

	private String auditInfo;

	private String auditId;

	private String auditLogId;


	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	public Integer getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

	public String getApplyPersonName() {
		return applyPersonName;
	}

	public void setApplyPersonName(String applyPersonName) {
		this.applyPersonName = applyPersonName;
	}

	public String getApplyPersonId() {
		return applyPersonId;
	}

	public void setApplyPersonId(String applyPersonId) {
		this.applyPersonId = applyPersonId;
	}

	public Timestamp getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}

	public String getAuditPersonName() {
		return auditPersonName;
	}

	public void setAuditPersonName(String auditPersonName) {
		this.auditPersonName = auditPersonName;
	}

	public String getAuditPersonId() {
		return auditPersonId;
	}

	public void setAuditPersonId(String auditPersonId) {
		this.auditPersonId = auditPersonId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getApplyPersonPhone() {
		return applyPersonPhone;
	}

	public void setApplyPersonPhone(String applyPersonPhone) {
		this.applyPersonPhone = applyPersonPhone;
	}

	public String getApplyPersonSocialLic() {
		return applyPersonSocialLic;
	}

	public void setApplyPersonSocialLic(String applyPersonSocialLic) {
		this.applyPersonSocialLic = applyPersonSocialLic;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getAuditLogId() {
		return auditLogId;
	}

	public void setAuditLogId(String auditLogId) {
		this.auditLogId = auditLogId;
	}
}
