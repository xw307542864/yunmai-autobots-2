package com.logibeat.cloud.persistent.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 表 driver_task
 * 
 * @author wilson
 * @date 2020-04-14
 */
public class DriverTaskEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**guid  */
	private String guid;
	/** 单号 */
	private String orderNumber;
	/** 业务类型 100：整车  200：零担 */
	private Integer bizType;
	/** 类型  100：零担运输（配载单派车）   200：零担派送（派送单派车）  300 零担揽收 （揽收单派车）  400：整车（托运单派车）  500：整车请车单 （预约单派车） */
	private Integer taskType;
	/**始发城市编码  */
	private String originalCityCode;
	/** 始发地址 */
	private String originalAddress;
	/** 目的地城市 */
	private String destinationCityCode;
	/** 目的地地址 */
	private String destinationAddress;
	/** 派车的单据（配载单、派送单、揽收单、托运单、请车单）guid */
	private String relationOrderId;
	/** 派车的单据（配载单、派送单、揽收单、托运单、请车单）单号 */
	private String relationOrderNumber;
	/** 单据状态 100：待发车  200：在途中  300：已完成  400：已取消 */
	private Integer taskStatus;
	/** 单据当前状态 */
	private Integer taskCurrentStatus;
	/** 企业id */
	private String entId;
	/** 派单人 */
	private String personId;
	/** 共享/请车上游企业id */
	private String relationEntId;
	/** 共享/请车   车辆所属企业配载单id */
	private String relationStowageId;
	/** 线路类型  100：指定线路  200：推荐线路 */
	private Integer pointType;
	/** 车辆id */
	private String vehicleId;
	/** 车牌号 */
	private String vehiclePlateNumber;
	/** 车辆类型 */
	private String vehicleType;
	/** 所属组织id */
	private String orgnId;

	/**
	 * 任务主键（如果是运脉的单据就存运脉的单据id,如果是第三方导入的单据id,就存第三方的单据id）
	 */
	private String outTaskId;
	/**
	 * 装卸点数量
	 */
	private Integer pointCount;
	/**
	 * 当前工作装卸点
	 */
	private String workPointId;
	/** 主驾司机id */
	private String firstPersonId;
	/** 主驾司机姓名 */
	private String firstPersonName;
	/** 主驾司机手机 */
	private String firstPersonMobile;
	/** 副驾司机id */
	private String secondPersonId;
	/** 副驾司机姓名 */
	private String secondPersonName;
	/** 副驾司机手机号 */
	private String secondPersonMobile;
	/** 派单方信息（企业、人） */
	private String entrustInfo;
	/** 用车时间（计划/预计发车时间） */
	private Date planDepartTime;

	private Double actualDepartLat;

	private Double actualDepartLng;


	/** 预计/计划 到达时间 */
	private Date planArriveTime;
	/** 预计在途时长 （分钟） */
	private Integer planRuningDuration;

	/** 开始时间 第一次点击去装货  则=触发去装货 **/
	private Date startTime;

	/** 实际发车时间 */
	private Date actualDepartTime;
	/** 实际完成时间 */
	private Date actualArriveTime;

	private Double actualArriveLat;

	private Double actualArriveLng;

	/** 实际执行时长 */
	private Integer actualRuningDuration;
	/** 实际执行里程  */
	private Integer actualRuningMileage;
	/** 是否已读  1：已读 0：未读 */
	private Integer read = 0;
	/** 是否删除  1：已删除  0：未删除 */

	private Integer delete = 0;
	/** 是否取消  1：已取消  0：未取消 */

	private Integer cancel = 0;
	/** 是否异常发车单 1：是   0：否 */
	private Integer exceptionDepart = 0;
	/** 是否异常到达单 1：是   0：否 */
	private Integer exceptionArrive = 0;
	/** 删除时间 */
	private Date deleteTime;
	/** 取消时间 */
	private Date cancelTime;
	/** 取消原因 */
	private String cancelReason;
	/**创建时间*/
	private Date createTime;
	/** 编辑时间 */
	private Date editTime;



	public void setGuid(String guid) 
	{
		this.guid = guid;
	}

	public String getGuid() 
	{
		return guid;
	}
	public void setOrderNumber(String orderNumber) 
	{
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() 
	{
		return orderNumber;
	}
	public void setBizType(Integer bizType) 
	{
		this.bizType = bizType;
	}

	public Integer getBizType() 
	{
		return bizType;
	}
	public void setTaskType(Integer taskType) 
	{
		this.taskType = taskType;
	}

	public Integer getTaskType() 
	{
		return taskType;
	}
	public void setOriginalCityCode(String originalCityCode) 
	{
		this.originalCityCode = originalCityCode;
	}

	public String getOriginalCityCode() 
	{
		return originalCityCode;
	}
	public void setOriginalAddress(String originalAddress) 
	{
		this.originalAddress = originalAddress;
	}

	public String getOriginalAddress() 
	{
		return originalAddress;
	}
	public void setDestinationCityCode(String destinationCityCode) 
	{
		this.destinationCityCode = destinationCityCode;
	}

	public String getDestinationCityCode() 
	{
		return destinationCityCode;
	}
	public void setDestinationAddress(String destinationAddress) 
	{
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationAddress() 
	{
		return destinationAddress;
	}
	public void setRelationOrderId(String relationOrderId) 
	{
		this.relationOrderId = relationOrderId;
	}

	public String getRelationOrderId() 
	{
		return relationOrderId;
	}
	public void setRelationOrderNumber(String relationOrderNumber) 
	{
		this.relationOrderNumber = relationOrderNumber;
	}

	public String getRelationOrderNumber() 
	{
		return relationOrderNumber;
	}
	public void setTaskStatus(Integer taskStatus) 
	{
		this.taskStatus = taskStatus;
	}

	public Integer getTaskStatus() 
	{
		return taskStatus;
	}
	public void setTaskCurrentStatus(Integer taskCurrentStatus) 
	{
		this.taskCurrentStatus = taskCurrentStatus;
	}

	public Integer getTaskCurrentStatus() 
	{
		return taskCurrentStatus;
	}
	public void setEntId(String entId) 
	{
		this.entId = entId;
	}

	public String getEntId() 
	{
		return entId;
	}
	public void setRelationEntId(String relationEntId) 
	{
		this.relationEntId = relationEntId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getRelationEntId()
	{
		return relationEntId;
	}
	public void setRelationStowageId(String relationStowageId) 
	{
		this.relationStowageId = relationStowageId;
	}

	public String getRelationStowageId() 
	{
		return relationStowageId;
	}
	public void setPointType(Integer pointType) 
	{
		this.pointType = pointType;
	}

	public Integer getPointType() 
	{
		return pointType;
	}
	public void setVehicleId(String vehicleId) 
	{
		this.vehicleId = vehicleId;
	}

	public String getVehicleId() 
	{
		return vehicleId;
	}
	public void setVehiclePlateNumber(String vehiclePlateNumber) 
	{
		this.vehiclePlateNumber = vehiclePlateNumber;
	}

	public String getVehiclePlateNumber() 
	{
		return vehiclePlateNumber;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleType()
	{
		return vehicleType;
	}

	public Integer getPointCount() {
		return pointCount;
	}

	public void setPointCount(Integer pointCount) {
		this.pointCount = pointCount;
	}

	public String getWorkPointId() {
		return workPointId;
	}

	public void setWorkPointId(String workPointId) {
		this.workPointId = workPointId;
	}

	public void setOrgnId(String orgnId)
	{
		this.orgnId = orgnId;
	}

	public String getOrgnId() 
	{
		return orgnId;
	}

	public String getOutTaskId() {
		return outTaskId;
	}

	public void setOutTaskId(String outTaskId) {
		this.outTaskId = outTaskId;
	}

	public void setFirstPersonId(String firstPersonId)
	{
		this.firstPersonId = firstPersonId;
	}

	public String getFirstPersonId() 
	{
		return firstPersonId;
	}
	public void setFirstPersonName(String firstPersonName) 
	{
		this.firstPersonName = firstPersonName;
	}

	public String getFirstPersonName() 
	{
		return firstPersonName;
	}
	public void setFirstPersonMobile(String firstPersonMobile) 
	{
		this.firstPersonMobile = firstPersonMobile;
	}

	public String getFirstPersonMobile() 
	{
		return firstPersonMobile;
	}
	public void setSecondPersonId(String secondPersonId) 
	{
		this.secondPersonId = secondPersonId;
	}

	public String getSecondPersonId() 
	{
		return secondPersonId;
	}
	public void setSecondPersonName(String secondPersonName) 
	{
		this.secondPersonName = secondPersonName;
	}

	public String getSecondPersonName() 
	{
		return secondPersonName;
	}
	public void setSecondPersonMobile(String secondPersonMobile) 
	{
		this.secondPersonMobile = secondPersonMobile;
	}

	public String getSecondPersonMobile() 
	{
		return secondPersonMobile;
	}
	public void setEntrustInfo(String entrustInfo) 
	{
		this.entrustInfo = entrustInfo;
	}

	public String getEntrustInfo() 
	{
		return entrustInfo;
	}
	public void setPlanDepartTime(Date planDepartTime) 
	{
		this.planDepartTime = planDepartTime;
	}

	public Date getPlanDepartTime() 
	{
		return planDepartTime;
	}
	public void setPlanArriveTime(Date planArriveTime) 
	{
		this.planArriveTime = planArriveTime;
	}

	public Date getPlanArriveTime() 
	{
		return planArriveTime;
	}
	public void setPlanRuningDuration(Integer planRuningDuration) 
	{
		this.planRuningDuration = planRuningDuration;
	}

	public Integer getPlanRuningDuration() 
	{
		return planRuningDuration;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setActualDepartTime(Date actualDepartTime)
	{
		this.actualDepartTime = actualDepartTime;
	}

	public Date getActualDepartTime() 
	{
		return actualDepartTime;
	}
	public void setActualArriveTime(Date actualArriveTime) 
	{
		this.actualArriveTime = actualArriveTime;
	}

	public Date getActualArriveTime() 
	{
		return actualArriveTime;
	}
	public void setActualRuningDuration(Integer actualRuningDuration) 
	{
		this.actualRuningDuration = actualRuningDuration;
	}

	public Integer getActualRuningDuration() 
	{
		return actualRuningDuration;
	}
	public void setActualRuningMileage(Integer actualRuningMileage)
	{
		this.actualRuningMileage = actualRuningMileage;
	}

	public Integer getActualRuningMileage()
	{
		return actualRuningMileage;
	}
	public void setRead(Integer read) 
	{
		this.read = read;
	}

	public Integer getRead() 
	{
		return read;
	}
	public void setDelete(Integer delete) 
	{
		this.delete = delete;
	}

	public Integer getDelete() 
	{
		return delete;
	}

	public Integer getExceptionDepart() {
		return exceptionDepart;
	}

	public void setExceptionDepart(Integer exceptionDepart) {
		this.exceptionDepart = exceptionDepart;
	}

	public Integer getExceptionArrive() {
		return exceptionArrive;
	}

	public void setExceptionArrive(Integer exceptionArrive) {
		this.exceptionArrive = exceptionArrive;
	}

	public void setDeleteTime(Date deleteTime)
	{
		this.deleteTime = deleteTime;
	}

	public Date getDeleteTime() 
	{
		return deleteTime;
	}
	public void setCancelTime(Date cancelTime) 
	{
		this.cancelTime = cancelTime;
	}

	public Date getCancelTime() 
	{
		return cancelTime;
	}
	public void setCancelReason(String cancelReason) 
	{
		this.cancelReason = cancelReason;
	}

	public String getCancelReason() 
	{
		return cancelReason;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setEditTime(Date editTime)
	{
		this.editTime = editTime;
	}

	public Date getEditTime() 
	{
		return editTime;
	}


	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}


	public Double getActualDepartLat() {
		return actualDepartLat;
	}

	public void setActualDepartLat(Double actualDepartLat) {
		this.actualDepartLat = actualDepartLat;
	}

	public Double getActualDepartLng() {
		return actualDepartLng;
	}

	public void setActualDepartLng(Double actualDepartLng) {
		this.actualDepartLng = actualDepartLng;
	}

	public Double getActualArriveLat() {
		return actualArriveLat;
	}

	public void setActualArriveLat(Double actualArriveLat) {
		this.actualArriveLat = actualArriveLat;
	}

	public Double getActualArriveLng() {
		return actualArriveLng;
	}

	public void setActualArriveLng(Double actualArriveLng) {
		this.actualArriveLng = actualArriveLng;
	}

	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("guid", getGuid())
            .append("orderNumber", getOrderNumber())
            .append("bizType", getBizType())
            .append("taskType", getTaskType())
            .append("originalCityCode", getOriginalCityCode())
            .append("originalAddress", getOriginalAddress())
            .append("destinationCityCode", getDestinationCityCode())
            .append("destinationAddress", getDestinationAddress())
            .append("relationOrderId", getRelationOrderId())
            .append("relationOrderNumber", getRelationOrderNumber())
            .append("taskStatus", getTaskStatus())
            .append("taskCurrentStatus", getTaskCurrentStatus())
            .append("entId", getEntId())
            .append("relationEntId", getRelationEntId())
            .append("relationStowageId", getRelationStowageId())
            .append("pointType", getPointType())
            .append("vehicleId", getVehicleId())
            .append("vehiclePlateNumber", getVehiclePlateNumber())
            .append("vehicleType", getVehicleType())
            .append("orgnId", getOrgnId())
            .append("firstPersonId", getFirstPersonId())
            .append("firstPersonName", getFirstPersonName())
            .append("firstPersonMobile", getFirstPersonMobile())
            .append("secondPersonId", getSecondPersonId())
            .append("secondPersonName", getSecondPersonName())
            .append("secondPersonMobile", getSecondPersonMobile())
            .append("entrustInfo", getEntrustInfo())
            .append("planDepartTime", getPlanDepartTime())
            .append("planArriveTime", getPlanArriveTime())
            .append("planRuningDuration", getPlanRuningDuration())
            .append("actualDepartTime", getActualDepartTime())
            .append("actualArriveTime", getActualArriveTime())
            .append("actualRuningDuration", getActualRuningDuration())
            .append("actualRuningMileage", getActualRuningMileage())
            .append("read", getRead())
            .append("delete", getDelete())
            .append("deleteTime", getDeleteTime())
            .append("cancelTime", getCancelTime())
            .append("cancelReason", getCancelReason())
            .append("createTime", getCreateTime())
            .append("editTime", getEditTime())
            .toString();
    }
}
