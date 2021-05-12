package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class FriendsEntity extends EntitySerialize {
    private String guid;

    private String personID;

    private String groupGUID;

    private String friendPersonID;

    private String friendPersonEntID;

    private String friendName;

    private String initial;

    private String friendRemark;

    private Integer inviteType;

    private Integer inviteState;

    private Byte isFriend;

    private Byte isInvite;

    private Byte isUsual;

    private String remark;

    private Date lastEventTime;

    private Byte isDelete;

    private Date deleteTime;

    private Date addTime;

    private Date editTime;

    private Byte isEnt;

    private String pinYin;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getGroupGUID() {
        return groupGUID;
    }

    public void setGroupGUID(String groupGUID) {
        this.groupGUID = groupGUID;
    }

    public String getFriendPersonID() {
        return friendPersonID;
    }

    public void setFriendPersonID(String friendPersonID) {
        this.friendPersonID = friendPersonID;
    }

    public String getFriendPersonEntID() {
        return friendPersonEntID;
    }

    public void setFriendPersonEntID(String friendPersonEntID) {
        this.friendPersonEntID = friendPersonEntID;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getFriendRemark() {
        return friendRemark;
    }

    public void setFriendRemark(String friendRemark) {
        this.friendRemark = friendRemark;
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

    public Byte getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Byte isFriend) {
        this.isFriend = isFriend;
    }

    public Byte getIsInvite() {
        return isInvite;
    }

    public void setIsInvite(Byte isInvite) {
        this.isInvite = isInvite;
    }

    public Byte getIsUsual() {
        return isUsual;
    }

    public void setIsUsual(Byte isUsual) {
        this.isUsual = isUsual;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLastEventTime() {
        return lastEventTime;
    }

    public void setLastEventTime(Date lastEventTime) {
        this.lastEventTime = lastEventTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
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

    public Byte getIsEnt() {
        return isEnt;
    }

    public void setIsEnt(Byte isEnt) {
        this.isEnt = isEnt;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }
}