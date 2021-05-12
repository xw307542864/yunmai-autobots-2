package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.EntClassLineEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EntClassLineDao {
    int delete(String guid);

    int insert(EntClassLineEntity entity);

    EntClassLineEntity select(String guid);

    int update(EntClassLineEntity entity);

    /**
     * 获得历史线路
     * @param entId
     * @param personId
     * @return
     */
    List<EntClassLineEntity> getHistoryLine(@Param("entId") String entId, @Param("personId")String personId);
}