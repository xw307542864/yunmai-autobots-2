package com.logibeat.cloud.persistent.entity;


import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

public class EntStarSoftEntity extends EntitySerialize {

    private String guid;

    private String entId;

    private String entName;

    private String starsoftId;

    private Integer changeStatus;

    private Integer driverGps;
    
    private String source;
    
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

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

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getStarsoftId() {
        return starsoftId;
    }

    public void setStarsoftId(String starsoftId) {
        this.starsoftId = starsoftId;
    }

    public Integer getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Integer changeStatus) {
        this.changeStatus = changeStatus;
    }

    public Integer getDriverGps() {
        return driverGps;
    }

    public void setDriverGps(Integer driverGps) {
        this.driverGps = driverGps;
    }
}
