package com.logibeat.cloud.msg.extra;

public class AuditExtraDto  {

    private Integer bizType;

    /**
     * 认证类别
     */
    private Integer auditEventType;


    /**
     * 认证类别
     */
    private String auditEventTypeValue;

    /**
     * 证件号码
     */
    private String identificationNumber;

    /**
     * 证件有效期
     */
    private String expireDate;

    /**
     * url
     */
    private String linkUrl;


    private String plateNumber;

    private String plateColorValue;

    private String driverName;

    private String driverMobile;

    private String carTypeValue;

    private Double ratedLoad;

    private Double ratedVolume;

    private String coopTypeValue;

    private String vehicleLicenseOwner;
    
    private String carId;
    
    public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getAuditEventType() {
        return auditEventType;
    }

    public void setAuditEventType(Integer auditEventType) {
        this.auditEventType = auditEventType;
    }

    public String getAuditEventTypeValue() {
        return auditEventTypeValue;
    }

    public void setAuditEventTypeValue(String auditEventTypeValue) {
        this.auditEventTypeValue = auditEventTypeValue;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }


    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateColorValue() {
        return plateColorValue;
    }

    public void setPlateColorValue(String plateColorValue) {
        this.plateColorValue = plateColorValue;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

    public String getCarTypeValue() {
        return carTypeValue;
    }

    public void setCarTypeValue(String carTypeValue) {
        this.carTypeValue = carTypeValue;
    }

    public Double getRatedLoad() {
        return ratedLoad;
    }

    public void setRatedLoad(Double ratedLoad) {
        this.ratedLoad = ratedLoad;
    }

    public Double getRatedVolume() {
        return ratedVolume;
    }

    public void setRatedVolume(Double ratedVolume) {
        this.ratedVolume = ratedVolume;
    }

    public String getCoopTypeValue() {
        return coopTypeValue;
    }

    public void setCoopTypeValue(String coopTypeValue) {
        this.coopTypeValue = coopTypeValue;
    }

    public String getVehicleLicenseOwner() {
        return vehicleLicenseOwner;
    }

    public void setVehicleLicenseOwner(String vehicleLicenseOwner) {
        this.vehicleLicenseOwner = vehicleLicenseOwner;
    }
}
