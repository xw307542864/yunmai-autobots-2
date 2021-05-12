package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;
import java.util.List;

public class TaskPointVo {

	private Double lat;
	
	private Double lng;
	
	private String address;
	
	private Timestamp time;

	private Long addTime;
	
	private Integer action;
	
	private String actionName;
	
	private String dynamicId;
	
	private Integer dynamicType;
	
	private String dynamicPic;

	private String content;
	
	private List<String> dynamicPics;
	
	private String driverLogo;

	private String driverName;

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(String dynamicId) {
		this.dynamicId = dynamicId;
	}

	public String getDynamicPic() {
		return dynamicPic;
	}

	public void setDynamicPic(String dynamicPic) {
		this.dynamicPic = dynamicPic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getDynamicPics() {
		return dynamicPics;
	}

	public void setDynamicPics(List<String> dynamicPics) {
		this.dynamicPics = dynamicPics;
	}

	public String getDriverLogo() {
		return driverLogo;
	}

	public void setDriverLogo(String driverLogo) {
		this.driverLogo = driverLogo;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Integer getDynamicType() {
		return dynamicType;
	}

	public void setDynamicType(Integer dynamicType) {
		this.dynamicType = dynamicType;
	}

	
}
