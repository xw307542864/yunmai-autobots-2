package com.logibeat.cloud.persistent.dao;


import com.logibeat.cloud.persistent.entity.SystemPersonEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SyspersonDao {
    int delete(String personID);

    int insert(SystemPersonEntity entity);

    SystemPersonEntity select(String personID);

    int update(SystemPersonEntity entity);

    List<SystemPersonEntity> getSearchList(@Param("keyWord") String keyWord,@Param("personId") String personId);

    /**
     * 获取企业子账号
     * @param entId
     * @return
     */
    List<SystemPersonEntity> getChildPersonList(@Param("entId")String entId);

    SystemPersonEntity getEntityByMobile(@Param("mobile")String mobile);

    SystemPersonEntity getEntityByImId(@Param("imId")String imId);




}