package com.logibeat.cloud.core.dto;

public class AuditQualityDto {

    private String personId;

    private String personName;

    private String auditId;

    private String certificatesTypeValue;

    private String certificatesTypeCode;

    private String certificatesPic;

    private String qualityType;

    private String idCardNumber;

    private String expireDate;

    private String certificatesCardNumber;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getCertificatesTypeValue() {
        return certificatesTypeValue;
    }

    public void setCertificatesTypeValue(String certificatesTypeValue) {
        this.certificatesTypeValue = certificatesTypeValue;
    }

    public String getCertificatesTypeCode() {
        return certificatesTypeCode;
    }

    public void setCertificatesTypeCode(String certificatesTypeCode) {
        this.certificatesTypeCode = certificatesTypeCode;
    }

    public String getCertificatesPic() {
        return certificatesPic;
    }

    public void setCertificatesPic(String certificatesPic) {
        this.certificatesPic = certificatesPic;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCertificatesCardNumber() {
        return certificatesCardNumber;
    }

    public void setCertificatesCardNumber(String certificatesCardNumber) {
        this.certificatesCardNumber = certificatesCardNumber;
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
}
