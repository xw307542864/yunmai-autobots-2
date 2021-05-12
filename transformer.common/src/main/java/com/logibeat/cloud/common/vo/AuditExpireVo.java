package com.logibeat.cloud.common.vo;

public class AuditExpireVo {

    /**
     * 实名认证
     */
    private boolean realNameAuthentication;

    /**
     * 驾驶证认证
     */
    private boolean drivingLicenseAuthentication;

    /**
     * 从业资格认证
     */
    private boolean qualityAuthentication;


    public boolean isRealNameAuthentication() {
        return realNameAuthentication;
    }

    public void setRealNameAuthentication(boolean realNameAuthentication) {
        this.realNameAuthentication = realNameAuthentication;
    }

    public boolean isDrivingLicenseAuthentication() {
        return drivingLicenseAuthentication;
    }

    public void setDrivingLicenseAuthentication(boolean drivingLicenseAuthentication) {
        this.drivingLicenseAuthentication = drivingLicenseAuthentication;
    }

    public boolean isQualityAuthentication() {
        return qualityAuthentication;
    }

    public void setQualityAuthentication(boolean qualityAuthentication) {
        this.qualityAuthentication = qualityAuthentication;
    }
}
