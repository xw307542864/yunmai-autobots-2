package com.logibeat.cloud.core.dto;

/**
 * Created by wilson on 2016/12/7.
 */
public class DynamicGpsDTO {

    private String lastGpsTime;

    private Double lng;

    private Double lat;

    private String address;

	public String getLastGpsTime() {
		return lastGpsTime;
	}

	public void setLastGpsTime(String lastGpsTime) {
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
