package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;


public class MyVehicleEntity extends EntitySerialize {

    private String guid;

    private String carId;

    private String entId;

    private String personId;

    private String entCarId;

    private Integer coopType;

    private Integer status;

    private Integer auditStatus;

    private String plateNumber;

    private String plateColor;

    private String vehicleLogo;

    private String vehicleTypeCode;

    private String vehicleTypeValue;

    private String vehicleLengthCode;

    private String vehicleLengthValue;

    private Double vehicleRatedLoad;

    private Double vehicleRatedVolume;

    private String vehiclePic;

    private String vehicleLicense;

    private String transportTypeCode;

    private String transportTypeValue;

    private String vehicleIdentificationCode;

    private String vehicleEngineCode;

    private String vehicleLicenseOwnner; //所有人

    private String ownerInfo;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String licenseNumber;


    private String firstDriverId;

    private String firstDriverName;

    private String firstDriverMobile;

    private String secondDriverId;

    private String secondDriverName;

    private String secondDriverMobile;
    
    private String handlePersonName;
    
    private String handleMessage;
    
    private String type;
    
    public String getHandlePersonName() {
		return handlePersonName;
	}

	public void setHandlePersonName(String handlePersonName) {
		this.handlePersonName = handlePersonName;
	}

	public String getHandleMessage() {
		return handleMessage;
	}

	public void setHandleMessage(String handleMessage) {
		this.handleMessage = handleMessage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEntCarId() {
        return entCarId;
    }

    public void setEntCarId(String entCarId) {
        this.entCarId = entCarId;
    }

    public Integer getCoopType() {
        return coopType;
    }

    public void setCoopType(Integer coopType) {
        this.coopType = coopType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public String getVehicleLogo() {
        return vehicleLogo;
    }

    public void setVehicleLogo(String vehicleLogo) {
        this.vehicleLogo = vehicleLogo;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleTypeValue() {
        return vehicleTypeValue;
    }

    public void setVehicleTypeValue(String vehicleTypeValue) {
        this.vehicleTypeValue = vehicleTypeValue;
    }

    public String getVehicleLengthCode() {
        return vehicleLengthCode;
    }

    public void setVehicleLengthCode(String vehicleLengthCode) {
        this.vehicleLengthCode = vehicleLengthCode;
    }

    public String getVehicleLengthValue() {
        return vehicleLengthValue;
    }

    public void setVehicleLengthValue(String vehicleLengthValue) {
        this.vehicleLengthValue = vehicleLengthValue;
    }

    public Double getVehicleRatedLoad() {
        return vehicleRatedLoad;
    }

    public void setVehicleRatedLoad(Double vehicleRatedLoad) {
        this.vehicleRatedLoad = vehicleRatedLoad;
    }

    public Double getVehicleRatedVolume() {
        return vehicleRatedVolume;
    }

    public void setVehicleRatedVolume(Double vehicleRatedVolume) {
        this.vehicleRatedVolume = vehicleRatedVolume;
    }

    public String getVehiclePic() {
        return vehiclePic;
    }

    public void setVehiclePic(String vehiclePic) {
        this.vehiclePic = vehiclePic;
    }

    public String getTransportTypeCode() {
        return transportTypeCode;
    }

    public void setTransportTypeCode(String transportTypeCode) {
        this.transportTypeCode = transportTypeCode;
    }

    public String getTransportTypeValue() {
        return transportTypeValue;
    }

    public void setTransportTypeValue(String transportTypeValue) {
        this.transportTypeValue = transportTypeValue;
    }

    public String getVehicleIdentificationCode() {
        return vehicleIdentificationCode;
    }

    public void setVehicleIdentificationCode(String vehicleIdentificationCode) {
        this.vehicleIdentificationCode = vehicleIdentificationCode;
    }

    public String getVehicleEngineCode() {
        return vehicleEngineCode;
    }

    public void setVehicleEngineCode(String vehicleEngineCode) {
        this.vehicleEngineCode = vehicleEngineCode;
    }

    public String getVehicleLicenseOwnner() {
        return vehicleLicenseOwnner;
    }

    public void setVehicleLicenseOwnner(String vehicleLicenseOwnner) {
        this.vehicleLicenseOwnner = vehicleLicenseOwnner;
    }

    public String getOwnerInfo() {
        return ownerInfo;
    }

    public void setOwnerInfo(String ownerInfo) {
        this.ownerInfo = ownerInfo;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getFirstDriverId() {
        return firstDriverId;
    }

    public void setFirstDriverId(String firstDriverId) {
        this.firstDriverId = firstDriverId;
    }

    public String getFirstDriverName() {
        return firstDriverName;
    }

    public void setFirstDriverName(String firstDriverName) {
        this.firstDriverName = firstDriverName;
    }

    public String getFirstDriverMobile() {
        return firstDriverMobile;
    }

    public void setFirstDriverMobile(String firstDriverMobile) {
        this.firstDriverMobile = firstDriverMobile;
    }

    public String getSecondDriverId() {
        return secondDriverId;
    }

    public void setSecondDriverId(String secondDriverId) {
        this.secondDriverId = secondDriverId;
    }

    public String getSecondDriverName() {
        return secondDriverName;
    }

    public void setSecondDriverName(String secondDriverName) {
        this.secondDriverName = secondDriverName;
    }

    public String getSecondDriverMobile() {
        return secondDriverMobile;
    }

    public void setSecondDriverMobile(String secondDriverMobile) {
        this.secondDriverMobile = secondDriverMobile;
    }
}
