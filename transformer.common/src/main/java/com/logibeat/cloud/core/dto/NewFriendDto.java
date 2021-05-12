package com.logibeat.cloud.core.dto;


import com.logibeat.cloud.util.tools.pageMdl.BaseDTO;

public class NewFriendDto extends BaseDTO {

	/**
	 * 新联系人ID
	 */
	private String NewFriendGUID;
	
	/**
	 * 邀请状态
	 */
	private Integer InviteState;
	
	/**
	 * 留言
	 */
	private String Message;
	
	/**
	 * 是否共享
	 */
	private boolean IsShareGps;


	/**
	 * 车辆ID
	 */
	private String carId;

	/**
	 * 车牌号
	 */
	private String plateNumber;
	/**
	 * 伙伴企业id
	 */
	private String  friendObjectId;

	public String getNewFriendGUID() {
		return NewFriendGUID;
	}

	public void setNewFriendGUID(String newFriendGUID) {
		NewFriendGUID = newFriendGUID;
	}

	public Integer getInviteState() {
		return InviteState;
	}

	public void setInviteState(Integer inviteState) {
		InviteState = inviteState;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public boolean getIsShareGps() {
		return IsShareGps;
	}

	public void setIsShareGps(Boolean isShareGps) {
		IsShareGps = isShareGps;
	}


	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getFriendObjectId() {
		return friendObjectId;
	}

	public void setFriendObjectId(String friendObjectId) {
		this.friendObjectId = friendObjectId;
	}
	
}
