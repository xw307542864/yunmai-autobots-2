package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;
import java.util.Date;

public class SyspersonEntity extends EntitySerialize {
    private String personid;

    private String id;

    private String loginname;

    private String myname;

    private String password;

    private String surepassword;

    private String sex;

    private String sysdepartmentid;

    private String position;

    private String mobilephonenumber;

    private String phonenumber;

    private String province;

    private String city;

    private String village;

    private String address;

    private String emailaddress;

    private String remark;

    private String state;

    private Date createtime;

    private String createperson;

    private Date updatetime;

    private Integer logonnum;

    private Date logontime;

    private String logonip;

    private Date lastlogontime;

    private String lastlogonip;

    private String pagestyle;

    private String updateperson;

    private String hdpic;

    private String nichen;

    private Integer regioncode;

    private String qrfileguid;

    private String regtypedictguid;

    private String inviterpersonid;

    private Integer personstate;

    private Integer pwderrnum;

    private Date unlocktime;

    private Byte isim;

    private Byte isentchildadmin;

    private Integer childadmintype;

    private Long childadminentid;

    private Integer persontype;

    private Byte isdelete;

    private Byte issynchim;

    private String wechat;

    private Integer driverauditstatus;

    private Date driveraudittime;

    private String drivinglic;

    private Date drivinglicdate;

    private String drivinglictype;

    private Double drivingrange;

    private String email;

    private String entid;

    private String initial;

    private Byte ischeckfriend;

    private Byte isdriver;

    private Byte isreg;

    private Byte issharegpstocoopent;

    private Byte issharegpstomyent;

    private String qq;

    private Date refreshdrivingrangetime;

    private String sociallic;

    private String impwd;

    private String imid;

    private String lastentid;

    private Byte isDisable;

    private String logitalkid;

    private String usualcity;

    private Integer auditStatus;

    private Integer verifiedStatus;

    private Integer drivingLicenseStatus;

    private Date birthday;

    private Integer dataSource;

    private String userType;

    private String idcardExpireDate;

    private String drivingLicense;

    private String roleType;

    private Date agreementSignTime;

    private String signDevice;

    private String nation;

    private String issuingAuthority;

    private Integer driversign;

    private Integer entsign;

    private Integer gpsisswitch;

    private String openaccountstate;

    private byte[] version;

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurepassword() {
        return surepassword;
    }

