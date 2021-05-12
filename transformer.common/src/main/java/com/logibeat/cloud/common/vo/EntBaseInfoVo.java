package com.logibeat.cloud.common.vo;

/**
 * 企业信息VO
* @Title: EntBaseInfoVo
* @Description:
* @Company: 运脉科技
* @author   wilson
* @date     2015年12月11日
 */
public class EntBaseInfoVo {
	
	/**
	 * 企业地址 
	 */
	private String Address;
	
	/**
	 * 企业编号 
	 */
	private String Code;
	
	/**
	 * DictGUID
	 */
	private String DictGUID;
	
    /**
     * 主键ID 	
     */
	private String ID;
	
	/**
	 * Name
	 */
	private String Name;
	
	/**
	 * 企业简介 
	 */
	private String Profile;
	
	/**
	 * RegionCode
	 */
	private String RegionCode;
	
	/**
	 * logo
	 */
	private String logo;

	/**
	 * 法人或认证人
	 * @return
     */
	private String legalPerson;

	/**
	 * 企业id
	 */
	private String entId;

	/**
	 * 认证类型
	 */
	private Integer oauthType;

	/**
	 * 企业类型
	 */
	private Integer entStatus;

	/**
	 *
	 * 成立日期
	 * @return
     */
	private String buildDate;


	/**
	 * 统一社会信用代码
	 */
	private String regNumber;

	/**
	 * 登记状态
	 */
	private String regStatus;

	/**
	 * 是否是入驻或待认领
	 * @return
     */
    private Boolean flag;


	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getDictGUID() {
		return DictGUID;
	}

	public void setDictGUID(String dictGUID) {
		DictGUID = dictGUID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getProfile() {
		return Profile;
	}

	public void setProfile(String profile) {
		Profile = profile;
	}

	public String getRegionCode() {
		return RegionCode;
	}

	public void setRegionCode(String regionCode) {
		RegionCode = regionCode;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public Integer getOauthType() {
		return oauthType;
	}

	public void setOauthType(Integer oauthType) {
		this.oauthType = oauthType;
	}

	public Integer getEntStatus() {
		return entStatus;
	}

	public void setEntStatus(Integer entStatus) {
		this.entStatus = entStatus;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
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

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
}
