package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class EnterpriseCooperateCarEntity extends EntitySerialize {
    private String guid;

    private String entid;

    private String carid;

    private String ownerpersonid;

    private String groupid;

    private Integer inviteType;

    private Integer inviteState;

    private Date edittime;

    private String remark;

    private Integer coopType;

    private Byte isStarMark;

    private String firstDriverPersonID;

    private String secondDriverPersonID;

    private Byte isDelete;

    private Byte carStatus;

    private Byte isFriend;

    private String firstDriverMobile;

    private String firstDriverName;

    private String secondDriverMobile;

    private String secondDriverName;

    private String orgGuid;

    private String plateNumber;
    
    private String plateColor;

    private String allOrgGuid;
    
    private String sourceType;
    
    private Integer auditStatus;
    
    public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEntid() {
        return entid;
    }

    public void setEntid(String entid) {
        this.entid = entid;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getOwnerpersonid() {
        return ownerpersonid;
    }

    public void setOwnerpersonid(String ownerpersonid) {
        this.ownerpersonid = ownerpersonid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
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

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCoopType() {
        return coopType;
    }

    public void setCoopType(Integer coopType) {
        this.coopType = coopType;
    }

    public Byte getIsStarMark() {
        return isStarMark;
    }

    public void setIsStarMark(Byte isStarMark) {
        this.isStarMark = isStarMark;
    }

    public String getFirstDriverPersonID() {
        return firstDriverPersonID;
    }

    public void setFirstDriverPersonID(String firstDriverPersonID) {
        this.firstDriverPersonID = firstDriverPersonID;
    }

    public String getSecondDriverPersonID() {
        return secondDriverPersonID;
    }

    public void setSecondDriverPersonID(String secondDriverPersonID) {
        this.secondDriverPersonID = secondDriverPersonID;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Byte carStatus) {
        this.carStatus = carStatus;
    }

    public Byte getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Byte isFriend) {
        this.isFriend = isFriend;
    }

    public String getFirstDriverMobile() {
        return firstDriverMobile;
    }

    public void setFirstDriverMobile(String firstDriverMobile) {
        this.firstDriverMobile = firstDriverMobile;
    }

    public String getFirstDriverName() {
        return firstDriverName;
    }

    public void setFirstDriverName(String firstDriverName) {
        this.firstDriverName = firstDriverName;
    }

    public String getSecondDriverMobile() {
        return secondDriverMobile;
    }

    public void setSecondDriverMobile(String secondDriverMobile) {
        this.secondDriverMobile = secondDriverMobile;
    }

    public String getSecondDriverName() {
        return secondDriverName;
    }

    public void setSecondDriverName(String secondDriverName) {
        this.secondDriverName = secondDriverName;
    }

    public String getOrgGuid() {
        return orgGuid;
    }

    public void setOrgGuid(String orgGuid) {
        this.orgGuid = orgGuid;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getAllOrgGuid() {
        return allOrgGuid;
    }

    public void setAllOrgGuid(String allOrgGuid) {
        this.allOrgGuid = allOrgGuid;
    }
}