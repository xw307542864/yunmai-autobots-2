package com.logibeat.cloud.msg.template;

import com.alibaba.fastjson.JSONObject;
import com.logibeat.jetfire.client.template.CustomChannelType;
import com.logibeat.jetfire.client.template.CustomTemplate;

/**
 *
 */
public class GpsExceptionCustomTemplate extends CustomTemplate{

    public GpsExceptionCustomTemplate(String startAreaName, String endAreaName, CustomChannelType customChannelType){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("adress", startAreaName+"到"+endAreaName);
        setParameter(jsonObject);
        setTypeId(customChannelType.getValue());
    }

}
