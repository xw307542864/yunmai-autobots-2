package com.logibeat.cloud.persistent.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc: Model类
 * Date: 2020-05-26
 */
public class DriverInterestLabelEntity implements Serializable {

    private String guid;
    
    private String baseUserId;
    
    private String interestLabel;
    
    private Date createTime;
    
    private Date modifyTime;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getInterestLabel() {
        return interestLabel;
    }

    public void setInterestLabel(String interestLabel) {
        this.interestLabel = interestLabel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
