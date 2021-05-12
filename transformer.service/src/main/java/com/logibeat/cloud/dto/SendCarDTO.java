package com.logibeat.cloud.dto;



import com.logibeat.cloud.persistent.entity.TaskOrdersEntity;

import java.sql.Timestamp;

/**
 * Created by wilson on 2017/8/1.
 */
public class SendCarDTO {

    private String sourcePersonId;

    private String  sourceEntId;

    private String ordersid;

    private String carID;

    private String plateNumber;

    private String firstDriverID;

    private String firstDriverName;

    private String firstDriverMobile;

    private String secondDriverID;

    private String secondDriverName;

    private String secondDriverMobile;

    private Integer taskStatus;

    private boolean sysAutoCarrier;  //系统接单

    private boolean sysAutoDepart; //系统发车
    
    private Timestamp startAreaActualLeaveTime;
    
    private Byte isAutoDepart;
    
    private Byte isAutoArrive;

    private Integer taskType;
    
    private String outType;

    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    private TaskOrdersEntity taskEntrustEntity;//派单给第三方企业实体
    
    private TaskOrdersEntity firstTaskEntity;//首单实体

    public String getSourcePersonId() {
        return sourcePersonId;
    }

    public void setSourcePersonId(String sourcePersonId) {
        this.sourcePersonId = sourcePersonId;
    }

    public String getSourceEntId() {
        return sourceEntId;
    }

    public void setSourceEntId(String sourceEntId) {
        this.sourceEntId = sourceEntId;
    }

    public String getOrdersid() {
        return ordersid;
    }

    public void setOrdersid(String ordersid) {
        this.ordersid = ordersid;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getFirstDriverID() {
        return firstDriverID;
    }

    public void setFirstDriverID(String firstDriverID) {
        this.firstDriverID = firstDriverID;
    }

    public String getFirstDriverName() {
        return firstDriverName;
    }

    public void setFirstDriverName(String firstDriverName) {
        this.firstDriverName = firstDriverName;
    }

    public String getFirstDriverMobile() {
        return firstDriverMobile;
    }

    public void setFirstDriverMobile(String firstDriverMobile) {
        this.firstDriverMobile = firstDriverMobile;
    }

    public String getSecondDriverID() {
        return secondDriverID;
    }

    public void setSecondDriverID(String secondDriverID) {
        this.secondDriverID = secondDriverID;
    }

    public String getSecondDriverName() {
        return secondDriverName;
    }

    public void setSecondDriverName(String secondDriverName) {
        this.secondDriverName = secondDriverName;
    }

    public String getSecondDriverMobile() {
        return secondDriverMobile;
    }

    public void setSecondDriverMobile(String secondDriverMobile) {
        this.secondDriverMobile = secondDriverMobile;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public boolean isSysAutoCarrier() {
        return sysAutoCarrier;
    }

    public void setSysAutoCarrier(boolean sysAutoCarrier) {
        this.sysAutoCarrier = sysAutoCarrier;
    }

    public boolean isSysAutoDepart() {
        return sysAutoDepart;
    }

    public void setSysAutoDepart(boolean sysAutoDepart) {
        this.sysAutoDepart = sysAutoDepart;
    }

	public Timestamp getStartAreaActualLeaveTime() {
		return startAreaActualLeaveTime;
	}

	public void setStartAreaActualLeaveTime(Timestamp startAreaActualLeaveTime) {
		this.startAreaActualLeaveTime = startAreaActualLeaveTime;
	}

	public Byte getIsAutoDepart() {
		return isAutoDepart;
	}

	public void setIsAutoDepart(Byte isAutoDepart) {
		this.isAutoDepart = isAutoDepart;
	}

	public Byte getIsAutoArrive() {
		return isAutoArrive;
	}

	public void setIsAutoArrive(Byte isAutoArrive) {
		this.isAutoArrive = isAutoArrive;
	}

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

	public String getOutType() {
		return outType;
	}

	public void setOutType(String outType) {
		this.outType = outType;
	}

	public TaskOrdersEntity getTaskEntrustEntity() {
		return taskEntrustEntity;
	}

	public void setTaskEntrustEntity(TaskOrdersEntity taskEntrustEntity) {
		this.taskEntrustEntity = taskEntrustEntity;
	}

	public TaskOrdersEntity getFirstTaskEntity() {
		return firstTaskEntity;
	}

	public void setFirstTaskEntity(TaskOrdersEntity firstTaskEntity) {
		this.firstTaskEntity = firstTaskEntity;
	}
    
    
}
