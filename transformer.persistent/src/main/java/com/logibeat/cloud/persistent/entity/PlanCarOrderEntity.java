package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class PlanCarOrderEntity  extends EntitySerialize {

    private String guid;

    private String code;

    private String outKeyId;

    private String entId;

    private Integer queueNumber;

    private String vehicleId;

    private String vehiclePlateNumber;

    private String driverId;

    private String driverName;

    private String driverPhone;

    private Double destionLat;

    private Double destionLng;

    private String destionRegionCode;

    private String destionRegionName;

    private String destionAddress;

    private Date vehicleTime;

    private Integer status;

    private Date createTime;

    private String createPersonId;

    private String createPersonName;

    private Integer isDelete;

    private Date deleteTime;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOutKeyId() {
        return outKeyId;
    }

    public void setOutKeyId(String outKeyId) {
        this.outKeyId = outKeyId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public Integer getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(Integer queueNumber) {
        this.queueNumber = queueNumber;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Double getDestionLat() {
        return destionLat;
    }

    public void setDestionLat(Double destionLat) {
        this.destionLat = destionLat;
    }

    public Double getDestionLng() {
        return destionLng;
    }

    public void setDestionLng(Double destionLng) {
        this.destionLng = destionLng;
    }

    public String getDestionRegionCode() {
        return destionRegionCode;
    }

    public void setDestionRegionCode(String destionRegionCode) {
        this.destionRegionCode = destionRegionCode;
    }

    public String getDestionRegionName() {
        return destionRegionName;
    }

    public void setDestionRegionName(String destionRegionName) {
        this.destionRegionName = destionRegionName;
    }

    public String getDestionAddress() {
        return destionAddress;
    }

    public void setDestionAddress(String destionAddress) {
        this.destionAddress = destionAddress;
    }

    public Date getVehicleTime() {
        return vehicleTime;
    }

    public void setVehicleTime(Date vehicleTime) {
        this.vehicleTime = vehicleTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    public String getCreatePersonName() {
        return createPersonName;
    }

    public void setCreatePersonName(String createPersonName) {
        this.createPersonName = createPersonName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
