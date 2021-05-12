package com.logibeat.cloud.common.vo;

public class NoticeInfoVo {
	private String GUID;                // 通知主键
	private int NoticeSmallType;        // 消息类型
	private String RelationGUID;        // 关联GUID（有ID的话存ID,例如任务就存任务的GUID）
	private String SendTime;            // 发送时间
	private String Title;               // 标题
	private String Content;             // 内容
	private boolean IsRead;             // 是否已读
	private int NoticeGrade;            // 重要等级
	
	private String CarId;
	
	public String getGUID() {
		return GUID;
	}
	public void setGUID(String gUID) {
		GUID = gUID;
	}
	public int getNoticeSmallType() {
		return NoticeSmallType;
	}
	public void setNoticeSmallType(int noticeSmallType) {
		NoticeSmallType = noticeSmallType;
	}
	public String getRelationGUID() {
		return RelationGUID;
	}
	public void setRelationGUID(String relationGUID) {
		RelationGUID = relationGUID;
	}
	public String getSendTime() {
		return SendTime;
	}
	public void setSendTime(String sendTime) {
		SendTime = sendTime;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public boolean isIsRead() {
		return IsRead;
	}
	public void setIsRead(boolean isRead) {
		IsRead = isRead;
	}
	public int getNoticeGrade() {
		return NoticeGrade;
	}
	public void setNoticeGrade(int noticeGrade) {
		NoticeGrade = noticeGrade;
	}
	public String getCarId() {
		return CarId;
	}
	public void setCarId(String carId) {
		CarId = carId;
	}
	
	
	
	
	
}
