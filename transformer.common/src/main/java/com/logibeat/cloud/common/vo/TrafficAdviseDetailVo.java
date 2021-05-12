package com.logibeat.cloud.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TrafficAdviseDetailVo {

private String adviseId;
	
	/** 企业ID */
	private String entId;
	/** 企业名称 */
	private String entName;
	
	/** 0待执行 1执行中 3未完成 4完成 5已失效*/
	private Integer status;
	/** 开始时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date beginTime;
	/** 结束时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date endTime;
	/** 劝导时长 */
	private Integer duration = 0;
	/** 间隔打卡时长 */
	private Integer interval = 0;
	/** 已劝导时长 */
	private Integer useTime = 0;
	
	private Integer surplusTime = 0;//剩余劝导时长
	
	private Integer nextClockInTime = 0;//下次打卡时长
	
	/** 经度 */
	private Double lng;
	/** 纬度 */
	private Double lat;
	
	private Integer range;
	
	private String address;
	
	private String placeName;
	
	private String receiptPic;
	
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public Integer getRange() {
		return range;
	}
	public void setRange(Integer range) {
		this.range = range;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReceiptPic() {
		return receiptPic;
	}
	public void setReceiptPic(String receiptPic) {
		this.receiptPic = receiptPic;
	}
	public Integer getNextClockInTime() {
		return nextClockInTime;
	}
	public void setNextClockInTime(Integer nextClockInTime) {
		this.nextClockInTime = nextClockInTime;
	}
	public String getAdviseId() {
		return adviseId;
	}
	public void setAdviseId(String adviseId) {
		this.adviseId = adviseId;
	}
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public Integer getUseTime() {
		return useTime;
	}
	public void setUseTime(Integer useTime) {
		this.useTime = useTime;
	}
	public Integer getSurplusTime() {
		return surplusTime;
	}
	public void setSurplusTime(Integer surplusTime) {
		this.surplusTime = surplusTime;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
}
