package com.logibeat.cloud.common.vo;

public class EntCoopShortInfoVo {

	/**
	 * 主键ID
	 */
	private String ID;

	/**
	 * 企业名称
	 */
	private String Name;

	/**
	 * 企业地址
	 */
	private String Address;

	/**
	 * 企业编号
	 */
	private String Code;

	/**
	 * 合作关系
	 */
	private Integer CoopType;

	/**
	 * 企业认证状态
	 */
	private Integer EntAuditStatus;

	/**
	 * 企业类型编码
	 */
	private String EntTypeDictGUID;

	/**
	 * 企业类型名称
	 */
	private String EntTypeDictName;

	/**
	 * 邀请状态（枚举 对方状态）
	 */
	private Integer HeInviteState;

	/**
	 * 是否星标企业（企业版专用）
	 */
	private boolean IsStarMark;

	/**
	 * 是否有关系
	 */
	private boolean IsFriend;

	/**
	 * 企业Logo图片地址
	 */
	private String Logo;

	/**
	 * 邀请状态（枚举 我方状态）
	 */
	private Integer MyInviteState;

	/**
	 * 企业简介
	 */
	private String Profile;

	/**
	 * 是否被企业邀请
	 */
	private boolean IsInviteByEnt;

	/**
	 * 新联系人GUID
	 */
	private String NewFriendGUID;

	private String pinYin;
	/**
	 * 企业状态
	 */
	private Integer entStatus;
	/**
	 * 认证类型 （1.个人 2.企业）
	 */
	private Integer oauthType;
	/**
	 * 成立时间
	 */
	private String buildDate;

	/**
	 * 登记状态
	 */
	private String regStatus;
	/**
	 * 统一社会信用代码
	 */
	private String regNumber;
	/**
	 * 法人
	 */
	private String legalPerson;

	/**
	 * 企业类型 上级
	 */
	private String parentDictGUID;
	/**
	 * 工商信息里的企业名称
	 */
	private String entName;
	/**
	 * 邀请状态
	 */
	private Integer inviteState;
	 

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

	public Integer getCoopType() {
		return CoopType;
	}

	public void setCoopType(Integer coopType) {
		CoopType = coopType;
	}

	public Integer getEntAuditStatus() {
		return EntAuditStatus;
	}

	public void setEntAuditStatus(Integer entAuditStatus) {
		EntAuditStatus = entAuditStatus;
	}

	public String getEntTypeDictGUID() {
		return EntTypeDictGUID;
	}

	public void setEntTypeDictGUID(String entTypeDictGUID) {
		EntTypeDictGUID = entTypeDictGUID;
	}

	public String getEntTypeDictName() {
		return EntTypeDictName;
	}

	public void setEntTypeDictName(String entTypeDictName) {
		EntTypeDictName = entTypeDictName;
	}

	public Integer getHeInviteState() {
		return HeInviteState;
	}

	public void setHeInviteState(Integer heInviteState) {
		HeInviteState = heInviteState;
	}

	public boolean isIsStarMark() {
		return IsStarMark;
	}

	public void setIsStarMark(boolean isStarMark) {
		IsStarMark = isStarMark;
	}

	public boolean isIsFriend() {
		return IsFriend;
	}

	public void setIsFriend(boolean isFriend) {
		IsFriend = isFriend;
	}

	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public Integer getMyInviteState() {
		return MyInviteState;
	}

	public void setMyInviteState(Integer myInviteState) {
		MyInviteState = myInviteState;
	}

	public String getProfile() {
		return Profile;
	}

	public void setProfile(String profile) {
		Profile = profile;
	}

	public boolean isIsInviteByEnt() {
		return IsInviteByEnt;
	}

	public void setIsInviteByEnt(boolean isInviteByEnt) {
		IsInviteByEnt = isInviteByEnt;
	}

	public String getNewFriendGUID() {
		return NewFriendGUID;
	}

	public void setNewFriendGUID(String newFriendGUID) {
		NewFriendGUID = newFriendGUID;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}
 
	public Integer getOauthType() {
		return oauthType;
	}

	public void setOauthType(Integer oauthType) {
		this.oauthType = oauthType;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getParentDictGUID() {
		return parentDictGUID;
	}

	public void setParentDictGUID(String parentDictGUID) {
		this.parentDictGUID = parentDictGUID;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public Integer getEntStatus() {
		return entStatus;
	}

	public void setEntStatus(Integer entStatus) {
		this.entStatus = entStatus;
	}

	public Integer getInviteState() {
		return inviteState;
	}

	public void setInviteState(Integer inviteState) {
		this.inviteState = inviteState;
	}

}
