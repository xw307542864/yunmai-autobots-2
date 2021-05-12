package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;

/**
 * 用户组织架构VO
 * @author wangjie
 *
 */
public class PerOrgVo {

	/**
	 * 组织id
	 */
	private String guid;

	/**
	 * 父级id
	 */
	private String parentId;

	/**
	 * 组织名称
	 */
	private String name;

	/**
	 * 组织联系人姓名
	 */
	private String orgContactUser;

	/**
	 * 联系人手机号
	 */
	private String orgContactPhone;

	/**
	 * 联系人地址
	 */
	private String orgContactAddress;

	/**
	 * 组织层级
	 */
	private String orgLevel;
	
	/**
	 * 车辆总数
	 */
	private long perNum;
	
	/**
	 * 是否有子组织
	 */
	private Boolean isHasChildOrg;
	
	/**
	 * 子组织车辆
	 *//*
	private PersonOrganizationCarVo personOrganizationCarVo;*/
	
	/**
	 * 添加时间
	 */
	private Timestamp addTime;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
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

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public long getPerNum() {
		return perNum;
	}

	public void setPerNum(long perNum) {
		this.perNum = perNum;
	}

	public Boolean getIsHasChildOrg() {
		return isHasChildOrg;
	}

	public void setIsHasChildOrg(Boolean isHasChildOrg) {
		this.isHasChildOrg = isHasChildOrg;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	
}
