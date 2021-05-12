package com.logibeat.cloud.common.vo;

public class EntNetWorkVo {
	private String guid; //网点GUID（新增时不需要赋值）

    private String entId;//网点所属企业

    private String name; //网点名称
    private String address; //详细地址
    private String contactName; //网点联系人姓名
    private String contactPhone; //网点联系人电话

    private Double lat; //纬度(保留8位小数)
    private Double lng; //经度(保留8位小数)

    private String regionCode; //省市县编码

    private int sortNum;//网点顺序

    private String networkNamePinyin;
    private String networkAreainfo;//省市名称
    private Double near;//距离（米），139860.28
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	public String getNetworkNamePinyin() {
		return networkNamePinyin;
	}
	public void setNetworkNamePinyin(String networkNamePinyin) {
		this.networkNamePinyin = networkNamePinyin;
	}
	public String getNetworkAreainfo() {
		return networkAreainfo;
	}
	public void setNetworkAreainfo(String networkAreainfo) {
		this.networkAreainfo = networkAreainfo;
	}
	public Double getNear() {
		return near;
	}
	public void setNear(Double near) {
		this.near = near;
	}
    
    
}
