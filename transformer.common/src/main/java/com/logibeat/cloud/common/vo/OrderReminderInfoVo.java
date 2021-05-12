package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;
import java.util.List;

/**
 * 新任务提醒Vo
 * @ClassName: OrderReminderInfoVo 
 * @Description: 
 * @author kzz 
 * @date 2017年2月23日 下午1:52:31 
 * @version 1.0
 */
public class OrderReminderInfoVo {
	/**
	 * 起点
	 */
	private String startPoint;
	/**
	 * 起点纬度
	 */
	private Double lat;
	/**
	 * 起点经度
	 */
	private Double lng;
	/**
	 * 终点
	 */
	private String endPoint;
	
	/**
	 * 货物信息
	 */
	private GoodsInfoVo goodsInfo;
	
	/**
	 * 路程（公里）
	 */
	private Double distance;
	
	/**
	 * 计划发车时间 
	 */
	private Timestamp startAreaPlanLeavTime;
	/**
	 * 计划发车时间 
	 */
	private Timestamp endAreaPlanArriveTime;
	
	/**
	 * 委托方企业id
	 */
	private String entrustEntId;
	/**
	 * 委托方企业名称
	 */
	private String entrustEntName;
	/**
	 * 委托方企业logo 
	 */
	private String entrustEntLogo;
	
	/**
	 * 委托人Id
	 */
	private String entrustPersonId;
	/**
	 * 委托人姓名
	 */
	private String entrustPersonName;
	/**
	 * 委托人电话
	 */
	private String entrustPersonPhone;
	/**
	 * 委托人logitalkId
	 */
	private String entrustPersonLogitalkId;
	/**
	 * 城市编码（跨城则为空）
	 */
	private String regionCode;
	/**
	 * 任务车辆表id
	 */
	private String orderCarId;
	
	private List<Integer> minutes;
	
	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public GoodsInfoVo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfoVo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Double getDistance() {
		return this.distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Timestamp getStartAreaPlanLeavTime() {
		return this.startAreaPlanLeavTime;
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

	public String getEntrustEntName() {
		return entrustEntName;
	}

	public void setEntrustEntName(String entrustEntName) {
		this.entrustEntName = entrustEntName;
	}

	public String getEntrustEntLogo() {
		return entrustEntLogo;
	}

	public void setEntrustEntLogo(String entrustEntLogo) {
		this.entrustEntLogo = entrustEntLogo;
	}

	public String getEntrustPersonName() {
		return entrustPersonName;
	}

	public void setEntrustPersonName(String entrustPersonName) {
		this.entrustPersonName = entrustPersonName;
	}

	public String getEntrustPersonPhone() {
		return entrustPersonPhone;
	}

	public void setEntrustPersonPhone(String entrustPersonPhone) {
		this.entrustPersonPhone = entrustPersonPhone;
	}

	public String getEntrustPersonLogitalkId() {
		return entrustPersonLogitalkId;
	}

	public void setEntrustPersonLogitalkId(String entrustPersonLogitalkId) {
		this.entrustPersonLogitalkId = entrustPersonLogitalkId;
	}

	public String getEntrustEntId() {
		return entrustEntId;
	}

	public void setEntrustEntId(String entrustEntId) {
		this.entrustEntId = entrustEntId;
	}

	public String getEntrustPersonId() {
		return entrustPersonId;
	}

	public void setEntrustPersonId(String entrustPersonId) {
		this.entrustPersonId = entrustPersonId;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getOrderCarId() {
		return orderCarId;
	}

	public void setOrderCarId(String orderCarId) {
		this.orderCarId = orderCarId;
	}

	public List<Integer> getMinutes() {
		return minutes;
	}

	public void setMinutes(List<Integer> minutes) {
		this.minutes = minutes;
	}

}
