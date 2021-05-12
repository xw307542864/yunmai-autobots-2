package com.logibeat.cloud.vo;

import com.logibeat.cloud.common.vo.DynamicTaskInfoVo;
import com.logibeat.cloud.common.vo.FriendShortInfoVo;
import com.logibeat.cloud.common.vo.OrdersAreaInfoVo;
import com.logibeat.cloud.common.vo.OrdersGoodsInfoVo;

import java.sql.Timestamp;
import java.util.List;

public class OrdersDetailVo {

	/// 订单CID
	private String ordersCID;

	/// 订单号16位数字 YYMMddHHmmss+4位随机数
	private String code;

	/// 订单名称
	private String name;

	/// 是否取消派单或接单
	private boolean isCancle;

	/// 是否承运订单（第三方企业委托给我的）
	private boolean isCarrierOrders;

	/// 订单状态
	private int ordersStatus;

	/// 是否手动完成
	private Byte isManual;

	/// 时效(0表示按计划到达时间来)
	private Integer statute;

	private Integer duration;

	/// 计划到达时间
	private Timestamp endAreaPlanArriveTime;

	/// 计划发车时间
	private Timestamp startAreaPlanLeavTime;

	/// 实际到达时间
	private Timestamp endAreaActualArriveTime;

	/// 实际发车时间
	private Timestamp startAreaActualLeavTime;
	/// 创建时间
	private Timestamp createTime;
	/// 路程（公里）
	private Double distance;

	/// 位置
	private String address;

	/// 最新gps时间
	private Timestamp lastGpsTime;

	/// 经度
	private Double lng;

	/// 纬度
	private Double lat;

	/// 是否已评价
	private boolean isRate;

	/// 动态数量
	private int feedbackNum;

	/// 司机反馈未查看数量

	private int driverFeedbackUnLookNum;
	/// 是否车辆订单
	private boolean isCarOrders;

	/// 订单备注
	private String ordersRemark;

	/// 车厢长度：单位米
	private float carLength;

	/// 车型名称（车辆要求）
	private String coachTypeDictName;

	/// 额定载重 吨（车辆要求）
	private float ratedLoad;

	/// 额定体积 立方米（车辆要求）
	private float ratedVolume;

	/// 货物信息：“货物类型+重量”，若用户未输入重量，展现“货物类型+体积”
	private String cargoInfo;

	/// 货物信息（集合）
	private List<OrdersGoodsInfoVo> goods;

	/// 订单网点信息
	private List<OrdersAreaInfoVo> areaInfoList;

	/// 委托/派单人信息
	private FriendShortInfoVo entrustPerson;

	/// 上级是否已取消委托
	private boolean isCancleEntrust;

	/// 委托时间（派单时间）
	private Timestamp entrustTime;

	/// 接单人信息
	private FriendShortInfoVo carrierPerson;

	/// 承运方接单时间
	private Timestamp carrierTime;

	/// 车辆信息
	private CarInfoVo car;
	private String firstOrdersCID;
	/// 是否取消委托给下级
	private boolean esCancleEntrustNext;

	// 最新一条动态
	private DynamicTaskInfoVo lastFeedBack;

	/**
	 * 创建人
	 */
	private FriendShortInfoVo createPerson;

	/*
	 * 总里程
	 */
	private Double range;

	/*
	 * 超时
	 */
	private String beyondTime;

	/*
	 * 总时间
	 */
	private String totalTime;


	//订单冗余字段
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


	public Double getRange() {
		return range;
	}

	public void setRange(Double range) {
		this.range = range;
	}

	public String getBeyondTime() {
		return beyondTime;
	}

