package com.logibeat.cloud.common.vo;

import java.util.Date;

public class TaskAreaVo {

	/** 网点guid */
	private String areaGuid;
	/** 网点名称 */
	private String areaName;
	/** 网点城市编码 */
	private Integer regionCode;
    /** 网点联系人详细地址 */
    private String contactAddress;
    /** 网点联系人姓名 */
    private String contactName; 
    /** 网点联系人电话  */
    private String contactPhone;
    /** 经度  */
    private double lng; 
    /** 纬度  */
    private double lat;
    /** 半径 m  */
    private int radius = 500;
    /** 网点顺序（从0开始） */
    private Integer sortNum = -1;
    /** 实际发车时间 */
    private Date actualLeaveTime;
    /** 实际到达时间 */
    private Date actualArriveTime;
    /** 是否发车点 */
    private Boolean isStartPoint;
    /** 是否终点 */
    private Boolean isEndPoint;
    
    /** 确认发车按钮 */
    private Boolean sendCarBtn;
    /** 确认到达按钮 */
    private Boolean arriveBtn;
    /** 确认完成按钮 */
    private Boolean finishBtn;
    /** 在途反馈按钮 */
    private Boolean feedbackBtn;
    
	public String getAreaGuid() {
		return areaGuid;
	}
	public void setAreaGuid(String areaGuid) {
		this.areaGuid = areaGuid;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		if(null != sortNum){
			this.sortNum = sortNum;
		}
	}
	public Date getActualLeaveTime() {
		return actualLeaveTime;
	}
	public void setActualLeaveTime(Date actualLeaveTime) {
		this.actualLeaveTime = actualLeaveTime;
	}
	public Date getActualArriveTime() {
		return actualArriveTime;
	}
	public void setActualArriveTime(Date actualArriveTime) {
		this.actualArriveTime = actualArriveTime;
	}
	public Boolean getSendCarBtn() {
		return sendCarBtn;
	}
	public void setSendCarBtn(Boolean sendCarBtn) {
		this.sendCarBtn = sendCarBtn;
	}
	public Boolean getArriveBtn() {
		return arriveBtn;
	}
	public void setArriveBtn(Boolean arriveBtn) {
		this.arriveBtn = arriveBtn;
	}
	public Boolean getFeedbackBtn() {
		return feedbackBtn;
	}
	public void setFeedbackBtn(Boolean feedbackBtn) {
		this.feedbackBtn = feedbackBtn;
	}
	public Boolean getFinishBtn() {
		return finishBtn;
	}
	public void setFinishBtn(Boolean finishBtn) {
		this.finishBtn = finishBtn;
	}
	public Boolean getIsStartPoint() {
		return isStartPoint;
	}
	public void setIsStartPoint(Boolean isStartPoint) {
		this.isStartPoint = isStartPoint;
	}
	public Boolean getIsEndPoint() {
		return isEndPoint;
	}
	public void setIsEndPoint(Boolean isEndPoint) {
		this.isEndPoint = isEndPoint;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Integer getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(Integer regionCode) {
		this.regionCode = regionCode;
	}

}
