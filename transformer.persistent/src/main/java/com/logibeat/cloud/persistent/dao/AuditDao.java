package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.AuditEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AuditDao {
    int delete(String guid);

    int merge(AuditEntity record);

    AuditEntity select(String guid);

    List<AuditEntity> selectAll();

    int update(AuditEntity record);
    
    AuditEntity getAuditEnity(@Param("objectId") String objectId,@Param("auditEventType") Integer auditEventType);

    AuditEntity getsAuditEnityByItem(@Param("objectId") String objectId,@Param("auditEventType") Integer auditEventType,
                                      @Param("auditEventTypeItem") String auditEventTypeItem);


    List<AuditEntity> getAuditByPersonId(String baseUserId);
	
    AuditEntity getAuditEnityBySubmitType(@Param("objectId")String objectId,@Param("auditEventType")Integer auditEventType,
                                                 @Param("personId")String personId);

    List<AuditEntity> getAuditEntityByPass(@Param("personId")String personId,@Param("auditEventType")Integer auditEventType);

    List<AuditEntity> getsAuditListByType(@Param("objectId") String objectId,@Param("auditEventType") Integer auditEventType);


    List<AuditEntity> getExpireList(@Param("objectId") String objectId);

    List<AuditEntity> getWillExpireList(@Param("objectId") String objectId);
    
    List<AuditEntity> getAuditEnityByStatus(@Param("objectId") String objectId,@Param("auditEventType") Integer auditEventType,@Param("auditStatus") Integer auditStatus);

    List<AuditEntity> getAllEntityByEventType(@Param("auditEventType") Integer auditEventType);
}