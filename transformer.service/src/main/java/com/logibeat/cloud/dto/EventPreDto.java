package com.logibeat.cloud.dto;



import com.logibeat.cloud.persistent.entity.TaskOrdersAreaEntity;

import java.util.Date;

/**
 * Created by wilson on 2017/8/2.
 */
public class EventPreDto {
    /**
     * 标识是0：发车/2：到达途径点/3：离开途经点/6：临近N米/7：到达250米/10：其他
     */
    private Integer pointState;

    private String carId;

    private Integer action;

    private TaskOrdersAreaEntity targetArea;

    private Integer isAutoDepart;

    private Integer isAutoArrive;

    private Date planLeaveTime;

    private Date planArriveTime;

    private Integer effectTime;

    private String entId;

    private String taskId;

    private String personId;


    public Integer getPointState() {
        return pointState;
    }

    public void setPointState(Integer pointState) {
        this.pointState = pointState;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public TaskOrdersAreaEntity getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(TaskOrdersAreaEntity targetArea) {
        this.targetArea = targetArea;
    }

    public Integer getIsAutoDepart() {
        return isAutoDepart;
    }

    public void setIsAutoDepart(Integer isAutoDepart) {
        this.isAutoDepart = isAutoDepart;
    }

    public Integer getIsAutoArrive() {
        return isAutoArrive;
    }

    public void setIsAutoArrive(Integer isAutoArrive) {
        this.isAutoArrive = isAutoArrive;
    }

    public Date getPlanLeaveTime() {
        return planLeaveTime;
    }

    public void setPlanLeaveTime(Date planLeaveTime) {
        this.planLeaveTime = planLeaveTime;
    }

    public Date getPlanArriveTime() {
        return planArriveTime;
    }

    public void setPlanArriveTime(Date planArriveTime) {
        this.planArriveTime = planArriveTime;
    }

    public Integer getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Integer effectTime) {
        this.effectTime = effectTime;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
