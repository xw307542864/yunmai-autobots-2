package com.logibeat.cloud.common.vo;

import java.util.List;

/**
 * 
 * @Description TODO 
 * @ClassName   EntCoopInfoEntVo 
 * @Date        2017年2月16日 上午10:46:10 
 * @Author      jin
 * Copyright (c) All Rights Reserved, 2017.
 */
public class EntCoopInfoEntVo {
		// 企业地址 
		private String address;
		// 企业认证状态
		private Integer entAuditStatus;
		// 企业类型编码 
		private String entTypeDictGUID;
		// 企业类型名称 
		private String entTypeDictName;
		// 主键ID 
		private String entId;
		// 主键ID 
		private String ID;
		// 企业Logo图片地址 
		private String logo;
		// 企业名称 
		private String name;
		// 企业简介 
		private String profile;
		//认证状态
		private Integer oauthType;
		// 发单总数
		private long totalEntrustOrdersNum;
		// 接单总数/合作次数（我方为该企业承运的订单总数）
		private long totaltCarrierOrdersNum;
		//邀请状态
		private int InviteState;
		//邀请状态-解释
		private String InviteStateName;
		// 联系人数量
		private long contactNum;
		// 联系人id
		private String personId;
		//联系人名称
		private String personName;
		//联系人手机
		private String personMobile;
		//联系人im
		private String imGuid;
		//合作伙伴标签
		private List<String> labelDictName;
		//合作伙伴星级
		private String starLevelName;
		//城市编码
		private String regionCode;
		//运脉号
		private String code;
		//企业状态（1. 已入驻 2.待认领 3.未入驻）
		private int entStatus;
		//拼音
		private String pinYin;
		//企业状态名称
		private String typeName;
		//企业类型     0 内部企业   1 工商局企业
		private Integer entType; 
		/**
	     * 企业名称
	     */
	    private String entName;
	    /**
	     * 法人
	     */
	    private String legalPerson;
	    /**
	     * 成立时间
	     */
	    private String buildDate;
	    /**
	     * 统一社会信用代码
	     */
	    private String regNumber;
	    /**
	     * 登记状态
	     */
	    private String regStatus;
	    
	    /**
	     * 法人或者认证人（内部企业）
	     */
	    private String legalPeople;
	    
	    
	    private SimpEntButton simpEntButton;
		
		public String getEntName() {
			return entName;
		}
		public void setEntName(String entName) {
			this.entName = entName;
		}
		public String getLegalPerson() {
			return legalPerson;
		}
		public void setLegalPerson(String legalPerson) {
			this.legalPerson = legalPerson;
		}
		public String getBuildDate() {
			return buildDate;
		}
		public void setBuildDate(String buildDate) {
			this.buildDate = buildDate;
		}
		public String getRegNumber() {
			return regNumber;
		}
		public void setRegNumber(String regNumber) {
			this.regNumber = regNumber;
		}
		public String getRegStatus() {
			return regStatus;
		}
		public void setRegStatus(String regStatus) {
			this.regStatus = regStatus;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public Integer getEntAuditStatus() {
			return entAuditStatus;
		}
		public void setEntAuditStatus(Integer entAuditStatus) {
			this.entAuditStatus = entAuditStatus;
		}
		public String getEntTypeDictGUID() {
			return entTypeDictGUID;
		}
		public void setEntTypeDictGUID(String entTypeDictGUID) {
			this.entTypeDictGUID = entTypeDictGUID;
		}
		public String getEntTypeDictName() {
			return entTypeDictName;
		}
		public void setEntTypeDictName(String entTypeDictName) {
			this.entTypeDictName = entTypeDictName;
		}
		public String getEntId() {
			return entId;
		}
		public void setEntId(String entId) {
			this.entId = entId;
		}
		public String getLogo() {
			return logo;
		}
		public void setLogo(String logo) {
			this.logo = logo;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getProfile() {
			return profile;
		}
		public void setProfile(String profile) {
			this.profile = profile;
		}
		public long getTotalEntrustOrdersNum() {
			return totalEntrustOrdersNum;
		}
		public void setTotalEntrustOrdersNum(long totalEntrustOrdersNum) {
			this.totalEntrustOrdersNum = totalEntrustOrdersNum;
		}
		public long getTotaltCarrierOrdersNum() {
			return totaltCarrierOrdersNum;
		}
		public void setTotaltCarrierOrdersNum(long totaltCarrierOrdersNum) {
			this.totaltCarrierOrdersNum = totaltCarrierOrdersNum;
		}
		public int getInviteState() {
			return InviteState;
		}
		public void setInviteState(int inviteState) {
			InviteState = inviteState;
		}
		public long getContactNum() {
			return contactNum;
		}
		public void setContactNum(long contactNum) {
			this.contactNum = contactNum;
		}
		public String getPersonId() {
			return personId;
		}
		public void setPersonId(String personId) {
			this.personId = personId;
		}
		public String getPersonName() {
			return personName;
		}
		public void setPersonName(String personName) {
			this.personName = personName;
		}
		public String getPersonMobile() {
			return personMobile;
		}
		public void setPersonMobile(String personMobile) {
			this.personMobile = personMobile;
		}
		public String getImGuid() {
			return imGuid;
		}
		public void setImGuid(String imGuid) {
			this.imGuid = imGuid;
		}
		public Integer getOauthType() {
			return oauthType;
		}
		public void setOauthType(Integer oauthType) {
			this.oauthType = oauthType;
		}
		public String getStarLevelName() {
			return starLevelName;
		}
		public void setStarLevelName(String starLevelName) {
			this.starLevelName = starLevelName;
		}
		public String getRegionCode() {
			return regionCode;
		}
		public void setRegionCode(String regionCode) {
			this.regionCode = regionCode;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getInviteStateName() {
			return InviteStateName;
		}
		public void setInviteStateName(String inviteStateName) {
			InviteStateName = inviteStateName;
		}
		public int getEntStatus() {
			return entStatus;
		}
		public void setEntStatus(int entStatus) {
			this.entStatus = entStatus;
		}
		public List<String> getLabelDictName() {
			return labelDictName;
		}
		public void setLabelDictName(List<String> list) {
			this.labelDictName = list;
		}
		public String getPinYin() {
			return pinYin;
		}
		public void setPinYin(String pinYin) {
			this.pinYin = pinYin;
		}
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		
		public String getLegalPeople() {
			return legalPeople;
		}
		public void setLegalPeople(String legalPeople) {
			this.legalPeople = legalPeople;
		}
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		public SimpEntButton getSimpEntButton() {
			return simpEntButton;
		}
		public void setSimpEntButton(SimpEntButton simpEntButton) {
			this.simpEntButton = simpEntButton;
		}
		
}
