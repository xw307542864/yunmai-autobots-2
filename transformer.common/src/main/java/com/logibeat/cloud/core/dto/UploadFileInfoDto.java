package com.logibeat.cloud.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.logibeat.cloud.util.tools.pageMdl.Page;

/**
 *文件DTO

* @Title: UploadFileInfoDto.java

* @Package com.yunmaigo.qtz.dto

* @Description: TODO(用一句话描述该文件做什么)

 * @author Wilson

* @date 2016年1月13日 下午1:57:02

* @version V1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadFileInfoDto extends Page {

    private Integer action;

    private String oldFileName;


    private String fileName;

    private String fileSuffix;

    private Double fileSize;

    private Double duration;

    private String fileBase64;

    private String proTime;

    private String filePath;

    private String fileGUID;
    
    private Integer ord;

    //登录引导页
    private String handImg;
    private String licenseImg;
    private String cardImg;

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

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }

    public String getProTime() {
        return proTime;
    }

    public void setProTime(String proTime) {
        this.proTime = proTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileGUID() {
        return fileGUID;
    }

    public void setFileGUID(String fileGUID) {
        this.fileGUID = fileGUID;
    }

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

    public String getHandImg() {
        return handImg;
    }

    public void setHandImg(String handImg) {
        this.handImg = handImg;
    }

    public String getLicenseImg() {
        return licenseImg;
    }

    public void setLicenseImg(String licenseImg) {
        this.licenseImg = licenseImg;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }
}
