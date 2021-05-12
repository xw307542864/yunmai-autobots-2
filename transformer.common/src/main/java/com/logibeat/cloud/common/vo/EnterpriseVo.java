package com.logibeat.cloud.common.vo;


import java.util.List;

/**
 * 
* @ClassName: enterpriseVo 
* @Description: 企业Vo
* @author sean 
* @date 2016年5月30日 下午4:17:30 
* @version 1.0
 */
public class EnterpriseVo {
    
    private String id;
    private String name;
    private String owerPersonId;
    private String ownerTitle;
    private String auditImage;
     
    private String entType;
    private String entTypeTitle;
    
    private String personName;
    private String personPhone;
    //省市县编码
    private String address;
    private String profile;
    //联系人
    private String contact;
    //企业Logo
    private String logoFileGUID;
    private Integer auditStatus;
    private String auditStatusTitle;
    private Byte isLock;
    private String isLockTitle;
    //是否伪企业
    private Byte isSham;
    private String isShamTitle;
    private String entCode;
    private String regionTitle;
    
    private Long carNums;
    private Long perNums;
    //企业Logo
    private String entLogo;
    
    private List<OrgVo> orgVoList;
    
    private List<EntCooperAndDriverVo> entCooperAndDriverList;
    
    private Long perNum;
    
//    private String authImage;
    
//    public String getAuthImage() {
//        return authImage;
//    }
//    public void setAuthImage(String authImage) {
//        this.authImage = authImage;
//    }
    
    
    public Long getCarNums() {
        return carNums;
    }
    public void setCarNums(Long carNums) {
        this.carNums = carNums;
    }
    public Long getPerNums() {
        return perNums;
    }
    public void setPerNums(Long perNums) {
        this.perNums = perNums;
    }
    public String getRegionTitle() {
        return regionTitle;
    }
    public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonPhone() {
		return personPhone;
	}
	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}
	public void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
    }
    public String getEntCode() {
        return entCode;
    }
    public void setEntCode(String entCode) {
        this.entCode = entCode;
    }
    public String getAuditImage() {
        return auditImage;
    }
    public void setAuditImage(String auditImage) {
        this.auditImage = auditImage;
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
    public String getOwerPersonId() {
        return owerPersonId;
    }
    public void setOwerPersonId(String owerPersonId) {
        this.owerPersonId = owerPersonId;
    }
    public String getOwnerTitle() {
        return ownerTitle;
    }
    public void setOwnerTitle(String ownerTitle) {
        this.ownerTitle = ownerTitle;
    }
    public String getEntType() {
        return entType;
    }
    public void setEntType(String entType) {
        this.entType = entType;
    }
    
    public String getEntTypeTitle() {
        return entTypeTitle;
    }
    public void setEntTypeTitle(String entTypeTitle) {
        this.entTypeTitle = entTypeTitle;
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
    public Integer getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
    public String getAuditStatusTitle() {
        return auditStatusTitle;
    }
    public void setAuditStatusTitle(String auditStatusTitle) {
        this.auditStatusTitle = auditStatusTitle;
    }
    public Byte getIsLock() {
        return isLock;
    }
    public void setIsLock(Byte isLock) {
        this.isLock = isLock;
    }
    public String getIsLockTitle() {
        return isLockTitle;
    }
    public void setIsLockTitle(String isLockTitle) {
        this.isLockTitle = isLockTitle;
    }
    public Byte getIsSham() {
        return isSham;
    }
    public void setIsSham(Byte isSham) {
        this.isSham = isSham;
    }
    public String getIsShamTitle() {
        return isShamTitle;
    }
    public void setIsShamTitle(String isShamTitle) {
        this.isShamTitle = isShamTitle;
    }
	public List<OrgVo> getOrgVoList() {
		return orgVoList;
	}
	public void setOrgVoList(List<OrgVo> orgVoList) {
		this.orgVoList = orgVoList;
	}
	public List<EntCooperAndDriverVo> getEntCooperAndDriverList() {
		return entCooperAndDriverList;
	}
	public void setEntCooperAndDriverList(List<EntCooperAndDriverVo> entCooperAndDriverList) {
		this.entCooperAndDriverList = entCooperAndDriverList;
	}
	public String getEntLogo() {
		return entLogo;
	}
	public void setEntLogo(String entLogo) {
		this.entLogo = entLogo;
	}
	public Long getPerNum() {
		return perNum;
	}
	public void setPerNum(Long perNum) {
		this.perNum = perNum;
	}
    
}
