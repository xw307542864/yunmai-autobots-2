package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;

public class ChoiceCarVo {
	
	private String OrdersCID;//  订单id
	
	private String CarID;  //车辆id
	
	private String startPoint;// 起点
	
	private String endPoint;//  终点
	
	private String plateNumber;//车牌号
	
	private Integer carOrTaskType;//类型  （0-car 1-task）
	
	private Timestamp startAreaActualLeavTime;//实际发车时间
	
	private String entName;

	public String getOrdersCID() {
		return OrdersCID;
	}

	public void setOrdersCID(String ordersCID) {
		OrdersCID = ordersCID;
	}

	public String getCarID() {
		return CarID;
	}

	public void setCarID(String carID) {
		CarID = carID;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Integer getCarOrTaskType() {
		return carOrTaskType;
	}

	public void setCarOrTaskType(Integer carOrTaskType) {
		this.carOrTaskType = carOrTaskType;
	}

	public Timestamp getStartAreaActualLeavTime() {
		return startAreaActualLeavTime;
	}

	public void setStartAreaActualLeavTime(Timestamp startAreaActualLeavTime) {
		this.startAreaActualLeavTime = startAreaActualLeavTime;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}
	
}
