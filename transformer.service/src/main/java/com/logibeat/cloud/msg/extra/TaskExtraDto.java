package com.logibeat.cloud.msg.extra;

public class TaskExtraDto {

    /**
     * 类型
     */
    private Integer bizType;

    /**
     * 任务单号
     */
    private String taskCarNumber;

    /**
     * 派单企业
     */
    private String entrustEntName;

    /**
     * 运输路线
     */
    private String taskTransportRoute;

    /**
     * 装货点
     */
    private String loadPlace;

    /**
     * 当前状态
     */
    private String taskStatus;

    /**
     * 用车时间
     */
    private String vehicleTime;

    /**
     * 跳转URL
     */
    private String linkUrl;


    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getTaskCarNumber() {
        return taskCarNumber;
    }

    public void setTaskCarNumber(String taskCarNumber) {
        this.taskCarNumber = taskCarNumber;
    }

    public String getEntrustEntName() {
        return entrustEntName;
    }

    public void setEntrustEntName(String entrustEntName) {
        this.entrustEntName = entrustEntName;
    }

    public String getTaskTransportRoute() {
        return taskTransportRoute;
    }

    public void setTaskTransportRoute(String taskTransportRoute) {
        this.taskTransportRoute = taskTransportRoute;
    }

    public String getVehicleTime() {
        return vehicleTime;
    }

    public void setVehicleTime(String vehicleTime) {
        this.vehicleTime = vehicleTime;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLoadPlace() {
        return loadPlace;
    }

    public void setLoadPlace(String loadPlace) {
        this.loadPlace = loadPlace;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
