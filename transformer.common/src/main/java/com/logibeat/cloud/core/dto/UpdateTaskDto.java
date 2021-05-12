package com.logibeat.cloud.core.dto;

/**
 * Created by wilson on 2017/11/30.
 */
public class UpdateTaskDto {

    private String taskId;

    private String taskAreaId;

    private String updateTime;

    private Integer action;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskAreaId() {
        return taskAreaId;
    }

    public void setTaskAreaId(String taskAreaId) {
        this.taskAreaId = taskAreaId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
}
