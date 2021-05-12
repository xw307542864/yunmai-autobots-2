package com.logibeat.cloud.common.vo;

public class EventRelationInfoVo {
	
	private String ID;//关联信息的ID（企业ID、人员ID、车辆ID等） 
	
	private String Logo;//关联信息的图片（企业、人员、车辆等图片）
	
	private String Name;//关联信息的名称（企业、车辆、人员等名称） 
	
	private String NameRemark;//关联信息的备注名称（企业、车辆、人员等名称）
	
	private int Type;//关联信息类型(EventObjectType)

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getNameRemark() {
		return NameRemark;
	}

	public void setNameRemark(String nameRemark) {
		NameRemark = nameRemark;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}
	
	
}
