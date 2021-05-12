package com.logibeat.cloud.msg.extra;

import com.logibeat.cloud.core.dto.PushImInfoDto;

import java.util.List;

public class CommonExtraDto {

    /**
     * 类型
     */
    private Integer bizType;

    /**
     * 点击
     */
    private boolean click;


    /**
     * 跳转url
     */
    private String url;


    private List<PushImInfoDto> imInfoList;

    public Integer getBizType() {
        return bizType;
    }


    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public List<PushImInfoDto> getImInfoList() {
        return imInfoList;
    }

    public void setImInfoList(List<PushImInfoDto> imInfoList) {
        this.imInfoList = imInfoList;
    }



}
