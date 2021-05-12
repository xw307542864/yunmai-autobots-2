package com.logibeat.cloud.vo.startsoft;



import com.logibeat.cloud.core.dto.PunishTypeDto;
import com.logibeat.cloud.core.dto.ViolationInfoDto;

import java.util.List;

public class StarSoft_ViolationInfoVo {
    /**
     * 处置guid
     */
    private String violationId;


    /**
     * 违规来源  100：违规报警 200 人工新增 300 违规规则 400 积分规则
     */
    private Integer violationSource;


    /**
     * 单号
     */
    private String orderNumber;


    /**
     * 企业id
     */
    private String companyId;


    /**
     * 司机id
     */
    private String driverId;


    /**
     * 处置人
     */
    private String punishPersonName;


    /**
     * 处置状态  100：处置中 200：申述中 300 取消处置 400：处置完成
     */
    private Integer punishStatus;

    /**
     * 处罚方式
     */
    private List<PunishTypeDto> punishTypeList;


    /**
     * 违规时间
     */
    private String violationTime;


    /**
     * 违规说明
     */
    private String violationRemark;


    /**
     * 处置说明
     */
    private String punishRemark;

    /**
     * 处置时间
     */
    private String punishTime;



    /**
     * 报警内容
     */
    private List<ViolationInfoDto> violationInfoList;


    /**
     * 处罚机构
     */
    private  String penaltyOrg;





    public String getViolationId() {
        return violationId;
    }

    public void setViolationId(String violationId) {
        this.violationId = violationId;
    }

    public Integer getViolationSource() {
        return violationSource;
    }

    public void setViolationSource(Integer violationSource) {
        this.violationSource = violationSource;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPunishPersonName() {
        return punishPersonName;
    }

    public void setPunishPersonName(String punishPersonName) {
        this.punishPersonName = punishPersonName;
    }

    public Integer getPunishStatus() {
        return punishStatus;
    }

    public void setPunishStatus(Integer punishStatus) {
        this.punishStatus = punishStatus;
    }

    public List<PunishTypeDto> getPunishTypeList() {
        return punishTypeList;
    }

    public void setPunishTypeList(List<PunishTypeDto> punishTypeList) {
        this.punishTypeList = punishTypeList;
    }

    public String getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(String violationTime) {
        this.violationTime = violationTime;
    }

    public String getViolationRemark() {
        return violationRemark;
    }

    public void setViolationRemark(String violationRemark) {
        this.violationRemark = violationRemark;
    }

    public String getPunishRemark() {
        return punishRemark;
    }

    public void setPunishRemark(String punishRemark) {
        this.punishRemark = punishRemark;
    }

    public String getPunishTime() {
        return punishTime;
    }

    public void setPunishTime(String punishTime) {
        this.punishTime = punishTime;
    }

    public List<ViolationInfoDto> getViolationInfoList() {
        return violationInfoList;
    }

    public void setViolationInfoList(List<ViolationInfoDto> violationInfoList) {
        this.violationInfoList = violationInfoList;
    }

    public String getPenaltyOrg() {
        return penaltyOrg;
    }

    public void setPenaltyOrg(String penaltyOrg) {
        this.penaltyOrg = penaltyOrg;
    }


}
