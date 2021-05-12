package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class SystemPersonEntity extends EntitySerialize {
    private String personID;

    private String id;

    private String loginName;

    private String myName;

    private String password;

    private String surePassword;

    private String sex;

    private String sysDepartmentId;

    private String position;

    private String mobilePhoneNumber;

    private String phoneNumber;

    private String province;

    private String city;

    private String village;

    private String address;

    private String emailAddress;

    private String remark;

    private String state;

    private Date createTime;

    private String createPerson;

    private Date updateTime;

    private Integer logonNum;

    private Date logonTime;

    private String logonIP;

    private Date lastLogonTime;

    private String lastLogonIP;

    private String pageStyle;

    private String updatePerson;

    private String hDpic;

    private String niChen;

    private Integer regionCode;

    private String qRFileGUID;

    private String regTypeDictGUID;

    private String inviterPersonID;

    private Integer personState;

    private Integer pwdErrNum;

    private Date unLockTime;

    private Byte isim;

    private Byte isEntChildAdmin;

    private Integer childAdminType;

    private Long childAdminEntID;

    private Integer personType;

    private Byte isDelete;

    private Byte isSynchIm;

    private String weChat;

    private Integer driverAuditStatus;

    private Date driverAuditTime;

    private String drivingLic;

    private Date drivingLicDate;

    private String drivingLicType;

    private Double drivingRange;

    private String email;

    private String entID;

    private String initial;

    private Byte isCheckFriend;

    private Byte isDriver;

    private Byte isReg;

    private Byte isShareGpsToCoopEnt;

    private Byte isShareGpsToMyEnt;

    private String qq;

    private Date refreshDrivingRangeTime;

    private String socialLic;

    private String impwd;

    private String imid;

    private String lastEntId;

    private Byte isDisable;

    private String logitalkId;

    private String usualCity;

    private Integer auditStatus;

    private Integer verifiedStatus;

    private Date birthDay;

    private byte[] version;


    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurePassword() {
        return surePassword;
    }

    public void setSurePassword(String surePassword) {
        this.surePassword = surePassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSysDepartmentId() {
        return sysDepartmentId;
    }

    public void setSysDepartmentId(String sysDepartmentId) {
        this.sysDepartmentId = sysDepartmentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLogonNum() {
        return logonNum;
    }

    public void setLogonNum(Integer logonNum) {
        this.logonNum = logonNum;
    }

    public Date getLogonTime() {
        return logonTime;
    }

    public void setLogonTime(Date logonTime) {
        this.logonTime = logonTime;
    }

    public String getLogonIP() {
        return logonIP;
    }

    public void setLogonIP(String logonIP) {
        this.logonIP = logonIP;
    }

    public Date getLastLogonTime() {
        return lastLogonTime;
    }

    public void setLastLogonTime(Date lastLogonTime) {
        this.lastLogonTime = lastLogonTime;
    }

    public String getLastLogonIP() {
        return lastLogonIP;
    }

    public void setLastLogonIP(String lastLogonIP) {
        this.lastLogonIP = lastLogonIP;
    }

    public String getPageStyle() {
        return pageStyle;
    }

    public void setPageStyle(String pageStyle) {
        this.pageStyle = pageStyle;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public String gethDpic() {
        return hDpic;
    }

    public void sethDpic(String hDpic) {
        this.hDpic = hDpic;
    }

    public String getNiChen() {
        return niChen;
    }

    public void setNiChen(String niChen) {
        this.niChen = niChen;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public String getqRFileGUID() {
        return qRFileGUID;
    }

    public void setqRFileGUID(String qRFileGUID) {
        this.qRFileGUID = qRFileGUID;
    }

    public String getRegTypeDictGUID() {
        return regTypeDictGUID;
    }

    public void setRegTypeDictGUID(String regTypeDictGUID) {
        this.regTypeDictGUID = regTypeDictGUID;
    }

    public String getInviterPersonID() {
        return inviterPersonID;
    }

    public void setInviterPersonID(String inviterPersonID) {
        this.inviterPersonID = inviterPersonID;
    }

    public Integer getPersonState() {
        return personState;
    }

    public void setPersonState(Integer personState) {
        this.personState = personState;
    }

    public Integer getPwdErrNum() {
        return pwdErrNum;
    }

    public void setPwdErrNum(Integer pwdErrNum) {
        this.pwdErrNum = pwdErrNum;
    }

    public Date getUnLockTime() {
        return unLockTime;
    }

    public void setUnLockTime(Date unLockTime) {
        this.unLockTime = unLockTime;
    }

    public Byte getIsim() {
        return isim;
    }

    public void setIsim(Byte isim) {
        this.isim = isim;
    }

    public Byte getIsEntChildAdmin() {
        return isEntChildAdmin;
    }

    public void setIsEntChildAdmin(Byte isEntChildAdmin) {
        this.isEntChildAdmin = isEntChildAdmin;
    }

    public Integer getChildAdminType() {
        return childAdminType;
    }

    public void setChildAdminType(Integer childAdminType) {
        this.childAdminType = childAdminType;
    }

    public Long getChildAdminEntID() {
        return childAdminEntID;
    }

    public void setChildAdminEntID(Long childAdminEntID) {
        this.childAdminEntID = childAdminEntID;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getIsSynchIm() {
        return isSynchIm;
    }

    public void setIsSynchIm(Byte isSynchIm) {
        this.isSynchIm = isSynchIm;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public Integer getDriverAuditStatus() {
        return driverAuditStatus;
    }

    public void setDriverAuditStatus(Integer driverAuditStatus) {
        this.driverAuditStatus = driverAuditStatus;
    }

    public Date getDriverAuditTime() {
        return driverAuditTime;
    }

    public void setDriverAuditTime(Date driverAuditTime) {
        this.driverAuditTime = driverAuditTime;
    }

    public String getDrivingLic() {
        return drivingLic;
    }

    public void setDrivingLic(String drivingLic) {
        this.drivingLic = drivingLic;
    }

    public Date getDrivingLicDate() {
        return drivingLicDate;
    }

    public void setDrivingLicDate(Date drivingLicDate) {
        this.drivingLicDate = drivingLicDate;
    }

    public String getDrivingLicType() {
        return drivingLicType;
    }

    public void setDrivingLicType(String drivingLicType) {
        this.drivingLicType = drivingLicType;
    }

    public Double getDrivingRange() {
        return drivingRange;
    }

    public void setDrivingRange(Double drivingRange) {
        this.drivingRange = drivingRange;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEntID() {
        return entID;
    }

    public void setEntID(String entID) {
        this.entID = entID;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public Byte getIsCheckFriend() {
        return isCheckFriend;
    }

    public void setIsCheckFriend(Byte isCheckFriend) {
        this.isCheckFriend = isCheckFriend;
    }

    public Byte getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(Byte isDriver) {
        this.isDriver = isDriver;
    }

    public Byte getIsReg() {
        return isReg;
    }

    public void setIsReg(Byte isReg) {
        this.isReg = isReg;
    }

    public Byte getIsShareGpsToCoopEnt() {
        return isShareGpsToCoopEnt;
    }

    public void setIsShareGpsToCoopEnt(Byte isShareGpsToCoopEnt) {
        this.isShareGpsToCoopEnt = isShareGpsToCoopEnt;
    }

    public Byte getIsShareGpsToMyEnt() {
        return isShareGpsToMyEnt;
    }

    public void setIsShareGpsToMyEnt(Byte isShareGpsToMyEnt) {
        this.isShareGpsToMyEnt = isShareGpsToMyEnt;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getRefreshDrivingRangeTime() {
        return refreshDrivingRangeTime;
    }

    public void setRefreshDrivingRangeTime(Date refreshDrivingRangeTime) {
        this.refreshDrivingRangeTime = refreshDrivingRangeTime;
    }

    public String getSocialLic() {
        return socialLic;
    }

    public void setSocialLic(String socialLic) {
        this.socialLic = socialLic;
    }

    public String getLastEntId() {
        return lastEntId;
    }

    public void setLastEntId(String lastEntId) {
        this.lastEntId = lastEntId;
    }

    public Byte getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Byte isDisable) {
        this.isDisable = isDisable;
    }

    public String getLogitalkId() {
        return logitalkId;
    }

    public void setLogitalkId(String logitalkId) {
        this.logitalkId = logitalkId;
    }

    public String getUsualCity() {
        return usualCity;
    }

    public void setUsualCity(String usualCity) {
        this.usualCity = usualCity;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(Integer verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public byte[] getVersion() {
        return version;
    }

    public void setVersion(byte[] version) {
        this.version = version;
    }

}