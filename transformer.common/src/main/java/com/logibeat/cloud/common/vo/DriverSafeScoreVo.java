package com.logibeat.cloud.common.vo;

public class DriverSafeScoreVo {


    /**
     * 当前分数
     */
    private Integer currentScore;


    /**
     * 扣除分数
     */
    private Integer deductScore;


    /**
     * 允许加分（加分剩余）
     */
    private Integer allowAddScore;


    /**
     * 清分周期
     */
    private Integer nextCycle;


    /**
     * 处罚方式说明
     */
    private String punishTypeRemark;


    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Integer getDeductScore() {
        return deductScore;
    }

    public void setDeductScore(Integer deductScore) {
        this.deductScore = deductScore;
    }

    public Integer getAllowAddScore() {
        return allowAddScore;
    }

    public void setAllowAddScore(Integer allowAddScore) {
        this.allowAddScore = allowAddScore;
    }

    public Integer getNextCycle() {
        return nextCycle;
    }

    public void setNextCycle(Integer nextCycle) {
        this.nextCycle = nextCycle;
    }

    public String getPunishTypeRemark() {
        return punishTypeRemark;
    }

    public void setPunishTypeRemark(String punishTypeRemark) {
        this.punishTypeRemark = punishTypeRemark;
    }
}
