package com.logibeat.cloud.common.vo;

/**
 * 合作企业相关信息
* @Title: EntCoopShortInfoVo
* @Description:
* @Company: 运脉科技
* @author   wilson
* @date     2015年12月11日
 */
public class EntCoopInfoVo {
	
	/**
	 * 企业地址
	 */
	private String Address;
	
	/**
	 * 企业编号 

	 */
	private String Code;
	
	/**
	 * 名称
	 */
	private String Name;
	
	/**
	 * 联系人 
	 */
	private ContactInfoVo Contact;
	
	/**
	 * 联系人数量 
	 */
	private Integer ContactNum;
	
	/**
	 * 关联车辆 
	 */
	private Integer CoopCarNum;
	
	/**
	 * 合作关系 

	 */
	private String CoopType;
	
	/**
	 * 当前接单数/当前任务（我方为该企业承运的未完成订单数） 
	 */
	private Integer CurrentCarrierOrdersNum;
	
	/**
	 * 当前发单数（企业版专用 我方委托给该企业未完成的订单数） 
	 */
	private Integer CurrentEntrustOrdersNum;
	
	/**
	 * 企业认证状态 
	 */
	private Integer EntAuditStatus;
	
	/**
	 * 企业类型编码 
	 */
	private String entTypeDictGUID;
	
	/**
	 * 企业类型名称 
	 */
	private String EntTypeDictName;
	
	/**
	 * 邀请状态（枚举 对方状态） 
	 */
	private Integer HeInviteState;
	
	/**
	 * 主键
	 */
	private String ID;
	
	/**
	 * 是否共享地理位置（司机版专用） 
	 */
	private boolean IsShareGps;
	
	/**
	 * 是否星标企业（企业版专用） 
	 */
	private boolean IsStarMark;
	
	/**
	 * 是否合作/所属企业
	 */
	private boolean isFriend;
	
	/**
	 * 企业Logo图片地址 
	 */
	private String Logo;
	
	/**
	 * 企业简介
	 */
	private String Profile;
	
	
	/**
	 * 邀请状态（枚举 我方状态） 
	 */
	private Integer MyInviteState;
	
	/**
	 * 自有车辆 
	 */
	private Integer SelfCarNum;
	
	/**
	 * 企业星级 
	 */
	private Integer Star;
	
	/**
	 * 发单总数（企业版专用 我方委托给该企业的订单总数） 
	 */
	private Integer TotalEntrustOrdersNum;
	
	/**
	 * 接单总数/合作次数（我方为该企业承运的订单总数） 
	 */
	private Integer TotaltCarrierOrdersNum;
	
	private String ImGUID;
	/**
	 * 人气
	 */
	private Integer popularity;

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	 

	public ContactInfoVo getContact() {
		return Contact;
	}

	public void setContact(ContactInfoVo contact) {
		Contact = contact;
	}

	public Integer getContactNum() {
		return ContactNum;
	}

	public void setContactNum(Integer contactNum) {
		ContactNum = contactNum;
	}

	public Integer getCoopCarNum() {
		return CoopCarNum;
	}

	public void setCoopCarNum(Integer coopCarNum) {
		CoopCarNum = coopCarNum;
	}

	public String getCoopType() {
		return CoopType;
	}

	public void setCoopType(String coopType) {
		CoopType = coopType;
	}

	public Integer getCurrentCarrierOrdersNum() {
		return CurrentCarrierOrdersNum;
	}

	public void setCurrentCarrierOrdersNum(Integer currentCarrierOrdersNum) {
		CurrentCarrierOrdersNum = currentCarrierOrdersNum;
	}

	public Integer getCurrentEntrustOrdersNum() {
		return CurrentEntrustOrdersNum;
	}

	public void setCurrentEntrustOrdersNum(Integer currentEntrustOrdersNum) {
		CurrentEntrustOrdersNum = currentEntrustOrdersNum;
	}

	public Integer getEntAuditStatus() {
		return EntAuditStatus;
	}

	public void setEntAuditStatus(Integer entAuditStatus) {
		EntAuditStatus = entAuditStatus;
	}

	public String getEntTypeDictGUID() {
		return entTypeDictGUID;
	}

	public void setEntTypeDictGUID(String entTypeDictGUID) {
		this.entTypeDictGUID = entTypeDictGUID;
	}

	public String getEntTypeDictName() {
		return EntTypeDictName;
	}

	public void setEntTypeDictName(String entTypeDictName) {
		EntTypeDictName = entTypeDictName;
	}

	public Integer getHeInviteState() {
		return HeInviteState;
	}

	public void setHeInviteState(Integer heInviteState) {
		HeInviteState = heInviteState;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public boolean getIsShareGps() {
		return IsShareGps;
	}

	public void setIsShareGps(boolean isShareGps) {
		IsShareGps = isShareGps;
	}

	public boolean getIsStarMark() {
		return IsStarMark;
	}

	public void setIsStarMark(boolean isStarMark) {
		IsStarMark = isStarMark;
	}

	public void setIsFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
	
	public boolean getIsFriend() {
		return isFriend;
	}
	
	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public String getProfile() {
		return Profile;
	}

	public void setProfile(String profile) {
		Profile = profile;
	}

	public Integer getMyInviteState() {
		return MyInviteState;
	}

	public void setMyInviteState(Integer myInviteState) {
		MyInviteState = myInviteState;
	}

	public Integer getSelfCarNum() {
		return SelfCarNum;
	}

	public void setSelfCarNum(Integer selfCarNum) {
		SelfCarNum = selfCarNum;
	}

	public Integer getStar() {
		return Star;
	}

	public void setStar(Integer star) {
		Star = star;
	}

	public Integer getTotalEntrustOrdersNum() {
		return TotalEntrustOrdersNum;
	}

	public void setTotalEntrustOrdersNum(Integer totalEntrustOrdersNum) {
		TotalEntrustOrdersNum = totalEntrustOrdersNum;
	}

	public Integer getTotaltCarrierOrdersNum() {
		return TotaltCarrierOrdersNum;
	}

	public void setTotaltCarrierOrdersNum(Integer totaltCarrierOrdersNum) {
		TotaltCarrierOrdersNum = totaltCarrierOrdersNum;
	}

	public String getImGUID() {
		return ImGUID;
	}

	public void setImGUID(String imGUID) {
		ImGUID = imGUID;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	 
	

}
