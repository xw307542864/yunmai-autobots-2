package com.logibeat.cloud.core.dto;


import com.logibeat.cloud.util.tools.pageMdl.BaseDTO;

import java.util.List;

/**
 * 路况DTO

* @Title: Driver_TrafficDto.java

* @Package com.yunmaigo.qtz.driver.dto

* @Description: TODO(用一句话描述该文件做什么)

* @author Wilson   

* @date 2016年1月6日 下午4:40:18

* @version V1.0
 */
public class TrafficDto extends BaseDTO {
	
	private String CarID;
	
	private String OrdersCID;
	
	private Integer EventAction;
	
	private String TxtContent;
	
    private DynamicGpsDTO Gps;
	
	private List<DynamicFileDTO> Files;

	private String picUrls;

	private String Guid;
	
	private String plateNumber;

	private String version;
	
	private Byte isAtPoint;
	
	private String startAreaGUID;
	
	public String getCarID() {
		return CarID;
	}

	public void setCarID(String carID) {
		CarID = carID;
	}

	public Integer getEventAction() {
		return EventAction;
	}

	public void setEventAction(Integer eventAction) {
		EventAction = eventAction;
	}

	public String getTxtContent() {
		return TxtContent;
	}

	public void setTxtContent(String txtContent) {
		TxtContent = txtContent;
	}

	public DynamicGpsDTO getGps() {
		return Gps;
	}

	public void setGps(DynamicGpsDTO gps) {
		Gps = gps;
	}

	public List<DynamicFileDTO> getFiles() {
		return Files;
	}

	public void setFiles(List<DynamicFileDTO> files) {
		Files = files;
	}

	public String getGuid() {
		return Guid;
	}

	public void setGuid(String guid) {
		Guid = guid;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public String getOrdersCID() {
		return OrdersCID;
	}

	public void setOrdersCID(String ordersCID) {
		OrdersCID = ordersCID;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Byte getIsAtPoint() {
		return isAtPoint;
	}

	public void setIsAtPoint(Byte isAtPoint) {
		this.isAtPoint = isAtPoint;
	}

	public String getStartAreaGUID() {
		return startAreaGUID;
	}

	public void setStartAreaGUID(String startAreaGUID) {
		this.startAreaGUID = startAreaGUID;
	}
	
}
