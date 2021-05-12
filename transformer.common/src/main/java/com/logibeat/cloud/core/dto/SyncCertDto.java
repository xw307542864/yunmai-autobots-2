package com.logibeat.cloud.core.dto;

public class SyncCertDto {

	private String companyId;//顶级企业ID
	private String personId;//司机id
	private String mobile;//司机手机号码
	private String name;//司机名称
	private String idCardNumber;//身份证号
	private String certType;//证书类型
	private String driverCarType;//驾驶车型
	private String starCertId;//证书id
	private String certCardNumber;//证书编号
	private String certName;//证书名称
	private String trainTime;//培训时间
	private String trainProject;//培训项目
	private String sendCertStartDate;//发证日期
	private String certExpireDate;//有效证书日期
	private String sendCertCompany;//发证单位
	private String remark;//备注
	private Integer opType;//操作类型 100新增 200变更 300删除
	private Integer source = 300;
	
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCardNumber() {
		return idCardNumber;
	}
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getDriverCarType() {
		return driverCarType;
	}
	public void setDriverCarType(String driverCarType) {
		this.driverCarType = driverCarType;
	}
	public String getStarCertId() {
		return starCertId;
	}
	public void setStarCertId(String starCertId) {
		this.starCertId = starCertId;
	}
	public String getCertCardNumber() {
		return certCardNumber;
	}
	public void setCertCardNumber(String certCardNumber) {
		this.certCardNumber = certCardNumber;
	}
	public String getCertName() {
		return certName;
	}
	public void setCertName(String certName) {
		this.certName = certName;
	}
	public String getTrainTime() {
		return trainTime;
	}
	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}
	public String getTrainProject() {
		return trainProject;
	}
	public void setTrainProject(String trainProject) {
		this.trainProject = trainProject;
	}
	public String getSendCertStartDate() {
		return sendCertStartDate;
	}
	public void setSendCertStartDate(String sendCertStartDate) {
		this.sendCertStartDate = sendCertStartDate;
	}
	public String getCertExpireDate() {
		return certExpireDate;
	}
	public void setCertExpireDate(String certExpireDate) {
		this.certExpireDate = certExpireDate;
	}
	public String getSendCertCompany() {
		return sendCertCompany;
	}
	public void setSendCertCompany(String sendCertCompany) {
		this.sendCertCompany = sendCertCompany;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOpType() {
		return opType;
	}
	public void setOpType(Integer opType) {
		this.opType = opType;
	}
	
	
}
