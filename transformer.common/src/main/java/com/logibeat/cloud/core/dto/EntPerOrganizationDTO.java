package com.logibeat.cloud.core.dto;


/**
 * 人员权限组织DTO
 * @author wangjie
 *
 */
public class EntPerOrganizationDTO extends SearchCarDTO{
	
	/**
	 * 组织id
	 */
	private String orgGuid;					
	
	/**
	 * 父级id
	 */
	private String parentId;			
	
	/**
	 * 用户类型
	 */
	private Integer personType;
	
	/**
	 * 被修改的用户id
	 */
	private String editPersonId;
	
	/**
	 * 组织id
	 */
	private String entId;
	
	public String getOrgGuid() {
		return orgGuid;
	}

	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getPersonType() {
		return personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}

	public String getEditPersonId() {
		return editPersonId;
	}

	public void setEditPersonId(String editPersonId) {
		this.editPersonId = editPersonId;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}
}
