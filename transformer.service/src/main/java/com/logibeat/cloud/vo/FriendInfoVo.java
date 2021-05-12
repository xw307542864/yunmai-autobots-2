package com.logibeat.cloud.vo;

/**
 * 好友信息VO
 * 
 * @Title: Driver_FriendInfoVo
 * @Description:
 * @Company: 运脉科技
 * @author wilson
 * @date 2015年12月15日
 */
public class FriendInfoVo {

	/**
	 * 时效
	 */
	private Double Aging;

	/**
	 * 车辆信息
	 */
	private CarShortInfoVo Car;

	/**
	 * 企业子账号人员类型（岗位）（枚举）
	 */
	private String ChildAdminType;

	/**
	 * 合作关系
	 */
	private Integer CoopType;

	/**
	 * 司机认证状态
	 */
	private Integer DriverAuditStatus;

	/**
	 * 经济（实惠）
	 */
	private Double Economic;

	/**
	 * 平台认证类型
	 */
	private Integer EntAuditStatus;

	/**
	 * 平台ID
	 */
	private String EntID;

	/**
	 * 平台名称
	 */
	private String EntName;

	/**
	 * 图片地址
	 */
	private String HDpic;

	/**
	 * 邀请类型
	 */
	private Integer HeInviteState;

	/**
	 * 通讯Id
	 */
	private String ImGUID;

	/**
	 * 是否司机
	 */
	private boolean IsDriver;

	/**
	 * 是否已注册
	 */
	private boolean IsReg;

	/**
	 * 是否常用联系人
	 */
	private boolean IsUsual;

	/**
	 * 是否好友
	 */
	private boolean IsFriend;

	/**
	 * 岗位名称
	 */
	private String JobName;

	/**
	 * 手机号
	 */
	private String Mobile;

	/**
	 * 邀请状态（枚举 我方状态）
	 */
	private Integer MyInviteState;

	/**
	 * 名称备注
	 */
	private String NameRemark;

	/**
	 * 昵称
	 */
	private String NiChen;

	/**
	 * 会员ID
	 */
	private String PersonID;

	/**
	 * 安全
	 */
	private Double Security;

	private String pinYin;

	/**
	 * im ID
	 */
	private String logitalkId;

	/**
	 * im 名称
	 */
	private String displayName;

	private boolean isImFriend = false;
	
	//组织guid
	private String orgGuid;
	
	//组织名字
	private String orgName;
	
	//是否展示展示组织名字
	private boolean isShowOrgName;

	public Double getAging() {
		return Aging;
	}

	public void setAging(Double aging) {
		Aging = aging;
	}

	public CarShortInfoVo getCar() {
		return Car;
	}

	public void setCar(CarShortInfoVo car) {
		Car = car;
	}

	public String getChildAdminType() {
		return ChildAdminType;
	}

	public void setChildAdminType(String childAdminType) {
		ChildAdminType = childAdminType;
	}

	public Integer getCoopType() {
		return CoopType;
	}

	public void setCoopType(Integer coopType) {
		CoopType = coopType;
	}

	public Integer getDriverAuditStatus() {
		return DriverAuditStatus;
	}

	public void setDriverAuditStatus(Integer driverAuditStatus) {
		DriverAuditStatus = driverAuditStatus;
	}

	public Double getEconomic() {
		return Economic;
	}

	public void setEconomic(Double economic) {
		Economic = economic;
	}

	public Integer getEntAuditStatus() {
		return EntAuditStatus;
	}

	public void setEntAuditStatus(Integer entAuditStatus) {
		EntAuditStatus = entAuditStatus;
	}

	public String getEntID() {
		return EntID;
	}

	public void setEntID(String entID) {
		EntID = entID;
	}

	public String getEntName() {
		return EntName;
	}

	public void setEntName(String entName) {
		EntName = entName;
	}

	public String getHDpic() {
		return HDpic;
	}

	public void setHDpic(String hDpic) {
		HDpic = hDpic;
	}

	public Integer getHeInviteState() {
		return HeInviteState;
	}

	public void setHeInviteState(Integer heInviteState) {
		HeInviteState = heInviteState;
	}

	public String getImGUID() {
		return ImGUID;
	}

	public void setImGUID(String imGUID) {
		ImGUID = imGUID;
	}

	public boolean getIsDriver() {
		return IsDriver;
	}

	public void setIsDriver(boolean isDriver) {
		IsDriver = isDriver;
	}

	public boolean getIsReg() {
		return IsReg;
	}

	public void setIsReg(boolean isReg) {
		IsReg = isReg;
	}

	public boolean getIsFriend() {
		return IsFriend;
	}

	public void setIsFriend(boolean isFriend) {
		IsFriend = isFriend;
	}

	public boolean getIsUsual() {
		return IsUsual;
	}

	public void setIsUsual(boolean isUsual) {
		IsUsual = isUsual;
	}

	public String getJobName() {
		return JobName;
	}

	public void setJobName(String jobName) {
		JobName = jobName;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public Integer getMyInviteState() {
		return MyInviteState;
	}

	public void setMyInviteState(Integer myInviteState) {
		MyInviteState = myInviteState;
	}

	public String getNameRemark() {
		return NameRemark;
	}

	public void setNameRemark(String nameRemark) {
		NameRemark = nameRemark;
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

	public Double getSecurity() {
		return Security;
	}

	public void setSecurity(Double security) {
		Security = security;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
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

	public boolean getIsImFriend() {
		return isImFriend;
	}

	public void setIsImFriend(boolean isImFriend) {
		this.isImFriend = isImFriend;
	}

	public String getOrgGuid() {
		return orgGuid;
	}

	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public boolean getIsShowOrgName() {
		return isShowOrgName;
	}

	public void setIsShowOrgName(boolean isShowOrgName) {
		this.isShowOrgName = isShowOrgName;
	}


}
