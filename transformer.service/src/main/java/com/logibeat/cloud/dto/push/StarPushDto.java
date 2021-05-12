package com.logibeat.cloud.dto.push;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StarPushDto {


    private String url;

    private String callParam;

    private String pushPlatform;

    private Integer pushType;

    private String keyId;

    private String time;
    
    public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCallParam() {
        return callParam;
    }

    public void setCallParam(String callParam) {
        this.callParam = callParam;
    }

    public String getPushPlatform() {
        return pushPlatform;
    }

    public void setPushPlatform(String pushPlatform) {
        this.pushPlatform = pushPlatform;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }
}
