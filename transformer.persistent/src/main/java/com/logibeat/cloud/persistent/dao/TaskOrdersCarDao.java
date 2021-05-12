package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.TaskOrdersCarEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TaskOrdersCarDao {

    int delete(String id);

    int insert(TaskOrdersCarEntity entity);

    TaskOrdersCarEntity select(String id);

    int update(TaskOrdersCarEntity entity);

    TaskOrdersCarEntity getOrdersCarByOriginalcid(@Param("originalcid")String originalcid);

    /**
     * 根据订单ID获取订单派车信息
     * @Title: getOrdersCarByOrderId
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param ordercCid
     * @param @return    设定文件
     * @return TaskOrdersCarEntity    返回类型
     * @throws
     */
    TaskOrdersCarEntity getOrdersCarByOrderId(@Param("ordersCid")String ordersCid);

    List<TaskOrdersCarEntity> getUnFinishOrdersCar(@Param("personId")String personId);

    /**
     * 获取任务数量
     * @param entId
     * @param carId
     * @param ordersStatus
     * @return
     */
    Long getTaskNum(@Param("entId")String entId, @Param("carId")String carId, @Param("ordersStatus")Integer ordersStatus);


    Long getDriverTaskNum(@Param("personId")String personId,@Param("taskStatus")Integer taskStatus);



    List<TaskOrdersCarEntity> getTaskOrdersCarByCid(String ordersCID);

    List<TaskOrdersCarEntity> getNoticeList(@Param("time")String time, @Param("isUp")boolean isUp, @Param("personId")String personId);

    /**
     * 进行中的任务 (ordersStatus为4，5，6)
     * @param personId
     * @param entId
     * @return
     */
    List<TaskOrdersCarEntity> getRunningTaskList(@Param("personId")String personId, @Param("entId")String entId, @Param("ordersStatus") Integer ordersStatus);

    /**
     * 全部任务
     * @param personId
     * @param entId
     * @return
     */
    List<TaskOrdersCarEntity> getAllTaskList(@Param("personId")String personId, @Param("entId")String entId, @Param("ordersStatus") Integer ordersStatus);

    /**
     * 历史任务
     * @param personId
     * @param entId
     * @return
     */
    List<TaskOrdersCarEntity> getHistoryTaskList(@Param("personId")String personId, @Param("entId")String entId);



    List<TaskOrdersCarEntity> getWaitDepartTaskList(@Param("personId")String personId);
    
    /**
     * 获取所有任务数量
     * @param entId
     * @param personId
     * @return
     */
    Long getAllTaskCount(@Param("personId")String personId, @Param("entId")String entId);

    /**
     * 获取未完成任务数量
     * @param entId
     * @param personId
     * @return
     */
    Long getUnfinishTaskCount(@Param("personId")String personId, @Param("entId")String entId);

    /**
     * 更新任务
     * @param taskId
     * @param originalcid
     * @param taskStatus
     * @param actualLeaveTime
     * @param actualArriveTime
     * @param finishTime
     * @param lat
     * @param lng
     */
    void updateTask(@Param("taskId") String taskId,
                    @Param("originalcid") String originalcid, @Param("taskStatus") Integer taskStatus,
                    @Param("actualLeaveTime") Timestamp actualLeaveTime,
                    @Param("actualArriveTime") Timestamp actualArriveTime,
                    @Param("finishTime") Timestamp finishTime,
                    @Param("lat") Double lat, @Param("lng") Double lng,
                    @Param("exceptionSendTask")Integer exceptionSendTask,
                    @Param("exceptionArriveTask")Integer exceptionArriveTask);


    void  autoFinishPlanCar();

}

