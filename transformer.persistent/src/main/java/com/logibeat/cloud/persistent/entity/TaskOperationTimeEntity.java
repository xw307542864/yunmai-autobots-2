package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;

public class TaskOperationTimeEntity extends EntitySerialize {

    private String guid;

    private String ordersCid;

    private String carId;

    private String plateNumber;

    private String personId;

    private Timestamp startTime;

    private Timestamp endTime;

    private Integer deviceType;

    private String equipment;

    private String keyWord;

    private String endReason;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrdersCid() {
        return ordersCid;
    }

    public void setOrdersCid(String ordersCid) {
        this.ordersCid = ordersCid;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getEndReason() {
        return endReason;
    }

    public void setEndReason(String endReason) {
        this.endReason = endReason;
    }
}