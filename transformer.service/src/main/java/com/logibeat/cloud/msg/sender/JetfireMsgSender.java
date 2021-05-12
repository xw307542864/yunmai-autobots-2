package com.logibeat.cloud.msg.sender;

import com.google.gson.Gson;
import com.logibeat.cloud.common.cache.util.JsonMapper;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.properties.PropertiesUtil;
import com.logibeat.cloud.core.properties.RunMode;
import com.logibeat.cloud.util.tools.HttpClientUtil;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import com.logibeat.jetfire.client.template.CustomTemplate;
import com.logibeat.jetfire.client.template.PushTemplate;
import com.logibeat.jetfire.client.template.SmsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @ClassName: JetfireMsgSender 
 * @Description: 
 * @author kzz 
 * @date 2017年8月19日 下午7:10:21 
 * @version 1.0
 */
@Component
public class JetfireMsgSender {

	private static final Logger logger = LoggerFactory.getLogger(JetfireMsgSender.class);
	
	@Autowired
	private PropertiesUtil properties;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private RunMode runMode;

	/**
	 * 发推送
	 * @param pushTemplates
	 */
	public void sendPush(List<? extends PushTemplate> pushTemplates) {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("template", new Gson().toJson(pushTemplates));
		logger.info(properties.getJetfireURL() + ConstantUtil.pushjetfireUrl);
		logger.info(new Gson().toJson(params));
		taskExecutor.execute(() -> HttpClientUtil.post(properties.getJetfireURL() + ConstantUtil.pushjetfireUrl, params));
    }

	/**
	 * 发推送
	 * @param pushTemplate
	 */
	public void sendPush(PushTemplate pushTemplate) {
		List<PushTemplate> pushTemplates = new ArrayList<>();
		pushTemplates.add(pushTemplate);
		sendPush(pushTemplates);
	}

	/**
	 * 发短信
	 * @param smsTemplate
	 */
	public void sendSms(SmsTemplate smsTemplate){
		Map<String, String> params = new TreeMap<String, String>();
		params.put("smsTemplate", new Gson().toJson(smsTemplate));
		logger.info(properties.getJetfireURL() + ConstantUtil.sendjetfireUrl);
		logger.info(new Gson().toJson(params));
		if(runMode.isSmsMute()){
			taskExecutor.execute(() -> HttpClientUtil.post(properties.getJetfireURL() + ConstantUtil.sendjetfireUrl, params));
		}
	}

	/**
	 * 发自定义
	 * @param customTemplate
	 */
	public void sendCustom(CustomTemplate customTemplate){
        Map<String, String> params = new TreeMap<String, String>();
        params.put("customTemplate", new Gson().toJson(customTemplate));
        logger.info(properties.getJetfireURL() + ConstantUtil.ReqjetfireUrl);
        logger.info(new Gson().toJson(params));
        if(runMode.isSmsMute()){
			taskExecutor.execute(() -> HttpClientUtil.post(properties.getJetfireURL() + ConstantUtil.ReqjetfireUrl, params));
		}
	}


	public String getShortUrl (String url){
		logger.info("调用URL"+properties.getJetfireURL()+ConstantUtil.ShortUrl);
		try{
			Map<String, String> params = new TreeMap<String, String>();
			params.put("url",url);
			String result = HttpClientUtil.post(properties.getJetfireURL()+ConstantUtil.ShortUrl, params);
			JSONPrompt jsonPrompt = JsonMapper.buildNonDefaultMapper().fromJson(result, JSONPrompt.class);
			if(null != jsonPrompt && jsonPrompt.isSuc()){
				return jsonPrompt.getData().toString();
			}
		}catch (Exception e){
			logger.info("短链接异常");
		}


		return null;
	}








 }
