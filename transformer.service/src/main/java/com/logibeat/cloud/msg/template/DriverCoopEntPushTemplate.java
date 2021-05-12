package com.logibeat.cloud.msg.template;

import com.alibaba.fastjson.JSONObject;
import com.logibeat.cloud.msg.constant.MessageConstant;
import com.logibeat.jetfire.client.template.PushTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * 司机申请与企业建立合作
 */
public class DriverCoopEntPushTemplate extends PushTemplate {

    public DriverCoopEntPushTemplate(String personId,String niChen,String entId, String entName, String plateNumber){

        String title = null;
        if(StringUtils.isNotBlank(plateNumber) && plateNumber.length() > 2){
            title = niChen + "[" + plateNumber.substring(0, 2) + "*****]";
        }

        setTitle(title);
        setText(MessageConstant.PushTipMessage.ADD_ANT_ANT.getName());
        setTicker(title + MessageConstant.PushTipMessage.ADD_ANT_ANT.getName());
        setBaseUserId(personId);
        setDisplayType(MessageConstant.PushContentType.NOTIFICATION.getName());
        setType(MessageConstant.MessageTypeToDevice.MESSAGENEWFRIEND.getValue());
        setSound(MessageConstant.DEFAULT_SOUND);

        JSONObject json = new JSONObject();
        json.put("extraBaseUserId", personId);
        json.put("extraEntId", entId);
        json.put("extraEntName", entName);
        json.put("enclosure", "{\"entId\":\"" + entId + "\"}");
        setExtra(json);
    }

}
