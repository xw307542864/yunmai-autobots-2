package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;


/**
 * 认证日志

* @Title: AuditLogEntity.java

* @Package com.yunmaigo.qtz.persistence.entity

* @Description: TODO(用一句话描述该文件做什么)

* @author Wilson   

* @date 2016年1月12日 下午2:53:36

* @version V1.0
 */
public class AuditLogEntity extends EntitySerialize {
	
	private String guid;
	 
	private String entId;
	 
	private String objectId;
	 
	private String applyPersonId;
	 
	private Integer auditType;
	 
	private String content;
	 
	private String remark;
	 
	private Timestamp addTime;
	 
	private Timestamp editTime;
	 
	private Integer auditStatus;
	 
	private Timestamp auditTime;
	 
	private String auditPersonId;
	 
    private byte isDelete;
	 
	private Timestamp deleteTime;

	private String auditInfo;//认证内容

	private Integer auditEventModel;//认证模块（新增）

	private Integer auditEventType;//认证类型（新增）

	private String failMessage;//认证失败原因

	private Integer auditSource;//认证来源

	private Integer submitType;//提交类型（认证/认领）

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

	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(byte isDelete) {
		this.isDelete = isDelete;
	}

	public Timestamp getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}

    public String getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(String auditInfo) {
        this.auditInfo = auditInfo;
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

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public Integer getAuditSource() {
        return auditSource;
    }

    public void setAuditSource(Integer auditSource) {
        this.auditSource = auditSource;
    }

    public Integer getSubmitType() {
        return submitType;
    }

    public void setSubmitType(Integer submitType) {
        this.submitType = submitType;
    }
}
