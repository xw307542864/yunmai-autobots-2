package com.logibeat.cloud.vo.task;


import com.logibeat.cloud.persistent.entity.DriverTaskEntity;

public class CreateTaskByOutVo {

    private DriverTaskEntity driverTaskEntity;

    private String action;

    public DriverTaskEntity getDriverTaskEntity() {
        return driverTaskEntity;
    }

    public void setDriverTaskEntity(DriverTaskEntity driverTaskEntity) {
        this.driverTaskEntity = driverTaskEntity;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
