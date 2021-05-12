package com.logibeat.cloud.common.vo;

import java.util.List;

//司机与企业vo
public class CooperateDriverVo {

	/**
	 * 企业与人员关系id
	 */
    private String entPerId;
    
    /**
	 * 企业子账号人员类型（岗位）（枚举） 
	 */
    private String childAdminType;
    
    /**
	 * 岗位名称
	 */
    private String jobName;
    
    /**
	 * 合作关系
	 */
    private Integer coopType;
    
    /**
	 * 司机认证状态
	 */
    private Integer driverAuditStatus;
    
    /**
	 * 邀请状态（枚举 我方状态） 
	 */
    private Integer inviteState;
    
    /**
	 * 邀请类型
	 */
    private Integer inviteType;
    
    /**
	 * 名称备注
	 */
    private String nameRemark;
    
    /**
	 * 是否常用联系人
	 */
    private Boolean isUsual;
    
    /**
	 * 司机星级
	 */
    private Integer star;
    
    /**
	 * 处理新联系人备注信息
	 */
    private String message;
    
    /**
	 * 是否所属全部组织
	 */
	private boolean isAllOrg;
	
	/**
	 * 组织id
	 */
    private String orgGuid;
    
    /**
	 * 组织名称
	 */
    private String orgName;
    
    /**
     * 最新动态  
     */
    private String lastDynamic;
    
    /**
	 * 是否主驾
	 */
	private Boolean isFirstDriver;
    
    /**
     * 动态图片
     */
    private List<String> picList;

	public String getEntPerId() {
		return entPerId;
	}

	public void setEntPerId(String entPerId) {
		this.entPerId = entPerId;
	}

	public String getChildAdminType() {
		return childAdminType;
	}

	public void setChildAdminType(String childAdminType) {
		this.childAdminType = childAdminType;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getCoopType() {
		return coopType;
	}

	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}

	public Integer getDriverAuditStatus() {
		return driverAuditStatus;
	}

	public void setDriverAuditStatus(Integer driverAuditStatus) {
		this.driverAuditStatus = driverAuditStatus;
	}

	public Integer getInviteState() {
		return inviteState;
	}

	public void setInviteState(Integer inviteState) {
		this.inviteState = inviteState;
	}

	public Integer getInviteType() {
		return inviteType;
	}

	public void setInviteType(Integer inviteType) {
		this.inviteType = inviteType;
	}

	public String getNameRemark() {
		return nameRemark;
	}

	public void setNameRemark(String nameRemark) {
		this.nameRemark = nameRemark;
	}

	public Boolean getIsUsual() {
		return isUsual;
	}

	public void setIsUsual(Boolean isUsual) {
		this.isUsual = isUsual;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isAllOrg() {
		return isAllOrg;
	}

	public void setAllOrg(boolean isAllOrg) {
		this.isAllOrg = isAllOrg;
	}

	public String getOrgGuid() {
		return orgGuid;
	}

	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getLastDynamic() {
		return lastDynamic;
	}

	public void setLastDynamic(String lastDynamic) {
		this.lastDynamic = lastDynamic;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public Boolean getIsFirstDriver() {
		return isFirstDriver;
	}

	public void setIsFirstDriver(Boolean isFirstDriver) {
		this.isFirstDriver = isFirstDriver;
	}
    
    
}
