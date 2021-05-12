package com.logibeat.cloud.vo;

/**
 * 
 * @Title: Driver_NewFriendDriverInfoVo
 * @Description:
 * @Company: 运脉科技
 * @author wilson
 * @date 2015年12月17日
 */
public class NewFriendDriverInfoVo {

	/**
	 * 车辆信息
	 */
	private CarShortInfoVo Car;

	/**
	 * 环信ID
	 */
	private String ImGUID;

	/**
	 * 司机认证状态
	 */
	private Integer DriverAuditStatus;

	/**
	 * 累计行驶里程（公里）
	 */
	private Double DrivingRange;

	/**
	 * 头像
	 */
	private String HDpic;

	/**
	 * 邀请类型（枚举）
	 */
	private Integer inviteType;

	/**
	 * 邀请状态（枚举）
	 */
	private Integer inviteState;

	/**
	 * 是否
	 */
	private boolean IsFriend;

	/**
	 * 留言
	 */
	private String message;

	/**
	 * Mobile
	 */
	private String Mobile;

	/**
	 * 新联系人GUID
	 */
	private String NewFriendGUID;

	/**
	 * 昵称
	 */
	private String NiChen;

	/**
	 * 会员ID
	 */
	private String PersonID;

	/**
	 * 星级
	 */
	private Double Star;

	/**
	 * 时效
	 */
	private Double Aging;

	/**
	 * 经济（实惠）
	 */
	private Double Economic;

	/**
	 * 安全
	 */
	private Double Security;

	/**
	 * IM ID
	 */
	private String logitalkId;

	/**
	 * IM 名称
	 */
	private String displayName;

	public CarShortInfoVo getCar() {
		return Car;
	}

	public void setCar(CarShortInfoVo car) {
		Car = car;
	}

	public String getImGUID() {
		return ImGUID;
	}

	public void setImGUID(String imGUID) {
		ImGUID = imGUID;
	}

	public Integer getDriverAuditStatus() {
		return DriverAuditStatus;
	}

	public void setDriverAuditStatus(Integer driverAuditStatus) {
		DriverAuditStatus = driverAuditStatus;
	}

	public Double getDrivingRange() {
		return DrivingRange;
	}

	public void setDrivingRange(Double drivingRange) {
		DrivingRange = drivingRange;
	}

	public String getHDpic() {
		return HDpic;
	}

	public void setHDpic(String hDpic) {
		HDpic = hDpic;
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

	public boolean isIsFriend() {
		return IsFriend;
	}

	public void setIsFriend(boolean isFriend) {
		IsFriend = isFriend;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getNewFriendGUID() {
		return NewFriendGUID;
	}

	public void setNewFriendGUID(String newFriendGUID) {
		NewFriendGUID = newFriendGUID;
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

	public Double getStar() {
		return Star;
	}

	public void setStar(Double star) {
		Star = star;
	}

	public Double getAging() {
		return Aging;
	}

	public void setAging(Double aging) {
		Aging = aging;
	}

	public Double getEconomic() {
		return Economic;
	}

	public void setEconomic(Double economic) {
		Economic = economic;
	}

	public Double getSecurity() {
		return Security;
	}

	public void setSecurity(Double security) {
		Security = security;
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
