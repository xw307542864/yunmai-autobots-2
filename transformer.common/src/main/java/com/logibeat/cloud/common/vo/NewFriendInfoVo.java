package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;

/**
 * 
* @ClassName: NewFriendInfoVo 
* @Description: 新朋友Vo 
* @author sean 
* @date 2015年12月14日 下午2:16:27 
* @version 1.0
 */
public class NewFriendInfoVo {
    
    //车牌号
    private String plateNumber;
    //企业或个人ID（个人可以不用赋值）
    private String ObjectID;
    //新联系人GUID
    private String newFriendGUID;
    //新联系人名称
    private String name;
    //手机号
    private String mobile;
    //留言
    private String message;
    //新联系人头像图片地址
    private String logo;
    //是企业还是个人
    private boolean IsFriendEnt;
    //邀请类型（枚举）
    private Integer inviteType;
    //邀请状态（枚举）  
    private Integer inviteState;
    //新联系人的企业或个人ID
    private String friendObjectID;
    //车辆ID
    private String carID;
    //新增时间
    private Timestamp addTime;
    //申请描述
    private String newFriendDescription;
    
    private String carId;
    
    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
    public String getObjectID() {
        return ObjectID;
    }
    public void setObjectID(String ObjectID) {
        this.ObjectID = ObjectID;
    }
    public String getNewFriendGUID() {
        return newFriendGUID;
    }
    public void setNewFriendGUID(String newFriendGUID) {
        this.newFriendGUID = newFriendGUID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public boolean getIsFriendEnt() {
        return IsFriendEnt;
    }
    public void setIsFriendEnt(boolean isFriendEnt) {
        IsFriendEnt = isFriendEnt;
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
    public String getFriendObjectID() {
        return friendObjectID;
    }
    public void setFriendObjectID(String friendObjectID) {
        this.friendObjectID = friendObjectID;
    }
    public String getCarID() {
        return carID;
    }
    public void setCarID(String carID) {
        this.carID = carID;
    }
    public Timestamp getAddTime() {
        return addTime;
    }
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }
	public String getNewFriendDescription() {
		return newFriendDescription;
	}
	public void setNewFriendDescription(String newFriendDescription) {
		this.newFriendDescription = newFriendDescription;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	
	
       
}
