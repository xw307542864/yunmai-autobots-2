package com.logibeat.cloud.vo;

import com.logibeat.cloud.common.vo.DriverEntVo;

import java.util.List;

/**
 * 好友详细VO
 * 
 * @Title: DriverFriendDetailVo
 * @Description:
 * @Company: 运脉科技
 * @author wilson
 * @date 2015年12月14日
 */
public class FriendDetailVo {

	/**
	 * 车辆信息
	 */
	private CarShortInfoVo car;

	/**
	 * 企业子账号人员类型（岗位）（枚举）
	 */
	private Integer childAdminType;

	/**
	 * 合作关系
	 */
	private Integer coopType;

	/**
	 * 司机认证状态
	 */
	private Integer driverAuditStatus;

	/**
	 * 累计行驶里程（公里）
	 */
	private Double drivingRange;

	/**
	 * 头像
	 */
	private String logo;

	/**
	 * 环信
	 */
	private String imGUID;

	/**
	 * 邀请状态（枚举 对方状态）
	 */
	private Integer inviteState;// (1,2,3)

	/**
	 * 是否已注册
	 */
	private boolean isReg;

	/**
	 * 手机号
	 */
	private String mobile;

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

	/**
	 * 新联系人ID
	 */
	private String newFriendId;

	/**
	 * 动态内容
	 */
	private String lastDynamic;

	/**
	 * 动态图片
	 */
	private List<String> picList;

	/**
	 * 发送邀请
	 */
	private Boolean sendInvite = false;

	/**
	 * 发送消息
	 */
	private Boolean sendMessage = false;

	/**
	 * 发送注册
	 */
	private Boolean sendRegist = false;

	/**
	 * 通过验证
	 */
	private Boolean sendAgree = false;

	/**
	 * 拒绝验证
	 */
	private boolean sendRefuse = false;

	/**
	 * 共同所属企业
	 */

	private List<DriverEntVo> entList;

	/**
	 * 邀请备注
	 */
	private String inviteRemark;

	/**
	 * im ID
	 */
	private String logitalkId;

	/**
	 * im 名称
	 */
	private String displayName;

	private boolean isImFriend = false;

	public CarShortInfoVo getCar() {
		return car;
	}

	public void setCar(CarShortInfoVo car) {
		this.car = car;
	}

	public Integer getChildAdminType() {
		return childAdminType;
	}

	public void setChildAdminType(Integer childAdminType) {
		this.childAdminType = childAdminType;
	}

	public Integer getCoopType() {
		return coopType;
	}

	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}

	public Integer getDriverAuditStatus() {
		return driverAuditStatus;
	}

	public void setDriverAuditStatus(Integer driverAuditStatus) {
		this.driverAuditStatus = driverAuditStatus;
	}

	public Double getDrivingRange() {
		return drivingRange;
	}

	public void setDrivingRange(Double drivingRange) {
		this.drivingRange = drivingRange;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getImGUID() {
		return imGUID;
	}

	public void setImGUID(String imGUID) {
		this.imGUID = imGUID;
	}

	public Integer getInviteState() {
		return inviteState;
	}

	public void setInviteState(Integer inviteState) {
		this.inviteState = inviteState;
	}

	public boolean getIsReg() {
		return isReg;
	}

	public void setIsReg(boolean isReg) {
		this.isReg = isReg;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getNewFriendId() {
		return newFriendId;
	}

	public void setNewFriendId(String newFriendId) {
		this.newFriendId = newFriendId;
	}

	public String getLastDynamic() {
		return lastDynamic;
	}

	public void setLastDynamic(String lastDynamic) {
		this.lastDynamic = lastDynamic;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public void setReg(boolean isReg) {
		this.isReg = isReg;
	}

	public Boolean getSendInvite() {
		return sendInvite;
	}

	public void setSendInvite(Boolean sendInvite) {
		this.sendInvite = sendInvite;
	}

	public Boolean getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(Boolean sendMessage) {
		this.sendMessage = sendMessage;
	}

	public Boolean getSendRegist() {
		return sendRegist;
	}

	public void setSendRegist(Boolean sendRegist) {
		this.sendRegist = sendRegist;
	}

	public Boolean getSendAgree() {
		return sendAgree;
	}

	public void setSendAgree(Boolean sendAgree) {
		this.sendAgree = sendAgree;
	}

	public boolean isSendRefuse() {
		return sendRefuse;
	}

	public void setSendRefuse(boolean sendRefuse) {
		this.sendRefuse = sendRefuse;
	}

	public List<DriverEntVo> getEntList() {
		return entList;
	}

	public void setEntList(List<DriverEntVo> entList) {
		this.entList = entList;
	}

	public String getInviteRemark() {
		return inviteRemark;
	}

	public void setInviteRemark(String inviteRemark) {
		this.inviteRemark = inviteRemark;
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

}
