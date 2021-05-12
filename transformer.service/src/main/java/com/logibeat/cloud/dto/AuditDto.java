package com.logibeat.cloud.dto;


import com.logibeat.cloud.msg.enumtype.MessageBizType;

public class AuditDto {
    private String auditId;
    private String auditInfo;
    private Integer auditEventType;
    private String auditEventTypeItem;
    private String personId;
    private String personName;
    private String phoneNumber;
    private String idcardNumber;
    private Integer auditStatus;
    private String objectId;
    private String identificationNumber;
    private String expireDate;
    private String pushUrl;
    private Integer messageType;
    private MessageBizType messageBizType;

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(String auditInfo) {
        this.auditInfo = auditInfo;
    }

    public Integer getAuditEventType() {
        return auditEventType;
    }

    public void setAuditEventType(Integer auditEventType) {
        this.auditEventType = auditEventType;
    }

    public String getAuditEventTypeItem() {
        return auditEventTypeItem;
    }

    public void setAuditEventTypeItem(String auditEventTypeItem) {
        this.auditEventTypeItem = auditEventTypeItem;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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


    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public MessageBizType getMessageBizType() {
        return messageBizType;
    }

    public void setMessageBizType(MessageBizType messageBizType) {
        this.messageBizType = messageBizType;
    }
}
