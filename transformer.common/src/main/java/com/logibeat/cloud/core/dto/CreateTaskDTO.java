package com.logibeat.cloud.core.dto;

import java.sql.Timestamp;

public class CreateTaskDTO {
	
	/**
	 * 企业ID
	 */
	private String entId;
	
	/**
	 * 订单ID
	 */
	private String orderCid;
	
	/**
	 * 订单原始ID
	 */
    private String originalcid;
	
    /**
     * 订单父ID
     */
	private String parentOrdersCid;
	
	/**
	 * 预计出发时间
	 */
	private Timestamp startAreaPlanLeavTime;
	
	/**
	 * 预计到达时间
	 */
	private Timestamp endAreaPlanArriveTime;

	private Timestamp startAreaActualLeaveTime;

	private Timestamp endAreaActualArriveTime;


	/**
	 * 订单备注
	 */
	private String ordersRemark;
	
	/**
	 * 时效（老版，现在仍然预留，兼容老数据）
	 */
	private Integer statue;
	
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	
	/**
	 * 订单委托状态
	 */
	private Boolean entrustState;
	
	/**
	 * 是否车辆订单
	 */
	private Byte isCarOrders;
	
	/**
	 * 起始网点ID
	 */
	private String startAreaGUID;
	
	/**
	 *结束网点ID
	 */
	private String endAreaGUID;
	
	/**
	 * 新版时效
	 */
	private Integer duration;
	
	/**
	 * 预计里程
	 */
	private Integer expectsMileage;

	/**
	 * 起始网点名称
	 */
	private String startAreaName;
	
	/**
	 * 起始网点编号
	 */
	private String startAreaCode;
	
	/**
	 * 结束网点名称
	 */
	private String endAreaName;
	
	/**
	 * 结束网点编号
	 */
	private String endAreaCode;


	/**
	 * 线路ID
	 */
	private String lineId;


	private String carId;

	private String plateNumber;

	private String startRegionCode;

	private String endRegionCode;

	private String firstPersonId;

	private String firstPersonName;

	private String firstPersonMobile;

	private String secondPersonId;

	private String secondPersonName;

	private String secondPersonMobile;


	private Timestamp createTime;

	private Integer taskStatus;

	private String OwnOrderId;

	private String ownType;


	private boolean isCarrier;


	private Integer orderType;


	private Byte isFirstTask;

	private Byte isEndTask;


	private String createPersonInfo;



	/**
	 * 承运企业
	 */
	private String carrierEntId;

	/**
	 * 承运信息
	 */
	private String carrierInfo;



	/**
	 * 委托时间
	 */
	private Timestamp entrustTime;

	/**
	 * 委托人
	 */
	private String entrustPersonId;

	/**
	 * 委托企业
	 */
	private String entrustEntId;

	/**
	 * 委托详细信息
	 */
	private String entrustInfo;



	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getOrderCid() {
		return orderCid;
	}

	public void setOrderCid(String orderCid) {
		this.orderCid = orderCid;
	}

	public String getOriginalcid() {
		return originalcid;
	}

	public void setOriginalcid(String originalcid) {
		this.originalcid = originalcid;
	}

	public String getParentOrdersCid() {
		return parentOrdersCid;
	}

	public void setParentOrdersCid(String parentOrdersCid) {
		this.parentOrdersCid = parentOrdersCid;
	}

	public Timestamp getStartAreaPlanLeavTime() {
		return startAreaPlanLeavTime;
	}

	public void setStartAreaPlanLeavTime(Timestamp startAreaPlanLeavTime) {
		this.startAreaPlanLeavTime = startAreaPlanLeavTime;
	}

	public Timestamp getEndAreaPlanArriveTime() {
		return endAreaPlanArriveTime;
	}

	public void setEndAreaPlanArriveTime(Timestamp endAreaPlanArriveTime) {
		this.endAreaPlanArriveTime = endAreaPlanArriveTime;
	}

	public String getOrdersRemark() {
		return ordersRemark;
	}

	public void setOrdersRemark(String ordersRemark) {
		this.ordersRemark = ordersRemark;
	}

	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Boolean getEntrustState() {
		return entrustState;
	}

	public void setEntrustState(Boolean entrustState) {
		this.entrustState = entrustState;
	}

	public Byte getIsCarOrders() {
		return isCarOrders;
	}

	public void setIsCarOrders(Byte isCarOrders) {
		this.isCarOrders = isCarOrders;
	}

	public String getStartAreaGUID() {
		return startAreaGUID;
	}

	public void setStartAreaGUID(String startAreaGUID) {
		this.startAreaGUID = startAreaGUID;
	}

	public String getEndAreaGUID() {
		return endAreaGUID;
	}

