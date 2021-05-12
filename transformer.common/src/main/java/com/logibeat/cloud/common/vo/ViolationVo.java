package com.logibeat.cloud.common.vo;


import com.logibeat.cloud.core.dto.ViolationInfoDto;

import java.util.Date;
import java.util.List;

public class ViolationVo {


    /**
     * guid
     */
    private String guid;

    /**
     * 单号
     */
    private String orderNumber;

    /**
     * 类型
     */
    private String type;

    /**
     * 类型描述
     */
    private String typeValue;


    /**
     * 处罚对应的考试计划/学习计划 id
     */
    private String dataId;


    /**
     * 处罚对应的考试计划/学习计划 类型  100 视频  200 图文  300 视频
     */
    private String dataType;

    /**
     * 时间
     */
    private Date time;

    /**
     * 状态
     */
    private Integer status;


    /**
     * 处置人
     */
    private String punishPersonName;


    /**
     * 企业名称
     */
    private String entName;


    /**
     * 说明
     */
    private String remark;


    /**
     * 时间
     */
    private Date punishTime;


    /**
     * 是否确认
     */
    private Integer confirm;


    /**
     * 违规时间
     */
    private Date violationTime;

    /**
     * 违规描述
     */
    private String violationRemark;



    private Integer source;


    /**
     * 违规内容
     */
    private List<ViolationInfoDto> violationInfoList;


    /**
     * 处罚方式
     */
    private List<String> punishTypeList;


    /**
     * 处罚机构
     */
    private String penaltyOrg;





    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPunishPersonName() {
        return punishPersonName;
    }

    public void setPunishPersonName(String punishPersonName) {
        this.punishPersonName = punishPersonName;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getPunishTime() {
        return punishTime;
    }

    public void setPunishTime(Date punishTime) {
        this.punishTime = punishTime;
    }

    public List<ViolationInfoDto> getViolationInfoList() {
        return violationInfoList;
    }

    public void setViolationInfoList(List<ViolationInfoDto> violationInfoList) {
        this.violationInfoList = violationInfoList;
    }


    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }


    public Date getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(Date violationTime) {
        this.violationTime = violationTime;
    }

    public String getViolationRemark() {
        return violationRemark;
    }

    public void setViolationRemark(String violationRemark) {
        this.violationRemark = violationRemark;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public List<String> getPunishTypeList() {
        return punishTypeList;
    }

    public void setPunishTypeList(List<String> punishTypeList) {
        this.punishTypeList = punishTypeList;
    }

    public String getPenaltyOrg() {
        return penaltyOrg;
    }

    public void setPenaltyOrg(String penaltyOrg) {
        this.penaltyOrg = penaltyOrg;
    }



}
