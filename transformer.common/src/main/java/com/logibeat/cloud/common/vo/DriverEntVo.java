package com.logibeat.cloud.common.vo;

/**
 * 企业
* @ClassName: DriverEntVo 
* @Description: 司机所属企业信息
* @author Wilson
* @date 2016年5月10日 上午11:44:11 
*
 */
public class DriverEntVo {

	/**
	 * 企业ID
	 */
	private String entId;
	
	/**
	 * 企业名称
	 */
	private String entName;
	
	/**
	 * 企业LOGO
	 */
	private String entLogo;
	
	/**
	 * 车辆ID
	 */
	private String carId;
	
	/**
	 * 车牌号
	 */
	private String plateNumber;
	
	/**
	 * 车型
	 */
	private String carCoachType;
	
	/**
	 * 车长
	 */
    private String carLength;
    
    /**
     * 重量
     */
    private Double ratedLoad;
    
    /**
     * 体积
     */
    private Double ratedVolume;
    
    /**
     * 企业备注
     */
    private String nameRemark;
    
    /**
     * 是否主驾
     */
    private Boolean isFirstDriver;

	/**
	 * 审核状态
	 * @return
     */
	private Integer auditStatus;

	/**
	 * 车辆认证状态
	 */
	private Integer carAuditStatus;
	
	/**
	 * 车logo
	 * @return
	 */
	private String carLogo;
	
	//所属组织
	private String orgGuid;
	
	//组织名字
	private String orgName;
	
	
	//是否显示车牌
	private boolean isShowPlateNumber;

	//是否显示车型
	private boolean isShowCarCoachType;
	
	
	
	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getEntLogo() {
		return entLogo;
	}

	public void setEntLogo(String entLogo) {
		this.entLogo = entLogo;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}
	
	

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	 

	public String getCarCoachType() {
		return carCoachType;
	}

	public void setCarCoachType(String carCoachType) {
		this.carCoachType = carCoachType;
	}

	public String getCarLength() {
		return carLength;
	}

	public void setCarLength(String carLength) {
		this.carLength = carLength;
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

	public String getNameRemark() {
		return nameRemark;
	}

	public void setNameRemark(String nameRemark) {
		this.nameRemark = nameRemark;
	}

	public Boolean getIsFirstDriver() {
		return isFirstDriver;
	}

	public void setIsFirstDriver(Boolean isFirstDriver) {
		this.isFirstDriver = isFirstDriver;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getCarAuditStatus() {
		return carAuditStatus;
	}

	public void setCarAuditStatus(Integer carAuditStatus) {
		this.carAuditStatus = carAuditStatus;
	}

	public String getCarLogo() {
		return carLogo;
	}

	public void setCarLogo(String carLogo) {
		this.carLogo = carLogo;
	}

	public String getOrgGuid() {
		return orgGuid;
	}

	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public boolean getIsShowPlateNumber() {
		return isShowPlateNumber;
	}

	public void setIsShowPlateNumber(boolean isShowPlateNumber) {
		this.isShowPlateNumber = isShowPlateNumber;
	}

	public boolean getIsShowCarCoachType() {
		return isShowCarCoachType;
	}

	public void setIsShowCarCoachType(boolean isShowCarCoachType) {
		this.isShowCarCoachType = isShowCarCoachType;
	}
	
	
}
