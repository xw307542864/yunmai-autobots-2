package com.logibeat.cloud.persistent.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 表 driver_task_point
 * 
 * @author wilson
 * @date 2020-04-14
 */
public class DriverTaskPointEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**  */
	private String guid;
	/**  */
	private String taskId;
	/**  */
	private Integer sort;
	/** 100:装  200:卸 */
	private Integer type;
	/** 是否可以进行工作 **/
	private Integer work=0;
	/**  */
	private String netWorkId;
	/**  */
	private String netWorkCode;
	/**  */
	private String name;
	/**  */
	private String address;
	/**  */
	private String cityCode;
	/**  */
	private String cityName;
	/**  */
	private Double lat;
	/**  */
	private Double lng;
	/**  */
	private Integer orderType;
	/**  */
	private String orderGuid;
	/**  */
	private Integer status;
	/**  */
	private String statusValue;
	/**  */
	private String contact;
	/**  */
	private String contactMobile;
	/**  */
	private Date planDepartTime;
	/**  */
	private Date planArriveTime;
	/** 开始时间  去装/卸 货**/
	private Date actualStartTime;
	/***工作时间 开始装/卸 货*/
	private Date actualWorkTime;
	/** 发车时间 */
	private Date actualDepartTime;
	/** 到达 装卸点时间 */
	private Date actualArriveTime;
	/** 完成 装卸时间 */
	private Date actualFinishTime;
	/**  */
	private Double actualLat;
	/**  */
	private Double actualLng;

	/** 实际执行时长 */
	private Integer actualRuningDuration;
	/** 实际执行里程  */
	private Integer actualRuningMileage;
	/**  */
	private Integer startPoint;
	/**  */
	private Integer endPoint;
	/** 是否需要签收 1：是  0：否 */
	private Integer sign = 0;
	/**  */
	private Integer delete = 0;

	private Date createTime;

	public void setGuid(String guid) 
	{
		this.guid = guid;
	}

	public String getGuid() 
	{
		return guid;
	}
	public void setTaskId(String taskId) 
	{
		this.taskId = taskId;
	}

	public String getTaskId() 
	{
		return taskId;
	}
	public void setSort(Integer sort) 
	{
		this.sort = sort;
	}

	public Integer getSort() 
	{
		return sort;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}

	public Integer getWork() {
		return work;
	}

	public void setWork(Integer work) {
		this.work = work;
	}

	public void setNetWorkId(String netWorkId)
	{
		this.netWorkId = netWorkId;
	}

	public String getNetWorkId() 
	{
		return netWorkId;
	}
	public void setNetWorkCode(String netWorkCode) 
	{
		this.netWorkCode = netWorkCode;
	}

	public String getNetWorkCode() 
	{
		return netWorkCode;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setCityCode(String cityCode) 
	{
		this.cityCode = cityCode;
	}

	public String getCityCode() 
	{
		return cityCode;
	}
	public void setCityName(String cityName) 
	{
		this.cityName = cityName;
	}

	public String getCityName() 
	{
		return cityName;
	}
	public void setLat(Double lat) 
	{
		this.lat = lat;
	}

	public Double getLat() 
	{
		return lat;
	}
	public void setLng(Double lng) 
	{
		this.lng = lng;
	}

	public Double getLng() 
	{
		return lng;
	}
	public void setOrderType(Integer orderType) 
	{
		this.orderType = orderType;
	}

	public Integer getOrderType() 
	{
		return orderType;
	}
	public void setOrderGuid(String orderGuid) 
	{
		this.orderGuid = orderGuid;
	}

	public String getOrderGuid() 
	{
		return orderGuid;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setStatusValue(String statusValue) 
	{
		this.statusValue = statusValue;
	}

	public String getStatusValue() 
	{
		return statusValue;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	public String getContact() 
	{
		return contact;
	}
	public void setContactMobile(String contactMobile) 
	{
		this.contactMobile = contactMobile;
	}

	public String getContactMobile() 
	{
		return contactMobile;
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

	public Date getActualStartTime() {
		return actualStartTime;
	}

	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public Date getActualWorkTime() {
		return actualWorkTime;
	}

	public void setActualWorkTime(Date actualWorkTime) {
		this.actualWorkTime = actualWorkTime;
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

	public Date getActualFinishTime() {
		return actualFinishTime;
	}

	public void setActualFinishTime(Date actualFinishTime) {
		this.actualFinishTime = actualFinishTime;
	}

	public void setActualLat(Double actualLat)
	{
		this.actualLat = actualLat;
	}

	public Double getActualLat() 
	{
		return actualLat;
	}
	public void setActualLng(Double actualLng) 
	{
		this.actualLng = actualLng;
	}

	public Double getActualLng() 
	{
		return actualLng;
	}
	public void setStartPoint(Integer startPoint) 
	{
		this.startPoint = startPoint;
	}

	public Integer getActualRuningDuration() {
		return actualRuningDuration;
	}

	public void setActualRuningDuration(Integer actualRuningDuration) {
		this.actualRuningDuration = actualRuningDuration;
	}

	public Integer getActualRuningMileage() {
		return actualRuningMileage;
	}

	public void setActualRuningMileage(Integer actualRuningMileage) {
		this.actualRuningMileage = actualRuningMileage;
	}

	public Integer getStartPoint()
	{
		return startPoint;
	}
	public void setEndPoint(Integer endPoint) 
	{
		this.endPoint = endPoint;
	}

	public Integer getEndPoint() 
	{
		return endPoint;
	}
	public void setSign(Integer sign) 
	{
		this.sign = sign;
	}

	public Integer getSign() 
	{
		return sign;
	}
	public void setDelete(Integer delete) 
	{
		this.delete = delete;
	}

	public Integer getDelete() 
	{
		return delete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("guid", getGuid())
            .append("taskId", getTaskId())
            .append("sort", getSort())
            .append("type", getType())
            .append("netWorkId", getNetWorkId())
            .append("netWorkCode", getNetWorkCode())
            .append("name", getName())
            .append("address", getAddress())
            .append("cityCode", getCityCode())
            .append("cityName", getCityName())
            .append("lat", getLat())
            .append("lng", getLng())
            .append("orderType", getOrderType())
            .append("orderGuid", getOrderGuid())
            .append("status", getStatus())
            .append("statusValue", getStatusValue())
            .append("contact", getContact())
            .append("contactMobile", getContactMobile())
            .append("planDepartTime", getPlanDepartTime())
            .append("planArriveTime", getPlanArriveTime())
            .append("actualDepartTime", getActualDepartTime())
            .append("actualArriveTime", getActualArriveTime())
            .append("actualLat", getActualLat())
            .append("actualLng", getActualLng())
            .append("startPoint", getStartPoint())
            .append("endPoint", getEndPoint())
            .append("sign", getSign())
            .append("createTime", getCreateTime())
            .append("delete", getDelete())
            .toString();
    }
}
