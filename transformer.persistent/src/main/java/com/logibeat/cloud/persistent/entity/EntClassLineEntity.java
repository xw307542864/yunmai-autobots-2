package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class EntClassLineEntity extends EntitySerialize {
    private String guid;

    private String entId;

    private String lineName;

    private String lineNamePinyin;

    private String startNetworkId;

    private String endNetworkId;

    private Integer networkCount;

    private Double expectsMileage;

    private Integer duration;

    private Date addTime;

    private String addPersonId;

    private Date editTime;

    private String editPersonId;

    private Byte isDelete;

    private Date deleteTime;

    private Integer lineType;

    private Byte isDisplay;

    private String serviceGuids;

    private String transportTypeGuids;

    private String startNetwordGuids;

    private String endNetwordGuids;

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

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineNamePinyin() {
        return lineNamePinyin;
    }

    public void setLineNamePinyin(String lineNamePinyin) {
        this.lineNamePinyin = lineNamePinyin;
    }

    public String getStartNetworkId() {
        return startNetworkId;
    }

    public void setStartNetworkId(String startNetworkId) {
        this.startNetworkId = startNetworkId;
    }

    public String getEndNetworkId() {
        return endNetworkId;
    }

    public void setEndNetworkId(String endNetworkId) {
        this.endNetworkId = endNetworkId;
    }

    public Integer getNetworkCount() {
        return networkCount;
    }

    public void setNetworkCount(Integer networkCount) {
        this.networkCount = networkCount;
    }

    public Double getExpectsMileage() {
        return expectsMileage;
    }

    public void setExpectsMileage(Double expectsMileage) {
        this.expectsMileage = expectsMileage;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public Integer getLineType() {
        return lineType;
    }

    public void setLineType(Integer lineType) {
        this.lineType = lineType;
    }

    public Byte getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Byte isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getServiceGuids() {
        return serviceGuids;
    }

    public void setServiceGuids(String serviceGuids) {
        this.serviceGuids = serviceGuids;
    }

    public String getTransportTypeGuids() {
        return transportTypeGuids;
    }

    public void setTransportTypeGuids(String transportTypeGuids) {
        this.transportTypeGuids = transportTypeGuids;
    }

    public String getStartNetwordGuids() {
        return startNetwordGuids;
    }

    public void setStartNetwordGuids(String startNetwordGuids) {
        this.startNetwordGuids = startNetwordGuids;
    }

    public String getEndNetwordGuids() {
        return endNetwordGuids;
    }

    public void setEndNetwordGuids(String endNetwordGuids) {
        this.endNetwordGuids = endNetwordGuids;
    }
}