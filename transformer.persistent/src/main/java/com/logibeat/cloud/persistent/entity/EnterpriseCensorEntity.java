package com.logibeat.cloud.persistent.entity;


import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;

/**
 * Created by wudi on 2018/4/11.
 */
public class EnterpriseCensorEntity extends EntitySerialize {
    private  String guid;//主键
    private  String entId;//企业ID
    private  Integer censorStates;//审核状态(0：未知，1：待审核，2,：审核通过，3，审核不通过)
    private  String censorPersonGuid;//审核人
    private  String censorPersonName;//审核人名
    private  Timestamp censorTime;//审核时间
    private  Integer censorType;//审核类型 审核类型(0.未知,1,初审，2：复审)
    private  String censorRemarks;//备注
    private  Integer accountVersion;//账户版本(1：试用；2：正式)
    private  Timestamp accountValidityDate;//审核时间
    private  Integer accountState;//账户状态 (1：正常；2：停用；3：注销)


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public Integer getCensorStates() {
        return censorStates;
    }

    public void setCensorStates(Integer censorStates) {
        this.censorStates = censorStates;
    }

    public String getCensorPersonGuid() {
        return censorPersonGuid;
    }

    public void setCensorPersonGuid(String censorPersonGuid) {
        this.censorPersonGuid = censorPersonGuid;
    }

    public String getCensorPersonName() {
        return censorPersonName;
    }

    public void setCensorPersonName(String censorPersonName) {
        this.censorPersonName = censorPersonName;
    }

    public Timestamp getCensorTime() {
        return censorTime;
    }

    public void setCensorTime(Timestamp censorTime) {
        this.censorTime = censorTime;
    }

    public Integer getCensorType() {
        return censorType;
    }

    public void setCensorType(Integer censorType) {
        this.censorType = censorType;
    }

    public String getCensorRemarks() {
        return censorRemarks;
    }

    public void setCensorRemarks(String censorRemarks) {
        this.censorRemarks = censorRemarks;
    }

    public Integer getAccountVersion() {
        return accountVersion;
    }

    public void setAccountVersion(Integer accountVersion) {
        this.accountVersion = accountVersion;
    }

    public Timestamp getAccountValidityDate() {
        return accountValidityDate;
    }

    public void setAccountValidityDate(Timestamp accountValidityDate) {
        this.accountValidityDate = accountValidityDate;
    }

    public Integer getAccountState() {
        return accountState;
    }

    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }
}
