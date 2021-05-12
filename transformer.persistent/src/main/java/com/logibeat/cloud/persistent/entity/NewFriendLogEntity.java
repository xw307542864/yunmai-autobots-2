package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;

public class NewFriendLogEntity extends EntitySerialize {
    private String newFriendGUID;

    private Integer yyyymmdd;

    private Byte isEnt;

    private String entID;

    private String objectID;

    private Byte isFriendEnt;

    private String friendObjectID;

    private String invitePersonID;

    private Integer inviteType;

    private Integer inviteState;

    private String remark;

    private Byte isHandle;

    private String handlePersonID;

    private Byte isDelete;

    private Timestamp addTime;

    private Timestamp editTime;

    private Byte isRead;

    private String contentPrex;

    public String getNewFriendGUID() {
        return newFriendGUID;
    }

    public void setNewFriendGUID(String newFriendGUID) {
        this.newFriendGUID = newFriendGUID;
    }

    public Integer getYyyymmdd() {
        return yyyymmdd;
    }

    public void setYyyymmdd(Integer yyyymmdd) {
        this.yyyymmdd = yyyymmdd;
    }

    public Byte getIsEnt() {
        return isEnt;
    }

    public void setIsEnt(Byte isEnt) {
        this.isEnt = isEnt;
    }

    public String getEntID() {
        return entID;
    }

    public void setEntID(String entID) {
        this.entID = entID;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public Byte getIsFriendEnt() {
        return isFriendEnt;
    }

    public void setIsFriendEnt(Byte isFriendEnt) {
        this.isFriendEnt = isFriendEnt;
    }

    public String getFriendObjectID() {
        return friendObjectID;
    }

    public void setFriendObjectID(String friendObjectID) {
        this.friendObjectID = friendObjectID;
    }

    public String getInvitePersonID() {
        return invitePersonID;
    }

    public void setInvitePersonID(String invitePersonID) {
        this.invitePersonID = invitePersonID;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(Byte isHandle) {
        this.isHandle = isHandle;
    }

    public String getHandlePersonID() {
        return handlePersonID;
    }

    public void setHandlePersonID(String handlePersonID) {
        this.handlePersonID = handlePersonID;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public String getContentPrex() {
        return contentPrex;
    }

    public void setContentPrex(String contentPrex) {
        this.contentPrex = contentPrex;
    }
}