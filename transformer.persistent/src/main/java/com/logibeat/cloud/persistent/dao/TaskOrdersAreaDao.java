package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.TaskOrdersAreaEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskOrdersAreaDao {

    int delete(String guid);

    int insert(TaskOrdersAreaEntity entity);

    TaskOrdersAreaEntity select(String guid);

    int update(TaskOrdersAreaEntity entity);

    int deleteOrdersAreaFromOrdersId(String ordersCid);

    /**
     * 查询起始网点
     * @Title: getStartPoint
     * @param ordersCID
     * @return TaskOrdersAreaEntity
     */
    TaskOrdersAreaEntity getStartPoint(@Param("ordersCID") String ordersCID);

    /**
     * 获得结束网点
     * @param ordersCID
     * @return
     */
    TaskOrdersAreaEntity getEndPoint(@Param("ordersCID")String ordersCID);

    /**
     * 获取下一个未触发的网点
     * @param ordersCID
     * @return
     */
    List<TaskOrdersAreaEntity> getUnLeaveNode(@Param("ordersCID")String ordersCID);

    /**
     * 根据订单id查询网点信息
     * @param ordersCID
     * @return
     */
    List<TaskOrdersAreaEntity> getOrdersAreaByCid(@Param("ordersCID")String ordersCID);


    /**
     * 获取途经点
     * @param taskId
     * @return
     */
    List<TaskOrdersAreaEntity> getWayAreaList(@Param("taskId")String taskId);

}