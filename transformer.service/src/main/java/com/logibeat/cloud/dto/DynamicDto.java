package com.logibeat.cloud.dto;


import com.logibeat.cloud.core.dto.DynamicGpsDTO;
import com.logibeat.cloud.info.VoiceInfo;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wilson on 2017/12/7.
 */
public class DynamicDto {

    private String  entId;

    private String taskId;

    private Integer action;

    private String carId;

    private String plateNumber;

    private String personId;

    private String personName;

    private String personMobile;

    private String taskAreaId;

    private String cotent;

    private String picUrl;

    private Byte isAtPoint;

    private String taskPrex;

    private String entPrex;

    private Timestamp repeatTime;

    private DynamicGpsDTO dynamicGpsDTO;

    private String orgId;

    private Integer exceptionTask;
    
    private List<VoiceInfo> voiceList;//语音
	
	public List<VoiceInfo> getVoiceList() {
		return voiceList;
	}

	public void setVoiceList(List<VoiceInfo> voiceList) {
		this.voiceList = voiceList;
	}

    public Integer getExceptionTask() {
        return exceptionTask;
    }

    public void setExceptionTask(Integer exceptionTask) {
        this.exceptionTask = exceptionTask;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public String getTaskAreaId() {
        return taskAreaId;
    }

    public void setTaskAreaId(String taskAreaId) {
        this.taskAreaId = taskAreaId;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Byte getIsAtPoint() {
        return isAtPoint;
    }

    public void setIsAtPoint(Byte isAtPoint) {
        this.isAtPoint = isAtPoint;
    }

    public String getTaskPrex() {
        return taskPrex;
    }

    public void setTaskPrex(String taskPrex) {
        this.taskPrex = taskPrex;
    }

    public String getEntPrex() {
        return entPrex;
    }

    public void setEntPrex(String entPrex) {
        this.entPrex = entPrex;
    }

    public Timestamp getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(Timestamp repeatTime) {
        this.repeatTime = repeatTime;
    }

    public DynamicGpsDTO getDynamicGpsDTO() {
        return dynamicGpsDTO;
    }

    public void setDynamicGpsDTO(DynamicGpsDTO dynamicGpsDTO) {
        this.dynamicGpsDTO = dynamicGpsDTO;
    }
}
