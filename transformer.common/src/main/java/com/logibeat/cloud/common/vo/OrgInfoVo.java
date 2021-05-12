package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;

/**
 * 
* @ClassName: OrgInfoVo 
* @Description: 组织结构类型
* @author sean 
* @date 2015年12月15日 上午10:23:35 
* @version 1.0
 */
public class OrgInfoVo {
    
    private String guid;
    
    private String name;

    private Long carNum;

    private Long driverNum;

    private Long employNum;

    private Long perNum;
    
//    组织结构类型 DEPT=部门，MOTOTRCADE=车队
    private String orgTypeDictGUID;
    
    private String parentId;

    private long orgLevel;
    
    private Timestamp addTime;

    public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgTypeDictGUID() {
        return orgTypeDictGUID;
    }

    public void setOrgTypeDictGUID(String orgTypeDictGUID) {
        this.orgTypeDictGUID = orgTypeDictGUID;
    }

    public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getCarNum() {
        return carNum;
    }

    public void setCarNum(Long carNum) {
        this.carNum = carNum;
    }

    public Long getDriverNum() {
        return driverNum;
    }

    public void setDriverNum(Long driverNum) {
        this.driverNum = driverNum;
    }

    public Long getEmployNum() {
        return employNum;
    }

    public void setEmployNum(Long employNum) {
        this.employNum = employNum;
    }

    public Long getPerNum() {
        return perNum;
    }

    public void setPerNum(Long perNum) {
        this.perNum = perNum;
    }

	public long getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(long orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

}
