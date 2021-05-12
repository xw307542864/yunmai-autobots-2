package com.logibeat.cloud.vo;


import com.logibeat.cloud.common.vo.CarShareVo;
import com.logibeat.cloud.common.vo.PersonOrganizationVo;

import java.sql.Timestamp;

public class CarInfoVo {

	private String carID;// 车辆主键ID
	private String groupId;
	private String carLengthDictGUID;// 车厢长度：单位米
	// private String carLengthDictName;
	private String coachTypeDictGUID;// 车型 (编码)
	// private String coachTypeDictName;//车型 （名称）
	private String entID;// 企业主键ID
	private String entName;// 企业名称
	private Boolean isStarMark;// 是否星标车辆
	private String logo;// 车辆图像
	private String plateNumber;// 车牌号码
	private Double ratedLoad;// 额定载重 吨
	private Double ratedVolume;// 额定体积 立方米
	private Integer drivingAuditStatus;// 车辆认证状态
	private Integer carState;// 车辆状态 0:全部 1:空闲 2:待发 3:在途
	private Long cooperationNum;// 合作次数
	private Integer coopType;// 关系 0:员工 1:司机 2:熟车 3:合作企业
	private Integer coopSatatus; // 合作状态 1:正在合作 2：司机未注册 3：司机待确认 4：已终止合作
	private Long currentTaskNum;// 当前任务
	private String firstDriverPersonID;// 主驾ID
	private String firstDriverPhoto;// 主驾头像地址
	private String firstDriverPersonName;// 主驾姓名
	private String firstDriverPersonPhoneNumber;// 主驾手机
	private String secondDriverPersonID;// 副驾ID
	private String secondDriverPhoto;// 副驾头像地址
	private String secondDriverPersonName;// 副驾姓名
	private String secondDriverPersonPhoneNumber;// 副驾手机
	private String guid;// 关系GUID
	private Integer inviteState;// 邀请状态（枚举，当车辆为关联车时使用） (InviteState)
	private String groupGUID;// 分组管理GUID
	private String groupName;// 车辆分组名称
	private Boolean isdelete;
	private Timestamp editTime;
	private String logitalkId;
	private String secondLogitalkId;
	private String displayName;

	private String imGuid;// 环信id
	private boolean sendMessageBtn;// 发消息
	private boolean sendTaskBtn;// 发任务
	private Integer srcSoid; // 车辆服务id，唯一值
	// 用于车辆
	private CarGpsInfoVo carGpsInfo;

	private Integer carAuditStatus;
	private Integer auditStatus;
	private String auditStatusTitle;

	private String niChen;
	private String loginName;
	private String PersonID;
	private String carLengthValue;

	// 共享车辆
	private Integer shareNum;// 分享的数量
	private String orgName;// 只显示架构的名称
	private String toEntId;// 被共享的企业的id
	private String toEntName;// 被共享的企业的名称
	private String endTime;// 被共享的企业的时间

	private PersonOrganizationVo carOrganizationVo; // 车辆组织信息

	private CarShareVo carShareVo;

	private String pinYin;

	private String usualCity;// 常跑城市
	private Integer star;// 企业星级
	private String directName; // 方向名称
	private String parkingTime; // 停车时长
	private Double directDegree; // 方向的度数
	
	private boolean isDefaultLogo;//是否默认车辆图像
	
	
	public String getSecondLogitalkId() {
		return secondLogitalkId;
	}

	public void setSecondLogitalkId(String secondLogitalkId) {
		this.secondLogitalkId = secondLogitalkId;
	}

	public Double getDirectDegree() {
		return directDegree;
	}

	public void setDirectDegree(Double directDegree) {
		this.directDegree = directDegree;
	}

	public String getDirectName() {
		return directName;
	}

	public void setDirectName(String directName) {
		this.directName = directName;
	}

	public String getParkingTime() {
		return parkingTime;
	}

	public void setParkingTime(String parkingTime) {
		this.parkingTime = parkingTime;
	}

	private String entShareLogo;//企业logo

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getPersonID() {
		return PersonID;
	}

	public void setPersonID(String personID) {
		PersonID = personID;
	}

	public CarShareVo getCarShareVo() {
		return carShareVo;
	}

	public void setCarShareVo(CarShareVo carShareVo) {
		this.carShareVo = carShareVo;
	}

	public Integer getCarAuditStatus() {
		return carAuditStatus;
	}

