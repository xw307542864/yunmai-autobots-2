package com.logibeat.cloud.core.dto;


import java.util.List;

public class PushImDto {



    /**
     * 消息title
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 100：企业消息  200：系统消息
     */
    private Integer type;

    /**
     * 企业id
     */
    private String entId;

    /**
     * 业务类型
     */
    private Integer bizType;


    /**
     * 是否点击跳转
     */
    private boolean click;

    /**
     * 跳转url
     */
    private String url;


    /**
     * 具体展示内容
     */
    private List<PushImInfoDto> infoList;


    /**
     * 司机id
     */
    private List<String> personIdList;




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
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

    public List<PushImInfoDto> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<PushImInfoDto> infoList) {
        this.infoList = infoList;
    }


    public List<String> getPersonIdList() {
        return personIdList;
    }

    public void setPersonIdList(List<String> personIdList) {
        this.personIdList = personIdList;
    }
}
