package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 订单详细VO

* @Title: Driver_OrdersDriverDetailVo.java

* @Package com.yunmaigo.qtz.driver.vo

* @Description: TODO(用一句话描述该文件做什么)

* @author Wilson   

* @date 2016年1月8日 下午2:32:50

* @version V1.0
 */
public class OrdersDriverDetailVo {

	/**
	 * 订单网点信息 
	 */
	private List<OrdersAreaInfoVo> AreaInfoList;
	
	
	/**
	 * 货物信息
	 */
	private GoodsInfoVo goodsInfo;
	
	
	/**
	 * 订单号16位数字 YYMMddHHmmss+4位随机数 
	 */
	private String Code;
	
	/**
	 * 路程（公里）
	 */
	private Double Distance;
	
	/**
	 * 实际到达时间 
	 */
	private Date EndAreaActualArriveTime;
	
	/**
	 * 计划到达时间 
	 */
	private Date EndAreaPlanArriveTime;
	
	
	/**
	 * 是否已取消委托 
	 */
	private boolean IsCancleEntrust;
	
	
	/**
	 * 委托方企业信息（货物订单 需要对企业ID、Logo、名称赋值） 
	 */
	private ContactInfoVo EntrustEnt;
	
	/**
	 * 委托人（调度 货物订单 需要对调度人ID、姓名、手机号赋值） 
	 */
	private ContactInfoVo EntrustPerson;
	
	/**
	 * 委托时间（派单时间） 
	 */
	private Timestamp 	EntrustTime;
	
	 
	
	/**
	 * 订单CID 
	 */
	private String OrdersCID;
	/**
	 * 订单车辆表id
	 */
	private String orderCarId;
	
	/**
	 * 订单状态 
	 */
	private Integer OrdersStatus;
	
	/**
	 * 接单时间 
	 */
	private Timestamp ReceivTime;
	
	/**
	 * 剩余里程（公里） 
	 */
	private Double RemDistance;
	
	/**
	 * 已行驶里程（公里） 
	 */
	private Double RunDistance;
	
	/**
	 * 实际发车时间 
	 */
	private Date StartAreaActualLeavTime;
	
	/**
	 * 计划发车时间 
	 */
	private Date StartAreaPlanLeavTime;
	
	private String ordersRemark;
	
	/**
	 * 时效(0表示按计划到达时间来) 
	 */
	private Integer Statute;
	
	private Integer duration;

	private Integer orderType;

	private String carID;
	
	private String plateNumber;

	private boolean isDelete;
	
	private Double actualMileage;

	//按钮展示
	private ButtonShowVo button;
	
	private String ownOrderId;
	
	private String barcode;//对应中通的ownOrderId
	
	private String barcodeSymbology;//中通取EAN128
	
	private String QrCode;//渣土车应用订单需要展示二维码
	
	private Boolean isNeedSign;//渣土车应用订单是否需要签收

	private Boolean isTimeOut;//是否超时
	private String timeOut;//超时，准时到达，提前
	private String shippingManifestsId;//配载单id

	private boolean read;
	/**
	 * 预计里程
	 */
	private Double planMileage; // 

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

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public Double getDistance() {
		return Distance;
	}

	public void setDistance(Double distance) {
		Distance = distance;
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

	public ContactInfoVo getEntrustEnt() {
		return EntrustEnt;
	}

	public void setEntrustEnt(ContactInfoVo entrustEnt) {
		EntrustEnt = entrustEnt;
	}

	public ContactInfoVo getEntrustPerson() {
		return EntrustPerson;
	}

	public void setEntrustPerson(ContactInfoVo entrustPerson) {
		EntrustPerson = entrustPerson;
	}

	public Timestamp getEntrustTime() {
		return EntrustTime;
	}

	public void setEntrustTime(Timestamp entrustTime) {
		EntrustTime = entrustTime;
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

	public Timestamp getReceivTime() {
		return ReceivTime;
	}

	public void setReceivTime(Timestamp receivTime) {
		ReceivTime = receivTime;
	}

	public Double getRemDistance() {
		return RemDistance;
	}

	public void setRemDistance(Double remDistance) {
		RemDistance = remDistance;
	}

	public Double getRunDistance() {
		return RunDistance;
	}

	public void setRunDistance(Double runDistance) {
		RunDistance = runDistance;
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

	public boolean isIsCancleEntrust() {
		return IsCancleEntrust;
	}

	public void setIsCancleEntrust(boolean isCancleEntrust) {
		IsCancleEntrust = isCancleEntrust;
	}

	public String getOrdersRemark() {
		return ordersRemark;
	}

	public void setOrdersRemark(String ordersRemark) {
		this.ordersRemark = ordersRemark;
	}


	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getCarID() {
		return carID;
	}

	public void setCarID(String carID) {
		this.carID = carID;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Double getActualMileage() {
		return actualMileage;
	}

	public void setActualMileage(Double actualMileage) {
		this.actualMileage = actualMileage;
	}

	public ButtonShowVo getButton() {
		return button;
	}

	public void setButton(ButtonShowVo button) {
		this.button = button;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
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

	public Boolean getIsNeedSign() {
		return isNeedSign;
	}

	public void setIsNeedSign(Boolean isNeedSign) {
		this.isNeedSign = isNeedSign;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getOrderCarId() {
		return orderCarId;
	}

	public void setOrderCarId(String orderCarId) {
		this.orderCarId = orderCarId;
	}

	public Boolean getIsTimeOut() {
		return isTimeOut;
	}

	public void setIsTimeOut(Boolean isTimeOut) {
		this.isTimeOut = isTimeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public Double getPlanMileage() {
		return planMileage;
	}

	public void setPlanMileage(Double planMileage) {
		this.planMileage = planMileage;
	}

	public String getShippingManifestsId() {
		return shippingManifestsId;
	}

	public void setShippingManifestsId(String shippingManifestsId) {
		this.shippingManifestsId = shippingManifestsId;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}
}
