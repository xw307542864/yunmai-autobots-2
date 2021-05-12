package com.logibeat.cloud.core.dto;

public class DrivingLicenseDto {
    private String baseUserId;
    private String clientSystem;
    private String personName;
    private String driverLicensePic;//驾驶证照片
    private String driverLicenseNumber;//驾驶证号码
    private String driverType;//驾照类型 eg:A1,C1
    private String driverStartTime;//驾照有效期开始时间
    private String diverEndTime;//驾照有效期结束时间
    private String driverLicenseBackPic;//驾驶证反面照片
    private String auditInfo;
    private Integer auditStatus; //认证状态

    private String auditType;// 1:新认证  2：过期认证

    private String exceptionReason; //异常原因

    //1：是 0：否
    private Integer exception;


    private String firstTakeTime;
    
    public String getDriverLicenseBackPic() {
		return driverLicenseBackPic;
	}
	public void setDriverLicenseBackPic(String driverLicenseBackPic) {
		this.driverLicenseBackPic = driverLicenseBackPic;
	}
	public String getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}
	public String getClientSystem() {
        return clientSystem;
    }
    public void setClientSystem(String clientSystem) {
        this.clientSystem = clientSystem;
    }
    public String getBaseUserId() {
        return baseUserId;
    }
    public void setBaseUserId(String baseUserId) {
        this.baseUserId = baseUserId;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getExceptionReason() {
        return exceptionReason;
    }

    public void setExceptionReason(String exceptionReason) {
        this.exceptionReason = exceptionReason;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getException() {
        return exception;
    }

    public void setException(Integer exception) {
        this.exception = exception;
    }


    public String getFirstTakeTime() {
        return firstTakeTime;
    }

    public void setFirstTakeTime(String firstTakeTime) {
        this.firstTakeTime = firstTakeTime;
    }
}



