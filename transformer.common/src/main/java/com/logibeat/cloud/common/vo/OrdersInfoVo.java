package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrdersInfoVo {

	/**
	 * 位置 
	 */
	private String Address;
	
	/**
	 * 订单网点信息 
	 */
	private List<OrdersAreaInfoVo> AreaInfoList;
	
	private GoodsInfoVo goodsInfo;
	
	/**
	 * 货物信息：“货物类型+重量”，若用户未输入重量，展现“货物类型+体积” 
	 */
	private String CargoInfo;
	
	/**
	 * 车辆ID（仅委托单时使用） 
	 */
	private String CarID;
	
	/**
	 * 承运对象ID(车辆或企业ID) 
	 */
	private String CarrierID;
	
	/**
	 * 承运对象名称（车牌号或企业名称） 
	 */
	private String CarrierName;
	
	/**
	 * 承运对象：1自有车辆2关联车辆3企业 
	 */
	private Integer CarrierType;
	
	/**
	 * 创建人ID 
	 */
	private String CreatePersonID;
	
	/**
	 * 创建人名称 
	 */
	private String CreatePersonName;
	
	/**
	 * 路程（公里
	 */
	private Double Distance;
	
	/**
	 * 司机反馈总数 
	 */
	private Integer DriverFeedbackNum;
	
	/**
	 * 司机反馈未查看数量 
	 */
	private Integer DriverFeedbackUnLookNum;
	
	/**
	 * 实际到达时间 
	 */
	private Date EndAreaActualArriveTime;
	
	/**
	 * 计划到达时间 
	 */
	private Date EndAreaPlanArriveTime;
	
	/**
	 * 委托方企业ID 
	 */
	private String EntrustEntID;
	
	/**
	 * 委托方企业名称 
	 */
	private String EntrustEntName;

	/**
	 * 委托方企业认证状态
	 */
	private Integer EntrustEntAuditStatus;
	
	/**
	 * 委托时间（任务派发时间） 
	 */
	private Timestamp EntrustTime;
	
	
	/**
	 * 和委托方是否合作关系
	 */
	private boolean IsFriend;
	
	/**
	 * 是否已取消委托 
	 */
	private boolean IsCancleEntrust;
	
	/**
	 * 是否已评价 
	 */
	private boolean IsRate;
	
	/**
	 * 最新gps时间 
	 */
	private Timestamp LastGpsTime;
	
	/**
	 * 纬度 
	 */
	private Double Lat;
	
	/**
	 * 经度 
	 */
	private Double Lng;
	
	/**
	 * 订单名称 
	 */
	private String Name;
	
	/**
	 * 订单CID 
	 */
	private String OrdersCID;
	
	/**
	 * 订单状态 
	 */
	private Integer OrdersStatus;
	
	/**
	 * 车牌号码（仅委托单时使用） 
	 */
	private String PlateNumber;
	
	/**
	 * 实际发车时间 
	 */
	private Date StartAreaActualLeavTime;
	
	/**
	 * 计划发车时间 
	 */
	private Date StartAreaPlanLeavTime;
	
	/**
	 * 时效(0表示按计划到达时间来) 
	 */
	private Integer Statute;
	
	private Integer duration;
	
	/**
	 * 单号 
	 */
	private String TaskCode;
	
	/**
	 * 派车信息ID
	 */
	private String OrderCarId;

	/**
	 * 委托名称的类型
	 */
	private Integer entrustNameType;

	/**
	 * 委托名称
	 */
	private String entrustName;
	
	/**
	 * 总时间
	 * @return
	 */
	private String totalTime;
	/**
	 * 超时或提前或准时到达
	 */
	private String TimeOut;
	
	/**
	 * 总里程String
	 * @return
	 */
	private String range;


	private Integer orderType;

	private boolean showRed;

	private boolean read;

	private Integer queueNumber; //排队
	
	
	private boolean isAutoSendCar;//是否自动确认发车

	private boolean isAutoArrival;//是否自动确认到达
	
	private Double planMileage; // 预计里程
	
	private Double actualMileage;
	
	private String entLogo;
	
	private String ownOrderId;
	
	private String barcode;//对应中通的ownOrderId
	
	private String barcodeSymbology;//中通取EAN128
	
	private String QrCode;//渣土车应用订单二维码url
	
	private Boolean isNeedSign;//渣土车应用，是否需要签收

	private boolean isTimeOut;// 是否超时
	
	private boolean isSameCity;// 是否同城

	private Integer exceptionSendTask;//异常状态

	private Integer exceptionArriveTask;

	public Integer getExceptionSendTask() {
		return exceptionSendTask;
	}

	public void setExceptionSendTask(Integer exceptionSendTask) {
		this.exceptionSendTask = exceptionSendTask;
	}

	public Integer getExceptionArriveTask() {
		return exceptionArriveTask;
	}

	public void setExceptionArriveTask(Integer exceptionArriveTask) {
		this.exceptionArriveTask = exceptionArriveTask;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getTimeOut() {
		return TimeOut;
	}

	public void setTimeOut(String timeOut) {
		TimeOut = timeOut;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public List<OrdersAreaInfoVo> getAreaInfoList() {
		return AreaInfoList;
	}

	public void setAreaInfoList(List<OrdersAreaInfoVo> areaInfoList) {
		AreaInfoList = areaInfoList;
	}






	public GoodsInfoVo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfoVo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getCargoInfo() {
		return CargoInfo;
	}

	public void setCargoInfo(String cargoInfo) {
		CargoInfo = cargoInfo;
	}

	public String getCarID() {
		return CarID;
	}

	public void setCarID(String carID) {
		CarID = carID;
	}

	public String getCarrierID() {
		return CarrierID;
	}

	public void setCarrierID(String carrierID) {
		CarrierID = carrierID;
	}

	public String getCarrierName() {
		return CarrierName;
	}

	public void setCarrierName(String carrierName) {
		CarrierName = carrierName;
	}

	public Integer getCarrierType() {
		return CarrierType;
	}

	public void setCarrierType(Integer carrierType) {
		CarrierType = carrierType;
	}

	public String getCreatePersonID() {
		return CreatePersonID;
	}

	public void setCreatePersonID(String createPersonID) {
		CreatePersonID = createPersonID;
	}

	public String getCreatePersonName() {
		return CreatePersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		CreatePersonName = createPersonName;
	}

	public Double getDistance() {
		return Distance;
	}

	public void setDistance(Double distance) {
		Distance = distance;
	}

	public Integer getDriverFeedbackNum() {
		return DriverFeedbackNum;
	}

	public void setDriverFeedbackNum(Integer driverFeedbackNum) {
		DriverFeedbackNum = driverFeedbackNum;
	}

	public Integer getDriverFeedbackUnLookNum() {
		return DriverFeedbackUnLookNum;
	}

	public void setDriverFeedbackUnLookNum(Integer driverFeedbackUnLookNum) {
		DriverFeedbackUnLookNum = driverFeedbackUnLookNum;
	}

	public Date getEndAreaActualArriveTime() {
		return EndAreaActualArriveTime;
	}

	public void setEndAreaActualArriveTime(Date endAreaActualArriveTime) {
		EndAreaActualArriveTime = endAreaActualArriveTime;
	}

	public Date getEndAreaPlanArriveTime() {
		return EndAreaPlanArriveTime;
	}

	public void setEndAreaPlanArriveTime(Date endAreaPlanArriveTime) {
		EndAreaPlanArriveTime = endAreaPlanArriveTime;
	}

	public String getEntrustEntID() {
		return EntrustEntID;
	}

	public void setEntrustEntID(String entrustEntID) {
		EntrustEntID = entrustEntID;
	}

	public String getEntrustEntName() {
		return EntrustEntName;
	}

	public void setEntrustEntName(String entrustEntName) {
		EntrustEntName = entrustEntName;
	}

	public Timestamp getEntrustTime() {
		return EntrustTime;
	}

	public void setEntrustTime(Timestamp entrustTime) {
		EntrustTime = entrustTime;
	}


	public boolean isIsFriend() {
		return IsFriend;
	}

	public void setIsFriend(boolean isFriend) {
		IsFriend = isFriend;
	}

	public boolean getIsCancleEntrust() {
		return IsCancleEntrust;
	}

	public void setIsCancleEntrust(boolean isCancleEntrust) {
		IsCancleEntrust = isCancleEntrust;
	}

	public boolean getIsRate() {
		return IsRate;
	}

	public void setIsRate(boolean isRate) {
		IsRate = isRate;
	}

	public Timestamp getLastGpsTime() {
		return LastGpsTime;
	}

	public void setLastGpsTime(Timestamp lastGpsTime) {
		LastGpsTime = lastGpsTime;
	}

	public Double getLat() {
		return Lat;
	}

	public void setLat(Double lat) {
		Lat = lat;
	}

	public Double getLng() {
		return Lng;
	}

	public void setLng(Double lng) {
		Lng = lng;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getOrdersCID() {
		return OrdersCID;
	}

	public void setOrdersCID(String ordersCID) {
		OrdersCID = ordersCID;
	}

	public Integer getOrdersStatus() {
		return OrdersStatus;
	}

	public void setOrdersStatus(Integer ordersStatus) {
		OrdersStatus = ordersStatus;
	}

	public String getPlateNumber() {
		return PlateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		PlateNumber = plateNumber;
	}

	public Date getStartAreaActualLeavTime() {
		return StartAreaActualLeavTime;
	}

	public void setStartAreaActualLeavTime(Date startAreaActualLeavTime) {
		StartAreaActualLeavTime = startAreaActualLeavTime;
	}

	public Date getStartAreaPlanLeavTime() {
		return StartAreaPlanLeavTime;
	}

	public void setStartAreaPlanLeavTime(Date startAreaPlanLeavTime) {
		StartAreaPlanLeavTime = startAreaPlanLeavTime;
	}

	public Integer getStatute() {
		return Statute;
	}

	public void setStatute(Integer statute) {
		Statute = statute;
	}



	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getTaskCode() {
		return TaskCode;
	}

	public void setTaskCode(String taskCode) {
		TaskCode = taskCode;
	}

	public String getOrderCarId() {
		return OrderCarId;
	}

	public void setOrderCarId(String orderCarId) {
		OrderCarId = orderCarId;
	}

	public Integer getEntrustNameType() {
		return entrustNameType;
	}

	public void setEntrustNameType(Integer entrustNameType) {
		this.entrustNameType = entrustNameType;
	}

	public String getEntrustName() {
		return entrustName;
	}

	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}


	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public boolean getIsAutoSendCar() {
		return isAutoSendCar;
	}

	public void setIsAutoSendCar(boolean isAutoSendCar) {
		this.isAutoSendCar = isAutoSendCar;
	}


	public boolean getIsAutoArrival() {
		return isAutoArrival;
	}

	public void setIsAutoArrival(boolean autoArrival) {
		isAutoArrival = autoArrival;
	}

	public String getEntLogo() {
		return entLogo;
	}

	public void setEntLogo(String entLogo) {
		this.entLogo = entLogo;
	}

	public Double getActualMileage() {
		return actualMileage;
	}

	public void setActualMileage(Double actualMileage) {
		this.actualMileage = actualMileage;
	}

	public String getOwnOrderId() {
		return ownOrderId;
	}

	public void setOwnOrderId(String ownOrderId) {
		this.ownOrderId = ownOrderId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcodeSymbology() {
		return barcodeSymbology;
	}

	public void setBarcodeSymbology(String barcodeSymbology) {
		this.barcodeSymbology = barcodeSymbology;
	}

	public String getQrCode() {
		return QrCode;
	}

	public void setQrCode(String qrCode) {
		QrCode = qrCode;
	}

	public void setAutoSendCar(boolean isAutoSendCar) {
		this.isAutoSendCar = isAutoSendCar;
	}

	public void setAutoArrival(boolean isAutoArrival) {
		this.isAutoArrival = isAutoArrival;
	}

	public Boolean getIsNeedSign() {
		return isNeedSign;
	}

	public void setIsNeedSign(Boolean isNeedSign) {
		this.isNeedSign = isNeedSign;
	}

	public Double getPlanMileage() {
		return planMileage;
	}

	public void setPlanMileage(Double planMileage) {
		this.planMileage = planMileage;
	}

	public boolean getIsSameCity() {
		return isSameCity;
	}

	public void setIsSameCity(boolean isSameCity) {
		this.isSameCity = isSameCity;
	}

	public boolean getIsTimeOut() {
		return isTimeOut;
	}

	public void setIsTimeOut(boolean isTimeOut) {
		this.isTimeOut = isTimeOut;
	}

	public void setTimeOut(boolean isTimeOut) {
		this.isTimeOut = isTimeOut;
	}

	public void setSameCity(boolean isSameCity) {
		this.isSameCity = isSameCity;
	}

	public Integer getEntrustEntAuditStatus() {
		return EntrustEntAuditStatus;
	}

	public void setEntrustEntAuditStatus(Integer entrustEntAuditStatus) {
		EntrustEntAuditStatus = entrustEntAuditStatus;
	}


	public boolean isShowRed() {
		return showRed;
	}

	public void setShowRed(boolean showRed) {
		this.showRed = showRed;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}


	public Integer getQueueNumber() {
		return queueNumber;
	}

	public void setQueueNumber(Integer queueNumber) {
		this.queueNumber = queueNumber;
	}
}
