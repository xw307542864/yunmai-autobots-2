package com.logibeat.cloud.common.vo;

public class NewFriendEntInfoVo {
	/**
	 * 企业地址 
	 */
	private String Address;
	
	/**
	 * 企业编号 
	 */
	private String Code;
	
	/**
	 * 企业认证状态 
	 */
	private Integer EntAuditStatus;
	
	/**
	 * 企业类型
	 */
	private String EntTypeDictName;
	
	/**
	 * 邀请类型（枚举） 
	 */
	private Integer inviteType;
	
	/**
	 * 邀请状态（枚举） 
	 */
	private Integer inviteState;
	
	/**
	 * 企业logo图片地址 
	 */
	private String Logo;
	
	/**
	 * 验证信息（留言） 
	 */
	private String Message;
	
	/**
	 * 联系人手机号 
	 */
	private String Mobile;
	
	/**
	 * 企业名称 
	 */
	private String Name;
	
	/**
	 * 是否好友
	 */
	private boolean isFriend;
	
	
	private boolean isShareGps;
	
	/**
	 * 环信
	 */
	private String ImGUID;   
	
	
	
	/**
	 * 新联系人GUID 
	 */
	private String NewFriendGUID;
	
	/**
	 * 联系人名字 
	 */
	private String NiChen;
	
	/**
	 * 联系人ID 
	 */
	private String PersonID;
	
	/**
	 * 企业简介
	 */
	private String Profile;
	
	/**
	 * IM ID
	 */
	private String logitalkId;
	
	/**
	 * IM 名称
	 */
    private String displayName;

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

	public Integer getEntAuditStatus() {
		return EntAuditStatus;
	}

	public void setEntAuditStatus(Integer entAuditStatus) {
		EntAuditStatus = entAuditStatus;
	}

	 

	public String getEntTypeDictName() {
		return EntTypeDictName;
	}

	public void setEntTypeDictName(String entTypeDictName) {
		EntTypeDictName = entTypeDictName;
	}

	public Integer getInviteType() {
		return inviteType;
	}

	public void setInviteType(Integer inviteType) {
		this.inviteType = inviteType;
	}

	public Integer getInviteState() {
		return inviteState;
	}

	public void setInviteState(Integer inviteState) {
		this.inviteState = inviteState;
	}

	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getNewFriendGUID() {
		return NewFriendGUID;
	}

	public void setNewFriendGUID(String newFriendGUID) {
		NewFriendGUID = newFriendGUID;
	}
	
	
	

	public boolean getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
	
	public boolean getIsShareGps() {
		return isShareGps;
	}

	public void setIsShareGps(boolean isShareGps) {
		this.isShareGps = isShareGps;
	}

	public String getImGUID() {
		return ImGUID;
	}

	public void setImGUID(String imGUID) {
		ImGUID = imGUID;
	}

	public String getNiChen() {
		return NiChen;
	}

	public void setNiChen(String niChen) {
		NiChen = niChen;
	}

	public String getPersonID() {
		return PersonID;
	}

	public void setPersonID(String personID) {
		PersonID = personID;
	}

	public String getProfile() {
		return Profile;
	}

	public void setProfile(String profile) {
		Profile = profile;
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
	
	
	
}