	public void setBeyondTime(String beyondTime) {
		this.beyondTime = beyondTime;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getOrdersCID() {
		return ordersCID;
	}

	public void setOrdersCID(String ordersCID) {
		this.ordersCID = ordersCID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getLastGpsTime() {
		return lastGpsTime;
	}

	public void setLastGpsTime(Timestamp lastGpsTime) {
		this.lastGpsTime = lastGpsTime;
	}

	public Timestamp getEntrustTime() {
		return entrustTime;
	}

	public void setEntrustTime(Timestamp entrustTime) {
		this.entrustTime = entrustTime;
	}

	public Timestamp getCarrierTime() {
		return carrierTime;
	}

	public void setCarrierTime(Timestamp carrierTime) {
		this.carrierTime = carrierTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsCancle() {
		return isCancle;
	}

	public void setCancle(boolean isCancle) {
		this.isCancle = isCancle;
	}

	public boolean getIsCarrierOrders() {
		return isCarrierOrders;
	}

	public void setIsCarrierOrders(boolean isCarrierOrders) {
		this.isCarrierOrders = isCarrierOrders;
	}

	public int getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(int ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

	public Integer getStatute() {
		return statute;
	}

	public void setStatute(Integer statute) {
		this.statute = statute;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Timestamp getEndAreaPlanArriveTime() {
		return endAreaPlanArriveTime;
	}

	public void setEndAreaPlanArriveTime(Timestamp endAreaPlanArriveTime) {
		this.endAreaPlanArriveTime = endAreaPlanArriveTime;
	}

	public Timestamp getStartAreaPlanLeavTime() {
		return startAreaPlanLeavTime;
	}

	public void setStartAreaPlanLeavTime(Timestamp startAreaPlanLeavTime) {
		this.startAreaPlanLeavTime = startAreaPlanLeavTime;
	}

	public Timestamp getEndAreaActualArriveTime() {
		return endAreaActualArriveTime;
	}

	public void setEndAreaActualArriveTime(Timestamp endAreaActualArriveTime) {
		this.endAreaActualArriveTime = endAreaActualArriveTime;
	}

	public Timestamp getStartAreaActualLeavTime() {
		return startAreaActualLeavTime;
	}

	public void setStartAreaActualLeavTime(Timestamp startAreaActualLeavTime) {
		this.startAreaActualLeavTime = startAreaActualLeavTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public boolean getIsRate() {
		return isRate;
	}

	public void setRate(boolean isRate) {
		this.isRate = isRate;
	}

	public int getFeedbackNum() {
		return feedbackNum;
	}

	public void setFeedbackNum(int feedbackNum) {
		this.feedbackNum = feedbackNum;
	}

	public int getDriverFeedbackUnLookNum() {
		return driverFeedbackUnLookNum;
	}

	public void setDriverFeedbackUnLookNum(int driverFeedbackUnLookNum) {
		this.driverFeedbackUnLookNum = driverFeedbackUnLookNum;
	}

	public boolean getIsCarOrders() {
		return isCarOrders;
	}

	public void setIsCarOrders(boolean isCarOrders) {
		this.isCarOrders = isCarOrders;
	}

	public String getOrdersRemark() {
		return ordersRemark;
	}

	public void setOrdersRemark(String ordersRemark) {
		this.ordersRemark = ordersRemark;
	}

	public float getCarLength() {
		return carLength;
	}

	public void setCarLength(float carLength) {
		this.carLength = carLength;
	}

	public String getCoachTypeDictName() {
		return coachTypeDictName;
	}

	public void setCoachTypeDictName(String coachTypeDictName) {
		this.coachTypeDictName = coachTypeDictName;
	}

	public float getRatedLoad() {
		return ratedLoad;
	}

	public void setRatedLoad(float ratedLoad) {
		this.ratedLoad = ratedLoad;
	}

	public float getRatedVolume() {
		return ratedVolume;
	}

	public void setRatedVolume(float ratedVolume) {
		this.ratedVolume = ratedVolume;
	}

	public String getCargoInfo() {
		return cargoInfo;
	}

	public void setCargoInfo(String cargoInfo) {
		this.cargoInfo = cargoInfo;
	}

	public List<OrdersGoodsInfoVo> getGoods() {
		return goods;
	}

	public void setGoods(List<OrdersGoodsInfoVo> goods) {
		this.goods = goods;
	}

	public List<OrdersAreaInfoVo> getAreaInfoList() {
		return areaInfoList;
	}

	public void setAreaInfoList(List<OrdersAreaInfoVo> areaInfoList) {
		this.areaInfoList = areaInfoList;
	}

	public FriendShortInfoVo getEntrustPerson() {
		return entrustPerson;
	}

	public void setEntrustPerson(FriendShortInfoVo entrustPerson) {
		this.entrustPerson = entrustPerson;
	}

	public boolean getIsCancleEntrust() {
		return isCancleEntrust;
	}

	public void setIsCancleEntrust(boolean isCancleEntrust) {
		this.isCancleEntrust = isCancleEntrust;
	}

	public FriendShortInfoVo getCarrierPerson() {
		return carrierPerson;
	}

	public void setCarrierPerson(FriendShortInfoVo carrierPerson) {
		this.carrierPerson = carrierPerson;
	}

	public CarInfoVo getCar() {
		return car;
	}

	public void setCar(CarInfoVo car) {
		this.car = car;
	}

	public String getFirstOrdersCID() {
		return firstOrdersCID;
	}

	public void setFirstOrdersCID(String firstOrdersCID) {
		this.firstOrdersCID = firstOrdersCID;
	}

	public boolean getIsEsCancleEntrustNext() {
		return esCancleEntrustNext;
	}

	public void setIsEsCancleEntrustNext(boolean esCancleEntrustNext) {
		this.esCancleEntrustNext = esCancleEntrustNext;
	}

	public FriendShortInfoVo getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(FriendShortInfoVo createPerson) {
		this.createPerson = createPerson;
	}

	public DynamicTaskInfoVo getLastFeedBack() {
		return lastFeedBack;
	}

	public void setLastFeedBack(DynamicTaskInfoVo lastFeedBack) {
		this.lastFeedBack = lastFeedBack;
	}

	public Byte getIsManual() {
		return isManual;
	}

	public void setIsManual(Byte isManual) {
		this.isManual = isManual;
	}

	public boolean isEsCancleEntrustNext() {
		return esCancleEntrustNext;
	}

	public void setEsCancleEntrustNext(boolean esCancleEntrustNext) {
		this.esCancleEntrustNext = esCancleEntrustNext;
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

}
