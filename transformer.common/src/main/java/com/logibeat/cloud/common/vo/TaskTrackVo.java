package com.logibeat.cloud.common.vo;


import java.util.Date;
import java.util.List;

public class TaskTrackVo {

	/** 任务id */
	private String ordersCid;
	/** 派车信息id */
	private String orderCarId;
	/** 任务状态 */
	private Integer ordersStatus;
	/** 任务类型 */
	private Integer ordersType;
	/** 委托企业id */
	private String entrustEntId;
	/** 是否自动发车 */
	private Boolean isAutoDepart;
	/** 是否自动到达 */
    private Boolean isAutoArrive;
	/** 已到达guid */
	private String arrivedAreaGuid;
	/** 下一站guid */
	private String nextAreaGuid;
	/** 车牌号 */
	private String plateNumber;

	private String vehicleColor;

	private String taskCarCode;

	private String stowageCode;

	private String vehicleType;

	/** 途经点信息 */
    private List<TaskAreaVo> taskAreaVoList;
    
    /** 计划发车时间 */
    private Date planLeaveTime;
    /** 计划到达时间 */
    private Date planArriveTime;
    
    // 任务完成时 才有的字段
    /** 实际发车时间 */
    private Date actualLeaveTime;
    /** 实际到达时间 */
    private Date actualArriveTime;
    /** 实际里程 km */
    private Double actualMileage;
    /** 时效 */
    private String  duration;
    /** 是否超时 */
    private boolean  isTimeOut;
    /**  */
    private String  beyondTime;
    
    // 任务进行中 才有的字段
    /** 任务运行时段 */
    private List<CarGpsSourceVo> carGpsSourceVoList;

	

	public String getOrderCarId() {
		return orderCarId;
	}

	public void setOrderCarId(String orderCarId) {
		this.orderCarId = orderCarId;
	}

	public Integer getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(Integer ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

	public List<TaskAreaVo> getTaskAreaVoList() {
		return taskAreaVoList;
	}

	public void setTaskAreaVoList(List<TaskAreaVo> taskAreaVoList) {
		this.taskAreaVoList = taskAreaVoList;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public List<CarGpsSourceVo> getCarGpsSourceVoList() {
		return carGpsSourceVoList;
	}

	public void setCarGpsSourceVoList(List<CarGpsSourceVo> carGpsSourceVoList) {
		this.carGpsSourceVoList = carGpsSourceVoList;
	}

	public Double getActualMileage() {
		return actualMileage;
	}

	public void setActualMileage(Double actualMileage) {
		this.actualMileage = actualMileage;
	}

	public String getArrivedAreaGuid() {
		return arrivedAreaGuid;
	}

	public void setArrivedAreaGuid(String arrivedAreaGuid) {
		this.arrivedAreaGuid = arrivedAreaGuid;
	}

	public String getNextAreaGuid() {
		return nextAreaGuid;
	}

	public void setNextAreaGuid(String nextAreaGuid) {
		this.nextAreaGuid = nextAreaGuid;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Boolean getIsAutoDepart() {
		return isAutoDepart;
	}

	public void setIsAutoDepart(Boolean isAutoDepart) {
		this.isAutoDepart = isAutoDepart;
	}

	public Boolean getIsAutoArrive() {
		return isAutoArrive;
	}

	public void setIsAutoArrive(Boolean isAutoArrive) {
		this.isAutoArrive = isAutoArrive;
	}

	public String getOrdersCid() {
		return ordersCid;
	}

	public void setOrdersCid(String ordersCid) {
		this.ordersCid = ordersCid;
	}

	public boolean getIsTimeOut() {
		return isTimeOut;
	}

	public void setIsTimeOut(boolean isTimeOut) {
		this.isTimeOut = isTimeOut;
	}

	public String getBeyondTime() {
		return beyondTime;
	}

	public void setBeyondTime(String beyondTime) {
		this.beyondTime = beyondTime;
	}

	public Integer getOrdersType() {
		return ordersType;
	}

	public void setOrdersType(Integer ordersType) {
		this.ordersType = ordersType;
	}

	public String getEntrustEntId() {
		return entrustEntId;
	}

	public void setEntrustEntId(String entrustEntId) {
		this.entrustEntId = entrustEntId;
	}

	public Date getPlanLeaveTime() {
		return planLeaveTime;
	}

	public void setPlanLeaveTime(Date planLeaveTime) {
		this.planLeaveTime = planLeaveTime;
	}

	public Date getPlanArriveTime() {
		return planArriveTime;
	}

	public void setPlanArriveTime(Date planArriveTime) {
		this.planArriveTime = planArriveTime;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getTaskCarCode() {
		return taskCarCode;
	}

	public void setTaskCarCode(String taskCarCode) {
		this.taskCarCode = taskCarCode;
	}

	public String getStowageCode() {
		return stowageCode;
	}

	public void setStowageCode(String stowageCode) {
		this.stowageCode = stowageCode;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}
