package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.UserAuditResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserAuditResultDao {
    int delete(String guid);

    void deleteByAudit(@Param("auditId") String auditId);

    int merge(UserAuditResultEntity record);

    UserAuditResultEntity select(@Param("guid") String guid);

    UserAuditResultEntity getByAudit(@Param("auditId") String auditId);

    List<UserAuditResultEntity> selectAll();

    int update(UserAuditResultEntity record);

    List<UserAuditResultEntity> getUserAuditInfo(@Param("objectId")String objectId,@Param("objectType")Integer objectType);








}