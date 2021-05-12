package com.logibeat.cloud.core.dto;

import java.util.List;

public class ViolationDetail {


    /**
     * 车牌号
     */
    private String plateNumber;


    /**
     * 时间
     */
    private String date;

    /**
     * 内容
     */
    private String content;

    /**
     * 地址
     */
    private String  address;



    private List<FileDto> proffList;



    /**
     * 证据---图片
     */
    private List<FileDto> proffPicList;

    /**
     * 证据---视频
     */

    private List<FileDto> proffVedioList;


    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FileDto> getProffPicList() {
        return proffPicList;
    }

    public void setProffPicList(List<FileDto> proffPicList) {
        this.proffPicList = proffPicList;
    }

    public List<FileDto> getProffVedioList() {
        return proffVedioList;
    }

    public void setProffVedioList(List<FileDto> proffVedioList) {
        this.proffVedioList = proffVedioList;
    }

    public List<FileDto> getProffList() {
        return proffList;
    }

    public void setProffList(List<FileDto> proffList) {
        this.proffList = proffList;
    }
}
