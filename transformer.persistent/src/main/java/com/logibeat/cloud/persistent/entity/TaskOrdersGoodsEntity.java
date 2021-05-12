package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;

public class TaskOrdersGoodsEntity extends EntitySerialize {
    private String guid;

    private Integer yyyymmdd;

    private String entID;

    private String ordersCID;

    private String name;

    private String goodsTypeDictGUID;

    private Double volume;

    private Double weight;

    private Timestamp addTime;

    private Timestamp editTime;

    private String editPersonID;

    private Byte isDelete;

    private String packTypeDictGUID;

    private String remarks;

    private Integer num;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public String getOrdersCID() {
        return ordersCID;
    }

    public void setOrdersCID(String ordersCID) {
        this.ordersCID = ordersCID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodsTypeDictGUID() {
        return goodsTypeDictGUID;
    }

    public void setGoodsTypeDictGUID(String goodsTypeDictGUID) {
        this.goodsTypeDictGUID = goodsTypeDictGUID;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

    public String getPackTypeDictGUID() {
        return packTypeDictGUID;
    }

    public void setPackTypeDictGUID(String packTypeDictGUID) {
        this.packTypeDictGUID = packTypeDictGUID;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}