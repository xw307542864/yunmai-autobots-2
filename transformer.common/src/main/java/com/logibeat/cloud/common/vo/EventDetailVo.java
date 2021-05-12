package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;
import java.util.List;

/**
 * 任务事件动态

* @Title: Driver_EventDetailVo.java

* @Package com.yunmaigo.qtz.driver.vo

* @Description: TODO(用一句话描述该文件做什么)

* @author Wilson   

* @date 2016年1月8日 下午4:37:41

* @version V1.0
 */
public class EventDetailVo {
	
	/**
	 * 位置 
	 */
	private String Address;
	
	/**
	 * 网点
	 */
	private String AreaName;
	
	/**
	 * 事件动作 
	 */
	private Integer EventAction;

	/**
	 * 事件动作说明
	 */
	private String EventActionStr;
	
	/**
	 * 事件GUID（含YYYYMMDD-） 
	 */
	private String EventGUID;
	
	/**
	 * 事件时间（反馈时间） 
	 */
	private Timestamp EventTime;
	
	/**
	 * 是否已读 
	 */
	private boolean IsRead;
	
	/**
	 * 是否点赞
	 */
	
	private boolean IsLike;
	
	/**
	 * 纬度 
	 */
	private Double Lat;
	
	/**
	 * 经度 
	 */
	private Double Lng;
	
	/**
	 * 点赞数 
	 */
	private Integer Likes;
	
	/**
	 * 多媒体（语音/视频）多个用“,”间隔 
	 */
	private String Medias;
	
	/**
	 * 留言数 
	 */
	private Integer Messages;
	
	/**
	 * 事件通知GUID（含YYYYMMDD-） 
	 */
	private String NoticeGUID;
	
	/**
	 * 照片地址，多个用“,”间隔 
	 */
	private String Pics;
	
	/**
	 * 文本内容 
	 */	
	private String TextContent;
	
	/**
	 * 是否可以删除
	 */
	private boolean IsDelete;
	
	private boolean IsAtPoint;
	
	
	private List<EventRelationInfoVo> Relattions;

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	

	public String getAreaName() {
		return AreaName;
	}

	public void setAreaName(String areaName) {
		AreaName = areaName;
	}

	public Integer getEventAction() {
		return EventAction;
	}

	public void setEventAction(Integer eventAction) {
		EventAction = eventAction;
	}

	public String getEventActionStr() {
		return EventActionStr;
	}

	public void setEventActionStr(String eventActionStr) {
		EventActionStr = eventActionStr;
	}

	public String getEventGUID() {
		return EventGUID;
	}

	public void setEventGUID(String eventGUID) {
		EventGUID = eventGUID;
	}

	public Timestamp getEventTime() {
		return EventTime;
	}

	public void setEventTime(Timestamp eventTime) {
		EventTime = eventTime;
	}

	public boolean getIsRead() {
		return IsRead;
	}

	public void setIsRead(boolean isRead) {
		IsRead = isRead;
	}
	
	public boolean getIsLike() {
		return IsLike;
	}

	public void setIsLike(boolean IsLike) {
		this.IsLike = IsLike;
	}
	

	public Double getLat() {
		return Lat;
	}

	public void setLat(Double lat) {
		Lat = lat;
	}

	public Double getLng() {
		return Lng;
	}

	public void setLng(Double lng) {
		Lng = lng;
	}

	public Integer getLikes() {
		return Likes;
	}

	public void setLikes(Integer likes) {
		Likes = likes;
	}

	public String getMedias() {
		return Medias;
	}

	public void setMedias(String medias) {
		Medias = medias;
	}

	public Integer getMessages() {
		return Messages;
	}

	public void setMessages(Integer messages) {
		Messages = messages;
	}

	public String getNoticeGUID() {
		return NoticeGUID;
	}

	public void setNoticeGUID(String noticeGUID) {
		NoticeGUID = noticeGUID;
	}

	public String getPics() {
		return Pics;
	}

	public void setPics(String pics) {
		Pics = pics;
	}

	public String getTextContent() {
		return TextContent;
	}

	public void setTextContent(String textContent) {
		TextContent = textContent;
	}

	public List<EventRelationInfoVo> getRelattions() {
		return Relattions;
	}

	public void setRelattions(List<EventRelationInfoVo> relattions) {
		Relattions = relattions;
	}

	public boolean getIsDelete() {
		return IsDelete;
	}

	public void setIsDelete(boolean IsDelete) {
		this.IsDelete = IsDelete;
	}
	
	public boolean getIsAtPoint() {
		return IsAtPoint;
	}

	public void setIsAtPoint(boolean IsAtPoint) {
		this.IsAtPoint = IsAtPoint;
	}
	
	

}
