package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;
import java.util.List;

public class DynamicTaskInfoVo implements Comparable<DynamicTaskInfoVo>{
    //事件动作 
    private int eventAction;

    //事件动作描述
    private String eventActionStr;
    
    //位置
    private String address;

    private String areaCode;

    private String areaName;
    
    private List<String> pics;
    
    //文本内容 
    private String content;
    
    //纬度
    private Double lat; 
    
    //经度 
    private Double lng;
    
    private String personLogo;

    private String personName;
    
    private Timestamp dateTime;
    
    private String plateNumber;
    
    private String entName;

    //是否指定地点发车false：否 true：是
    private Boolean isAtPoint;
    
    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getEventAction() {
        return eventAction;
    }

    public void setEventAction(int eventAction) {
        this.eventAction = eventAction;
    }

    public String getEventActionStr() {
        return eventActionStr;
    }

    public void setEventActionStr(String eventActionStr) {
        this.eventActionStr = eventActionStr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    

    public String getPersonLogo() {
        return personLogo;
    }

    public void setPersonLogo(String personLogo) {
        this.personLogo = personLogo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

	@Override
	public int compareTo(DynamicTaskInfoVo o) {
		return this.getDateTime().compareTo(o.getDateTime());
	}

    public Boolean getIsAtPoint() {
        return isAtPoint;
    }

    public void setIsAtPoint(Boolean isAtPoint) {
        this.isAtPoint = isAtPoint;
    }
}
