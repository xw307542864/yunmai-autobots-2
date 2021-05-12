package com.logibeat.cloud.core.dto;

public class PunishTypeDto {

    /**
     * 处置方式  星软code
     */
    private String code;

    /**
     * 处罚方式
     */
    private String value;

    /**
     * 扣分或者罚款数据
     */
    private String data;//根据code存放学习内容id，考试内容id，ai学习类目id，劝导时长(分钟)，其他对应的值（如扣分就是对应的分值，罚款就是对应的罚款金额）
    
    /*劝导数据*/
    private String persuasionBeginTime;//劝导开始时间
    private String persuasionEndTime;//劝导结束时间
    private Integer persuasionSignInterval;//打卡间隔时间(分钟)
    private Integer longitude;//经度
    private Integer latitude;//纬度
    private Integer range;//范围(米)
    private String address;//地址
    private String placeName;//地点
    
    public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPersuasionBeginTime() {
		return persuasionBeginTime;
	}

	public void setPersuasionBeginTime(String persuasionBeginTime) {
		this.persuasionBeginTime = persuasionBeginTime;
	}

	public String getPersuasionEndTime() {
		return persuasionEndTime;
	}

	public void setPersuasionEndTime(String persuasionEndTime) {
		this.persuasionEndTime = persuasionEndTime;
	}

	public Integer getPersuasionSignInterval() {
		return persuasionSignInterval;
	}

	public void setPersuasionSignInterval(Integer persuasionSignInterval) {
		this.persuasionSignInterval = persuasionSignInterval;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
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

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
