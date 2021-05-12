package com.logibeat.cloud.msg.sender;

import com.google.gson.Gson;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.SenderConstantUtil;
import com.logibeat.cloud.persistent.dao.SysSettingDao;
import com.logibeat.cloud.util.tools.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
public class InviteCarSender {


    private static final Logger logger = LoggerFactory.getLogger(InviteCarSender.class);


    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;



    @Autowired
    private SysSettingDao sysSettingDao;


    /**
     * 更新预约单（物流端）状态
     * @param orderId
     * @param status
     */
    public void updateStatus(String orderId,Integer status){
        Map<String, String> params = new TreeMap<>();
        params.put("subscribeId", orderId);
        params.put("status", status.toString());
        String url = sysSettingDao.getValue(SenderConstantUtil.BIZORDER_URL) + ConstantUtil.UPDATE_INVITECAR_STATUS;
        logger.info(url);
        logger.info(new Gson().toJson(params));
        taskExecutor.execute(() -> HttpClientUtil.post(url, params));
    }


}
