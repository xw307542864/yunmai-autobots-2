package com.logibeat.cloud.vo;import com.logibeat.cloud.core.constant.DateUtil;import org.apache.commons.lang3.StringUtils;import java.util.Date;/** * 车辆gps信息类 Created by Yujinjun on 2016/5/9. Edit by zhangf. */public class CarGpsInfoVo {	private Integer gpsType; // 0: GPS 1:手机 null:未获得GPS信息	private String source; //来源 0：手机，1：星软	private String carid;	private String plateNumber;	private String strSpeed; // 行驶速度	private Integer speed; // 行驶速度	private String dayMile; // 当日里程	private Double lat; // 经度	private Double lng; // 维度	private String temperture; // 温度	private Boolean isColdCar; // 是否是冷冻车	private String locationInfo; // 位置	private String lastGpsTime; // 最后记录时间	private String lastStopTime; // 最后停车时间	private String parkingTime; // 停车时长	private Long parkingTimeNum;// 停车时长	private Boolean stopFlag; // 是否停止	private String directName; // 方向名称	private Double directDegree; // 方向的度数	private Integer srcSoid; // 车辆服务id，唯一值	/**	 * 以下属性是车辆轨迹需要的字段，为了兼容旧版本的基础上优化代码，我将CarGpsVo合并到本类中 edit by zhangf 20161129.	 * 若需要修改本类，请确认后再修改	 */	// private Double directDegree; // 方向的度数	// private Integer speed; // 行驶速度	private Double longitude;// 经度	private Double latitude; // 纬度	private Double allDis;// 总里程数	private Integer feedbackType;// 反馈类型 包括：起,走,停,堵,封,终	private Long millisTime;// gps采集时间戳	private Long stopTime = 0L;	private String stopStartTime; // 停车的结束时间	private String stopEndTime; // 停车的开始时间	private String dynamicId; // 动态ID	private Long pushTimeFormat;		/*	 * public CarGpsInfoVo(CarGpsEntity carGps, String carid) { //super(carGps);	 * 	 * if (carGps == null) { return; }	 * 	 * if(StringUtils.isBlank(carGps.getAddress())){ return; }	 * 	 * this.setCarid(carid); this.setPlateNumber(carGps.getPlateNumber());	 * this.setGpsType(carGps.getSource()); Date end =	 * DateUtil.dateStrToDateTime(carGps.getGpstm()); // 最后记录GPS的时间 Date	 * currentDate = new Date(); // 当前时间	 * 	 * boolean isNotToday = false; if	 * (!DateUtil.dateTOString(currentDate).equals(DateUtil.dateTOString(end)))	 * { isNotToday = true; }	 * 	 * boolean isOverTime = false; Long minuteLong =	 * DateUtil.getDistanceTimeForMinute(end, currentDate); if (minuteLong > 30)	 * { isOverTime = true; }	 * 	 * this.setDayMile(isNotToday ? "- -" : carGps.getDayDis().toString());	 * this.setStrSpeed(isNotToday || isOverTime ? "- -" :	 * carGps.getSpeed()+"km/h"); this.setSpeed(isNotToday || isOverTime ? 0 :	 * carGps.getSpeed().intValue()); this.setLat(carGps.getLatitude());	 * this.setLng(carGps.getLongitude());	 * this.setLastGpsTime(carGps.getGpstm());	 * this.setLastStopTime(carGps.getLastStopTime());	 * this.setColdCar(carGps.getColdCar() == 1 ? true : false);	 * this.setLocationInfo(carGps.getAddress()); this.setDirectDegree((double)	 * (carGps.getDirection() / 10)); this.setStopFlag(false);	 * 	 * // 判断是否停止,速度小于5公里 if (carGps.getSpeed() < 5) this.setStopFlag(true);	 * 	 * this.setTemperture(carGps.getTemperture1());	 * 	 * if (this.getSpeed() == 0) { Date start =	 * DateUtil.dateStrToDateTime(carGps.getLastStopTime());	 * this.setParkingTime(isNotToday ? "- -" :	 * DateUtil.getDistanceTimeStr(start, end)); } }	 */	/*	 * public CarGpsInfoVo(CarLocationEntity carLocationEntity) {	 * super(carLocationEntity);	 * 	 * if (carLocationEntity == null) { return; }	 * 	 * this.setLat(carLocationEntity.getLat());	 * this.setLng(carLocationEntity.getLng());	 * this.setLocationInfo(carLocationEntity.getAddress());	 * this.setLastGpsTime(DateUtil.timestamp2Str(carLocationEntity.getAddtime()	 * )); }	 */	public CarGpsInfoVo() {		super();	}	public String getCarid() {		return carid;	}	public void setCarid(String carid) {		this.carid = carid;	}	public String getPlateNumber() {		return plateNumber;	}	public void setPlateNumber(String plateNumber) {		this.plateNumber = plateNumber;	}	public String getStrSpeed() {		return strSpeed;	}	public void setStrSpeed(String strSpeed) {		this.strSpeed = strSpeed;	}	public Integer getSpeed() {		return speed;	}	public void setSpeed(Integer speed) {		this.speed = speed;	}	public String getDayMile() {		return dayMile;	}	public void setDayMile(String dayMile) {		this.dayMile = dayMile;	}	public Double getLat() {		return lat;	}	public void setLat(Double lat) {		this.latitude = lat;		this.lat = lat;	}	public Double getLng() {		return lng;	}	public void setLng(Double lng) {		this.longitude = lng;		this.lng = lng;	}	public String getTemperture() {		return temperture;	}	public void setTemperture(String temperture) {		this.temperture = temperture;	}	public Boolean getIsColdCar() {		return isColdCar;	}	public void setColdCar(Boolean isColdCar) {		this.isColdCar = isColdCar;	}	public String getLocationInfo() {		return locationInfo;	}	public void setLocationInfo(String locationInfo) {		this.locationInfo = locationInfo;	}	public String getLastGpsTime() {		return lastGpsTime;	}	public void setLastGpsTime(String lastGpsTime) {		this.lastGpsTime = lastGpsTime;	}	public String getLastStopTime() {		return lastStopTime;	}	public void setLastStopTime(String lastStopTime) {		this.lastStopTime = lastStopTime;	}	public String getParkingTime() {		return parkingTime;	}	public void setParkingTime(String parkingTime) {		this.parkingTime = parkingTime;	}	public Boolean getStopFlag() {		return stopFlag;	}	public void setStopFlag(Boolean stopFlag) {		this.stopFlag = stopFlag;	}	public String getDirectName() {		return directName;	}	public void setDirectName(String directName) {		this.directName = directName;	}	public Double getDirectDegree() {		return directDegree;	}	public void setDirectDegree(Double directDegree) {		this.directDegree = directDegree;	}	public Integer getSrcSoid() {		return srcSoid;	}	public void setSrcSoid(Integer srcSoid) {		this.srcSoid = srcSoid;	}	public Long getParkingTimeNum() {		return parkingTimeNum;	}	public void setParkingTimeNum(Long parkingTimeNum) {		this.parkingTimeNum = parkingTimeNum;	}	public Double getLongitude() {		return longitude;	}	public void setLongitude(Double longitude) {		this.longitude = longitude;	}	public Double getLatitude() {		return latitude;	}	public void setLatitude(Double latitude) {		this.latitude = latitude;	}	public Double getAllDis() {		return allDis;	}	public void setAllDis(Double allDis) {		this.allDis = allDis;	}	public Integer getFeedbackType() {		return feedbackType;	}	public void setFeedbackType(Integer feedbackType) {		this.feedbackType = feedbackType;	}	public Long getMillisTime() {		return millisTime;	}	public void setMillisTime(Long millisTime) {		this.millisTime = millisTime;	}	public void setIsColdCar(Boolean isColdCar) {		this.isColdCar = isColdCar;	}	public Integer getGpsType() {		return gpsType;	}	public void setGpsType(Integer gpsType) {		this.gpsType = gpsType;	}	public String getSource() {		return source;	}	public void setSource(String source) {		this.source = source;	}	public Long getStopTime() {		return stopTime;	}	public void setStopTime(Long stopTime) {		this.stopTime = stopTime;	}	public String getStopStartTime() {		return stopStartTime;	}	public void setStopStartTime(String stopStartTime) {		this.stopStartTime = stopStartTime;	}	public String getStopEndTime() {		return stopEndTime;	}	public void setStopEndTime(String stopEndTime) {		this.stopEndTime = stopEndTime;	}	public String getDynamicId() {		return dynamicId;	}	public void setDynamicId(String dynamicId) {		this.dynamicId = dynamicId;	}	public Long getPushTimeFormat() {		return pushTimeFormat;	}	public void setPushTimeFormat(Long pushTimeFormat) {		this.pushTimeFormat = pushTimeFormat;	}	}