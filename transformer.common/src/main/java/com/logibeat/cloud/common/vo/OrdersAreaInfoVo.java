package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;

public class OrdersAreaInfoVo {
	
	/**
	 * 实际到达时间（null 表示还未到达） 
	 */
	private Timestamp ActualArriveTime;
	
	/**
	 * 实际发车时间（Null表示还未发车） 
	 */
	private Timestamp ActualLeavTime;
	
	/**
	 * 网点名称 
	 */
	private String AreaName;
	
	/**
	 * 纬度  
	 */
	private double Lat; 				 
	
	
	/**
	 * 经度
	 */
	private double Lng; 				 
	
	
	/**
	 * 网点联系人详细地址 
	 */
	private String ContactAddress;
	
	/**
	 * 网点联系人姓名 
	 */
	private String ContactName;
	
	/**
	 * 网点联系人电话 
	 */
	private String ContactPhone;
	
	/**
	 * 预计到达时间 
	 */
	private Timestamp EstimateArriveTime;
	
	/**
	 * 网点GUID 
	 */
	private String GUID;
	
	/**
	 * 计划到达时间 
	 */
	private Timestamp PlanArriveTime;
	
	/**
	 * 计划发车时间 
	 */
	private Timestamp PlanLeavTime;
	
	/**
	 * 图元GUID 
	 */
	private String PointLineGUID;
	
	/**
	 * 省市县编码 
	 */
	private Integer RegionCode;
	
	/**
	 * 剩余里程：单位公里 
	 */
	private String RemainderRange;
	
	/**
	 * 网点顺序（从0开始） 
	 */
	private Integer SortNum;

	/**
	 * 出发点
	 */
	private String startAreaName;
	
	/**
	 * 目的地
	 */
	private String endAreaName;
	
	/**
	 * 下一站地名
	 */
	private String nextStation;
	
	/**
	 * 途经点地名
	 */
	private String transitPoint;

	/** 是否发车点 */
	private Boolean isStartPoint;
	/** 是否终点 */
	private Boolean isEndPoint;
	
	public Timestamp getActualArriveTime() {
		return ActualArriveTime;
	}

	public void setActualArriveTime(Timestamp actualArriveTime) {
		ActualArriveTime = actualArriveTime;
	}

	public Timestamp getActualLeavTime() {
		return ActualLeavTime;
	}

	public void setActualLeavTime(Timestamp actualLeavTime) {
		ActualLeavTime = actualLeavTime;
	}

	public String getAreaName() {
		return AreaName;
	}

	public void setAreaName(String areaName) {
		AreaName = areaName;
	}
	

	public double getLat() {
		return Lat;
	}

	public void setLat(double lat) {
		Lat = lat;
	}

	public double getLng() {
		return Lng;
	}

	public void setLng(double lng) {
		Lng = lng;
	}

	public String getContactAddress() {
		return ContactAddress;
	}

	public void setContactAddress(String contactAddress) {
		ContactAddress = contactAddress;
	}

	public String getContactName() {
		return ContactName;
	}

	public void setContactName(String contactName) {
		ContactName = contactName;
	}

	public String getContactPhone() {
		return ContactPhone;
	}

	public void setContactPhone(String contactPhone) {
		ContactPhone = contactPhone;
	}

	public Timestamp getEstimateArriveTime() {
		return EstimateArriveTime;
	}

	public void setEstimateArriveTime(Timestamp estimateArriveTime) {
		EstimateArriveTime = estimateArriveTime;
	}

	public String getGUID() {
		return GUID;
	}

	public void setGUID(String gUID) {
		GUID = gUID;
	}

	public Timestamp getPlanArriveTime() {
		return PlanArriveTime;
	}

	public void setPlanArriveTime(Timestamp planArriveTime) {
		PlanArriveTime = planArriveTime;
	}

	public Timestamp getPlanLeavTime() {
		return PlanLeavTime;
	}

	public void setPlanLeavTime(Timestamp planLeavTime) {
		PlanLeavTime = planLeavTime;
	}

	public String getPointLineGUID() {
		return PointLineGUID;
	}

	public void setPointLineGUID(String pointLineGUID) {
		PointLineGUID = pointLineGUID;
	}

	public Integer getRegionCode() {
		return RegionCode;
	}

	public void setRegionCode(Integer regionCode) {
		RegionCode = regionCode;
	}

	public String getRemainderRange() {
		return RemainderRange;
	}

	public void setRemainderRange(String remainderRange) {
		RemainderRange = remainderRange;
	}

	public Integer getSortNum() {
		return SortNum;
	}

	public void setSortNum(Integer sortNum) {
		SortNum = sortNum;
	}

	public String getStartAreaName() {
		return startAreaName;
	}

	public void setStartAreaName(String startAreaName) {
		this.startAreaName = startAreaName;
	}

	public String getEndAreaName() {
		return endAreaName;
	}

	public void setEndAreaName(String endAreaName) {
		this.endAreaName = endAreaName;
	}

	public String getNextStation() {
		return nextStation;
	}

	public void setNextStation(String nextStation) {
		this.nextStation = nextStation;
	}

	public String getTransitPoint() {
		return transitPoint;
	}

	public void setTransitPoint(String transitPoint) {
		this.transitPoint = transitPoint;
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
}
