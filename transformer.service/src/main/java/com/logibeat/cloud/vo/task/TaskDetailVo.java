package com.logibeat.cloud.vo.task;


import com.logibeat.cloud.common.vo.CarGpsSourceVo;

import java.util.Date;
import java.util.List;

public class TaskDetailVo {

    /**
     * 任务单id
     */
    private String taskId;

    /**
     * 单号
     */
    private String number;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 状态
     */
    private Integer status;


    private boolean end;


    /**
     * 企业id
     */
    private String entId;

    /**
     * 车
     */
    private String vehicleId;


    /**
     * 车牌号
     */
    private String vehiclePlateNumber;


    /**
     * 派车单所属组织
     */
    private String orgnId;



    /**
     * 实际里程(米)
     */
    private Integer actualMileage=0;


    /**
     * 实际行驶时长(秒)
     */
    private Integer actualWorkTime=0;


    /**
     * 任务运行时段
     */
    private List<CarGpsSourceVo> carGpsSourceVoList;


    /**
     * 待签收的途经点
     */
    private Integer waitSignCount;




    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public String getOrgnId() {
        return orgnId;
    }

    public void setOrgnId(String orgnId) {
        this.orgnId = orgnId;
    }

    public Integer getActualMileage() {
        return actualMileage;
    }

    public void setActualMileage(Integer actualMileage) {
        this.actualMileage = actualMileage;
    }

    public Integer getActualWorkTime() {
        return actualWorkTime;
    }

    public void setActualWorkTime(Integer actualWorkTime) {
        this.actualWorkTime = actualWorkTime;
    }


    public List<CarGpsSourceVo> getCarGpsSourceVoList() {
        return carGpsSourceVoList;
    }

    public void setCarGpsSourceVoList(List<CarGpsSourceVo> carGpsSourceVoList) {
        this.carGpsSourceVoList = carGpsSourceVoList;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getWaitSignCount() {
        return waitSignCount;
    }

    public void setWaitSignCount(Integer waitSignCount) {
        this.waitSignCount = waitSignCount;
    }
}
