package com.logibeat.cloud.persistent.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 表 driver_task_info
 * 
 * @author wilson
 * @date 2020-04-14
 */
public class DriverTaskInfoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**  */
	private String guid;
	/**  */
	private String taskId;
	/** 单据数量 （托运单 订单） */
	private Integer orderNum;
	/** 始发网点id  整车没有网点 */
	private String originalNetworkId;
	/**  */
	private String originalNetworkName;
	/**  */
	private String originalCityCode;
	/**  */
	private String originalCity;
	/**  */
	private String originalAddress;
	/**  */
	private Double originalLat;
	/**  */
	private Double originalLng;
	/**  */
	private String originatContact;
	/**  */
	private String originatPhone;
	/**  */
	private String destinationNetworkId;
	/**  */
	private String destinationNetworkName;
	/**  */
	private String destinationCityCode;
	/**  */
	private String destinationCity;
	/**  */
	private String destinationAddress;
	/**  */
	private Double destinationLat;
	/**  */
	private Double destinationLng;
	/**  */
	private String destinationContact;
	/**  */
	private String destinationPhone;
	/**
	 *
	 */
	private Integer queueNumber;
	/** 三方系统同步id */
	private String outKeyId;
	/**  */
	private String outDestinationId;

	/**
	 * 货物名称
	 */
	private String cargoName;

	/**
	 * 货物件数
	 */
	private Integer cargoNum;

	/**
	 * 货物重量
	 */
	private Double cargoWeight;

	/**
	 * 货物体积
	 */
	private Double cargoVolume;

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
	public void setOrderNum(Integer orderNum) 
	{
		this.orderNum = orderNum;
	}

	public Integer getOrderNum() 
	{
		return orderNum;
	}
	public void setOriginalNetworkId(String originalNetworkId) 
	{
		this.originalNetworkId = originalNetworkId;
	}

	public String getOriginalNetworkId() 
	{
		return originalNetworkId;
	}
	public void setOriginalNetworkName(String originalNetworkName) 
	{
		this.originalNetworkName = originalNetworkName;
	}

	public String getOriginalNetworkName() 
	{
		return originalNetworkName;
	}
	public void setOriginalCityCode(String originalCityCode) 
	{
		this.originalCityCode = originalCityCode;
	}

	public String getOriginalCityCode() 
	{
		return originalCityCode;
	}
	public void setOriginalCity(String originalCity) 
	{
		this.originalCity = originalCity;
	}

	public String getOriginalCity() 
	{
		return originalCity;
	}
	public void setOriginalAddress(String originalAddress) 
	{
		this.originalAddress = originalAddress;
	}

	public String getOriginalAddress() 
	{
		return originalAddress;
	}
	public void setOriginalLat(Double originalLat) 
	{
		this.originalLat = originalLat;
	}

	public Double getOriginalLat() 
	{
		return originalLat;
	}
	public void setOriginalLng(Double originalLng) 
	{
		this.originalLng = originalLng;
	}

	public Double getOriginalLng() 
	{
		return originalLng;
	}
	public void setOriginatContact(String originatContact) 
	{
		this.originatContact = originatContact;
	}

	public String getOriginatContact() 
	{
		return originatContact;
	}
	public void setOriginatPhone(String originatPhone) 
	{
		this.originatPhone = originatPhone;
	}

	public String getOriginatPhone() 
	{
		return originatPhone;
	}
	public void setDestinationNetworkId(String destinationNetworkId) 
	{
		this.destinationNetworkId = destinationNetworkId;
	}

	public String getDestinationNetworkId() 
	{
		return destinationNetworkId;
	}
	public void setDestinationNetworkName(String destinationNetworkName) 
	{
		this.destinationNetworkName = destinationNetworkName;
	}

	public String getDestinationNetworkName() 
	{
		return destinationNetworkName;
	}
	public void setDestinationCityCode(String destinationCityCode) 
	{
		this.destinationCityCode = destinationCityCode;
	}

	public String getDestinationCityCode() 
	{
		return destinationCityCode;
	}
	public void setDestinationCity(String destinationCity) 
	{
		this.destinationCity = destinationCity;
	}

	public String getDestinationCity() 
	{
		return destinationCity;
	}
	public void setDestinationAddress(String destinationAddress) 
	{
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationAddress() 
	{
		return destinationAddress;
	}
	public void setDestinationLat(Double destinationLat) 
	{
		this.destinationLat = destinationLat;
	}

	public Double getDestinationLat() 
	{
		return destinationLat;
	}
	public void setDestinationLng(Double destinationLng) {
		this.destinationLng = destinationLng;
	}

	public Double getDestinationLng()
	{
		return destinationLng;
	}
	public void setDestinationContact(String destinationContact) 
	{
		this.destinationContact = destinationContact;
	}

	public String getDestinationContact() 
	{
		return destinationContact;
	}
	public void setDestinationPhone(String destinationPhone) 
	{
		this.destinationPhone = destinationPhone;
	}

	public String getDestinationPhone() 
	{
		return destinationPhone;
	}
	public void setOutKeyId(String outKeyId) 
	{
		this.outKeyId = outKeyId;
	}

	public String getOutKeyId() 
	{
		return outKeyId;
	}
	public void setOutDestinationId(String outDestinationId) 
	{
		this.outDestinationId = outDestinationId;
	}

	public String getOutDestinationId() 
	{
		return outDestinationId;
	}


	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public Integer getCargoNum() {
		return cargoNum;
	}

	public void setCargoNum(Integer cargoNum) {
		this.cargoNum = cargoNum;
	}

	public Double getCargoWeight() {
		return cargoWeight;
	}

	public void setCargoWeight(Double cargoWeight) {
		this.cargoWeight = cargoWeight;
	}

	public Double getCargoVolume() {
		return cargoVolume;
	}

	public void setCargoVolume(Double cargoVolume) {
		this.cargoVolume = cargoVolume;
	}

	public Integer getQueueNumber() {
		return queueNumber;
	}

	public void setQueueNumber(Integer queueNumber) {
		this.queueNumber = queueNumber;
	}

	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("guid", getGuid())
            .append("taskId", getTaskId())
            .append("orderNum", getOrderNum())
            .append("originalNetworkId", getOriginalNetworkId())
            .append("originalNetworkName", getOriginalNetworkName())
            .append("originalCityCode", getOriginalCityCode())
            .append("originalCity", getOriginalCity())
            .append("originalAddress", getOriginalAddress())
            .append("originalLat", getOriginalLat())
            .append("originalLng", getOriginalLng())
            .append("originatContact", getOriginatContact())
            .append("originatPhone", getOriginatPhone())
            .append("destinationNetworkId", getDestinationNetworkId())
            .append("destinationNetworkName", getDestinationNetworkName())
            .append("destinationCityCode", getDestinationCityCode())
            .append("destinationCity", getDestinationCity())
            .append("destinationAddress", getDestinationAddress())
            .append("destinationLat", getDestinationLat())
            .append("destinationLng", getDestinationLng())
            .append("destinationContact", getDestinationContact())
            .append("destinationPhone", getDestinationPhone())
            .append("outKeyId", getOutKeyId())
            .append("outDestinationId", getOutDestinationId())
            .toString();
    }
}
