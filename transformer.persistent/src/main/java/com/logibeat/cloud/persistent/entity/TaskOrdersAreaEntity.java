package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;

public class TaskOrdersAreaEntity extends EntitySerialize {
    private String guid;

    private String ordersCID;

    private Integer yyyymmdd;

    private String entID;

    private String pointLineGUID;

    private Integer sortNum;

    private Timestamp planArriveTime;

    private Timestamp planLeavTime;

    private Timestamp actualArriveTime;

    private Timestamp actualLeavTime;

    private String contact;

    private Integer mapType;

    private String name;

    private String portray;

    private Integer production;

    private Integer lngLatType;

    private Integer regionCode;

    private Double lng;

    private Double lat;

    private String initial;

    private Integer maxLng;

    private Integer maxLat;

    private Integer minLng;

    private Integer minLat;

    private String address;

    private Integer lineWidth;

    private String lineColor;

    private String fillAreaColor;

    private Integer opacity;

    private String fontColor;

    private Integer fontSize;

    private Integer startPtDis;

    private Integer nextPointLineDistance;

    private Integer nextPointLineTime;

    private Double carDistance;

    private Integer distanceType;

    private Double carTotalOil;

    private Timestamp addTime;

    private Timestamp editTime;

    private String editPersonID;

    private Byte isDelete;

    private Byte isStartPoint;

    private Byte isEndPoint;

    private String contactPersonId;

    private String contactphone;

    private String areaCode;

    private String netWorkGuid;

    private Byte isCurrent;

    private String cityName;

    private String lngLatDettail;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getOrdersCID() {
        return ordersCID;
    }

    public void setOrdersCID(String ordersCID) {
        this.ordersCID = ordersCID;
    }

    public Integer getYyyymmdd() {
        return yyyymmdd;
    }

    public void setYyyymmdd(Integer yyyymmdd) {
        this.yyyymmdd = yyyymmdd;
    }

    public String getEntID() {
        return entID;
    }

    public void setEntID(String entID) {
        this.entID = entID;
    }

    public String getPointLineGUID() {
        return pointLineGUID;
    }

    public void setPointLineGUID(String pointLineGUID) {
        this.pointLineGUID = pointLineGUID;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Timestamp getPlanArriveTime() {
        return planArriveTime;
    }

    public void setPlanArriveTime(Timestamp planArriveTime) {
        this.planArriveTime = planArriveTime;
    }

    public Timestamp getPlanLeavTime() {
        return planLeavTime;
    }

    public void setPlanLeavTime(Timestamp planLeavTime) {
        this.planLeavTime = planLeavTime;
    }

    public Timestamp getActualArriveTime() {
        return actualArriveTime;
    }

    public void setActualArriveTime(Timestamp actualArriveTime) {
        this.actualArriveTime = actualArriveTime;
    }

    public Timestamp getActualLeavTime() {
        return actualLeavTime;
    }

    public void setActualLeavTime(Timestamp actualLeavTime) {
        this.actualLeavTime = actualLeavTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getMapType() {
        return mapType;
    }

    public void setMapType(Integer mapType) {
        this.mapType = mapType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortray() {
        return portray;
    }

    public void setPortray(String portray) {
        this.portray = portray;
    }

    public Integer getProduction() {
        return production;
    }

    public void setProduction(Integer production) {
        this.production = production;
    }

    public Integer getLngLatType() {
        return lngLatType;
    }

    public void setLngLatType(Integer lngLatType) {
        this.lngLatType = lngLatType;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
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

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public Integer getMaxLng() {
        return maxLng;
    }

    public void setMaxLng(Integer maxLng) {
        this.maxLng = maxLng;
    }

    public Integer getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(Integer maxLat) {
        this.maxLat = maxLat;
    }

    public Integer getMinLng() {
        return minLng;
    }

    public void setMinLng(Integer minLng) {
        this.minLng = minLng;
    }

    public Integer getMinLat() {
        return minLat;
    }

    public void setMinLat(Integer minLat) {
        this.minLat = minLat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(Integer lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public String getFillAreaColor() {
        return fillAreaColor;
    }

    public void setFillAreaColor(String fillAreaColor) {
        this.fillAreaColor = fillAreaColor;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public void setOpacity(Integer opacity) {
        this.opacity = opacity;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getStartPtDis() {
        return startPtDis;
    }

    public void setStartPtDis(Integer startPtDis) {
        this.startPtDis = startPtDis;
    }

    public Integer getNextPointLineDistance() {
        return nextPointLineDistance;
    }

    public void setNextPointLineDistance(Integer nextPointLineDistance) {
        this.nextPointLineDistance = nextPointLineDistance;
    }

    public Integer getNextPointLineTime() {
        return nextPointLineTime;
    }

    public void setNextPointLineTime(Integer nextPointLineTime) {
        this.nextPointLineTime = nextPointLineTime;
    }

    public Double getCarDistance() {
        return carDistance;
    }

    public void setCarDistance(Double carDistance) {
        this.carDistance = carDistance;
    }

    public Integer getDistanceType() {
        return distanceType;
    }

    public void setDistanceType(Integer distanceType) {
        this.distanceType = distanceType;
    }

    public Double getCarTotalOil() {
        return carTotalOil;
    }

    public void setCarTotalOil(Double carTotalOil) {
        this.carTotalOil = carTotalOil;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public String getEditPersonID() {
        return editPersonID;
    }

    public void setEditPersonID(String editPersonID) {
        this.editPersonID = editPersonID;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getIsStartPoint() {
        return isStartPoint;
    }

    public void setIsStartPoint(Byte isStartPoint) {
        this.isStartPoint = isStartPoint;
    }

    public Byte getIsEndPoint() {
        return isEndPoint;
    }

    public void setIsEndPoint(Byte isEndPoint) {
        this.isEndPoint = isEndPoint;
    }

    public String getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(String contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNetWorkGuid() {
        return netWorkGuid;
    }

    public void setNetWorkGuid(String netWorkGuid) {
        this.netWorkGuid = netWorkGuid;
    }

    public Byte getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Byte isCurrent) {
        this.isCurrent = isCurrent;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLngLatDettail() {
        return lngLatDettail;
    }

    public void setLngLatDettail(String lngLatDettail) {
        this.lngLatDettail = lngLatDettail;
    }
}