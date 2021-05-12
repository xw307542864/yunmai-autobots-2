package com.logibeat.cloud.dto.task;

public class DriverTaskPointDto {


    /**
     * 100：装 200: 卸
     */
    private Integer type;

    /**
     * 网点（零担配载）
     */
    private String netWorkId;

    /**
     * 网点（零担配载）
     */
    private String netWorkCode;

    /**
     * 装卸点名称（网点、企业）
     */
    private String name;

    /**
     * 装卸点地址
     */
    private String address;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 经纬度
     */
    private Double lat;

    /**
     * 经纬度
     */
    private Double lng;


    /**
     * 关联 单据类型  托运单 订单、预约单
     */
    private Integer orderType;


    /**
     * 关联单据id
     */
    private String orderId;


    /**
     * 联系人（发货人/收货人）
     */
    private String contact;

    /**
     * 联系人（发货人/收货人）手机号
     */
    private String contactMobile;


    /**
     * 当前点的操作状态
     */
    private Integer currentStatus;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNetWorkId() {
        return netWorkId;
    }

    public void setNetWorkId(String netWorkId) {
        this.netWorkId = netWorkId;
    }

    public String getNetWorkCode() {
        return netWorkCode;
    }

    public void setNetWorkCode(String netWorkCode) {
        this.netWorkCode = netWorkCode;
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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }
}
