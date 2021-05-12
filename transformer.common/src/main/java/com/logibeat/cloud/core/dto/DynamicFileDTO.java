package com.logibeat.cloud.core.dto;



/**
 *文件DTO

 * @Title: DynamicFileDTO.java

 * @package com.logibeat.cloud.core.dto

 * @Description: TODO(用一句话描述该文件做什么)

 * @author Wilson

 * @date 2016年12月日 下午1:57:02

 * @version V1.0
 */
public class DynamicFileDTO {

    private Integer action;

    private String oldFileName;

    private String fileName;

    private String fileSuffix;

    private Double fileSize;

    private Integer duration;

    private String fileBase64;

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public Double getFileSize() {
		return fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getFileBase64() {
		return fileBase64;
	}

	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}

    
}
