package com.logibeat.cloud.core.dto;

public class UpdateViolationStatusDto {


    /**
     * 违规工单id
     */
    private String violationId;


    /**
     * 变更后的状态  100：处置中 200：申述中 300 取消 400 完成
     */
    private Integer status;


    /**
     * 取消处置/退回申诉原因
     */
    private String cancelReason;


    /**
     * 退回申诉  500 成功  600 失败
     */
    private Integer appealStatus;


    /**
     * 申诉id
     */
    private String appealId;

    public String getViolationId() {
        return violationId;
    }

    public void setViolationId(String violationId) {
        this.violationId = violationId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getAppealStatus() {
        return appealStatus;
    }

    public void setAppealStatus(Integer appealStatus) {
        this.appealStatus = appealStatus;
    }

    public String getAppealId() {
        return appealId;
    }

    public void setAppealId(String appealId) {
        this.appealId = appealId;
    }
}
