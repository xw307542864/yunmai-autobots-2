package com.logibeat.cloud.vo;


import java.util.List;

/**
 * 车辆VO对象
* @Title: Driver_CarShortInfoVo
* @Description:
* @Company: 运脉科技
* @author   wilson
* @date     2015年12月11日
 */
public class CarShortInfoVo {
	
	/**
	 * 车辆主键ID 
	 */
	private String carId;

	private Integer coopType;
	
	/**
	 * 车厢长度字典
	 */
	private String carLengthDictGuid;
	
	/**
	 * 车厢长度：单位米 
	 */
	private String carLength;
	
	
	/**
	 * 车型
	 */
	private String carCoachDictGuid;
	
	/**
	 * 车型key 
	 */
	private String carCoachType;
	
	
	/**
	 * 车辆图像 	
	 */
	private String logo;
	
	/**
	 * 车牌号码 
	 */
	private String plateNumber;
	
	/**
	 * 额定载重 吨 
	 */
	private Double ratedLoad;
	
	/**
	 * 额定体积 立方米 
	 */
	private Double ratedVolume;
	
	
	/**
	 * 认证状态
	 */
	private Integer auditStatus;

	/**
	 * 企业id
	 */
	private String entId;

	
	private Integer carAuditStatus;
	private String auditStatusTitle;

	
	private String niChen;
	private String loginName;
	
	/**
	 * 车辆logo
	 */
	private String carLogo;
	/**
	 * 归属企业
	 */
	private List<CarInfoVo> carInfoVos;


	private String entCarId;

	private String myVehicleId;

	
	public Integer getCarAuditStatus() {
		return carAuditStatus;
	}


	public void setCarAuditStatus(Integer carAuditStatus) {
		this.carAuditStatus = carAuditStatus;
	}


	public String getAuditStatusTitle() {
		return auditStatusTitle;
	}


	public void setAuditStatusTitle(String auditStatusTitle) {
		this.auditStatusTitle = auditStatusTitle;
	}


	public String getNiChen() {
		return niChen;
	}


	public void setNiChen(String niChen) {
		this.niChen = niChen;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public List<CarInfoVo> getCarInfoVos() {
		return carInfoVos;
	}


	public void setCarInfoVos(List<CarInfoVo> carInfoVos) {
		this.carInfoVos = carInfoVos;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getCarLength() {
		return carLength;
	}


	public void setCarLength(String carLength) {
		this.carLength = carLength;
	}


	public String getCarCoachType() {
		return carCoachType;
	}


	public void setCarCoachType(String carCoachType) {
		this.carCoachType = carCoachType;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public String getPlateNumber() {
		return plateNumber;
	}


	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}


	public Double getRatedLoad() {
		return ratedLoad;
	}


	public void setRatedLoad(Double ratedLoad) {
		this.ratedLoad = ratedLoad;
	}


	public Double getRatedVolume() {
		return ratedVolume;
	}


	public void setRatedVolume(Double ratedVolume) {
		this.ratedVolume = ratedVolume;
	}


	public Integer getAuditStatus() {
		return auditStatus;
	}


	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}


	public String getCarLengthDictGuid() {
		return carLengthDictGuid;
	}


	public void setCarLengthDictGuid(String carLengthDictGuid) {
		this.carLengthDictGuid = carLengthDictGuid;
	}


	public String getCarCoachDictGuid() {
		return carCoachDictGuid;
	}


	public void setCarCoachDictGuid(String carCoachDictGuid) {
		this.carCoachDictGuid = carCoachDictGuid;
	}


	public String getCarLogo() {
		return carLogo;
	}


	public void setCarLogo(String carLogo) {
		this.carLogo = carLogo;
	}


	public String getEntCarId() {
		return entCarId;
	}

	public void setEntCarId(String entCarId) {
		this.entCarId = entCarId;
	}


	public String getMyVehicleId() {
		return myVehicleId;
	}

	public void setMyVehicleId(String myVehicleId) {
		this.myVehicleId = myVehicleId;
	}


	public Integer getCoopType() {
		return coopType;
	}

	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}
}
