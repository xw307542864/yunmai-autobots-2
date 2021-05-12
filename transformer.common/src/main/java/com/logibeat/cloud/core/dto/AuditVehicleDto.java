package com.logibeat.cloud.core.dto;

public class AuditVehicleDto {

    private String personId;

    private Integer coopType; //50 司机自有车     51 企业车辆

    private String entCarId;

    private String myVehicleId;

    private String carId;

    private String plateNumber;

    private String plateColorCode;

    private String plateColorValue;

    private String carLengthCode;

    private String carLengthValue;

    private String carTypeValue;

    private String carTypeCode;

    private Double ratedLoad;

    private Double ratedVolume;

    private String transportTypeCode; //运输类型code

    private String transportTypeValue;

    private String originalVehicleLicense; //行驶证正本

    private String copyVehicleLicense1; // 行驶证副本1

    private String copyVehicleLicense2; // 行驶证副本2

    private String vehiclePic; //车辆照片

    private String vehicleIdentificationCode;

    private String vehicleEngineCode;

    private String vehicleLicenseOwner;
    
    private String sourceType;
    
    private String auditInfo;
    
    private String entId;
    
    public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
	}
	public String getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}
    
    public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Integer getCoopType() {
        return coopType;
    }

    public void setCoopType(Integer coopType) {
        this.coopType = coopType;
    }

    public String getEntCarId() {
        return entCarId;
    }


    public String getMyVehicleId() {
        return myVehicleId;
    }

    public void setMyVehicleId(String myVehicleId) {
        this.myVehicleId = myVehicleId;
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

    public String getPlateColorCode() {
        return plateColorCode;
    }

    public void setPlateColorCode(String plateColorCode) {
        this.plateColorCode = plateColorCode;
    }

    public String getPlateColorValue() {
        return plateColorValue;
    }

    public void setPlateColorValue(String plateColorValue) {
        this.plateColorValue = plateColorValue;
    }

    public void setEntCarId(String entCarId) {
        this.entCarId = entCarId;
    }

    public String getCarLengthCode() {
        return carLengthCode;
    }

    public void setCarLengthCode(String carLengthCode) {
        this.carLengthCode = carLengthCode;
    }

    public String getCarLengthValue() {
        return carLengthValue;
    }

    public void setCarLengthValue(String carLengthValue) {
        this.carLengthValue = carLengthValue;
    }

    public String getCarTypeValue() {
        return carTypeValue;
    }

    public void setCarTypeValue(String carTypeValue) {
        this.carTypeValue = carTypeValue;
    }

    public String getCarTypeCode() {
        return carTypeCode;
    }

    public void setCarTypeCode(String carTypeCode) {
        this.carTypeCode = carTypeCode;
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

    public String getOriginalVehicleLicense() {
        return originalVehicleLicense;
    }

    public void setOriginalVehicleLicense(String originalVehicleLicense) {
        this.originalVehicleLicense = originalVehicleLicense;
    }

    public String getCopyVehicleLicense1() {
        return copyVehicleLicense1;
    }

    public void setCopyVehicleLicense1(String copyVehicleLicense1) {
        this.copyVehicleLicense1 = copyVehicleLicense1;
    }

    public String getCopyVehicleLicense2() {
        return copyVehicleLicense2;
    }

    public void setCopyVehicleLicense2(String copyVehicleLicense2) {
        this.copyVehicleLicense2 = copyVehicleLicense2;
    }

    public String getVehiclePic() {
        return vehiclePic;
    }

    public void setVehiclePic(String vehiclePic) {
        this.vehiclePic = vehiclePic;
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

    public String getVehicleLicenseOwner() {
        return vehicleLicenseOwner;
    }

    public void setVehicleLicenseOwner(String vehicleLicenseOwner) {
        this.vehicleLicenseOwner = vehicleLicenseOwner;
    }
}
