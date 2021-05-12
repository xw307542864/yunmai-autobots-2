package com.logibeat.cloud.common.vo;

/**
 * 联系人（企业管理员）
 * 
 * @Title: Driver_ContactInfoVo
 * @Description:
 * @Company: 运脉科技
 * @author wilson
 * @date 2015年12月16日
 */
public class ContactInfoVo {

	private String ID;

	private String Logo;

	private String Name;

	private String Phone;

	private Integer auditStatus;

	private String CustomName;

	private String NiChen;

	private String ImGuid;

	private String logitalkId;

	private String displayName;
	
	private boolean isAutoSendCar;//是否自动确认发车

	private boolean isAutoArrival;//是否自动确认到达

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getCustomName() {
		return CustomName;
	}

	public void setCustomName(String customName) {
		CustomName = customName;
	}

	public String getNiChen() {
		return NiChen;
	}

	public void setNiChen(String niChen) {
		NiChen = niChen;
	}

	public String getImGuid() {
		return ImGuid;
	}

	public void setImGuid(String imGuid) {
		ImGuid = imGuid;
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

	public boolean getIsAutoSendCar() {
		return isAutoSendCar;
	}

	public void setIsAutoSendCar(boolean isAutoSendCar) {
		this.isAutoSendCar = isAutoSendCar;
	}

	public boolean getIsAutoArrival() {
		return isAutoArrival;
	}

	public void setIsAutoArrival(boolean autoArrival) {
		isAutoArrival = autoArrival;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
}
