package com.logibeat.cloud.core.dto;

/**
 * Created by wilson on 2017/8/11.
 */
public class TrackNodeDTO {


    private String bizJson;

    private String bizInfoJson;

    private Integer action;

    private Integer nodeStatus;

    private String content;

    private String contentRemark;


    public String getBizJson() {
        return bizJson;
    }

    public void setBizJson(String bizJson) {
        this.bizJson = bizJson;
    }

    public String getBizInfoJson() {
        return bizInfoJson;
    }

    public void setBizInfoJson(String bizInfoJson) {
        this.bizInfoJson = bizInfoJson;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(Integer nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentRemark() {
        return contentRemark;
    }

    public void setContentRemark(String contentRemark) {
        this.contentRemark = contentRemark;
    }

}
