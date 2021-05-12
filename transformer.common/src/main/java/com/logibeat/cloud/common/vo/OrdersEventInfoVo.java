package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;
import java.util.List;

public class OrdersEventInfoVo {

	/**
	 * 行驶里程（公里） 
	 */
	private Double Distance;
	
	/**
	 * 所有关联时间(按事件时间排序-顺序) 
	 */
	private List<EventDetailVo>  EventDetailList;
	
	/**
	 * 订单CID 
	 */
	private String OrdersCID;
	
	/**
	 * 实际发车时间(用于计算行驶时长) 
	 */
	private Timestamp StartAreaActualLeavTime;
	
	private int ordersStatus ;//订单状态
	
	private Timestamp endAreaActualArriveTime;//实际到达时间

	public Double getDistance() {
		return Distance;
	}

	public void setDistance(Double distance) {
		Distance = distance;
	}

	public List<EventDetailVo> getEventDetailList() {
		return EventDetailList;
	}

	public void setEventDetailList(List<EventDetailVo> eventDetailList) {
		EventDetailList = eventDetailList;
	}

	public String getOrdersCID() {
		return OrdersCID;
	}

	public void setOrdersCID(String ordersCID) {
		OrdersCID = ordersCID;
	}

	public Timestamp getStartAreaActualLeavTime() {
		return StartAreaActualLeavTime;
	}

	public void setStartAreaActualLeavTime(Timestamp startAreaActualLeavTime) {
		StartAreaActualLeavTime = startAreaActualLeavTime;
	}

	public int getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(int ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

	public Timestamp getEndAreaActualArriveTime() {
		return endAreaActualArriveTime;
	}

	public void setEndAreaActualArriveTime(Timestamp endAreaActualArriveTime) {
		this.endAreaActualArriveTime = endAreaActualArriveTime;
	}

	 
	
	
	
}