	public void setCarAuditStatus(Integer carAuditStatus) {
		this.carAuditStatus = carAuditStatus;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditStatusTitle() {
		return auditStatusTitle;
	}

	public void setAuditStatusTitle(String auditStatusTitle) {
		this.auditStatusTitle = auditStatusTitle;
	}

	public String getNiChen() {
		return niChen;
	}

	public void setNiChen(String niChen) {
		this.niChen = niChen;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Boolean getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}

	public String getCarID() {
		return carID;
	}

	public void setCarID(String carID) {
		this.carID = carID;
	}

	public String getCarLengthDictGUID() {
		return carLengthDictGUID;
	}

	public void setCarLengthDictGUID(String carLengthDictGUID) {
		this.carLengthDictGUID = carLengthDictGUID;
	}

	public String getCoachTypeDictGUID() {
		return coachTypeDictGUID;
	}

	public void setCoachTypeDictGUID(String coachTypeDictGUID) {
		this.coachTypeDictGUID = coachTypeDictGUID;
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

	public Boolean getIsStarMark() {
		return isStarMark;
	}

	public void setIsStarMark(Boolean isStarMark) {
		this.isStarMark = isStarMark;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Double getRatedLoad() {
		return ratedLoad;
	}

	public void setRatedLoad(Double ratedLoad) {
		this.ratedLoad = ratedLoad;
	}

	public Double getRatedVolume() {
		return ratedVolume;
	}

	public void setRatedVolume(Double ratedVolume) {
		this.ratedVolume = ratedVolume;
	}

	public Integer getDrivingAuditStatus() {
		return drivingAuditStatus;
	}

	public void setDrivingAuditStatus(Integer drivingAuditStatus) {
		this.drivingAuditStatus = drivingAuditStatus;
	}

	public Integer getCarState() {
		return carState;
	}

	public void setCarState(Integer carState) {
		this.carState = carState;
	}

	public Integer getSrcSoid() {
		return srcSoid;
	}

	public void setSrcSoid(Integer srcSoid) {
		this.srcSoid = srcSoid;
	}

	public Long getCooperationNum() {
		return cooperationNum;
	}

	public void setCooperationNum(Long cooperationNum) {
		this.cooperationNum = cooperationNum;
	}

	public Integer getCoopType() {
		return coopType;
	}

	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}

	public Integer getCoopSatatus() {
		return coopSatatus;
	}

	public void setCoopSatatus(Integer coopSatatus) {
		this.coopSatatus = coopSatatus;
	}

	public Long getCurrentTaskNum() {
		return currentTaskNum;
	}

	public void setCurrentTaskNum(Long currentTaskNum) {
		this.currentTaskNum = currentTaskNum;
	}

	public String getFirstDriverPersonID() {
		return firstDriverPersonID;
	}

	public void setFirstDriverPersonID(String firstDriverPersonID) {
		this.firstDriverPersonID = firstDriverPersonID;
	}

	public String getFirstDriverPhoto() {
		return firstDriverPhoto;
	}

	public void setFirstDriverPhoto(String firstDriverPhoto) {
		this.firstDriverPhoto = firstDriverPhoto;
	}

	public String getFirstDriverPersonName() {
		return firstDriverPersonName;
	}

	public void setFirstDriverPersonName(String firstDriverPersonName) {
		this.firstDriverPersonName = firstDriverPersonName;
	}

	public String getFirstDriverPersonPhoneNumber() {
		return firstDriverPersonPhoneNumber;
	}

	public void setFirstDriverPersonPhoneNumber(String firstDriverPersonPhoneNumber) {
		this.firstDriverPersonPhoneNumber = firstDriverPersonPhoneNumber;
	}

	public String getSecondDriverPersonID() {
		return secondDriverPersonID;
	}

	public void setSecondDriverPersonID(String secondDriverPersonID) {
		this.secondDriverPersonID = secondDriverPersonID;
	}

	public String getSecondDriverPhoto() {
		return secondDriverPhoto;
	}

	public void setSecondDriverPhoto(String secondDriverPhoto) {
		this.secondDriverPhoto = secondDriverPhoto;
	}

	public String getSecondDriverPersonName() {
		return secondDriverPersonName;
	}

	public void setSecondDriverPersonName(String secondDriverPersonName) {
		this.secondDriverPersonName = secondDriverPersonName;
	}

	public String getSecondDriverPersonPhoneNumber() {
		return secondDriverPersonPhoneNumber;
	}

	public void setSecondDriverPersonPhoneNumber(String secondDriverPersonPhoneNumber) {
		this.secondDriverPersonPhoneNumber = secondDriverPersonPhoneNumber;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Integer getInviteState() {
		return inviteState;
	}

	public void setInviteState(Integer inviteState) {
		this.inviteState = inviteState;
	}

	public String getGroupGUID() {
		return groupGUID;
	}

	public void setGroupGUID(String groupGUID) {
		this.groupGUID = groupGUID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public CarGpsInfoVo getCarGpsInfo() {
		return carGpsInfo;
	}

	public void setCarGpsInfo(CarGpsInfoVo carGpsInfo) {
		this.carGpsInfo = carGpsInfo;
	}

	public Timestamp getEditTime() {
		return editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

	public boolean isSendMessageBtn() {
		return sendMessageBtn;
	}

	public void setSendMessageBtn(boolean sendMessageBtn) {
		this.sendMessageBtn = sendMessageBtn;
	}

	public boolean isSendTaskBtn() {
		return sendTaskBtn;
	}

	public void setSendTaskBtn(boolean sendTaskBtn) {
		this.sendTaskBtn = sendTaskBtn;
	}

	public String getImGuid() {
		return imGuid;
	}

	public void setImGuid(String imGuid) {
		this.imGuid = imGuid;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Boolean getStarMark() {
		return isStarMark;
	}

	public void setStarMark(Boolean starMark) {
		isStarMark = starMark;
	}

	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getToEntId() {
		return toEntId;
	}

	public void setToEntId(String toEntId) {
		this.toEntId = toEntId;
	}

	public String getToEntName() {
		return toEntName;
	}

	public void setToEntName(String toEntName) {
		this.toEntName = toEntName;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public PersonOrganizationVo getCarOrganizationVo() {
		return carOrganizationVo;
	}

	public void setCarOrganizationVo(PersonOrganizationVo carOrganizationVo) {
		this.carOrganizationVo = carOrganizationVo;
	}

	public String getCarLengthValue() {
		return carLengthValue;
	}

	public void setCarLengthValue(String carLengthValue) {
		this.carLengthValue = carLengthValue;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String getUsualCity() {
		return usualCity;
	}

	public void setUsualCity(String usualCity) {
		this.usualCity = usualCity;
	}

	public String getEntShareLogo() {
		return entShareLogo;
	}

	public void setEntShareLogo(String entShareLogo) {
		this.entShareLogo = entShareLogo;
	}

	public boolean getIsDefaultLogo() {
		return isDefaultLogo;
	}

	public void setIsDefaultLogo(boolean isDefaultLogo) {
		this.isDefaultLogo = isDefaultLogo;
	}
 
    
}
