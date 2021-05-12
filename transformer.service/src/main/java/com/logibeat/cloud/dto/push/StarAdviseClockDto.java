package com.logibeat.cloud.dto.push;

public class StarAdviseClockDto {
	
	private String SignPhoto;//打卡照片(阿里云附件地址)
	
	private String SignTime;//打卡时间
	
	private int Longitude;//经度 (经度* 36000)
	
	private int Latitude;//纬度 (纬度* 36000)
	
	private String Address;//地址
	
	private String PlaceName;//地点
	
	public String getPlaceName() {
		return PlaceName;
	}

	public void setPlaceName(String placeName) {
		PlaceName = placeName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getSignPhoto() {
		return SignPhoto;
	}

	public void setSignPhoto(String signPhoto) {
		SignPhoto = signPhoto;
	}

	public String getSignTime() {
		return SignTime;
	}

	public void setSignTime(String signTime) {
		SignTime = signTime;
	}

	public int getLongitude() {
		return Longitude;
	}

	public void setLongitude(int longitude) {
		Longitude = longitude;
	}

	public int getLatitude() {
		return Latitude;
	}

	public void setLatitude(int latitude) {
		Latitude = latitude;
	}
	
	

}
