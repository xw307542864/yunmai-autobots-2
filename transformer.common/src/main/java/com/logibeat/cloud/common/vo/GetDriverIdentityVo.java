package com.logibeat.cloud.common.vo;

public class GetDriverIdentityVo {
	
	private Integer drivingLicenseVerification = 0;//驾驶认证结果
	
	private Integer personVerification = 0;//实名认证结果
	
	private Integer identityType = 1;//身份类型 1都未认证  2实名认证，驾驶未认证 3都已认证

	public Integer getDrivingLicenseVerification() {
		return drivingLicenseVerification;
	}

	public void setDrivingLicenseVerification(Integer drivingLicenseVerification) {
		this.drivingLicenseVerification = drivingLicenseVerification;
	}

	public Integer getPersonVerification() {
		return personVerification;
	}

	public void setPersonVerification(Integer personVerification) {
		this.personVerification = personVerification;
	}

	public Integer getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}
	
	

}
