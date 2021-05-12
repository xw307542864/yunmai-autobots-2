package com.logibeat.cloud.core.dto;

/**
 * Created by wilson on 2017/2/15.
 */
public class EntBusinessInfoDTO {

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 法人
     */
    private String legalPerson;

    /**
     * 成立时间
     */
    private String buildDate;

    /**
     * 统一社会信用代码
     */
    private String regNumber;

    /**
     * 登记状态
     */
    private String regStatus;


    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }
}
