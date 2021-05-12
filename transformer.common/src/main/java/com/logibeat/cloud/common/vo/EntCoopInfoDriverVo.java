package com.logibeat.cloud.common.vo;
/**
 * 司机端搜索企业
 * @author yg
 *
 */
public class EntCoopInfoDriverVo {
	/**
	 * 企业类型编码
	 */
	private String entTypeDictGUID;
	/**
	 * 企业类型名称
	 */
	private String entTypeDictName;
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 是否星标企业
	 */
	private boolean isStarMark;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 新联系人id
	 */
	private String newFriendGUID;
	/**
	 * 企业Logo图片地址
	 */
	private String logo;
	/**
	 * 企业名称
	 */
	private String name;
	/**
	 * 企业简介
	 */
	private String profile;
	/**
	 * 是否被企业邀请
	 */
	private boolean isInviteByEnt;
	/**
	 * 企业认证状态
	 */
	private Integer entAuditStatus;
	/**
	 * 认证类型：企业认证，个人认证
	 */
	private int oauthType;
	/**
	 * 法人或者认证人（内部企业）
	 */
	private String legalPeople;
	/**
	 * 拼音字段
	 */
	private String pinYin;
	/**
	 * 邀请状态
	 */
	private int inviteState;
	/**
	 * 邀请状态名称
	 */
	private String inviteStateName;
	/**
	 * 企业状态（1. 已入驻 2.待认领 3.未入驻）
	 */
	private int entStatus;
	/**
	 * 企业名称（工商局信息）
	 */
	private String entName;
	/**
	 * 法人（工商局信息）
	 */
	private String legalPerson;
	/**
	 * 成立时间（工商局信息）
	 */
	private String buildDate;
	/**
	 * 统一社会信用代码（工商局信息）
	 */
	private String regNumber;
	/**
	 * 登记状态（工商局信息）
	 */
	private String regStatus;

	public String getEntTypeDictGUID() {
		return entTypeDictGUID;
	}

	public void setEntTypeDictGUID(String entTypeDictGUID) {
		this.entTypeDictGUID = entTypeDictGUID;
	}

	public String getEntTypeDictName() {
		return entTypeDictName;
	}

	public void setEntTypeDictName(String entTypeDictName) {
		this.entTypeDictName = entTypeDictName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsStarMark() {
		return isStarMark;
	}

	public void setIsStarMark(boolean isStarMark) {
		this.isStarMark = isStarMark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNewFriendGUID() {
		return newFriendGUID;
	}

	public void setNewFriendGUID(String newFriendGUID) {
		this.newFriendGUID = newFriendGUID;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public boolean getIsInviteByEnt() {
		return isInviteByEnt;
	}

	public void setIsInviteByEnt(boolean isInviteByEnt) {
		this.isInviteByEnt = isInviteByEnt;
	}

	public Integer getEntAuditStatus() {
		return entAuditStatus;
	}

	public void setEntAuditStatus(Integer entAuditStatus) {
		this.entAuditStatus = entAuditStatus;
	}

	public int getOauthType() {
		return oauthType;
	}

	public void setOauthType(int oauthType) {
		this.oauthType = oauthType;
	}

	public String getLegalPeople() {
		return legalPeople;
	}

	public void setLegalPeople(String legalPeople) {
		this.legalPeople = legalPeople;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public int getInviteState() {
		return inviteState;
	}

	public void setInviteState(int inviteState) {
		this.inviteState = inviteState;
	}

	public String getInviteStateName() {
		return inviteStateName;
	}

	public void setInviteStateName(String inviteStateName) {
		this.inviteStateName = inviteStateName;
	}

	public int getEntStatus() {
		return entStatus;
	}

	public void setEntStatus(int entStatus) {
		this.entStatus = entStatus;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
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

}
