package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class EnterpriseCooperateEntEntity extends EntitySerialize {
    private String guid;

    private String coopGUID;

    private String entID;

    private String coopEntID;

    private Integer coopType;

    private Byte isCoopGoods;

    private String remark;

    private Date startTime;

    private Date endTime;

    private Date addTime;

    private String addPersonId;

    private Byte isUsual;

    private Byte isInvite;

    private Integer inviteType;

    private Integer inviteState;

    private Byte isHandle;

    private Date handleTime;

    private String handleRemark;

    private String handlePersonID;

    private String contact;

    private Byte isFriend;

    private Byte isStarMark;

    private String address;

    private Date editTime;

    private String editPersonID;

    private Byte isDelete;

    private String contactId;

    private String labelDictId;

    private String labelDictName;

    private String starLevelDictId;

    private String starLevelName;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCoopGUID() {
        return coopGUID;
    }

    public void setCoopGUID(String coopGUID) {
        this.coopGUID = coopGUID;
    }

    public String getEntID() {
        return entID;
    }

    public void setEntID(String entID) {
        this.entID = entID;
    }

    public String getCoopEntID() {
        return coopEntID;
    }

    public void setCoopEntID(String coopEntID) {
        this.coopEntID = coopEntID;
    }

    public Integer getCoopType() {
        return coopType;
    }

    public void setCoopType(Integer coopType) {
        this.coopType = coopType;
    }

    public Byte getIsCoopGoods() {
        return isCoopGoods;
    }

    public void setIsCoopGoods(Byte isCoopGoods) {
        this.isCoopGoods = isCoopGoods;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddPersonId() {
        return addPersonId;
    }

    public void setAddPersonId(String addPersonId) {
        this.addPersonId = addPersonId;
    }

    public Byte getIsUsual() {
        return isUsual;
    }

    public void setIsUsual(Byte isUsual) {
        this.isUsual = isUsual;
    }

    public Byte getIsInvite() {
        return isInvite;
    }

    public void setIsInvite(Byte isInvite) {
        this.isInvite = isInvite;
    }

    public Integer getInviteType() {
        return inviteType;
    }

    public void setInviteType(Integer inviteType) {
        this.inviteType = inviteType;
    }

    public Integer getInviteState() {
        return inviteState;
    }

    public void setInviteState(Integer inviteState) {
        this.inviteState = inviteState;
    }

    public Byte getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(Byte isHandle) {
        this.isHandle = isHandle;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleRemark() {
        return handleRemark;
    }

    public void setHandleRemark(String handleRemark) {
        this.handleRemark = handleRemark;
    }

    public String getHandlePersonID() {
        return handlePersonID;
    }

    public void setHandlePersonID(String handlePersonID) {
        this.handlePersonID = handlePersonID;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Byte getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Byte isFriend) {
        this.isFriend = isFriend;
    }

    public Byte getIsStarMark() {
        return isStarMark;
    }

    public void setIsStarMark(Byte isStarMark) {
        this.isStarMark = isStarMark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditPersonID() {
        return editPersonID;
    }

    public void setEditPersonID(String editPersonID) {
        this.editPersonID = editPersonID;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getLabelDictId() {
        return labelDictId;
    }

    public void setLabelDictId(String labelDictId) {
        this.labelDictId = labelDictId;
    }

    public String getLabelDictName() {
        return labelDictName;
    }

    public void setLabelDictName(String labelDictName) {
        this.labelDictName = labelDictName;
    }

    public String getStarLevelDictId() {
        return starLevelDictId;
    }

    public void setStarLevelDictId(String starLevelDictId) {
        this.starLevelDictId = starLevelDictId;
    }

    public String getStarLevelName() {
        return starLevelName;
    }

    public void setStarLevelName(String starLevelName) {
        this.starLevelName = starLevelName;
    }
}