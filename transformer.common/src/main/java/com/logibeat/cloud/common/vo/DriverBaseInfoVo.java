package com.logibeat.cloud.common.vo;

public class DriverBaseInfoVo {
	
	/**
	 * 图片地址	
	 */
	private String hdpic;
	
	/**
	 * 是否已注册 
	 */
	private Boolean isReg;
	
	/**
	 * 手机号 
	 */
	private String mobile;
	
	/**
	 * 会员ID
	 */
	private String personID;
	
	/**
	 * 昵称
	 */
	private String niChen;
	
	/**
	 * 通讯Id
	 */
	private String imGUID;
	
	/**
	 * imGUID
	 */
    private String logitalkId;
    
    /**
	 * IM 名称
	 */
    private String displayName;
    
    /**
	 * 是否是好友关系
	 */
    private Boolean isImFriend = false;
	
	public String getHdpic() {
		return hdpic;
	}
	public void setHdpic(String hdpic) {
		this.hdpic = hdpic;
	}
	public Boolean getIsReg() {
		return isReg;
	}
	public void setIsReg(Boolean isReg) {
		this.isReg = isReg;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String getNiChen() {
		return niChen;
	}
	public void setNiChen(String niChen) {
		this.niChen = niChen;
	}
	public String getImGUID() {
		return imGUID;
	}
	public void setImGUID(String imGUID) {
		this.imGUID = imGUID;
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
