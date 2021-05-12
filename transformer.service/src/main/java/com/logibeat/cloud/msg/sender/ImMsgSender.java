package com.logibeat.cloud.msg.sender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.properties.PropertiesUtil;
import com.logibeat.cloud.msg.dto.ImModeDto;
import com.logibeat.cloud.msg.dto.ImSysDto;
import com.logibeat.cloud.util.tools.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
public class ImMsgSender {
    private static final Logger logger = LoggerFactory.getLogger(ImMsgSender.class);

    @Autowired
    private PropertiesUtil properties;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;



    /**
     * 发IM推送
     * @param imModeDto
     */
    public void sendModePush(ImModeDto imModeDto) {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("modeDto", new Gson().toJson(imModeDto));
        String pushImUrl = properties.getImURL() + ConstantUtil.pushImModeUrl;
        logger.info(pushImUrl);
        logger.info(new Gson().toJson(params));
        taskExecutor.execute(() -> HttpClientUtil.post(pushImUrl, params));
    }

    /**
     * 发IM推送
     * @param imSysDto
     */
    public void sendSysPush(ImSysDto imSysDto) {
        Gson formatGson = new GsonBuilder().disableHtmlEscaping().create();
        Map<String, String> params = new TreeMap<String, String>();
        params.put("templateDto", formatGson.toJson(imSysDto));
        String pushImUrl = properties.getImURL() + ConstantUtil.pushImSysUrl;
        logger.info(pushImUrl);
        logger.info("推送参数"+formatGson.toJson(params));
        taskExecutor.execute(() -> HttpClientUtil.post(pushImUrl, params));
    }
}
