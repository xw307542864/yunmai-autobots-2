package com.logibeat.cloud.dto.task;

public class TaskVehicleDto {

    /**
     * 车辆ID
     */
    private String vehicleId;

    /**
     * 车牌号
     */
    private String vehicleNumber;


    /**
     * 车辆类型（企业、外协、合同、预约）
     */
    private String vehicleType;

    /**
     * 主驾司机id
     */
    private String firstPersonId;

    /**
     * 主驾司机姓名
     */
    private String firstPersonName;

    /**
     * 主驾司机手机号
     */
    private String firstPersonMobile;


    /**
     * 副驾司机id
     */
    private String secondPersonId;

    /**
     * 副驾司机姓名
     */
    private String secondPersonName;

    /**
     * 副驾司机手机号
     */
    private String secondPersonMobile;


    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getFirstPersonId() {
        return firstPersonId;
    }

    public void setFirstPersonId(String firstPersonId) {
        this.firstPersonId = firstPersonId;
    }

    public String getFirstPersonName() {
        return firstPersonName;
    }

    public void setFirstPersonName(String firstPersonName) {
        this.firstPersonName = firstPersonName;
    }

    public String getFirstPersonMobile() {
        return firstPersonMobile;
    }

    public void setFirstPersonMobile(String firstPersonMobile) {
        this.firstPersonMobile = firstPersonMobile;
    }

    public String getSecondPersonId() {
        return secondPersonId;
    }

    public void setSecondPersonId(String secondPersonId) {
        this.secondPersonId = secondPersonId;
    }

    public String getSecondPersonName() {
        return secondPersonName;
    }

    public void setSecondPersonName(String secondPersonName) {
        this.secondPersonName = secondPersonName;
    }

    public String getSecondPersonMobile() {
        return secondPersonMobile;
    }

    public void setSecondPersonMobile(String secondPersonMobile) {
        this.secondPersonMobile = secondPersonMobile;
    }
}
