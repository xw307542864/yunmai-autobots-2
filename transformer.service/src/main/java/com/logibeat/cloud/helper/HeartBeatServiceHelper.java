package com.logibeat.cloud.helper;


import com.logibeat.cloud.core.dto.HeartBeatDto;
import com.logibeat.cloud.persistent.entity.TaskOrdersCarEntity;

/**
 * Created by wilson on 2017/6/12.
 */
public interface HeartBeatServiceHelper {


    /**
     * 注册异常事件
     * @param taskId
     * @param personId
     * @param mobile
     */
    void regHeartBeatException(String taskId, String personId, String mobile, String exceptionId,int type);

    void delHeartBeatEvent(Integer type, String taskId);

    /**
     * 注册车辆GPS事件
     */
    void regCarGpsEvent(HeartBeatDto heartBeatDto);


    void delHeartBeatEvent(Integer eventAction,TaskOrdersCarEntity taskCar);



        /**
         * 删除车辆gps事件
         */
    void delCarGpsEvent(Integer type, String taskId);

}
