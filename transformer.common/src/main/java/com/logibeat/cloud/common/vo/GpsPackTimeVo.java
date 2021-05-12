package com.logibeat.cloud.common.vo;

/**
 * Created by wilson on 2016/10/27.
 */
public class GpsPackTimeVo {
	//gps最新上传频率
    private Integer uploadLastGpsTime;
    //gps采集频率
    private Integer collectTime;
    //gps历史频率
    private Integer uploadHistoryGpsTime ;
    //gps距离
    private Integer distance;
    //gps时间间隔
    private Integer seconds;
    //是否有任务
    private Boolean isTask;
    //机型
    private String phoneModel;
    

    public Integer getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Integer collectTime) {
        this.collectTime = collectTime;
    }

    
	public Integer getUploadLastGpsTime() {
		return uploadLastGpsTime;
	}

	public void setUploadLastGpsTime(Integer uploadLastGpsTime) {
		this.uploadLastGpsTime = uploadLastGpsTime;
	}

	public Integer getUploadHistoryGpsTime() {
		return uploadHistoryGpsTime;
	}

	public void setUploadHistoryGpsTime(Integer uploadHistoryGpsTime) {
		this.uploadHistoryGpsTime = uploadHistoryGpsTime;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	public Boolean getIsTask() {
		return isTask;
	}

	public void setIsTask(Boolean isTask) {
		this.isTask = isTask;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}
	
}
