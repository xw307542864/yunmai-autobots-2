package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;

public class TaskDynamicEntity extends EntitySerialize {
    private String guid;

    private String carId;

    private String personId;

    private String ordersCid;

    private String areaGuid;

    private String entId;

    private String imgaddress;

    private String content;

    private Timestamp dynamictime;

    private String address;

    private String remark;

    private Double lng;

    private Double lat;

    private Integer type;

    private Integer action;

    private Integer likesNum;

    private Integer messagesNum;

    private Integer readsNum;

    private Byte isDelete;

    private Timestamp deletetime;

    private Byte isRead;

    private Byte isAtPoint;

    private String actionName;

    private Byte dynamicSource;

    private Byte dyswitch;

    private String entPrex;

    private String orderPrex;

    private String carPlateNumber;

    private String driverMobile;

    private String driverName;

    private String carOrTaskMessage;

    private String upFilePrex;

    private String picUrls;

    private String orgId;
    
    private String voiceInfo;
    
    public String getVoiceInfo() {
		return voiceInfo;
	}

	public void setVoiceInfo(String voiceInfo) {
		this.voiceInfo = voiceInfo;
	}

	public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getOrdersCid() {
        return ordersCid;
    }

    public void setOrdersCid(String ordersCid) {
        this.ordersCid = ordersCid;
    }

    public String getAreaGuid() {
        return areaGuid;
    }

    public void setAreaGuid(String areaGuid) {
        this.areaGuid = areaGuid;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getImgaddress() {
        return imgaddress;
    }

    public void setImgaddress(String imgaddress) {
        this.imgaddress = imgaddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDynamictime() {
        return dynamictime;
    }

    public void setDynamictime(Timestamp dynamictime) {
        this.dynamictime = dynamictime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(Integer likesNum) {
        this.likesNum = likesNum;
    }

    public Integer getMessagesNum() {
        return messagesNum;
    }

    public void setMessagesNum(Integer messagesNum) {
        this.messagesNum = messagesNum;
    }

    public Integer getReadsNum() {
        return readsNum;
    }

    public void setReadsNum(Integer readsNum) {
        this.readsNum = readsNum;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Timestamp deletetime) {
        this.deletetime = deletetime;
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public Byte getIsAtPoint() {
        return isAtPoint;
    }

    public void setIsAtPoint(Byte isAtPoint) {
        this.isAtPoint = isAtPoint;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Byte getDynamicSource() {
        return dynamicSource;
    }

    public void setDynamicSource(Byte dynamicSource) {
        this.dynamicSource = dynamicSource;
    }

    public Byte getDyswitch() {
        return dyswitch;
    }

    public void setDyswitch(Byte dyswitch) {
        this.dyswitch = dyswitch;
    }

    public String getEntPrex() {
        return entPrex;
    }

    public void setEntPrex(String entPrex) {
        this.entPrex = entPrex;
    }

    public String getOrderPrex() {
        return orderPrex;
    }

    public void setOrderPrex(String orderPrex) {
        this.orderPrex = orderPrex;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCarOrTaskMessage() {
        return carOrTaskMessage;
    }

    public void setCarOrTaskMessage(String carOrTaskMessage) {
        this.carOrTaskMessage = carOrTaskMessage;
    }

    public String getUpFilePrex() {
        return upFilePrex;
    }

    public void setUpFilePrex(String upFilePrex) {
        this.upFilePrex = upFilePrex;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }
}