package com.logibeat.cloud.persistent.entity;
import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

/**
 * 表 traffic_advise_clock_log
 * 
 * @author wilson
 * @date 2021-03-26
 */
public class TrafficAdviseClockLogEntity extends EntitySerialize
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String guid;
	/**  */
	private String adviseId;
	/**  */
	private Double lng;
	/**  */
	private Double lat;
	/**  */
	private String picUrl;
	
	private Date createTime;
	
	private String address;
	
	private String placeName;
	
	private String curDate;//查询用
	
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getCurDate() {
		return curDate;
	}

	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setGuid(String guid) 
	{
		this.guid = guid;
	}

	public String getGuid() 
	{
		return guid;
	}
	public void setAdviseId(String adviseId) 
	{
		this.adviseId = adviseId;
	}

	public String getAdviseId() 
	{
		return adviseId;
	}
	public void setLng(Double lng) 
	{
		this.lng = lng;
	}

	public Double getLng() 
	{
		return lng;
	}
	public void setLat(Double lat) 
	{
		this.lat = lat;
	}

	public Double getLat() 
	{
		return lat;
	}
	public void setPicUrl(String picUrl) 
	{
		this.picUrl = picUrl;
	}

	public String getPicUrl() 
	{
		return picUrl;
	}

}
