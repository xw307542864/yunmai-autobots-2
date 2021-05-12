package com.logibeat.cloud.persistent.entity;

import java.sql.Timestamp;

public class AuditEntity {

	private String guid;
	 
	private String entId;
	 
	private String objectId;
	 
	private String applyPersonId;
	 
	private Timestamp addTime;
	 
	private Timestamp editTime;
	 
	private Integer auditStatus;
	 
	private Timestamp auditTime;
	 
	private String auditPersonId;
	
	private String auditInfo;//认证内容

	private String identificationNumber; //证件号码

	private String expireDate; //证件有效期
	
	private Integer auditEventModel;//认证模块（新增）
	
	private Integer auditEventType;//认证类型（新增）

	private String auditEventTypeItem; //认证类型具体条目
	
	private Integer auditSource;//认证来源

	private String auditMessage;

	private Integer diffDays;


	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getApplyPersonId() {
		return applyPersonId;
	}

	public void setApplyPersonId(String applyPersonId) {
		this.applyPersonId = applyPersonId;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Timestamp getEditTime() {
		return editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Timestamp getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditPersonId() {
		return auditPersonId;
	}

	public void setAuditPersonId(String auditPersonId) {
		this.auditPersonId = auditPersonId;
	}

	public String getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getAuditEventModel() {
		return auditEventModel;
	}

	public void setAuditEventModel(Integer auditEventModel) {
		this.auditEventModel = auditEventModel;
	}

	public Integer getAuditEventType() {
		return auditEventType;
	}

	public void setAuditEventType(Integer auditEventType) {
		this.auditEventType = auditEventType;
	}

	public Integer getAuditSource() {
		return auditSource;
	}

	public void setAuditSource(Integer auditSource) {
		this.auditSource = auditSource;
	}

	public String getAuditEventTypeItem() {
		return auditEventTypeItem;
	}

	public void setAuditEventTypeItem(String auditEventTypeItem) {
		this.auditEventTypeItem = auditEventTypeItem;
	}

	public String getAuditMessage() {
		return auditMessage;
	}

	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}


	public Integer getDiffDays() {
		return diffDays;
	}

	public void setDiffDays(Integer diffDays) {
		this.diffDays = diffDays;
	}



}
