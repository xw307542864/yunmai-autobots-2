package com.logibeat.cloud.common.starsoft;

import java.util.Date;

public class StarSoft_DriverScoreRecordVo {

    private Integer Score;

    private Integer AddType;

    private String Remark;

    private Date CreateTime;

    private String month;


    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public Integer getAddType() {
        return AddType;
    }

    public void setAddType(Integer addType) {
        AddType = addType;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
