package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class EntClassLineNetworkEntity extends EntitySerialize {
    private String guid;

    private String entId;

    private String lineId;

    private String networkId;

    private Integer sequence;

    private Date addTime;

    private String addPersonId;

    private Date editTime;

    private String editPersonId;

    private Byte isDelete;

    private Date deleteTime;

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

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddPersonId() {
        return addPersonId;
    }

    public void setAddPersonId(String addPersonId) {
        this.addPersonId = addPersonId;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditPersonId() {
        return editPersonId;
    }

    public void setEditPersonId(String editPersonId) {
        this.editPersonId = editPersonId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}