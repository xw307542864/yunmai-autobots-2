package com.logibeat.cloud.common.vo;

public class QualityAuditVo {

    private String certificatesTypeCode;

    private String certificatesTypeValue;

    private String certificatesPic;

    private String qualityType;

    private String idCardNumber;


    private String auditId;

    private Integer auditStatus;

    private String expireDate;

    private String personName;

    private String auditDate;

    private String certificatesCardNumber;

    private String auditMessage;


    public String getCertificatesTypeCode() {
        return certificatesTypeCode;
    }

    public void setCertificatesTypeCode(String certificatesTypeCode) {
        this.certificatesTypeCode = certificatesTypeCode;
    }

    public String getCertificatesTypeValue() {
        return certificatesTypeValue;
    }

    public void setCertificatesTypeValue(String certificatesTypeValue) {
        this.certificatesTypeValue = certificatesTypeValue;
    }

    public String getCertificatesPic() {
        return certificatesPic;
    }

    public void setCertificatesPic(String certificatesPic) {
        this.certificatesPic = certificatesPic;
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }


    public String getQualityType() {
        return qualityType;
    }

    public void setQualityType(String qualityType) {
        this.qualityType = qualityType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getCertificatesCardNumber() {
        return certificatesCardNumber;
    }

    public void setCertificatesCardNumber(String certificatesCardNumber) {
        this.certificatesCardNumber = certificatesCardNumber;
    }

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }
}
