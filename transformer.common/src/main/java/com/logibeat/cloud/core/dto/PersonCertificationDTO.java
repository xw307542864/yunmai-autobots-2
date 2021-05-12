package com.logibeat.cloud.core.dto;

public class PersonCertificationDTO {
	private String baseUserId;
	private String clientSystem;
	private String idCardFrontPic;//身份证正面照
	private String idCardBackPic;//身份证背面照
	private String handIdCardPic;//手持身份证照
	private String name;//姓名
	private String idCardNumber;//身份证
	private Integer sex;//性别1.男；2.女
	private String birthDay;//出生
	private String idCardExpireDate;
	private String nation;
	
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
	public String getHandIdCardPic() {
		return handIdCardPic;
	}
	public void setHandIdCardPic(String handIdCardPic) {
		this.handIdCardPic = handIdCardPic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getIdCardFrontPic() {
		return idCardFrontPic;
	}
	public void setIdCardFrontPic(String idCardFrontPic) {
		this.idCardFrontPic = idCardFrontPic;
	}
	public String getIdCardBackPic() {
		return idCardBackPic;
	}
	public void setIdCardBackPic(String idCardBackPic) {
		this.idCardBackPic = idCardBackPic;
	}
	public String getIdCardNumber() {
		return idCardNumber;
	}
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getIdCardExpireDate() {
		return idCardExpireDate;
	}

	public void setIdCardExpireDate(String idCardExpireDate) {
		this.idCardExpireDate = idCardExpireDate;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
}
