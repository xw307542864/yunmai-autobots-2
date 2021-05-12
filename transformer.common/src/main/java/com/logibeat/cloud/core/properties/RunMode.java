package com.logibeat.cloud.core.properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("file:${user.home}/webapp-conf/qtz.properties")
public class RunMode {

    private String runKey;
    private int cacheAppUserInfoTime;
    private int cacheWebUserInfoTime;
    private int cacheSmsTime;

    /**
     * 获得消息中心地址
     * @return
     */
    public String getMessageCenterUrl() {
        return getConfigProperty("message.center.url");
    }

    /**
     * 获得分享链接地址
     *
     * @return
     */
    public String getShareUrl() {
        return getConfigProperty("share.url");
    }

    /**
     * 获得查看共享车辆链接地址
     *
     * @return
     */
    public String getCatShareCarUrl() {
        return getConfigProperty("catShareCar.url");
    }

    /**
     * 确认是否为Debug模式
     *
     * @return
     */
    public Boolean debugMode() {
        runKey = getConfigProperty("qtz.runmode");
        if (!"pro".equalsIgnoreCase(runKey)) {
            return true;
        }
        return false;
    }

    /**
     * 获得高德地图的key
     * @return
     */
    public String getGaoDeKey() {
        return getConfigProperty("gaode.map.key");
    }

    /**
     * 获得服务器url
     * @return
     */
    public String getServerUrl() {
        return getConfigProperty("server.url");
    }

    /**
     * 获得高德地图的key
     * @return
     */
    public String getGaoDeUrl() {
        return getConfigProperty("gaode.map.url");
    }


    /**
     * 获得邀请注册来链接
     * @return
     */
    public String getInviteUrl () {
        return getConfigProperty("invite.url");
    }

    /**
     * 获得语音手机的号码
     * @return
     */
    public List<String> getVoiceMobiles() {
        String[] strArray = convertStrToArray(getConfigProperty("sms.voice.mobiles"));
        List<String> list = new ArrayList<>();
        for (String str: strArray
             ) {
            list.add(str);
        }
        return list;
    }

    private String[] convertStrToArray(String configProperty) {
        String[] strArray;
        strArray = configProperty.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }

    /**
     * 确认短信是否已经静音
     *
     * @return true代表静音，false代表直接发送
     */
    public Boolean isSmsMute() {
//        return "true".equalsIgnoreCase(getConfigProperty("sms.mute", true));
    	return "true".equalsIgnoreCase(getConfigProperty("sms.mute"));
    }

    /**
     * 获得推送(任务类型）的限制时间
     *
     * @return
     */
    public int getPushTaskLimitTime() {
        if (StringUtils.isNotBlank(getConfigProperty("push.task.time"))) return Integer.parseInt(getConfigProperty("push.task.time"));
        return 0;
    }

    /**
     * 获得推送(好友类型）的限制时间
     *
     * @return
     */
    public int getPushFriendimitTime() {
        if (StringUtils.isNotBlank(getConfigProperty("push.friend.time"))) return Integer.parseInt(getConfigProperty("push.friend.time"));
        return 0;
    }

    /**
     * @return
     */
    public int getCacheAppUserInfoTime() {
        String t = getConfigProperty("qtz.cache.app.user.time");
        if (!StringUtils.isEmpty(t)) {
            cacheAppUserInfoTime = Integer.valueOf(t);
            return cacheAppUserInfoTime;
        }
        return -1;
    }

    public int getCacheWebUserInfoTime() {
        String t = getConfigProperty("qtz.cache.web.user.time");
        if (!StringUtils.isEmpty(t)) {
            cacheWebUserInfoTime = Integer.valueOf(t);
            return cacheWebUserInfoTime;
        }
        return -1;
    }

    public int getCacheSmsTime() {
        String t = getConfigProperty("qtz.cache.sms.user.time");
        if (!StringUtils.isEmpty(t)) {
            cacheSmsTime = Integer.valueOf(t);
            return cacheSmsTime;
        }
        return -1;
    }

    public String getMsgSecret() {
        String t = getConfigProperty("msg.secret");
        if (!StringUtils.isEmpty(t)) {
            return t;
        }
        return "";
    }
    
    @Resource
    private Environment env;

    private String getConfigProperty(String prop) {
        return env.getProperty(prop);
    }
    
    /**
     * 获得微信url
     * @return
     */
    public String getWechatUrl() {
        return getConfigProperty("wechat.url");
    }

//    private String getConfigProperty(String prop, Boolean optional) {
//        if (!optional) {
//            return env.getRequiredProperty(prop);
//        } else {
//            return env.getProperty(prop);
//        }
//    }
}
