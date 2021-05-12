package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class CarsEntity extends EntitySerialize {
    private String carID;

    private String vin;

    private String plateNumber;

    private String entID;

    private String ownerPersonID;

    private String orgID;

    private String plateColor;

    private String operationLic;

    private String drivingLic;

    private String coachTypeDictGUID;

    private String carLengthDictGUID;

    private Double ratedLoad;

    private Double ratedVolume;

    private String logo;

    private String photos;

    private String drivingPics;

    private String firstDriverPersonID;

    private String secondDriverPersonID;

    private String remark;

    private Byte isDelete;

    private Date deleteTime;

    private Date addTime;

    private Date editTime;

    private String editPersonID;

    private Integer carAuditStatus;

    private Integer drivingAuditStatus;

    private Byte isEnt;

    private Byte isSelf;

    private Byte carStatus;

    private Double carLength;

    private Byte isImp;

    private Integer srcSoid;

    private String frame;

    private String engine;

    private String brank;

    private String carLengthValue;

    private String usualCity;

    private String lastLocation0;

    private String lastLocation1;

    private Date lastLocationTime;


    private String carTypeValue;
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getEntID() {
        return entID;
    }

    public void setEntID(String entID) {
        this.entID = entID;
    }

    public String getOwnerPersonID() {
        return ownerPersonID;
    }

    public void setOwnerPersonID(String ownerPersonID) {
        this.ownerPersonID = ownerPersonID;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
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

    public String getCoachTypeDictGUID() {
        return coachTypeDictGUID;
    }

    public void setCoachTypeDictGUID(String coachTypeDictGUID) {
        this.coachTypeDictGUID = coachTypeDictGUID;
    }

    public String getCarLengthDictGUID() {
        return carLengthDictGUID;
    }

    public void setCarLengthDictGUID(String carLengthDictGUID) {
        this.carLengthDictGUID = carLengthDictGUID;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getDrivingPics() {
        return drivingPics;
    }

    public void setDrivingPics(String drivingPics) {
        this.drivingPics = drivingPics;
    }

    public String getFirstDriverPersonID() {
        return firstDriverPersonID;
    }

    public void setFirstDriverPersonID(String firstDriverPersonID) {
        this.firstDriverPersonID = firstDriverPersonID;
    }

    public String getSecondDriverPersonID() {
        return secondDriverPersonID;
    }

    public void setSecondDriverPersonID(String secondDriverPersonID) {
        this.secondDriverPersonID = secondDriverPersonID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditPersonID() {
        return editPersonID;
    }

    public void setEditPersonID(String editPersonID) {
        this.editPersonID = editPersonID;
    }

    public Integer getCarAuditStatus() {
        return carAuditStatus;
    }

    public void setCarAuditStatus(Integer carAuditStatus) {
        this.carAuditStatus = carAuditStatus;
    }

    public Integer getDrivingAuditStatus() {
        return drivingAuditStatus;
    }

    public void setDrivingAuditStatus(Integer drivingAuditStatus) {
        this.drivingAuditStatus = drivingAuditStatus;
    }

    public Byte getIsEnt() {
        return isEnt;
    }

    public void setIsEnt(Byte isEnt) {
        this.isEnt = isEnt;
    }

    public Byte getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Byte isSelf) {
        this.isSelf = isSelf;
    }

    public Byte getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Byte carStatus) {
        this.carStatus = carStatus;
    }

    public Double getCarLength() {
        return carLength;
    }

    public void setCarLength(Double carLength) {
        this.carLength = carLength;
    }

    public Byte getIsImp() {
        return isImp;
    }

    public void setIsImp(Byte isImp) {
        this.isImp = isImp;
    }

    public Integer getSrcSoid() {
        return srcSoid;
    }

    public void setSrcSoid(Integer srcSoid) {
        this.srcSoid = srcSoid;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getBrank() {
        return brank;
    }

    public void setBrank(String brank) {
        this.brank = brank;
    }

    public String getCarLengthValue() {
        return carLengthValue;
    }

    public void setCarLengthValue(String carLengthValue) {
        this.carLengthValue = carLengthValue;
    }

    public String getUsualCity() {
        return usualCity;
    }

    public void setUsualCity(String usualCity) {
        this.usualCity = usualCity;
    }

    public String getLastLocation0() {
        return lastLocation0;
    }

    public void setLastLocation0(String lastLocation0) {
        this.lastLocation0 = lastLocation0;
    }

    public String getLastLocation1() {
        return lastLocation1;
    }

    public void setLastLocation1(String lastLocation1) {
        this.lastLocation1 = lastLocation1;
    }

    public Date getLastLocationTime() {
        return lastLocationTime;
    }

    public void setLastLocationTime(Date lastLocationTime) {
        this.lastLocationTime = lastLocationTime;
    }

    public String getCarTypeValue() {
        return carTypeValue;
    }

    public void setCarTypeValue(String carTypeValue) {
        this.carTypeValue = carTypeValue;
    }
}