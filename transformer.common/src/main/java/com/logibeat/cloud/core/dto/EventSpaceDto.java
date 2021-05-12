package com.logibeat.cloud.core.dto;

import java.util.Date;

/**
 * Created by wilson on 2017/7/26.
 */
public class EventSpaceDto {

    private String remark;

    private String remark1;

    private String groupId;

    private Integer spaceOnly ;

    private String eventId;

    private Integer eventState;

    private Integer spaceType;

    private Double lng;

    private Double lat;

    private String tag;

    private Integer meter;

    private Integer source;

    private String key;

    private String equipment;

    private Date startTime;

    private Date endTime;

    private Date planStart;

    private Date planEnd;

    private Integer totalPre;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getSpaceOnly() {
        return spaceOnly;
    }

    public void setSpaceOnly(Integer spaceOnly) {
        this.spaceOnly = spaceOnly;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Integer getEventState() {
        return eventState;
    }

    public void setEventState(Integer eventState) {
        this.eventState = eventState;
    }

    public Integer getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(Integer spaceType) {
        this.spaceType = spaceType;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getMeter() {
        return meter;
    }

    public void setMeter(Integer meter) {
        this.meter = meter;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getPlanStart() {
        return planStart;
    }

    public void setPlanStart(Date planStart) {
        this.planStart = planStart;
    }

    public Date getPlanEnd() {
        return planEnd;
    }

    public void setPlanEnd(Date planEnd) {
        this.planEnd = planEnd;
    }

    public Integer getTotalPre() {
        return totalPre;
    }

    public void setTotalPre(Integer totalPre) {
        this.totalPre = totalPre;
    }
}
