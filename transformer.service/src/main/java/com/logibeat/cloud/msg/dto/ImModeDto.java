package com.logibeat.cloud.msg.dto;

import java.util.List;

public class ImModeDto {

    private String title;

    private String content;

    private String extra;

    private String type;

    private String fromUser;

    private List<String> toUser;

    private String url;

    private String sendTime;
    
    private String sendPrefix;//100政府 200协会 300企业
    

    public String getSendPrefix() {
		return sendPrefix;
	}

	public void setSendPrefix(String sendPrefix) {
		this.sendPrefix = sendPrefix;
	}

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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public List<String> getToUser() {
        return toUser;
    }

    public void setToUser(List<String> toUser) {
        this.toUser = toUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
