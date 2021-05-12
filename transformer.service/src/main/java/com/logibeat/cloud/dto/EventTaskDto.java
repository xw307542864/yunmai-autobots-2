package com.logibeat.cloud.dto;


import com.logibeat.cloud.core.dto.DynamicFileDTO;
import com.logibeat.cloud.core.dto.DynamicGpsDTO;
import com.logibeat.cloud.info.VoiceInfo;
import com.logibeat.cloud.util.tools.pageMdl.BaseDTO;

import java.util.List;

public class EventTaskDto extends BaseDTO {
	
	/**
	 * 当前执行的任务（订单）GUID（必填）
	 */
	@Deprecated
	private String ordersCid;
	
	/**
	 * 当前派车信息GUID（必填）
	 */
	@Deprecated
	private String orderCarId;

	/**
	 * 任务单id (同上)
	 */
	private String taskId;
	
	/**
	 * 对应网点GUID（除接单外必填） 
	 */
	private String ordersAreaGuid;
	
	/**
	 * 当前驾驶的车辆ID
	 */
	private String carId;
	
	/**
	 * 事件动作
	 */
	private Integer eventAction;
	
	/**
	 * 文本内容
	 */
	private String txtContent;
	
	/**
	 * GPS信息（经纬度、地址）
	 */
	private DynamicGpsDTO gps;
	
	/**
	 * 多媒体文件
	 */
	private List<DynamicFileDTO> files;

	/**
	 * 高德地图截图文件
	 */
	private String guid;

	/**
	 * 是否在指定发车点
	 */
	private Integer isAtPoint;

	private String picUrls;
	
	/******************************* 以上字段用于自动发车/自动到达  *********************************/
	
	/**
	 * 标识是0：发车/2：到达途径点/3：离开途经点/6：临近N米/7：到达250米/10：其他
	 */
	private Integer pointState;
	
	/**
	 * 自动发车的前置条件数量
	 */
	private Integer pre;
	
	private Integer isAutoSend;
	private Integer isAutoArrive;
	
	private String repeatTime;//重复发到时间

	private String expectArriveTime;
	
	private String counterJson;//不知道什么用，先留着

	private Integer exceptionSendTask;//异常发车状态

	private Integer exceptionArriveTask;//异常到达状态
	
	private List<VoiceInfo> voiceList;//语音

	public String getOrdersCid() {
		return ordersCid;
	}

	public void setOrdersCid(String ordersCid) {
		this.ordersCid = ordersCid;
	}

	public String getOrderCarId() {
		return orderCarId;
	}

	public void setOrderCarId(String orderCarId) {
		this.orderCarId = orderCarId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOrdersAreaGuid() {
		return ordersAreaGuid;
	}

	public void setOrdersAreaGuid(String ordersAreaGuid) {
		this.ordersAreaGuid = ordersAreaGuid;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Integer getEventAction() {
		return eventAction;
	}

	public void setEventAction(Integer eventAction) {
		this.eventAction = eventAction;
	}

	public String getTxtContent() {
		return txtContent;
	}

	public void setTxtContent(String txtContent) {
		this.txtContent = txtContent;
	}

	public DynamicGpsDTO getGps() {
		return gps;
	}

	public void setGps(DynamicGpsDTO gps) {
		this.gps = gps;
	}

	public List<DynamicFileDTO> getFiles() {
		return files;
	}

	public void setFiles(List<DynamicFileDTO> files) {
		this.files = files;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Integer getIsAtPoint() {
		return isAtPoint;
	}

	public void setIsAtPoint(Integer isAtPoint) {
		this.isAtPoint = isAtPoint;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public Integer getPointState() {
		return pointState;
	}

	public void setPointState(Integer pointState) {
		this.pointState = pointState;
	}

	public Integer getPre() {
		return pre;
	}

	public void setPre(Integer pre) {
		this.pre = pre;
	}

	public Integer getIsAutoSend() {
		return isAutoSend;
	}

	public void setIsAutoSend(Integer isAutoSend) {
		this.isAutoSend = isAutoSend;
	}

	public Integer getIsAutoArrive() {
		return isAutoArrive;
	}

	public void setIsAutoArrive(Integer isAutoArrive) {
		this.isAutoArrive = isAutoArrive;
	}

	public String getRepeatTime() {
		return repeatTime;
	}

	public void setRepeatTime(String repeatTime) {
		this.repeatTime = repeatTime;
	}

	public String getExpectArriveTime() {
		return expectArriveTime;
	}

	public void setExpectArriveTime(String expectArriveTime) {
		this.expectArriveTime = expectArriveTime;
	}

	public String getCounterJson() {
		return counterJson;
	}

	public void setCounterJson(String counterJson) {
		this.counterJson = counterJson;
	}

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

	public List<VoiceInfo> getVoiceList() {
		return voiceList;
	}

	public void setVoiceList(List<VoiceInfo> voiceList) {
		this.voiceList = voiceList;
	}

}
