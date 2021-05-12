package com.logibeat.cloud.vo.task;

import java.util.Date;

public class TaskPointVo {

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
     * 发车时间
     */
    private Date departTime;

    /**
     * 到达时间
     */
    private Date arriveTime;

    /**
     * 完成时间
     */
    private Date finishTime;


    /**
     * 是否签收
     */
    private boolean sign;


    /**
     * 需要签收的托运单id
     */
    private String signConsignId;


    /**
     * 是否完成
     */
    private boolean finish;

    /**
     * 是否到达
     */
    private boolean arrive;

    /**
     * 派单员电话
     */
    private String entrustPersonMobile;


    /**
     * 实际里程(米)
     */
    private Integer actualMileage=0;


    /**
     * 实际行驶时长(秒)
     */
    private Integer actualWorkTime=0;


    /**
     * 小提示
     */
    private String tip;


    /**
     * 操作按钮
     */
    private TaskPointButtonVo button;


    /**
     * 签收二维码 url
     */
    private String signCodeUrl;


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

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    public String getSignConsignId() {
        return signConsignId;
    }

    public void setSignConsignId(String signConsignId) {
        this.signConsignId = signConsignId;
    }

    public TaskPointButtonVo getButton() {
        return button;
    }

    public void setButton(TaskPointButtonVo button) {
        this.button = button;
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


    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }


    public boolean isArrive() {
        return arrive;
    }

    public void setArrive(boolean arrive) {
        this.arrive = arrive;
    }


    public Integer getActualMileage() {
        return actualMileage;
    }

    public void setActualMileage(Integer actualMileage) {
        this.actualMileage = actualMileage;
    }

    public Integer getActualWorkTime() {
        return actualWorkTime;
    }

    public void setActualWorkTime(Integer actualWorkTime) {
        this.actualWorkTime = actualWorkTime;
    }

    public String getEntrustPersonMobile() {
        return entrustPersonMobile;
    }

    public void setEntrustPersonMobile(String entrustPersonMobile) {
        this.entrustPersonMobile = entrustPersonMobile;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getSignCodeUrl() {
        return signCodeUrl;
    }

    public void setSignCodeUrl(String signCodeUrl) {
        this.signCodeUrl = signCodeUrl;
    }
}
