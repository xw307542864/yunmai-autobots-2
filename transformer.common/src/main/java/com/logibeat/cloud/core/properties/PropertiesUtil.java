package com.logibeat.cloud.core.properties;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * @Title:
 * @Author:sean
 * @Since:2015年3月11日
 * @Version:1.1.0
 * @Description:加载properties文件
 */
@Service
@PropertySource("file:${user.home}/webapp-conf/qtz.properties")
public class PropertiesUtil {

    
    @Resource
    private Environment env;

	private String remoteURL;

	private String ikURL;
	
	private String imUrl;

	private String shareUrl;

    public String getStarSofturl() {
        return eval("starsoft.url");
    }
    public String getStarSoftApiKey() {
        return eval("starsoft.apikey");
    }
    public String getStarSoftUid() {
        return eval("starsoft.uid");
    }
    public String getStarSoftCid() {
        return eval("starsoft.cid");
    }
	
	
	public String getRemoteURL() {
        return eval("remote.url");
    }

    public String getJetfireURL() {
        return eval("message.center.url");
    }

    public String getIkURL() {
        return eval("ik.url");
    }
    
    public String getImURL() {
        return eval("im.url");
    }

    public String getShareURL() {
        return eval("shared.url");
    }


    private String eval(String prop) {
        String result = env.getRequiredProperty(prop);
        if (result != null) {
            result = result.trim();
        }
        return result;
    }

    private Integer evalInt(String prop) {
        return Integer.valueOf(env.getRequiredProperty(prop));
    }
	
	
	
	
}
