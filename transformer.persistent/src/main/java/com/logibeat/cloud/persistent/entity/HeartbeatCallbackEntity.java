package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class HeartbeatCallbackEntity extends EntitySerialize {
    private String id;

    private String eventId;

    private Date startTime;

    private Date endTime;

    private Date callbackTime;

    private Integer gpsPackNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public Date getCallbackTime() {
        return callbackTime;
    }

    public void setCallbackTime(Date callbackTime) {
        this.callbackTime = callbackTime;
    }

    public Integer getGpsPackNum() {
        return gpsPackNum;
    }

    public void setGpsPackNum(Integer gpsPackNum) {
        this.gpsPackNum = gpsPackNum;
    }
}