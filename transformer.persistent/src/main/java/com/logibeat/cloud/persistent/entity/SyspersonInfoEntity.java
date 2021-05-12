package com.logibeat.cloud.persistent.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc: Model类
 * Date: 2020-12-08
 */
public class SyspersonInfoEntity implements Serializable {
    //TODO: Do not forget add "serialVersionUID" field AND change package path!

    
    
    private String guid;
    
    private String personId;
    
    private String name;
    
    private String idCard;
    
    /**
     * 1:男 2：女
     */
    private Integer sex;
    
    private String birthDay;
    
    private String nation;
    
    private String issuingAuthority;
    
    private String idcardExpireDate;
    
    private String idcardFrontPic;
    
    private String idcardBackPic;
    
    private String idcardHandPic;
    
    /**
     * 驾驶证类型
     */
    private String drivingLicenseType;
    
    private String drivingLicenseNumber;
    
    private String drivingLicensePic;
    
    private String drivingLicenseBackPic;
    
    private String drivingLicenseStartDate;
    
    private String drivingLicenseEndDate;
    
    private String firstTakeTime;
    
    private Date createTime;


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public String getIdcardExpireDate() {
        return idcardExpireDate;
    }

    public void setIdcardExpireDate(String idcardExpireDate) {
        this.idcardExpireDate = idcardExpireDate;
    }

    public String getIdcardFrontPic() {
        return idcardFrontPic;
    }

    public void setIdcardFrontPic(String idcardFrontPic) {
        this.idcardFrontPic = idcardFrontPic;
    }

    public String getIdcardBackPic() {
        return idcardBackPic;
    }

    public void setIdcardBackPic(String idcardBackPic) {
        this.idcardBackPic = idcardBackPic;
    }

    public String getIdcardHandPic() {
        return idcardHandPic;
    }

    public void setIdcardHandPic(String idcardHandPic) {
        this.idcardHandPic = idcardHandPic;
    }

    public String getDrivingLicenseType() {
        return drivingLicenseType;
    }

    public void setDrivingLicenseType(String drivingLicenseType) {
        this.drivingLicenseType = drivingLicenseType;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getDrivingLicensePic() {
        return drivingLicensePic;
    }

    public void setDrivingLicensePic(String drivingLicensePic) {
        this.drivingLicensePic = drivingLicensePic;
    }

    public String getDrivingLicenseBackPic() {
        return drivingLicenseBackPic;
    }

    public void setDrivingLicenseBackPic(String drivingLicenseBackPic) {
        this.drivingLicenseBackPic = drivingLicenseBackPic;
    }

    public String getDrivingLicenseStartDate() {
        return drivingLicenseStartDate;
    }

    public void setDrivingLicenseStartDate(String drivingLicenseStartDate) {
        this.drivingLicenseStartDate = drivingLicenseStartDate;
    }

    public String getDrivingLicenseEndDate() {
        return drivingLicenseEndDate;
    }

    public void setDrivingLicenseEndDate(String drivingLicenseEndDate) {
        this.drivingLicenseEndDate = drivingLicenseEndDate;
    }

    public String getFirstTakeTime() {
        return firstTakeTime;
    }

    public void setFirstTakeTime(String firstTakeTime) {
        this.firstTakeTime = firstTakeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
