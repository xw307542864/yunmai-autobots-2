package com.logibeat.cloud.persistent.entity;


import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class EnterpriseInfoEntity extends EntitySerialize {
    private String id;

    private String name;

    private String owerPersonID;

    private String code;

    private String entType;

    private String dictGUID;

    private String cityName;

    private String regionCode;

    private String address;

    private String profile;

    private String contact;

    private String logoFileGUID;

    private String logo;

    private Integer star;

    private Integer integral;

    private Byte isDelete;

    private Date addTime;

    private Date editTime;

    private String editPersonID;

    private Integer auditStatus;

    private Integer qualityAuditStatus;

    private Date auditTime;

    private String auditPersonID;

    private Byte isCheckCoopEnt;

    private Byte isCheckCoopDriver;

    private Byte isCheckSelfDriver;

    private Byte issham;

    private Byte islock;

    private String pinYin;

    private String ikid;

    private Integer iktype;

    private String buildDate;

    private Integer popularity;

    private Integer ymNumber;

    private String hotLine;

    private String legalPerson;

    private String socialLic;

    private String realName;

    private String regNumber;

    private String regStatus;

    private Integer entStatus;

    private Integer oauthType;

    private Integer isSearch;

    private Byte isAutoSendCar;

    private Integer preConditionNum;

    private Byte isAutoArrival;

    private String parentDictGUID;

    private String ownEntId;

    private String ownType;

    private Integer smsNumber;

    private Byte entSource;

    private String inputEntId;

    private Byte isShow;
    
    private String entTypeCode;
    
    public String getEntTypeCode() {
		return entTypeCode;
	}

	public void setEntTypeCode(String entTypeCode) {
		this.entTypeCode = entTypeCode;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwerPersonID() {
        return owerPersonID;
    }

    public void setOwerPersonID(String owerPersonID) {
        this.owerPersonID = owerPersonID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEntType() {
        return entType;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getDictGUID() {
        return dictGUID;
    }

    public void setDictGUID(String dictGUID) {
        this.dictGUID = dictGUID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLogoFileGUID() {
        return logoFileGUID;
    }

    public void setLogoFileGUID(String logoFileGUID) {
        this.logoFileGUID = logoFileGUID;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
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

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getQualityAuditStatus() {
        return qualityAuditStatus;
    }

    public void setQualityAuditStatus(Integer qualityAuditStatus) {
        this.qualityAuditStatus = qualityAuditStatus;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditPersonID() {
        return auditPersonID;
    }

    public void setAuditPersonID(String auditPersonID) {
        this.auditPersonID = auditPersonID;
    }

    public Byte getIsCheckCoopEnt() {
        return isCheckCoopEnt;
    }

    public void setIsCheckCoopEnt(Byte isCheckCoopEnt) {
        this.isCheckCoopEnt = isCheckCoopEnt;
    }

    public Byte getIsCheckCoopDriver() {
        return isCheckCoopDriver;
    }

    public void setIsCheckCoopDriver(Byte isCheckCoopDriver) {
        this.isCheckCoopDriver = isCheckCoopDriver;
    }

    public Byte getIsCheckSelfDriver() {
        return isCheckSelfDriver;
    }

    public void setIsCheckSelfDriver(Byte isCheckSelfDriver) {
        this.isCheckSelfDriver = isCheckSelfDriver;
    }

    public Byte getIssham() {
        return issham;
    }

    public void setIssham(Byte issham) {
        this.issham = issham;
    }

    public Byte getIslock() {
        return islock;
    }

    public void setIslock(Byte islock) {
        this.islock = islock;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getIkid() {
        return ikid;
    }

    public void setIkid(String ikid) {
        this.ikid = ikid;
    }

    public Integer getIktype() {
        return iktype;
    }

    public void setIktype(Integer iktype) {
        this.iktype = iktype;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getYmNumber() {
        return ymNumber;
    }

    public void setYmNumber(Integer ymNumber) {
        this.ymNumber = ymNumber;
    }

    public String getHotLine() {
        return hotLine;
    }

    public void setHotLine(String hotLine) {
        this.hotLine = hotLine;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getSocialLic() {
        return socialLic;
    }

    public void setSocialLic(String socialLic) {
        this.socialLic = socialLic;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public Integer getEntStatus() {
        return entStatus;
    }

    public void setEntStatus(Integer entStatus) {
        this.entStatus = entStatus;
    }

    public Integer getOauthType() {
        return oauthType;
    }

    public void setOauthType(Integer oauthType) {
        this.oauthType = oauthType;
    }

    public Integer getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(Integer isSearch) {
        this.isSearch = isSearch;
    }

    public Byte getIsAutoSendCar() {
        return isAutoSendCar;
    }

    public void setIsAutoSendCar(Byte isAutoSendCar) {
        this.isAutoSendCar = isAutoSendCar;
    }

    public Integer getPreConditionNum() {
        return preConditionNum;
    }

    public void setPreConditionNum(Integer preConditionNum) {
        this.preConditionNum = preConditionNum;
    }

    public Byte getIsAutoArrival() {
        return isAutoArrival;
    }

    public void setIsAutoArrival(Byte isAutoArrival) {
        this.isAutoArrival = isAutoArrival;
    }

    public String getParentDictGUID() {
        return parentDictGUID;
    }

    public void setParentDictGUID(String parentDictGUID) {
        this.parentDictGUID = parentDictGUID;
    }

    public String getOwnEntId() {
        return ownEntId;
    }

    public void setOwnEntId(String ownEntId) {
        this.ownEntId = ownEntId;
    }

    public String getOwnType() {
        return ownType;
    }

    public void setOwnType(String ownType) {
        this.ownType = ownType;
    }

    public Integer getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(Integer smsNumber) {
        this.smsNumber = smsNumber;
    }

    public Byte getEntSource() {
        return entSource;
    }

    public void setEntSource(Byte entSource) {
        this.entSource = entSource;
    }

    public String getInputEntId() {
        return inputEntId;
    }

    public void setInputEntId(String inputEntId) {
        this.inputEntId = inputEntId;
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }
}