package com.logibeat.cloud.core.dto;

/**
 * Created by wilson on 2017/8/4.
 */
public class EntrustDTO {

    private String baseUserId;

    private String baseEntId;

    private String entId;

    private String ordersid;

    private boolean isAutoCarrier;

    private Integer taskStatus;

    public String getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getBaseEntId() {
        return baseEntId;
    }

    public void setBaseEntId(String baseEntId) {
        this.baseEntId = baseEntId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getOrdersid() {
        return ordersid;
    }

    public void setOrdersid(String ordersid) {
        this.ordersid = ordersid;
    }

    public boolean isAutoCarrier() {
        return isAutoCarrier;
    }

    public void setAutoCarrier(boolean autoCarrier) {
        isAutoCarrier = autoCarrier;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
}
