package com.logibeat.cloud.vo;


import com.logibeat.cloud.persistent.entity.EnterpriseOrganizationDictEntity;

import java.util.List;

public class EntPersonnelVo {

	private String entityId;

	private String personId;

	private String entId;

	private String personName;

	private String personPhone;

	private String personPosition; // 职位

	private String personLogo; // 人员头像

	private Boolean isManager; // 是否管理员

	private Boolean isVisible; // 是否外部可见

	private Boolean isActivate; // 是否激活

	private String imGuid; // 环信id

	private String pinYin;
	
	private boolean likeMan; //是否联系人

	private String logitalkId; // 融云Id

	private Boolean isBan;//是否被禁止
	
	private List<EnterpriseOrganizationDictEntity> orgList;//人员权限组织列表

	private List<EnterpriseOrganizationDictEntity> orgUnderList;//人员所属组织列表
	
	private boolean isAllOrg;//是否全部权限
	
	
	public Boolean getIsBan() {
		return isBan;
	}

	public void setIsBan(Boolean isBan) {
		this.isBan = isBan;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonPhone() {
		return personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	public String getPersonPosition() {
		return personPosition;
	}

	public void setPersonPosition(String personPosition) {
		this.personPosition = personPosition;
	}

	public String getPersonLogo() {
		return personLogo;
	}

	public void setPersonLogo(String personLogo) {
		this.personLogo = personLogo;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Boolean getIsActivate() {
		return isActivate;
	}

	public void setIsActivate(Boolean isActivate) {
		this.isActivate = isActivate;
	}

	public String getImGuid() {
		return imGuid;
	}

	public void setImGuid(String imGuid) {
		this.imGuid = imGuid;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}
	
	public boolean isLikeMan() {
		return likeMan;
	}

	public void setLikeMan(boolean likeMan) {
		this.likeMan = likeMan;
	}

	public String getLogitalkId() {
		return logitalkId;
	}

	public void setLogitalkId(String logitalkId) {
		this.logitalkId = logitalkId;
	}

	public List<EnterpriseOrganizationDictEntity> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<EnterpriseOrganizationDictEntity> orgList) {
		this.orgList = orgList;
	}

	public List<EnterpriseOrganizationDictEntity> getOrgUnderList() {
		return orgUnderList;
	}

	public void setOrgUnderList(List<EnterpriseOrganizationDictEntity> orgUnderList) {
		this.orgUnderList = orgUnderList;
	}

	public boolean isAllOrg() {
		return isAllOrg;
	}

	public void setAllOrg(boolean isAllOrg) {
		this.isAllOrg = isAllOrg;
	}

	
}
