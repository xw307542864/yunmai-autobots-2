package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.ImUserEntity;
import org.apache.ibatis.annotations.Param;

public interface ImUserDao {
    int delete(String guid);

    int insert(ImUserEntity entity);

    ImUserEntity select(String guid);

    int update(ImUserEntity entity);

    /**
     * 根据psersonId查询
     * @param personId
     * @return
     */
    ImUserEntity getByPersonId(@Param("personId") String personId);


    ImUserEntity getByType(@Param("platformId") String platformId,@Param("type") Integer type);


    ImUserEntity getByEnt(@Param("entId") String entId);

}
