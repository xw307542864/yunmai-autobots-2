package com.logibeat.cloud.core.dto;

/**
 * 企业组织DTO
 * @author wangjie
 *
 */
public class EntOrganizeDTO {
	
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
	
}
