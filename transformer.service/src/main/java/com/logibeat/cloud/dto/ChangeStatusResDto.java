package com.logibeat.cloud.dto;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

public class ChangeStatusResDto extends EntitySerialize {

    private String id;

    private String entKeyId;

    private String ordersId;

    private Integer currentStatus;

    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntKeyId() {
        return entKeyId;
    }

    public void setEntKeyId(String entKeyId) {
        this.entKeyId = entKeyId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
