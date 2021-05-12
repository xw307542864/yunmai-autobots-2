package com.logibeat.cloud.helper;

import com.logibeat.cloud.persistent.entity.TaskOrdersAreaEntity;
import com.logibeat.cloud.persistent.entity.TaskOrdersCarEntity;

/**
 * Created by wilson on 2017/8/2.
 */
public interface EventSpaceServiceHelper {

    /**
     * 根据当前网点注册下一个点的电子围栏
     * @param targetArea
     * @param action
     * @param pointState
     * @param taskCar
     */
     void registByTarget(TaskOrdersAreaEntity targetArea, Integer action, Integer pointState, TaskOrdersCarEntity taskCar);

    /**
     * 根据目标网点注册电子围栏
     * @param currentArea
     * @param action
     * @param taskCar
     */
     void registByCurrent(TaskOrdersAreaEntity currentArea, Integer action, TaskOrdersCarEntity taskCar);


    /**
     * 发车、到达后，结束电子围栏
     * @param isAutoDepart
     * @param isAutoArrive
     * @param orderscid
     * @param eventAction
     * @param taskAreaGUID
     */
     void finishEventLog(Integer isAutoDepart,Integer isAutoArrive,String orderscid,Integer eventAction, String taskAreaGUID);





}
