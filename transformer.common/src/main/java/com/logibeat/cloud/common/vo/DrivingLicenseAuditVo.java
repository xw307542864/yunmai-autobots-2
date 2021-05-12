package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;

public class DrivingLicenseAuditVo {
	private String baseUserId;
	private String personName;//姓名
 	private String failMessage;//认证失败原因
	private Integer driverAuditStatus;
	private String driverLicensePic;//驾驶证照片
	private String driverLicenseBackPic;//驾驶证反面照片

	private String driverLicenseNumber;//驾驶证号码
	private String driverType;//驾照类型 eg:A1,C1
	private String driverStartTime;//驾照有效期开始时间
	private String diverEndTime;//驾照有效期结束时间
	private String noEncryptionNumber;//不加密驾驶证号码
	private Timestamp auditDate;
	private String auditMessage;
	private Timestamp checkTime;//复审时间
	private String exceptionReason; //异常原因
	private String firstTakeTime;



	public Timestamp getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}
	public String getBaseUserId() {
		return baseUserId;
	}
	public void setBaseUserId(String baseUserId) {
		this.baseUserId = baseUserId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getFailMessage() {
		return failMessage;
	}
	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}
	public Integer getDriverAuditStatus() {
		return driverAuditStatus;
	}
	public void setDriverAuditStatus(Integer driverAuditStatus) {
		this.driverAuditStatus = driverAuditStatus;
	}
	public String getDriverLicensePic() {
		return driverLicensePic;
	}
	public void setDriverLicensePic(String driverLicensePic) {
		this.driverLicensePic = driverLicensePic;
	}
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}
	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}
	public String getDriverType() {
		return driverType;
	}
	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}
	public String getDriverStartTime() {
		return driverStartTime;
	}
	public void setDriverStartTime(String driverStartTime) {
		this.driverStartTime = driverStartTime;
	}
	public String getDiverEndTime() {
		return diverEndTime;
	}
	public void setDiverEndTime(String diverEndTime) {
		this.diverEndTime = diverEndTime;
	}

	public String getNoEncryptionNumber() {
		return noEncryptionNumber;
	}

	public void setNoEncryptionNumber(String noEncryptionNumber) {
		this.noEncryptionNumber = noEncryptionNumber;
	}

	public Timestamp getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditMessage() {
		return auditMessage;
	}

	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}

	public String getDriverLicenseBackPic() {
		return driverLicenseBackPic;
	}

	public void setDriverLicenseBackPic(String driverLicenseBackPic) {
		this.driverLicenseBackPic = driverLicenseBackPic;
	}

	public String getExceptionReason() {
		return exceptionReason;
	}

	public void setExceptionReason(String exceptionReason) {
		this.exceptionReason = exceptionReason;
	}

	public String getFirstTakeTime() {
		return firstTakeTime;
	}

	public void setFirstTakeTime(String firstTakeTime) {
		this.firstTakeTime = firstTakeTime;
	}
}
