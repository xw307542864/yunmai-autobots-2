package com.logibeat.cloud.common.enumtype;

public enum AuditEventModel {
	Unknown(0, "未知"),
    Person(1, "用户认证"), 
    Ent(2, "企业认证");
	
	protected Integer  value;
	protected String  description;

	AuditEventModel(Integer value, String description){
		this.value = value;
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
