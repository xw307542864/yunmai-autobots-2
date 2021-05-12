package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class NewFriendLogDetailEntity extends EntitySerialize {
    private String id;

    private String newfriendlogId;

    private String remark;

    private Date addTime;

    private String personId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewfriendlogId() {
        return newfriendlogId;
    }

    public void setNewfriendlogId(String newfriendlogId) {
        this.newfriendlogId = newfriendlogId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}