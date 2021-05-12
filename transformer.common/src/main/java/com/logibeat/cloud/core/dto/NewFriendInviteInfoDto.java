package com.logibeat.cloud.core.dto;

import com.logibeat.cloud.util.tools.pageMdl.BaseDTO;

/**
 * 邀请好友DTO

* @Title: Driver_NewFriendInviteInfoDto.java

* @Package com.yunmaigo.qtz.driver.dto

* @Description: TODO(用一句话描述该文件做什么)

* @author Wilson   

* @date 2015年12月25日 下午4:12:33

* @version V1.0
 */
public class NewFriendInviteInfoDto extends BaseDTO {
	   //人员ID（会员,0表示该会员还不存在后台会进行自动注册并发送短信）
    private String personID;
    
    //手机号
    private String mobile;
    
    //验证信息（留言）
    private String message;
    
    //姓名
    private String name;
    
    //企业ID
    private String entId;
    
    //绑定车辆ID（自有司机赋值）
    private String carId;
    
    //组织分组（外部司机赋值）
    private String orgId;
    
    //邀请类型（枚举）
    private Integer inviteType;

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getInviteType() {
		return inviteType;
	}

	public void setInviteType(Integer inviteType) {
		this.inviteType = inviteType;
	}
    
    
    
}
