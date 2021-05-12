package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.TaskOrdersGoodsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskOrdersGoodsDao {
    int delete(String guid);

    int insert(TaskOrdersGoodsEntity entity);

    TaskOrdersGoodsEntity select(String guid);

    int update(TaskOrdersGoodsEntity entity);

    /**
     * 获取货物信息
     * @param ordersCID
     * @return
     */
    TaskOrdersGoodsEntity getEntityByOrdersCid(@Param("ordersCID") String ordersCID);

    List<TaskOrdersGoodsEntity> getOrderGoods(@Param("ordersCID")String ordersCID);

    /**
     * 根据订单id删除 OrdersGoodsEntity信息
     * @param ordersCID
     */
    int deleteGoodsFromOrdersId(@Param("ordersCID")String ordersCID);
}