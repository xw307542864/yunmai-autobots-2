package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

public class EventSpaceLogEntity extends EntitySerialize {
    private String id;

    private String eventType;

    private String eventId;

    private String eventRemark;

    private String eventRemark1;

    private String eventRemark2;

    private String tag;

    private String isSuccess;

    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventRemark() {
        return eventRemark;
    }

    public void setEventRemark(String eventRemark) {
        this.eventRemark = eventRemark;
    }

    public String getEventRemark1() {
        return eventRemark1;
    }

    public void setEventRemark1(String eventRemark1) {
        this.eventRemark1 = eventRemark1;
    }

    public String getEventRemark2() {
        return eventRemark2;
    }

    public void setEventRemark2(String eventRemark2) {
        this.eventRemark2 = eventRemark2;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}