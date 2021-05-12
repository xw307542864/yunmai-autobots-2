package com.logibeat.cloud.common.enumtype;

public enum AuditEventType {

	Unknown(0, "未知"),
	PersonVerification(1, "实名认证"), 
	AgentVerification(2, "经纪人认证"), 
    DriverVerification(3, "司机认证"),
    EnterpriseVerification(4, "企业认证"), 
	QualityVerification(5, "资质认证"),
	DrivingLicenseVerification(6, "驾驶证认证"),
	PersonQualityVerification(7, "从业资格认证"),
	VehicleVerification(8, "车辆认证"),
	GuaranteeVerification(9, "交强险认证");



	protected Integer  value;
	protected String  description;

	AuditEventType(Integer value, String description){
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

	public static AuditEventType find(Integer type) {
		if (type == null) {
			return Unknown;
		}
		for (AuditEventType obj : AuditEventType.values()) {
			Integer value = obj.value;
			if (value.equals(type)) {
				return obj;
			}
		}
		return Unknown;
	}
	
	
}
