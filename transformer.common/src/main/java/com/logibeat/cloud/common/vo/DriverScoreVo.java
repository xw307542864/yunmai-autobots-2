package com.logibeat.cloud.common.vo;

public class DriverScoreVo {

    /**
     * 基础分
     */
    private Integer baseScore;


    /**
     * 扣分
     */
    private Integer deductScore;


    /**
     * 加分
     */
    private Integer addScore;


    /**
     * 总分
     */
    private Integer totalScore;


    /**
     * 驾驶安全评分
     */
    private Integer safeScore;

    public Integer getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(Integer baseScore) {
        this.baseScore = baseScore;
    }

    public Integer getDeductScore() {
        return deductScore;
    }

    public void setDeductScore(Integer deductScore) {
        this.deductScore = deductScore;
    }

    public Integer getAddScore() {
        return addScore;
    }

    public void setAddScore(Integer addScore) {
        this.addScore = addScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getSafeScore() {
        return safeScore;
    }

    public void setSafeScore(Integer safeScore) {
        this.safeScore = safeScore;
    }
}