	public void setEndAreaGUID(String endAreaGUID) {
		this.endAreaGUID = endAreaGUID;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getExpectsMileage() {
		return expectsMileage;
	}

	public void setExpectsMileage(Integer expectsMileage) {
		this.expectsMileage = expectsMileage;
	}

	public String getStartAreaName() {
		return startAreaName;
	}

	public void setStartAreaName(String startAreaName) {
		this.startAreaName = startAreaName;
	}

	public String getStartAreaCode() {
		return startAreaCode;
	}

	public void setStartAreaCode(String startAreaCode) {
		this.startAreaCode = startAreaCode;
	}

	public String getEndAreaName() {
		return endAreaName;
	}

	public void setEndAreaName(String endAreaName) {
		this.endAreaName = endAreaName;
	}

	public String getEndAreaCode() {
		return endAreaCode;
	}

	public void setEndAreaCode(String endAreaCode) {
		this.endAreaCode = endAreaCode;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
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

	public String getStartRegionCode() {
		return startRegionCode;
	}

	public void setStartRegionCode(String startRegionCode) {
		this.startRegionCode = startRegionCode;
	}

	public String getEndRegionCode() {
		return endRegionCode;
	}

	public void setEndRegionCode(String endRegionCode) {
		this.endRegionCode = endRegionCode;
	}

	public String getFirstPersonId() {
		return firstPersonId;
	}

	public void setFirstPersonId(String firstPersonId) {
		this.firstPersonId = firstPersonId;
	}

	public String getFirstPersonName() {
		return firstPersonName;
	}

	public void setFirstPersonName(String firstPersonName) {
		this.firstPersonName = firstPersonName;
	}

	public String getFirstPersonMobile() {
		return firstPersonMobile;
	}

	public void setFirstPersonMobile(String firstPersonMobile) {
		this.firstPersonMobile = firstPersonMobile;
	}

	public String getSecondPersonId() {
		return secondPersonId;
	}

	public void setSecondPersonId(String secondPersonId) {
		this.secondPersonId = secondPersonId;
	}

	public String getSecondPersonName() {
		return secondPersonName;
	}

	public void setSecondPersonName(String secondPersonName) {
		this.secondPersonName = secondPersonName;
	}

	public String getSecondPersonMobile() {
		return secondPersonMobile;
	}

	public void setSecondPersonMobile(String secondPersonMobile) {
		this.secondPersonMobile = secondPersonMobile;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getOwnOrderId() {
		return OwnOrderId;
	}

	public void setOwnOrderId(String ownOrderId) {
		OwnOrderId = ownOrderId;
	}

	public String getOwnType() {
		return ownType;
	}

	public void setOwnType(String ownType) {
		this.ownType = ownType;
	}

	public boolean isCarrier() {
		return isCarrier;
	}

	public void setCarrier(boolean carrier) {
		isCarrier = carrier;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Timestamp getStartAreaActualLeaveTime() {
		return startAreaActualLeaveTime;
	}

	public void setStartAreaActualLeaveTime(Timestamp startAreaActualLeaveTime) {
		this.startAreaActualLeaveTime = startAreaActualLeaveTime;
	}

	public Timestamp getEndAreaActualArriveTime() {
		return endAreaActualArriveTime;
	}

	public void setEndAreaActualArriveTime(Timestamp endAreaActualArriveTime) {
		this.endAreaActualArriveTime = endAreaActualArriveTime;
	}

	public Byte getIsFirstTask() {
		return isFirstTask;
	}

	public void setIsFirstTask(Byte isFirstTask) {
		this.isFirstTask = isFirstTask;
	}

	public Byte getIsEndTask() {
		return isEndTask;
	}

	public void setIsEndTask(Byte isEndTask) {
		this.isEndTask = isEndTask;
	}

	public String getCreatePersonInfo() {
		return createPersonInfo;
	}

	public void setCreatePersonInfo(String createPersonInfo) {
		this.createPersonInfo = createPersonInfo;
	}


	public String getCarrierEntId() {
		return carrierEntId;
	}

	public void setCarrierEntId(String carrierEntId) {
		this.carrierEntId = carrierEntId;
	}

	public String getCarrierInfo() {
		return carrierInfo;
	}

	public void setCarrierInfo(String carrierInfo) {
		this.carrierInfo = carrierInfo;
	}

	public Timestamp getEntrustTime() {
		return entrustTime;
	}

	public void setEntrustTime(Timestamp entrustTime) {
		this.entrustTime = entrustTime;
	}

	public String getEntrustPersonId() {
		return entrustPersonId;
	}

	public void setEntrustPersonId(String entrustPersonId) {
		this.entrustPersonId = entrustPersonId;
	}

	public String getEntrustEntId() {
		return entrustEntId;
	}

	public void setEntrustEntId(String entrustEntId) {
		this.entrustEntId = entrustEntId;
	}

	public String getEntrustInfo() {
		return entrustInfo;
	}

	public void setEntrustInfo(String entrustInfo) {
		this.entrustInfo = entrustInfo;
	}
}
