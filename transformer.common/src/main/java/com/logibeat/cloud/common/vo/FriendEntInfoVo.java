package com.logibeat.cloud.common.vo;

public class FriendEntInfoVo {
	/**
	 * 企业子账号人员类型（岗位）（枚举）
	 */
	private String childAdminType;

	/**
	 * 合作关系
	 */
	private Integer coopType;

	/**
	 * 经济（实惠）
	 */
	private Double economic;

	/**
	 * 平台ID
	 */
	private String entID;

	/**
	 * 平台名称
	 */
	private String entName;
	/**
	 * 图片地址
	 */
	private String hdpic;

	/**
	 * 邀请类型
	 */
	private Integer heInviteState;
	/**
	 * 通讯Id
	 */
	private String imGUID;

	/**
	 * 是否已注册
	 */
	private boolean isReg = false;

	/**
	 * 是否常用联系人
	 */
	private boolean isUsual = false;

	/**
	 * 岗位名称
	 */
	private String jobName;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 邀请状态（枚举 我方状态）
	 */
	private Integer myInviteState;
	/**
	 * 名称备注
	 */
	private String nameRemark;

	/**
	 * 昵称
	 */
	private String niChen;
	/**
	 * 会员ID
	 */
	private String personID;

	private String security;

	/**
	 * imID
	 */
	private String logitalkId;

	private String displayName;
	
	private Boolean isImFriend = false;//是否是好友关系

	public String getChildAdminType() {
		return childAdminType;
	}

	public void setChildAdminType(String childAdminType) {
		this.childAdminType = childAdminType;
	}

	public Integer getCoopType() {
		return coopType;
	}

	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}

	public Double getEconomic() {
		return economic;
	}

	public void setEconomic(Double economic) {
		this.economic = economic;
	}

	public String getEntID() {
		return entID;
	}

	public void setEntID(String entID) {
		this.entID = entID;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String gethdpic() {
		return hdpic;
	}

	public void sethdpic(String hDpic) {
		this.hdpic = hDpic;
	}

	public Integer getHeInviteState() {
		return heInviteState;
	}

	public void setHeInviteState(Integer heInviteState) {
		this.heInviteState = heInviteState;
	}

	public String getImGUID() {
		return imGUID;
	}

	public void setImGUID(String imGUID) {
		this.imGUID = imGUID;
	}

	public boolean getIsReg() {
		return isReg;
	}

	public void setIsReg(boolean isReg) {
		this.isReg = isReg;
	}

	public boolean getIsUsual() {
		return isUsual;
	}

	public void setIsUsual(boolean isUsual) {
		this.isUsual = isUsual;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getMyInviteState() {
		return myInviteState;
	}

	public void setMyInviteState(Integer myInviteState) {
		this.myInviteState = myInviteState;
	}

	public String getNameRemark() {
		return nameRemark;
	}

	public void setNameRemark(String nameRemark) {
		this.nameRemark = nameRemark;
	}

	public String getNiChen() {
		return niChen;
	}

	public void setNiChen(String niChen) {
		this.niChen = niChen;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getLogitalkId() {
		return logitalkId;
	}

	public void setLogitalkId(String logitalkId) {
		this.logitalkId = logitalkId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Boolean getIsImFriend() {
		return isImFriend;
	}

	public void setIsImFriend(Boolean isImFriend) {
		this.isImFriend = isImFriend;
	}

	
}
