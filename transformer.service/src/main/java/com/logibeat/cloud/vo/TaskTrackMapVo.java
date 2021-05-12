package com.logibeat.cloud.vo;

import com.logibeat.cloud.common.vo.TaskPointVo;

import java.sql.Timestamp;
import java.util.List;


public class TaskTrackMapVo {
	
	/**
	 * 订单ID
	 */
	private String orderCid;
	
	/**
	 * 任务状态
	 */
	private Integer orderStatus;
	
	/**
	 * 车辆Id
	 */
	private String carId;
	
	/**
	 * 车牌号
	 * 
	 */
	private String carPlateNumber;
	
	/**
	 * 主驾ID
	 */
	private String firstPersonId;
	
	/**
	 * 主驾姓名
	 */
	private String firstPersonName;
	
	/**
	 * 副驾ID
	 */
	private String secondPersonid;
	
	/**
	 * 副驾姓名
	 */
	private String secondPersonName;
	

	/**
	 * 接单时间
	 */
	private Timestamp receiveTime;
	
	/**
	 * 车辆维度
	 */
	private Double carLat;
	
	/**
	 * 车辆经度
	 */
	private Double carLng;
	
	/**
	 * 车辆地址
	 */
	private String carAddress;
	
	/**
	 * 车辆是否停止
	 */
    private boolean carStop;
    
    /**
     * 车辆角度
     */
    private Double degree;
    
    /**
     * 发车纬度
     */
    private Double departLat;
    
    /**
     * 发车经度
     */
    private Double departLng;
    
    /**
     * 发车位置
     */
    private String departSddress;
    
    /**
     * 发车时间
     */
    private Timestamp departTime;
    
    /**
     * 完成时间
     */
    private Timestamp finishTime;
    
    /**
     * 到达点
     */
    private List<TaskPointVo> taskPointVoList;
    
    /**
     * 在途反馈点
     */
    private List<TaskPointVo> taskDynamicList;
    /**
     * 到达点和在途反馈点
     */
    private List<TaskPointVo> pointAndDynamicList;
    
    private Double nextLat;
    
    private Double nextLng;
    
    private String nextName;
    
    private Double endLat;
    
    private Double endLng;
    
    private List<CarGpsInfoVo> carGpsList;

	public String getOrderCid() {
		return orderCid;
	}

	public void setOrderCid(String orderCid) {
		this.orderCid = orderCid;
	}

	
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarPlateNumber() {
		return carPlateNumber;
	}

	public void setCarPlateNumber(String carPlateNumber) {
		this.carPlateNumber = carPlateNumber;
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

	public String getSecondPersonid() {
		return secondPersonid;
	}

	public void setSecondPersonid(String secondPersonid) {
		this.secondPersonid = secondPersonid;
	}

	public String getSecondPersonName() {
		return secondPersonName;
	}

	public void setSecondPersonName(String secondPersonName) {
		this.secondPersonName = secondPersonName;
	}

	public Timestamp getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Double getCarLat() {
		return carLat;
	}

	public void setCarLat(Double carLat) {
		this.carLat = carLat;
	}

	public Double getCarLng() {
		return carLng;
	}

	public void setCarLng(Double carLng) {
		this.carLng = carLng;
	}

	public String getCarAddress() {
		return carAddress;
	}

	public void setCarAddress(String carAddress) {
		this.carAddress = carAddress;
	}

	public boolean isCarStop() {
		return carStop;
	}

	public void setCarStop(boolean carStop) {
		this.carStop = carStop;
	}

	 

	public Double getDegree() {
		return degree;
	}

	public void setDegree(Double degree) {
		this.degree = degree;
	}

	public Double getDepartLat() {
		return departLat;
	}

	public void setDepartLat(Double departLat) {
		this.departLat = departLat;
	}

	public Double getDepartLng() {
		return departLng;
	}

	public void setDepartLng(Double departLng) {
		this.departLng = departLng;
	}

	public String getDepartSddress() {
		return departSddress;
	}

	public void setDepartSddress(String departSddress) {
		this.departSddress = departSddress;
	}

	public Timestamp getDepartTime() {
		return departTime;
	}

	public void setDepartTime(Timestamp departTime) {
		this.departTime = departTime;
	}

	public Timestamp getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}

	public List<TaskPointVo> getTaskPointVoList() {
		return taskPointVoList;
	}

	public void setTaskPointVoList(List<TaskPointVo> taskPointVoList) {
		this.taskPointVoList = taskPointVoList;
	}

	public Double getNextLat() {
		return nextLat;
	}

	public void setNextLat(Double nextLat) {
		this.nextLat = nextLat;
	}

	public Double getNextLng() {
		return nextLng;
	}

	public void setNextLng(Double nextLng) {
		this.nextLng = nextLng;
	}

	public String getNextName() {
		return nextName;
	}

	public void setNextName(String nextName) {
		this.nextName = nextName;
	}

	public List<CarGpsInfoVo> getCarGpsList() {
		return carGpsList;
	}

	public void setCarGpsList(List<CarGpsInfoVo> carGpsList) {
		this.carGpsList = carGpsList;
	}

	public List<TaskPointVo> getTaskDynamicList() {
		return taskDynamicList;
	}

	public void setTaskDynamicList(List<TaskPointVo> taskDynamicList) {
		this.taskDynamicList = taskDynamicList;
	}

	public Double getEndLat() {
		return endLat;
	}

	public void setEndLat(Double endLat) {
		this.endLat = endLat;
	}

	public Double getEndLng() {
		return endLng;
	}

	public void setEndLng(Double endLng) {
		this.endLng = endLng;
	}

	public List<TaskPointVo> getPointAndDynamicList() {
		return pointAndDynamicList;
	}

	public void setPointAndDynamicList(List<TaskPointVo> pointAndDynamicList) {
		this.pointAndDynamicList = pointAndDynamicList;
	}
    
	
}
