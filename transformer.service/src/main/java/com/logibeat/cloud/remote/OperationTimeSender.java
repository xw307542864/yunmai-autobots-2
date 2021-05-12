package com.logibeat.cloud.remote;

import com.google.gson.Gson;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.SenderConstantUtil;
import com.logibeat.cloud.core.properties.PropertiesUtil;
import com.logibeat.cloud.persistent.dao.SysSettingDao;
import com.logibeat.cloud.util.tools.DateUtils;
import com.logibeat.cloud.util.tools.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Map;
import java.util.TreeMap;

/**
 * 操作时段
 */
@Component
public class OperationTimeSender {

    private static final Logger logger = LoggerFactory.getLogger(OperationTimeSender.class);

    @Autowired
    private PropertiesUtil properties;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private SysSettingDao sysSettingDao;

    /**
     * 发车
     * @param carId
     * @param plateNumber
     * @param firstDriverPersonId
     * @param ordersCid
     * @param startAreaActualLeavTime
     */
    public void sendCar(String carId, String plateNumber, String firstDriverPersonId, String ordersCid, Timestamp startAreaActualLeavTime) {
        Map<String, String> params = new TreeMap<>();
        params.put("carId", carId);
        params.put("plateNumber", plateNumber);
        params.put("firstDriverPersonId", firstDriverPersonId);
        params.put("ordersCid", ordersCid);
        if(startAreaActualLeavTime == null){
        	startAreaActualLeavTime = DateUtils.getSqlTime();
        }
        params.put("startAreaActualLeavTime", startAreaActualLeavTime.toString());
        String url = sysSettingDao.getValue(SenderConstantUtil.MISC_URL) + ConstantUtil.OPRATION_TIME_SEND_CAR;
        logger.info(url);
        logger.info(new Gson().toJson(params));
        taskExecutor.execute(() -> HttpClientUtil.post(url, params));
    }

    /**
     * 完成任务
     * @param ordersCid
     * @param endAreaActualArriveTime
     */
    public void finishTask(String ordersCid, String entId, Timestamp endAreaActualArriveTime) {
        Map<String, String> params = new TreeMap<>();
        params.put("ordersCid", ordersCid);
        if(endAreaActualArriveTime == null){
        	endAreaActualArriveTime = DateUtils.getSqlTime();
        }
        params.put("entId",entId);
        params.put("endAreaActualArriveTime", endAreaActualArriveTime.toString());
        String url = sysSettingDao.getValue(SenderConstantUtil.MISC_URL) + ConstantUtil.OPRATION_TIME_FINISH_TASK;
        logger.info(url);
        logger.info(new Gson().toJson(params));
        taskExecutor.execute(() -> HttpClientUtil.post(url, params));
    }

    /**
     * 司机同意加入企业
     * @param carId
     * @param personId
     */
    public void driverJoinEnt(String carId,  String personId) {
        Map<String, String> params = new TreeMap<>();
        params.put("carId", carId);
        params.put("personId", personId);

        String url = sysSettingDao.getValue(SenderConstantUtil.MISC_URL)  + ConstantUtil.OPRATION_TIME_DRIVER_JOIN_ENT;
        logger.info(url);
        logger.info(new Gson().toJson(params));
        taskExecutor.execute(() -> HttpClientUtil.post(url, params));
    }


}
