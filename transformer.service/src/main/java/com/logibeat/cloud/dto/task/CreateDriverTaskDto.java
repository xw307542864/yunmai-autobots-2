package com.logibeat.cloud.dto.task;

import java.util.Date;
import java.util.List;

public class CreateDriverTaskDto {

    /**
     * 派单人
     */
    private String personId;

    /**
     * 派单企业
     */
    private String entId;


    /**
     * 关联企业id（派给合同车、预约车 ） 合同车/预约车 所属企业
     */
    private String relationEntId;


    /**
     * （派给合同车、预约车 ）上下游公用一张派车单  下游的配载单id
     */
    private String relationStowageId;



    /**
     * 线路类型 100：指定  200：推荐
     */
    private Integer pointType;


    /**
     * 业务类型
     */
    private Integer bizType;


    /**
     * 类型  100：零担运输（配载单派车）   200：零担派送（派送单派车）  300 零担揽收 （揽收单派车）  400：整车（托运单派车）  500：整车请车单 （预约单派车）
     */
    private Integer taskType;


    /**
     * 派车单据id(配载单、派送单、揽收单、托运单、请车单)
     */
    private String relationOrderId;

    /**
     * 派车单据单号(配载单、派送单、揽收单、托运单、请车单)
     */
    private String relationOrderNumber;

    /**
     * 状态
     */
    private Integer taskStatus;

    /**
     * 当前标记状态
     */
    private Integer taskCurrentStatus;


    /**
     * 预计发车时间
     */
    private Date planDepartTime;

    /**
     * 预计到达时间
     */
    private Date planArriveTime;


    /**
     * 预计在途时长（分钟）
     */
    private Integer planRuningDuration;


    /**
     * 所属组织id
     */
    private String orgnId;


    /**
     * 车辆信息
     */
    private TaskVehicleDto taskVehicle;


    /**
     * 货物信息
     */
    private DriverTaskCargoDto taskCargo;


    /**
     * 任务单 装/卸点信息
     */
    private List<DriverTaskPointDto> taskPointList;


    /**
     * 用于修改任务单（运脉任务单id）
     */
    private String taskId;


    /**
     * 外部导入单据的主键
     */
    private String outTaskId;


    /**
     * 星软预约单 位置
     */
    private Integer queueNumber;


    /**
     * 终点单位id（兼容星软） 因为他们终点没有经纬度 需要司机在终点上传  星软过来取值
     */
    private String outDestinationId;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getRelationEntId() {
        return relationEntId;
    }

    public void setRelationEntId(String relationEntId) {
        this.relationEntId = relationEntId;
    }

    public String getRelationStowageId() {
        return relationStowageId;
    }

    public void setRelationStowageId(String relationStowageId) {
        this.relationStowageId = relationStowageId;
    }

    public Integer getPointType() {
        return pointType;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getRelationOrderId() {
        return relationOrderId;
    }

    public void setRelationOrderId(String relationOrderId) {
        this.relationOrderId = relationOrderId;
    }

    public String getRelationOrderNumber() {
        return relationOrderNumber;
    }

    public void setRelationOrderNumber(String relationOrderNumber) {
        this.relationOrderNumber = relationOrderNumber;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskCurrentStatus() {
        return taskCurrentStatus;
    }

    public void setTaskCurrentStatus(Integer taskCurrentStatus) {
        this.taskCurrentStatus = taskCurrentStatus;
    }

    public Date getPlanDepartTime() {
        return planDepartTime;
    }

    public void setPlanDepartTime(Date planDepartTime) {
        this.planDepartTime = planDepartTime;
    }

    public Date getPlanArriveTime() {
        return planArriveTime;
    }

    public void setPlanArriveTime(Date planArriveTime) {
        this.planArriveTime = planArriveTime;
    }

    public Integer getPlanRuningDuration() {
        return planRuningDuration;
    }

    public void setPlanRuningDuration(Integer planRuningDuration) {
        this.planRuningDuration = planRuningDuration;
    }

    public String getOrgnId() {
        return orgnId;
    }

    public void setOrgnId(String orgnId) {
        this.orgnId = orgnId;
    }

    public TaskVehicleDto getTaskVehicle() {
        return taskVehicle;
    }

    public void setTaskVehicle(TaskVehicleDto taskVehicle) {
        this.taskVehicle = taskVehicle;
    }

    public DriverTaskCargoDto getTaskCargo() {
        return taskCargo;
    }

    public void setTaskCargo(DriverTaskCargoDto taskCargo) {
        this.taskCargo = taskCargo;
    }

    public List<DriverTaskPointDto> getTaskPointList() {
        return taskPointList;
    }

    public void setTaskPointList(List<DriverTaskPointDto> taskPointList) {
        this.taskPointList = taskPointList;
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getOutTaskId() {
        return outTaskId;
    }

    public void setOutTaskId(String outTaskId) {
        this.outTaskId = outTaskId;
    }


    public Integer getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(Integer queueNumber) {
        this.queueNumber = queueNumber;
    }


    public String getOutDestinationId() {
        return outDestinationId;
    }

    public void setOutDestinationId(String outDestinationId) {
        this.outDestinationId = outDestinationId;
    }
}
