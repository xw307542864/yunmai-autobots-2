package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.TaskOrdersEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskOrdersDao {
    int delete(String ordersCID);

    int insert(TaskOrdersEntity entity);

    TaskOrdersEntity select(String ordersCID);

    int update(TaskOrdersEntity entity);

    List<TaskOrdersEntity> getTaskOrdersByOriginalcid(@Param("originalcid") String originalcid);

    TaskOrdersEntity getEndOrders(@Param("originalcid")String originalcid);

    List<TaskOrdersEntity> getOrersByEnd(@Param("originalcid")String originalcid);

    /**
     * 删除任务
     * @param orderscid
     * @return
     */
    List<TaskOrdersEntity> getOrderFormList(@Param("orderscid")String orderscid);

    /**
     * 获取首单
     * @param originalcid
     * @return
     */
    TaskOrdersEntity getFirstOrders(@Param("originalcid")String originalcid);

    /**
     * 获取下级委托订单数量
     * @return
     */
    Long getDownEntrustOrderCount(@Param("orderscid")String orderscid, @Param("createorder")Long createorder);

    Long getUnFinishedOrderNum(@Param("personId")String personId);
}