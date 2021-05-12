package com.logibeat.cloud.core.dto;

public class GuaranteeAuditDTO {


	private String baseUserId;

	/**
	 * 车辆Id
	 */
	private String carId;

	/**
	 * 保单照片
	 */
	private String guaranteeSlipPic;

	/**
	 * 投保单位
	 */
	private String guaranteeUnit;

	/**
	 * 保险单号
	 * @return
	 */
	private String guaranteeNo;

	/**
	 * 被保人
	 * @return
	 */
	private String recognizee;

	/**
	 * 被保人身份证号
	 */
	private String recCardId;

	/**
	 * 车牌
	 */
	private String platNumber;

	/**
	 * 发动机号
	 */
	private String engineNumber;

	/**
	 * 核定载客
	 */
	private String approvedPassenger;
	
	/**
	 * 核定载质量
	 */
	private String approvedQuality;

	private String guaranteeStartTime;

	private String guaranteeEndTime;
	
	private Integer type = 1;//车险类型
	
	private String platColor;//车牌颜色
	
	private String plateColorValue;
	
	private String platNumberText;
	
	public String getPlateColorValue() {
		return plateColorValue;
	}

	public void setPlateColorValue(String plateColorValue) {
		this.plateColorValue = plateColorValue;
	}

	public String getApprovedQuality() {
		return approvedQuality;
	}

	public void setApprovedQuality(String approvedQuality) {
		this.approvedQuality = approvedQuality;
	}

	public String getPlatNumberText() {
		return platNumberText;
	}

	public void setPlatNumberText(String platNumberText) {
		this.platNumberText = platNumberText;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPlatColor() {
		return platColor;
	}

	public void setPlatColor(String platColor) {
		this.platColor = platColor;
	}

	public String getBaseUserId() {
		return baseUserId;
	}

	public void setBaseUserId(String baseUserId) {
		this.baseUserId = baseUserId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getGuaranteeSlipPic() {
		return guaranteeSlipPic;
	}

	public void setGuaranteeSlipPic(String guaranteeSlipPic) {
		this.guaranteeSlipPic = guaranteeSlipPic;
	}

	public String getGuaranteeUnit() {
		return guaranteeUnit;
	}

	public void setGuaranteeUnit(String guaranteeUnit) {
		this.guaranteeUnit = guaranteeUnit;
	}

	public String getGuaranteeNo() {
		return guaranteeNo;
	}

	public void setGuaranteeNo(String guaranteeNo) {
		this.guaranteeNo = guaranteeNo;
	}

	public String getRecognizee() {
		return recognizee;
	}

	public void setRecognizee(String recognizee) {
		this.recognizee = recognizee;
	}

	public String getRecCardId() {
		return recCardId;
	}

	public void setRecCardId(String recCardId) {
		this.recCardId = recCardId;
	}

	public String getPlatNumber() {
		return platNumber;
	}

	public void setPlatNumber(String platNumber) {
		this.platNumber = platNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getApprovedPassenger() {
		return approvedPassenger;
	}

	public void setApprovedPassenger(String approvedPassenger) {
		this.approvedPassenger = approvedPassenger;
	}

	public String getGuaranteeStartTime() {
		return guaranteeStartTime;
	}

	public void setGuaranteeStartTime(String guaranteeStartTime) {
		this.guaranteeStartTime = guaranteeStartTime;
	}

	public String getGuaranteeEndTime() {
		return guaranteeEndTime;
	}

	public void setGuaranteeEndTime(String guaranteeEndTime) {
		this.guaranteeEndTime = guaranteeEndTime;
	}
}
