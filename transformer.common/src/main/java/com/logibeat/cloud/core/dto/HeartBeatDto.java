package com.logibeat.cloud.core.dto;

import java.sql.Timestamp;

/**
 * Created by wilson on 2017/6/12.
 */
public class HeartBeatDto {

    private Integer type;

    private String personId;

    private String mobile;

    private String carId;

    private String taskId;

    private String exceptionId;

    private String keyId;

    private Integer source;

    private String callbackUrl;

    /**
     * 监控开始时间
     */
    private Timestamp startTime;

    /**
     * 监控结束时间
     */
    private Timestamp endTime;

    private Integer Intervalsecond;
    
    private String plateNumber;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
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

    public Integer getIntervalsecond() {
        return Intervalsecond;
    }

    public void setIntervalsecond(Integer intervalsecond) {
        Intervalsecond = intervalsecond;

    }

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

}
