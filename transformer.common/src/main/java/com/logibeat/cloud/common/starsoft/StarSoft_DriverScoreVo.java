package com.logibeat.cloud.common.starsoft;

public class StarSoft_DriverScoreVo {

    /**
     * 基础分
     */
    private Integer BaseScore;

    /**
     * 加分
     */
    private Integer AddScore;


    /**
     * 扣分
     */
    private Integer DeductScore;


    /**
     * 当前分 =基础分-扣分+加分
     */
    private Integer CurrentScore;

    /**
     * 允许加分（加分剩余）
     */
    private Integer AllowAddScore;

    /**
     * 清分周期
     */
    private Integer NextCycle;


    /**
     * 处罚方式说明
     */
    private String PunishWay;


    public Integer getBaseScore() {
        return BaseScore;
    }

    public void setBaseScore(Integer baseScore) {
        BaseScore = baseScore;
    }

    public Integer getAddScore() {
        return AddScore;
    }

    public void setAddScore(Integer addScore) {
        AddScore = addScore;
    }

    public Integer getDeductScore() {
        return DeductScore;
    }

    public void setDeductScore(Integer deductScore) {
        DeductScore = deductScore;
    }

    public Integer getCurrentScore() {
        return CurrentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        CurrentScore = currentScore;
    }

    public Integer getAllowAddScore() {
        return AllowAddScore;
    }

    public void setAllowAddScore(Integer allowAddScore) {
        AllowAddScore = allowAddScore;
    }

    public Integer getNextCycle() {
        return NextCycle;
    }

    public void setNextCycle(Integer nextCycle) {
        NextCycle = nextCycle;
    }

    public String getPunishWay() {
        return PunishWay;
    }

    public void setPunishWay(String punishWay) {
        PunishWay = punishWay;
    }
}
