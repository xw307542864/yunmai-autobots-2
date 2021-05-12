package com.logibeat.cloud.dto.task;

import com.logibeat.cloud.persistent.entity.DriverTaskEntity;

public class CreateTaskNodeDto {

    /**
     * 司机端操作
     */
    private Integer action;


    /**
     * 任务单id
     */
    private String taskId;

    /**
     * 装卸点id
     */
    private String pointId;


    /**
     *出发点纬度
     */
    private Double fromLat;

    /**
     * 出发点经度
     */
    private Double fromLng;

    /**
     * 出发点地址
     */
    private String fromAddress;


    /**
     * 到达点纬度
     */
    private Double toLat;

    /**
     * 到达点经度
     */
    private Double toLng;

    /**
     * 到达点地址
     */
    private String toAddress;


    /**
     * 发车后 预计到达时间（qianduan）
     */
    private String planArriveTime;

    /**
     * 是否起始操作
     */
    private boolean start;


    /**
     * 是否需要签收
     */
    private boolean sign;


    /**
     * 装卸点对应的单据id
     */
    private String pointRelationOrderId;


    /**
     * 装卸点对应单据的状态
     */
    private Integer pointRelationOrderStatus;


    /**
     * 整车托运单拼装 上一个点对应的托运单id
     */
    private String previousPointRelationOrderId;

    /**
     * 整车托运单拼装 上一个点对应的托运单状态
     */
    private Integer previousPointRelationOrderStatus;


    private DriverTaskEntity driverTask;


    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
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

    public Double getFromLat() {
        return fromLat;
    }

    public void setFromLat(Double fromLat) {
        this.fromLat = fromLat;
    }

    public Double getFromLng() {
        return fromLng;
    }

    public void setFromLng(Double fromLng) {
        this.fromLng = fromLng;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public Double getToLat() {
        return toLat;
    }

    public void setToLat(Double toLat) {
        this.toLat = toLat;
    }

    public Double getToLng() {
        return toLng;
    }

    public void setToLng(Double toLng) {
        this.toLng = toLng;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getPlanArriveTime() {
        return planArriveTime;
    }

    public void setPlanArriveTime(String planArriveTime) {
        this.planArriveTime = planArriveTime;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    public String getPointRelationOrderId() {
        return pointRelationOrderId;
    }

    public void setPointRelationOrderId(String pointRelationOrderId) {
        this.pointRelationOrderId = pointRelationOrderId;
    }

    public Integer getPointRelationOrderStatus() {
        return pointRelationOrderStatus;
    }

    public void setPointRelationOrderStatus(Integer pointRelationOrderStatus) {
        this.pointRelationOrderStatus = pointRelationOrderStatus;
    }

    public DriverTaskEntity getDriverTask() {
        return driverTask;
    }

    public void setDriverTask(DriverTaskEntity driverTask) {
        this.driverTask = driverTask;
    }

    public String getPreviousPointRelationOrderId() {
        return previousPointRelationOrderId;
    }

    public void setPreviousPointRelationOrderId(String previousPointRelationOrderId) {
        this.previousPointRelationOrderId = previousPointRelationOrderId;
    }

    public Integer getPreviousPointRelationOrderStatus() {
        return previousPointRelationOrderStatus;
    }

    public void setPreviousPointRelationOrderStatus(Integer previousPointRelationOrderStatus) {
        this.previousPointRelationOrderStatus = previousPointRelationOrderStatus;
    }
}
