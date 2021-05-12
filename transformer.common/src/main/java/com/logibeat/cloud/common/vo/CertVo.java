package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;

public class CertVo {
    private String guid;
    private String personId;
    private String certType;
    private String certName;
    private Integer source;
    private String cardUrl;//证书图片
    private String name;//姓名
    private String certCardNumber;//证件号码
    private String idCardNumber;//身份证号
    private String certExpireDate;//过期时间
    private String sendCertStartDate;//发证时期
    private String sendCertCompany;//发证公司
    private Timestamp createTime;
    private String idcardHandPic;
    private Integer sex;
    private String nation;
    private String driverType;
    private String driverStartTime;
    private String diverEndTime;
    private String firstTakeTime;//初次领证时间
    private String qualiType;//资格类别

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertCardNumber() {
        return certCardNumber;
    }

    public void setCertCardNumber(String certCardNumber) {
        this.certCardNumber = certCardNumber;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getCertExpireDate() {
        return certExpireDate;
    }

    public void setCertExpireDate(String certExpireDate) {
        this.certExpireDate = certExpireDate;
    }

    public String getSendCertStartDate() {
        return sendCertStartDate;
    }

    public void setSendCertStartDate(String sendCertStartDate) {
        this.sendCertStartDate = sendCertStartDate;
    }

    public String getSendCertCompany() {
        return sendCertCompany;
    }

    public void setSendCertCompany(String sendCertCompany) {
        this.sendCertCompany = sendCertCompany;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getIdcardHandPic() {
        return idcardHandPic;
    }

    public void setIdcardHandPic(String idcardHandPic) {
        this.idcardHandPic = idcardHandPic;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getFirstTakeTime() {
        return firstTakeTime;
    }

    public void setFirstTakeTime(String firstTakeTime) {
        this.firstTakeTime = firstTakeTime;
    }

    public String getQualiType() {
        return qualiType;
    }

    public void setQualiType(String qualiType) {
        this.qualiType = qualiType;
    }
}
