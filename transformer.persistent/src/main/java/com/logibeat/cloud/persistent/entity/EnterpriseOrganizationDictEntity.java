package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class EnterpriseOrganizationDictEntity extends EntitySerialize {
    private String guid;

    private String entId;

    private String parentId;

    private String name;

    private String orgTypeDictGuid;

    private Byte isDelete;

    private Date addTime;

    private Date editTime;

    private String editPersonId;

    private Integer carNum;

    private Integer selfCarNum;

    private Integer perNum;

    private Integer entNum;

    private String orgContactUser;

    private String orgContactPhone;

    private String orgContactAddress;

    private Integer orgLevel;

    private String queryChar;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgTypeDictGuid() {
        return orgTypeDictGuid;
    }

    public void setOrgTypeDictGuid(String orgTypeDictGuid) {
        this.orgTypeDictGuid = orgTypeDictGuid;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditPersonId() {
        return editPersonId;
    }

    public void setEditPersonId(String editPersonId) {
        this.editPersonId = editPersonId;
    }

    public Integer getCarNum() {
        return carNum;
    }

    public void setCarNum(Integer carNum) {
        this.carNum = carNum;
    }

    public Integer getSelfCarNum() {
        return selfCarNum;
    }

    public void setSelfCarNum(Integer selfCarNum) {
        this.selfCarNum = selfCarNum;
    }

    public Integer getPerNum() {
        return perNum;
    }

    public void setPerNum(Integer perNum) {
        this.perNum = perNum;
    }

    public Integer getEntNum() {
        return entNum;
    }

    public void setEntNum(Integer entNum) {
        this.entNum = entNum;
    }

    public String getOrgContactUser() {
        return orgContactUser;
    }

    public void setOrgContactUser(String orgContactUser) {
        this.orgContactUser = orgContactUser;
    }

    public String getOrgContactPhone() {
        return orgContactPhone;
    }

    public void setOrgContactPhone(String orgContactPhone) {
        this.orgContactPhone = orgContactPhone;
    }

    public String getOrgContactAddress() {
        return orgContactAddress;
    }

    public void setOrgContactAddress(String orgContactAddress) {
        this.orgContactAddress = orgContactAddress;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getQueryChar() {
        return queryChar;
    }

    public void setQueryChar(String queryChar) {
        this.queryChar = queryChar;
    }
}