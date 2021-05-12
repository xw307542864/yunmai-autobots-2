package com.logibeat.cloud.common.vo;

/**
 * Created by wilson on 2017/4/20.
 */
public class CarBaseInfoVo {
    /**
     * 企业与车关系ID
     */

    private String guid;

    /**
     * 车辆ID
     */
    private String carId;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 车辆头像
     */
    private String logo;

    /**
     * 额定载重 吨
     */
    private Double ratedLoad;

    /**
     *  额定体积 立方米
     */
    private Double ratedVolume;

    /**
     * 车厢长度：单位米
     */
    private String carLengthDictGUID;

    /**
     * 车型 (编码)
     */
    private String coachTypeDictGUID;

    /**
     * 车辆认证状态
     */
    private Integer auditStatus;


    private String auditStatusValue;

    /**
     * 车辆状态 0:全部 1:空闲 2:待发 3:在途
     */
    private Integer carStatus;


    private String carStatusValue;

    /**
     * 合作类型
     */
    private Integer coopType;

    private String coopTypeValue;

    /**
     * 是否星标
     */
    private Boolean starMark;

    /**
     * 所属组织
     */
    private String orgName;

    /**
     *所属组织ID
     */
    private String orgId;


    private Integer coopStatus;

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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getCarLengthDictGUID() {
        return carLengthDictGUID;
    }

    public void setCarLengthDictGUID(String carLengthDictGUID) {
        this.carLengthDictGUID = carLengthDictGUID;
    }

    public String getCoachTypeDictGUID() {
        return coachTypeDictGUID;
    }

    public void setCoachTypeDictGUID(String coachTypeDictGUID) {
        this.coachTypeDictGUID = coachTypeDictGUID;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatusValue() {
        return auditStatusValue;
    }

    public void setAuditStatusValue(String auditStatusValue) {
        this.auditStatusValue = auditStatusValue;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    public String getCarStatusValue() {
        return carStatusValue;
    }

    public void setCarStatusValue(String carStatusValue) {
        this.carStatusValue = carStatusValue;
    }

    public Integer getCoopType() {
        return coopType;
    }

    public void setCoopType(Integer coopType) {
        this.coopType = coopType;
    }

    public String getCoopTypeValue() {
        return coopTypeValue;
    }

    public void setCoopTypeValue(String coopTypeValue) {
        this.coopTypeValue = coopTypeValue;
    }

    public Boolean isStarMark() {
        return starMark;
    }

    public void setStarMark(Boolean starMark) {
        this.starMark = starMark;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getCoopStatus() {
        return coopStatus;
    }

    public void setCoopStatus(Integer coopStatus) {
        this.coopStatus = coopStatus;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
