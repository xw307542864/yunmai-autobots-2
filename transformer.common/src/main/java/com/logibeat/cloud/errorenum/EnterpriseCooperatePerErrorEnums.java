package com.logibeat.cloud.errorenum;


import com.logibeat.cloud.util.tools.enumtype.ExceptionEnums;

public enum EnterpriseCooperatePerErrorEnums implements ExceptionEnums {

	UNKNOW("001", "未知错误"),
	ENTERPRISE_COOPERATE_PER_NOT_EXIST("002", "企业与人员关系不存在"),
	GUID_IS_NULL("003", "GUID不能为空 "),
	IS_NOT_SELF_DRIVER("004", "已经不是该企业的司机");


	private String value;
	private String description;

	EnterpriseCooperatePerErrorEnums(String value, String description) {
		this.value = value;
		this.description = description;
	}

	@Override
	public String module() {
		return "EnterpriseCooperatePer";
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}

