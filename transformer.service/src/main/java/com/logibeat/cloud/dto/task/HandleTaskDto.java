package com.logibeat.cloud.dto.task;

public class HandleTaskDto {

    /**
     *
     */
    private String personId;

    /**
     * 派车单id
     */
    private String taskId;

    /**
     * 装卸点id
     */
    private String pointId;


    /**
     * 纬度
     */
    private Double lat;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 地址
     */
    private String address;


    /**
     * 发车 后  预计到达时间（点击去装货、去卸货）
     */
    private String planArriveTime;


    /**
     * 异常发车
     */
    private Integer exceptionDepart = 0;


    /**
     * 异常到达
     */
    private Integer exceptionArrive = 0;


    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlanArriveTime() {
        return planArriveTime;
    }

    public void setPlanArriveTime(String planArriveTime) {
        this.planArriveTime = planArriveTime;
    }

    public Integer getExceptionDepart() {
        return exceptionDepart;
    }

    public void setExceptionDepart(Integer exceptionDepart) {
        this.exceptionDepart = exceptionDepart;
    }

    public Integer getExceptionArrive() {
        return exceptionArrive;
    }

    public void setExceptionArrive(Integer exceptionArrive) {
        this.exceptionArrive = exceptionArrive;
    }
}

