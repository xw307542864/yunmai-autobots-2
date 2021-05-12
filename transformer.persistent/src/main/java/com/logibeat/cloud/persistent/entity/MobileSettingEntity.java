package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class MobileSettingEntity extends EntitySerialize {
    private String id;

    private String phoneBrand;

    private String phoneModel;

    private Integer gpsUpload;

    private Integer gpsAcquisition;

    private Integer gpsHistory;

    private Integer distance;

    private Integer seconds;

    private Byte isTask;

    private Date addtime;

    private Byte isdelete;

    private Integer level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public Integer getGpsUpload() {
        return gpsUpload;
    }

    public void setGpsUpload(Integer gpsUpload) {
        this.gpsUpload = gpsUpload;
    }

    public Integer getGpsAcquisition() {
        return gpsAcquisition;
    }

    public void setGpsAcquisition(Integer gpsAcquisition) {
        this.gpsAcquisition = gpsAcquisition;
    }

    public Integer getGpsHistory() {
        return gpsHistory;
    }

    public void setGpsHistory(Integer gpsHistory) {
        this.gpsHistory = gpsHistory;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public Byte getIsTask() {
        return isTask;
    }

    public void setIsTask(Byte isTask) {
        this.isTask = isTask;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Byte getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Byte isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}