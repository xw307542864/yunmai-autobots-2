package com.logibeat.cloud.core.dto;


import com.logibeat.cloud.util.tools.pageMdl.Page;

public class SearchCarDTO extends Page {

	/**
	 * 是否全部，默认“是”，选择全部时 其他条件失效
	 */
	private boolean isAll;
	/**
	 * 星标车辆 0全部1星标2非星标
	 */
	private Byte star;
	/**
	 * 车辆位置,一组城市编码集合 如：300100,100100，默认“”
	 */
	private String carLocation;
	/**
	 * 车辆状态
	 */
	private Integer carState;

	/**
	 * 车型
	 */
	private String coachTypeDictGUID;
	/**
	 * 车长（车辆长度）
	 */
	private String carLengthDictGUID;

	/**
	 * 车长初始
	 */
	private Double startLength;

	/**
	 * 车长结束
	 */
	private Double endLength;

	/**
	 * 长重初始
	 */
	private Double startWeight;

	/**
	 * 车重结束
	 */
	private Double endWeight;
	
	/**
	 * 车辆载重
	 */
	private Double carVolume;

	/**
	 * 查询关键字[企业端]
	 */
	private String keyword;
	
	/**
	 * 查询司机姓名
	 */
	private String driverName;

	/**
	 * 车辆分组id
	 */
	private String groupId;

	/**
	 * 50：所有 51：自有车 52：关联车车
	 */
	private Integer coopType;

	/**
	 * 是否合作 0 ：所有 1 ：合作过的车辆
	 */
	private Integer isCooperation;

	/**
	 * 是否任务 0 ：否 1 ：是
	 */
	private Integer isTask = 0;
	// 企业名称
	private String entName;

	// 车牌号
	private String CarNumber;

	// 手机号
	private String phone;

	// 认证状态
	private Integer carStatus;

	/**
	 * 车辆组织id
	 */
	private String orgGuid;

    /**
	 * 用于组织筛选
	 */
	private String allOrgGuid;

	private String entId;

	private Integer auditStatus;

	/**
	 * 常跑城市
	 */
	private String usualCity;
	/**
	 * 所在城市
	 */
	private String homeCity;
	/**
	 * 所在省
	 */
	private String homeProvince;
	/**
	 * 是否查gps信息， TRUE为不查， false为查，默认false
	 */
	private boolean withoutGps =false;
	
	/**
	 * 0:全部  1企业  2外协
	 */
	private int carSource;

	
	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public boolean getIsAll() {
		return isAll;
	}

	public void setIsAll(boolean isAll) {
		this.isAll = isAll;
	}

	public Byte getStar() {
		return star;
	}

	public void setStar(Byte star) {
		this.star = star;
	}

	public String getCarLocation() {
		return carLocation;
	}

	public void setCarLocation(String carLocation) {
		this.carLocation = carLocation;
	}

	public Integer getCarState() {
		return carState;
	}

	public void setCarState(Integer carState) {
		this.carState = carState;
	}

	public String getCoachTypeDictGUID() {
		return coachTypeDictGUID;
	}

	public void setCoachTypeDictGUID(String coachTypeDictGUID) {
		this.coachTypeDictGUID = coachTypeDictGUID;
	}

	public String getCarLengthDictGUID() {
		return carLengthDictGUID;
	}

	public void setCarLengthDictGUID(String carLengthDictGUID) {
		this.carLengthDictGUID = carLengthDictGUID;
	}

	public Double getStartLength() {
		return startLength;
	}

	public void setStartLength(Double startLength) {
		this.startLength = startLength;
	}

	public Double getEndLength() {
		return endLength;
	}

	public void setEndLength(Double endLength) {
		this.endLength = endLength;
	}

	public Double getStartWeight() {
		return startWeight;
	}

	public void setStartWeight(Double startWeight) {
		this.startWeight = startWeight;
	}

	public Double getEndWeight() {
		return endWeight;
	}

	public void setEndWeight(Double endWeight) {
		this.endWeight = endWeight;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getCoopType() {
		return coopType;
	}

	public void setCoopType(Integer coopType) {
		this.coopType = coopType;
	}

	public Integer getIsCooperation() {
		return isCooperation;
	}

	public void setIsCooperation(Integer isCooperation) {
		this.isCooperation = isCooperation;
	}

	public Integer getIsTask() {
		return isTask;
	}

	public void setIsTask(Integer isTask) {
		this.isTask = isTask;
	}

	public String getAllOrgGuid() {
		return allOrgGuid;
	}

	public void setAllOrgGuid(String allOrgGuid) {
		this.allOrgGuid = allOrgGuid;
	}

	public String getOrgGuid() {
		return orgGuid;
	}

	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}

	public void setAll(boolean isAll) {
		this.isAll = isAll;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getCarNumber() {
		return CarNumber;
	}

	public void setCarNumber(String carNumber) {
		CarNumber = carNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getUsualCity() {
		return usualCity;
	}

	public void setUsualCity(String usualCity) {
		this.usualCity = usualCity;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getHomeProvince() {
		return homeProvince;
	}

	public void setHomeProvince(String homeProvince) {
		this.homeProvince = homeProvince;
	}

	public boolean isWithoutGps() {
		return withoutGps;
	}

	public void setWithoutGps(boolean withoutGps) {
		this.withoutGps = withoutGps;
	}

	public Double getCarVolume() {
		return carVolume;
	}

	public void setCarVolume(Double carVolume) {
		this.carVolume = carVolume;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public int getCarSource() {
		return carSource;
	}

	public void setCarSource(int carSource) {
		this.carSource = carSource;
	}

}