    public void setSurepassword(String surepassword) {
        this.surepassword = surepassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSysdepartmentid() {
        return sysdepartmentid;
    }

    public void setSysdepartmentid(String sysdepartmentid) {
        this.sysdepartmentid = sysdepartmentid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobilephonenumber() {
        return mobilephonenumber;
    }

    public void setMobilephonenumber(String mobilephonenumber) {
        this.mobilephonenumber = mobilephonenumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateperson() {
        return createperson;
    }

    public void setCreateperson(String createperson) {
        this.createperson = createperson;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getLogonnum() {
        return logonnum;
    }

    public void setLogonnum(Integer logonnum) {
        this.logonnum = logonnum;
    }

    public Date getLogontime() {
        return logontime;
    }

    public void setLogontime(Date logontime) {
        this.logontime = logontime;
    }

    public String getLogonip() {
        return logonip;
    }

    public void setLogonip(String logonip) {
        this.logonip = logonip;
    }

    public Date getLastlogontime() {
        return lastlogontime;
    }

    public void setLastlogontime(Date lastlogontime) {
        this.lastlogontime = lastlogontime;
    }

    public String getLastlogonip() {
        return lastlogonip;
    }

    public void setLastlogonip(String lastlogonip) {
        this.lastlogonip = lastlogonip;
    }

    public String getPagestyle() {
        return pagestyle;
    }

    public void setPagestyle(String pagestyle) {
        this.pagestyle = pagestyle;
    }

    public String getUpdateperson() {
        return updateperson;
    }

    public void setUpdateperson(String updateperson) {
        this.updateperson = updateperson;
    }

    public String getHdpic() {
        return hdpic;
    }

    public void setHdpic(String hdpic) {
        this.hdpic = hdpic;
    }

    public String getNichen() {
        return nichen;
    }

    public void setNichen(String nichen) {
        this.nichen = nichen;
    }

    public Integer getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(Integer regioncode) {
        this.regioncode = regioncode;
    }

    public String getQrfileguid() {
        return qrfileguid;
    }

    public void setQrfileguid(String qrfileguid) {
        this.qrfileguid = qrfileguid;
    }

    public String getRegtypedictguid() {
        return regtypedictguid;
    }

    public void setRegtypedictguid(String regtypedictguid) {
        this.regtypedictguid = regtypedictguid;
    }

    public String getInviterpersonid() {
        return inviterpersonid;
    }

    public void setInviterpersonid(String inviterpersonid) {
        this.inviterpersonid = inviterpersonid;
    }

    public Integer getPersonstate() {
        return personstate;
    }

    public void setPersonstate(Integer personstate) {
        this.personstate = personstate;
    }

    public Integer getPwderrnum() {
        return pwderrnum;
    }

    public void setPwderrnum(Integer pwderrnum) {
        this.pwderrnum = pwderrnum;
    }

    public Date getUnlocktime() {
        return unlocktime;
    }

    public void setUnlocktime(Date unlocktime) {
        this.unlocktime = unlocktime;
    }

    public Byte getIsim() {
        return isim;
    }

    public void setIsim(Byte isim) {
        this.isim = isim;
    }

    public Byte getIsentchildadmin() {
        return isentchildadmin;
    }

    public void setIsentchildadmin(Byte isentchildadmin) {
        this.isentchildadmin = isentchildadmin;
    }

    public Integer getChildadmintype() {
        return childadmintype;
    }

    public void setChildadmintype(Integer childadmintype) {
        this.childadmintype = childadmintype;
    }

    public Long getChildadminentid() {
        return childadminentid;
    }

    public void setChildadminentid(Long childadminentid) {
        this.childadminentid = childadminentid;
    }

    public Integer getPersontype() {
        return persontype;
    }

    public void setPersontype(Integer persontype) {
        this.persontype = persontype;
    }

    public Byte getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Byte isdelete) {
        this.isdelete = isdelete;
    }

    public Byte getIssynchim() {
        return issynchim;
    }

    public void setIssynchim(Byte issynchim) {
        this.issynchim = issynchim;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getDriverauditstatus() {
        return driverauditstatus;
    }

    public void setDriverauditstatus(Integer driverauditstatus) {
        this.driverauditstatus = driverauditstatus;
    }

    public Date getDriveraudittime() {
        return driveraudittime;
    }

    public void setDriveraudittime(Date driveraudittime) {
        this.driveraudittime = driveraudittime;
    }

    public String getDrivinglic() {
        return drivinglic;
    }

    public void setDrivinglic(String drivinglic) {
        this.drivinglic = drivinglic;
    }

    public Date getDrivinglicdate() {
        return drivinglicdate;
    }

    public void setDrivinglicdate(Date drivinglicdate) {
        this.drivinglicdate = drivinglicdate;
    }

    public String getDrivinglictype() {
        return drivinglictype;
    }

    public void setDrivinglictype(String drivinglictype) {
        this.drivinglictype = drivinglictype;
    }

    public Double getDrivingrange() {
        return drivingrange;
    }

    public void setDrivingrange(Double drivingrange) {
        this.drivingrange = drivingrange;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEntid() {
        return entid;
    }

    public void setEntid(String entid) {
        this.entid = entid;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public Byte getIscheckfriend() {
        return ischeckfriend;
    }

    public void setIscheckfriend(Byte ischeckfriend) {
        this.ischeckfriend = ischeckfriend;
    }

    public Byte getIsdriver() {
        return isdriver;
    }

    public void setIsdriver(Byte isdriver) {
        this.isdriver = isdriver;
    }

    public Byte getIsreg() {
        return isreg;
    }

    public void setIsreg(Byte isreg) {
        this.isreg = isreg;
    }

    public Byte getIssharegpstocoopent() {
        return issharegpstocoopent;
    }

    public void setIssharegpstocoopent(Byte issharegpstocoopent) {
        this.issharegpstocoopent = issharegpstocoopent;
    }

    public Byte getIssharegpstomyent() {
        return issharegpstomyent;
    }

    public void setIssharegpstomyent(Byte issharegpstomyent) {
        this.issharegpstomyent = issharegpstomyent;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getRefreshdrivingrangetime() {
        return refreshdrivingrangetime;
    }

    public void setRefreshdrivingrangetime(Date refreshdrivingrangetime) {
        this.refreshdrivingrangetime = refreshdrivingrangetime;
    }

    public String getSociallic() {
        return sociallic;
    }

    public void setSociallic(String sociallic) {
        this.sociallic = sociallic;
    }

    public String getImpwd() {
        return impwd;
    }

    public void setImpwd(String impwd) {
        this.impwd = impwd;
    }

    public String getImid() {
        return imid;
    }

    public void setImid(String imid) {
        this.imid = imid;
    }

    public String getLastentid() {
        return lastentid;
    }

    public void setLastentid(String lastentid) {
        this.lastentid = lastentid;
    }

    public Byte getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Byte isDisable) {
        this.isDisable = isDisable;
    }

    public String getLogitalkid() {
        return logitalkid;
    }

    public void setLogitalkid(String logitalkid) {
        this.logitalkid = logitalkid;
    }

    public String getUsualcity() {
        return usualcity;
    }

    public void setUsualcity(String usualcity) {
        this.usualcity = usualcity;
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

    public Integer getDrivingLicenseStatus() {
        return drivingLicenseStatus;
    }

    public void setDrivingLicenseStatus(Integer drivingLicenseStatus) {
        this.drivingLicenseStatus = drivingLicenseStatus;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIdcardExpireDate() {
        return idcardExpireDate;
    }

    public void setIdcardExpireDate(String idcardExpireDate) {
        this.idcardExpireDate = idcardExpireDate;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Date getAgreementSignTime() {
        return agreementSignTime;
    }

    public void setAgreementSignTime(Date agreementSignTime) {
        this.agreementSignTime = agreementSignTime;
    }

    public String getSignDevice() {
        return signDevice;
    }

    public void setSignDevice(String signDevice) {
        this.signDevice = signDevice;
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

    public Integer getDriversign() {
        return driversign;
    }

    public void setDriversign(Integer driversign) {
        this.driversign = driversign;
    }

    public Integer getEntsign() {
        return entsign;
    }

    public void setEntsign(Integer entsign) {
        this.entsign = entsign;
    }

    public Integer getGpsisswitch() {
        return gpsisswitch;
    }

    public void setGpsisswitch(Integer gpsisswitch) {
        this.gpsisswitch = gpsisswitch;
    }

    public String getOpenaccountstate() {
        return openaccountstate;
    }

    public void setOpenaccountstate(String openaccountstate) {
        this.openaccountstate = openaccountstate;
    }

    public byte[] getVersion() {
        return version;
    }

    public void setVersion(byte[] version) {
        this.version = version;
    }
}