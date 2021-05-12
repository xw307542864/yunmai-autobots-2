package com.logibeat.cloud.vo.task;


import com.logibeat.cloud.dto.task.EntrustInfoDto;

import java.util.Date;

public class DriverTaskListVo {

    /**
     * 派车单id
     */
    private String taskId;

    /**
     * 派车单状态
     */
    private Integer taskStatus;

    /**
     * 起点城市
     */
    private String originalCity;

    /**
     * 起点地址
     */
    private String originalAddress;

    /**
     * 终点城市
     */
    private String destinationCity;


    /**
     * 终点地址
     */
    private String destinationAddress;


    /**
     * 是否同城
     */
    private boolean sameCity;


    /**
     * 计划发车时间
     */
    private String planDepartTime;

    /**
     * 计划到达时间/失效
     */
    private String planArriveTime;


    /**
     * 用车时效描述
     */
    private String departDurationFlag;

    /**
     * 用车时效类型 1：准时  2：超时  3：提前
     */
    private String departDurationType;


    /**
     * 到达时效描述
     */
    private String arriveDurationFlag;


    /**
     * 到达时效类型 1：准时  2：超时  3：提前
     */
    private String arriveDurationType;



    /**
     * 派单企业、派单员信息
     */
    private EntrustInfoDto entrustInfo;


    /**
     * 货物
     */
    private TaskCargoVo cargoInfo;


    /**
     * 是否预约单
     */
    private boolean appointment;


    /**
     * 已读、未读
     */
    private Integer read;


    /**
     * 异常发车
     */
    private Integer exceptionDepart;


    /**
     * 异常到达
     */
    private Integer exceptionArrive;


    /**
     * 商砼预约单 排序
     */
    private Integer queueNumber;


    /**
     * 任务开始时间（第一次点击去装货）
     */
    private Date startTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getOriginalCity() {
        return originalCity;
    }

    public void setOriginalCity(String originalCity) {
        this.originalCity = originalCity;
    }

    public String getOriginalAddress() {
        return originalAddress;
    }

    public void setOriginalAddress(String originalAddress) {
        this.originalAddress = originalAddress;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public boolean isSameCity() {
        return sameCity;
    }

    public void setSameCity(boolean sameCity) {
        this.sameCity = sameCity;
    }

    public String getPlanDepartTime() {
        return planDepartTime;
    }

    public void setPlanDepartTime(String planDepartTime) {
        this.planDepartTime = planDepartTime;
    }

    public String getPlanArriveTime() {
        return planArriveTime;
    }

    public void setPlanArriveTime(String planArriveTime) {
        this.planArriveTime = planArriveTime;
    }

    public EntrustInfoDto getEntrustInfo() {
        return entrustInfo;
    }

    public void setEntrustInfo(EntrustInfoDto entrustInfo) {
        this.entrustInfo = entrustInfo;
    }


    public String getDepartDurationFlag() {
        return departDurationFlag;
    }

    public void setDepartDurationFlag(String departDurationFlag) {
        this.departDurationFlag = departDurationFlag;
    }

    public String getArriveDurationFlag() {
        return arriveDurationFlag;
    }

    public void setArriveDurationFlag(String arriveDurationFlag) {
        this.arriveDurationFlag = arriveDurationFlag;
    }


    public boolean isAppointment() {
        return appointment;
    }

    public void setAppointment(boolean appointment) {
        this.appointment = appointment;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public TaskCargoVo getCargoInfo() {
        return cargoInfo;
    }

    public void setCargoInfo(TaskCargoVo cargoInfo) {
        this.cargoInfo = cargoInfo;
    }


    public Integer getExceptionDepart() {
        return exceptionDepart;
    }

    public void setExceptionDepart(Integer exceptionDepart) {
        this.exceptionDepart = exceptionDepart;
    }

    public Integer getExceptionArrive() {
        return exceptionArrive;
    }

    public void setExceptionArrive(Integer exceptionArrive) {
        this.exceptionArrive = exceptionArrive;
    }


    public Integer getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(Integer queueNumber) {
        this.queueNumber = queueNumber;
    }


    public String getDepartDurationType() {
        return departDurationType;
    }

    public void setDepartDurationType(String departDurationType) {
        this.departDurationType = departDurationType;
    }

    public String getArriveDurationType() {
        return arriveDurationType;
    }

    public void setArriveDurationType(String arriveDurationType) {
        this.arriveDurationType = arriveDurationType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
