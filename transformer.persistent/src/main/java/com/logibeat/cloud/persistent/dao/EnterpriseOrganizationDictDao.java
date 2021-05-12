package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.EnterpriseOrganizationDictEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterpriseOrganizationDictDao {
    int delete(String guid);

    int insert(EnterpriseOrganizationDictEntity entity);

    EnterpriseOrganizationDictEntity select(String guid);

    int update(EnterpriseOrganizationDictEntity entity);

    List<EnterpriseOrganizationDictEntity> getEntityListByEntIdAndParentId(@Param("entId") String entId,@Param("parentId") String parentId);
}