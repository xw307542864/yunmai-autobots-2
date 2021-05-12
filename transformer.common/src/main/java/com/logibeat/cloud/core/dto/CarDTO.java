package com.logibeat.cloud.core.dto;


/**
 * @author karl
 * @version 1.0
 * @ClassName: CarDTO
 * @Description: 添加或修改自有车
 * @date 2015年12月25日 上午11:14:18
 */
public class CarDTO {

    private String entCarId;

    private String personId;

    private String entId;

    private String plateNumber;

    private String plateColor;

    private String carLengthDictGUID;

    private String carLengthValue;

    private String carLengthCode;

    private String coachTypeDictGUID;

    private String carTypeValue;

    private String carTypeCode;

    private Double ratedLoad;

    private Double ratedVolume;

    private String firstDriverPersonID;

    private String firstDriverName;

    private String firstDriverMobile;

    private String secondDriverPersonID;

    private String secondDriverName;

    private String secondDriverMobile;

    private String groupId;

    private String operationLic;

    private String drivingLic;

    private String orgGuid;//组织id

    private Integer srcSoid;

    private Integer coopType;

    private String clientType;

    private Integer dataSource;

    private String ownerId;

    private String ownerInfo;

    private String carLogo;

    private String carId;//等前端强制升级完成后，去掉carID，以后用carId

    private Integer inviteStatus;

    private String inputEntId;//通过外部导入的企业id

    private Integer inviteType;

    private String transportTypeCode; //运输类型code

    private String transportTypeValue;

    private String licenseNumber;//车号

    private Integer workItem;

    private String soid;

    private String vehicleIdentificationCode;

    private String vehicleEngineCode;

    private String vehicleLicense;

    private String vehiclePic; //车辆照片

    private String vehicleLicenseOwner; //所有人

    private Integer auditStatus;
    
    private String sourceType;
    
    public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getEntCarId() {
        return entCarId;
    }

    public void setEntCarId(String entCarId) {
        this.entCarId = entCarId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
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

    public String getCarLengthDictGUID() {
        return carLengthDictGUID;
    }

    public void setCarLengthDictGUID(String carLengthDictGUID) {
        this.carLengthDictGUID = carLengthDictGUID;
    }

    public String getCarLengthValue() {
        return carLengthValue;
    }

    public void setCarLengthValue(String carLengthValue) {
        this.carLengthValue = carLengthValue;
    }

    public String getCarLengthCode() {
        return carLengthCode;
    }

    public void setCarLengthCode(String carLengthCode) {
        this.carLengthCode = carLengthCode;
    }

    public String getCoachTypeDictGUID() {
        return coachTypeDictGUID;
    }

    public void setCoachTypeDictGUID(String coachTypeDictGUID) {
        this.coachTypeDictGUID = coachTypeDictGUID;
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

    public String getFirstDriverPersonID() {
        return firstDriverPersonID;
    }

    public void setFirstDriverPersonID(String firstDriverPersonID) {
        this.firstDriverPersonID = firstDriverPersonID;
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

    public String getSecondDriverPersonID() {
        return secondDriverPersonID;
    }

    public void setSecondDriverPersonID(String secondDriverPersonID) {
        this.secondDriverPersonID = secondDriverPersonID;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getOperationLic() {
        return operationLic;
    }

    public void setOperationLic(String operationLic) {
        this.operationLic = operationLic;
    }

    public String getDrivingLic() {
        return drivingLic;
    }

    public void setDrivingLic(String drivingLic) {
        this.drivingLic = drivingLic;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public Integer getSrcSoid() {
        return srcSoid;
    }

    public void setSrcSoid(Integer srcSoid) {
        this.srcSoid = srcSoid;
    }

    public Integer getCoopType() {
        return coopType;
    }

    public void setCoopType(Integer coopType) {
        this.coopType = coopType;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerInfo() {
        return ownerInfo;
    }

    public void setOwnerInfo(String ownerInfo) {
        this.ownerInfo = ownerInfo;
    }

    public String getCarLogo() {
        return carLogo;
    }

    public void setCarLogo(String carLogo) {
        this.carLogo = carLogo;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Integer getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(Integer inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    public String getInputEntId() {
        return inputEntId;
    }

    public void setInputEntId(String inputEntId) {
        this.inputEntId = inputEntId;
    }

    public Integer getInviteType() {
        return inviteType;
    }

    public void setInviteType(Integer inviteType) {
        this.inviteType = inviteType;
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

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Integer getWorkItem() {
        return workItem;
    }

    public void setWorkItem(Integer workItem) {
        this.workItem = workItem;
    }


    public String getSoid() {
        return soid;
    }

    public void setSoid(String soid) {
        this.soid = soid;
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

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public String getVehiclePic() {
        return vehiclePic;
    }

    public void setVehiclePic(String vehiclePic) {
        this.vehiclePic = vehiclePic;
    }

    public String getVehicleLicenseOwner() {
        return vehicleLicenseOwner;
    }

    public void setVehicleLicenseOwner(String vehicleLicenseOwner) {
        this.vehicleLicenseOwner = vehicleLicenseOwner;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
}
