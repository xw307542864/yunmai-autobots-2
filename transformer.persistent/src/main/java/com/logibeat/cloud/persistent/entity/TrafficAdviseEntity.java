package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

/**
 * 表 traffic_advise
 * 
 * @author wilson
 * @date 2021-03-26
 */
public class TrafficAdviseEntity extends EntitySerialize
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String guid;
	/** 企业ID */
	private String entId;
	/** 企业名称 */
	private String entName;
	/** 司机ID */
	private String personId;
	/** 0待执行 1执行中 3未完成 4完成 5已失效*/
	private Integer status;
	/** 开始时间 */
	private Date beginTime;
	/** 结束时间 */
	private Date endTime;
	/** 劝导时长 */
	private Integer duration;
	/** 间隔打卡时长 */
	private Integer intervalTime;
	/** 已劝导时长 */
	private Integer useTime;
	/** 完成时间 */
	private Date finishTime;
	/** 经度 */
	private Double lng;
	/** 纬度 */
	private Double lat;
	/** 回执单图片 */
	private String receiptPic;
	/** 回执单上传时间 */
	private Date receiptTime;
	/** 违规单号 */
	private String relationInfo;
	/** 是否关联安全码 */
	private Integer relationSafeCode;
	
	private Date createTime;
	
	private Date updateTime;
	
	private Integer scope;
	
	private String address;
	
	private String placeName;
	
	private Integer isDel;
	
	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setGuid(String guid) 
	{
		this.guid = guid;
	}

	public String getGuid() 
	{
		return guid;
	}
	public void setEntId(String entId) 
	{
		this.entId = entId;
	}

	public String getEntId() 
	{
		return entId;
	}
	public void setEntName(String entName) 
	{
		this.entName = entName;
	}

	public String getEntName() 
	{
		return entName;
	}
	public void setPersonId(String personId) 
	{
		this.personId = personId;
	}

	public String getPersonId() 
	{
		return personId;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setBeginTime(Date beginTime) 
	{
		this.beginTime = beginTime;
	}

	public Date getBeginTime() 
	{
		return beginTime;
	}
	public void setEndTime(Date endTime) 
	{
		this.endTime = endTime;
	}

	public Date getEndTime() 
	{
		return endTime;
	}
	public void setDuration(Integer duration) 
	{
		this.duration = duration;
	}

	public Integer getDuration() 
	{
		return duration;
	}
	public void setIntervalTime(Integer intervalTime) 
	{
		this.intervalTime = intervalTime;
	}

	public Integer getIntervalTime() 
	{
		return intervalTime;
	}
	public void setUseTime(Integer useTime) 
	{
		this.useTime = useTime;
	}

	public Integer getUseTime() 
	{
		return useTime;
	}
	public void setFinishTime(Date finishTime) 
	{
		this.finishTime = finishTime;
	}

	public Date getFinishTime() 
	{
		return finishTime;
	}
	public void setLng(Double lng) 
	{
		this.lng = lng;
	}

	public Double getLng() 
	{
		return lng;
	}
	public void setLat(Double lat) 
	{
		this.lat = lat;
	}

	public Double getLat() 
	{
		return lat;
	}
	public void setReceiptPic(String receiptPic) 
	{
		this.receiptPic = receiptPic;
	}

	public String getReceiptPic() 
	{
		return receiptPic;
	}
	public void setReceiptTime(Date receiptTime) 
	{
		this.receiptTime = receiptTime;
	}

	public Date getReceiptTime() 
	{
		return receiptTime;
	}
	public void setRelationInfo(String relationInfo) 
	{
		this.relationInfo = relationInfo;
	}

	public String getRelationInfo() 
	{
		return relationInfo;
	}
	public void setRelationSafeCode(Integer relationSafeCode) 
	{
		this.relationSafeCode = relationSafeCode;
	}

	public Integer getRelationSafeCode() 
	{
		return relationSafeCode;
	}
    
}
