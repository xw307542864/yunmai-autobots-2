package com.logibeat.cloud.persistent.entity;


import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class CarImportEntity extends EntitySerialize {
    private String carId;

    private String entId;

    private Integer srcEntId;

    private String srcEntName;

    private String targetEntName;

    private Date addTime;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public Integer getSrcEntId() {
        return srcEntId;
    }

    public void setSrcEntId(Integer srcEntId) {
        this.srcEntId = srcEntId;
    }

    public String getSrcEntName() {
        return srcEntName;
    }

    public void setSrcEntName(String srcEntName) {
        this.srcEntName = srcEntName;
    }

    public String getTargetEntName() {
        return targetEntName;
    }

    public void setTargetEntName(String targetEntName) {
        this.targetEntName = targetEntName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}