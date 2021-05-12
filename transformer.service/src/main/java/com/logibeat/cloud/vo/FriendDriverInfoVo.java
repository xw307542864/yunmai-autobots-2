package com.logibeat.cloud.vo;

public class FriendDriverInfoVo {
	private CarShortInfoVo Car;             // 车辆信息  
	private int ChildAdminType;           // 企业子账号人员类型（岗位）（枚举）  (ChildAdminType)
	private int CoopType;                 // 合作关系  (CoopType)
	private int DriverAuditStatus;        // 司机认证状态  (CheckStatus)
	private String HDpic;                 // 头像  
	private int HeInviteState;            // 邀请状态（枚举 对方状态）  (InviteState)
	private String ImGUID;                // 环信会话ID  
	private boolean IsDriver;             // 是否司机  
	private boolean IsReg;                // 是否已注册  
	private boolean IsUsual;              // 是否常用联系人  
	private String JobName;               // 岗位名称 
	private String Mobile;                // 手机号  
	private int MyInviteState;            // 邀请状态（枚举 我方状态）  (InviteState)
	private String NameRemark;            // 名称备注  
	private String NiChen;                // 昵称  
	private String PersonID;
	public CarShortInfoVo getCar() {
		return Car;
	}
	public void setCar(CarShortInfoVo car) {
		Car = car;
	}
	public int getChildAdminType() {
		return ChildAdminType;
	}
	public void setChildAdminType(int childAdminType) {
		ChildAdminType = childAdminType;
	}
	public int getCoopType() {
		return CoopType;
	}
	public void setCoopType(int coopType) {
		CoopType = coopType;
	}
	public int getDriverAuditStatus() {
		return DriverAuditStatus;
	}
	public void setDriverAuditStatus(int driverAuditStatus) {
		DriverAuditStatus = driverAuditStatus;
	}
	public String getHDpic() {
		return HDpic;
	}
	public void setHDpic(String hDpic) {
		HDpic = hDpic;
	}
	public int getHeInviteState() {
		return HeInviteState;
	}
	public void setHeInviteState(int heInviteState) {
		HeInviteState = heInviteState;
	}
	public String getImGUID() {
		return ImGUID;
	}
	public void setImGUID(String imGUID) {
		ImGUID = imGUID;
	}
	public boolean isIsDriver() {
		return IsDriver;
	}
	public void setIsDriver(boolean isDriver) {
		IsDriver = isDriver;
	}
	public boolean isIsReg() {
		return IsReg;
	}
	public void setIsReg(boolean isReg) {
		IsReg = isReg;
	}
	public boolean isIsUsual() {
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
	public int getMyInviteState() {
		return MyInviteState;
	}
	public void setMyInviteState(int myInviteState) {
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
	
	
	
}
