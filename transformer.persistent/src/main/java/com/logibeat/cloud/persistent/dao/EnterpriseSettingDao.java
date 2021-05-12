package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.EnterpriseSettingEntity;
import org.apache.ibatis.annotations.Param;

public interface EnterpriseSettingDao {

	EnterpriseSettingEntity find(@Param("guid") String guid);
	
	EnterpriseSettingEntity queryByEntId(@Param("entId") String entId);

    void merge(EnterpriseSettingEntity enterpriseSettingEntity);

    void update(EnterpriseSettingEntity entity);


}
