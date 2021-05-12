package com.logibeat.cloud.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

/**
 * GPS数据DTO

* @Title: Driver_GpsShortInfo.java

* @Package com.yunmaigo.qtz.driver.dto

* @Description: TODO(用一句话描述该文件做什么)

* @author Wilson   

* @date 2016年1月6日 下午2:26:01  

* @version V1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GpsShortInfoDto {

	private Timestamp lastGpsTime;

	private Double lng;

	private Double lat;

	private String address;

	public Timestamp getLastGpsTime() {
		return lastGpsTime;
	}

	public void setLastGpsTime(Timestamp lastGpsTime) {
		this.lastGpsTime = lastGpsTime;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
