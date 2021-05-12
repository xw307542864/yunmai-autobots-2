package com.logibeat.cloud.vo.task;

import java.util.Date;

public class RunningTaskVo {

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
     * 组织id
     */
    private String orgnId;


    /**
     * 企业id
     */
    private String entId;


    /**
     * 车牌号
     */
    private String vehiclePlateNumber;


    /**
     * 车辆id
     */
    private String vehicleId;


    /**
     * 实际发车时间
     */
    private Date actualDepartTime;


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

    public String getOrgnId() {
        return orgnId;
    }

    public void setOrgnId(String orgnId) {
        this.orgnId = orgnId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public Date getActualDepartTime() {
        return actualDepartTime;
    }

    public void setActualDepartTime(Date actualDepartTime) {
        this.actualDepartTime = actualDepartTime;
    }


    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}
