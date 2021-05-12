package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class HeartbeatExceptionEntity extends EntitySerialize {
    private String id;

    private String taskId;

    private String personId;

    private String mobile;

    private Integer exceptionNum;

    private Byte isSendMessage;

    private Integer sendMessageNum;

    private Date lastSendTime;

    private Byte isCallPhone;

    private Integer callPhoneNum;

    private Date lastPhoneTime;

    private Date addTime;

    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getExceptionNum() {
        return exceptionNum;
    }

    public void setExceptionNum(Integer exceptionNum) {
        this.exceptionNum = exceptionNum;
    }

    public Byte getIsSendMessage() {
        return isSendMessage;
    }

    public void setIsSendMessage(Byte isSendMessage) {
        this.isSendMessage = isSendMessage;
    }

    public Integer getSendMessageNum() {
        return sendMessageNum;
    }

    public void setSendMessageNum(Integer sendMessageNum) {
        this.sendMessageNum = sendMessageNum;
    }

    public Date getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Byte getIsCallPhone() {
        return isCallPhone;
    }

    public void setIsCallPhone(Byte isCallPhone) {
        this.isCallPhone = isCallPhone;
    }

    public Integer getCallPhoneNum() {
        return callPhoneNum;
    }

    public void setCallPhoneNum(Integer callPhoneNum) {
        this.callPhoneNum = callPhoneNum;
    }

    public Date getLastPhoneTime() {
        return lastPhoneTime;
    }

    public void setLastPhoneTime(Date lastPhoneTime) {
        this.lastPhoneTime = lastPhoneTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}