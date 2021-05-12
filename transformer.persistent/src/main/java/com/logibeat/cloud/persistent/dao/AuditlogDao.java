package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.AuditLogEntity;
import org.apache.ibatis.annotations.Param;


public interface AuditlogDao {

    void merge(AuditLogEntity audit);

    void update(AuditLogEntity entity);

    AuditLogEntity getAuditByEntIdPersonId(@Param("baseUserId") String baseUserId, @Param("entId") String entId,@Param("auditType") Integer auditType);

	AuditLogEntity getEnterPriseAuditLog(@Param("objectId")String objectId,@Param("auditEventType")Integer auditEventType);


	Long getSuccessCount(@Param("personId") String personId,@Param("auditType") Integer auditType);

}
