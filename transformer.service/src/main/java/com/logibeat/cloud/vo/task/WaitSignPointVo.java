package com.logibeat.cloud.vo.task;

public class WaitSignPointVo {

    /**
     * 装卸点id
     */
    private String pointId;

    /**
     * 装卸点类型 100：装 200：卸
     */
    private Integer type;

    /**
     * 联系单位
     */
    private String name;

    /**
     * 城市编码
     */
    private String city;

    /**
     * 地址
     */
    private String address;


    /**
     * 经纬度
     */
    private Double lat;

    /**
     * 经纬度
     */
    private Double lng;


    /**
     * 联系人
     */
    private String contact;


    /**
     * 联系人电话
     */
    private String contactMobile;


    /**
     * 是否签收
     */
    private boolean sign;


    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }
}
