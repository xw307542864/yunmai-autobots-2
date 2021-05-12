package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;

/**
 * 任务提醒
 * @author wangxp
 * @date 2016年3月24日
 * @version 1.0
 */
public class TaskNoticeVo {

	private String ordersCID;//订单Id

	private String objectId; //objectId

	private String objectName; //objectName

    private Integer objectType; //(1企业，2车)

    private Timestamp noticeTime; //通知时间

	private String startAreaName;//起点

    private String startAreaCode;

	private String endAreaName;//终点

    private String endAreaCode;

	private Integer ordersStatus;//状态(2新任务，7任务完成)

    private Integer statute; //时效(若时效不为0展示计划发车时间+时效，否则展示计划到达时间)
    
    private Integer duration; //分钟 （新加的字段，以前的字段暂时未删除，兼容老版本）

    private Timestamp startAreaPlanTime;  //计划发车时间

    private Timestamp endAreaPlanTime;  //计划到达时间

    public String getOrdersCID() {
        return ordersCID;
    }

    public void setOrdersCID(String ordersCID) {
        this.ordersCID = ordersCID;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public Timestamp getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Timestamp noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getStartAreaName() {
        return startAreaName;
    }

    public void setStartAreaName(String startAreaName) {
        this.startAreaName = startAreaName;
    }

    public String getStartAreaCode() {
        return startAreaCode;
    }

    public void setStartAreaCode(String startAreaCode) {
        this.startAreaCode = startAreaCode;
    }

    public String getEndAreaName() {
        return endAreaName;
    }

    public void setEndAreaName(String endAreaName) {
        this.endAreaName = endAreaName;
    }

    public String getEndAreaCode() {
        return endAreaCode;
    }

    public void setEndAreaCode(String endAreaCode) {
        this.endAreaCode = endAreaCode;
    }

    public Integer getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(Integer ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public Integer getStatute() {
        return statute;
    }

    public void setStatute(Integer statute) {
        this.statute = statute;
    }
    

    public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Timestamp getStartAreaPlanTime() {
        return startAreaPlanTime;
    }

    public void setStartAreaPlanTime(Timestamp startAreaPlanTime) {
        this.startAreaPlanTime = startAreaPlanTime;
    }

    public Timestamp getEndAreaPlanTime() {
        return endAreaPlanTime;
    }

    public void setEndAreaPlanTime(Timestamp endAreaPlanTime) {
        this.endAreaPlanTime = endAreaPlanTime;
    }
}
