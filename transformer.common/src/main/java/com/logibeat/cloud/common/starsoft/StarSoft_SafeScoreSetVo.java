package com.logibeat.cloud.common.starsoft;

import java.util.Date;
import java.util.List;

public class StarSoft_SafeScoreSetVo {

    /**
     * 基础分
     */
    private Integer baseScore;


    /**
     * 重置周期(月)
     */
    private Integer resetPeriod;


    /**
     * 周期开始时间
     */
    private Date periodDate;

    /**
     * 周期内可增分最大值
     */
    private Integer maxAddScore;


    /**
     * 规则
     */
    private List<StarSoft_SafeScoreRuleVo> ruleList;


    public Integer getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(Integer baseScore) {
        this.baseScore = baseScore;
    }

    public Integer getResetPeriod() {
        return resetPeriod;
    }

    public void setResetPeriod(Integer resetPeriod) {
        this.resetPeriod = resetPeriod;
    }

    public Date getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(Date periodDate) {
        this.periodDate = periodDate;
    }

    public Integer getMaxAddScore() {
        return maxAddScore;
    }

    public void setMaxAddScore(Integer maxAddScore) {
        this.maxAddScore = maxAddScore;
    }

    public List<StarSoft_SafeScoreRuleVo> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<StarSoft_SafeScoreRuleVo> ruleList) {
        this.ruleList = ruleList;
    }
}
