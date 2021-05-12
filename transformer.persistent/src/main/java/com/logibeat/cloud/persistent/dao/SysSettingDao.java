package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.SysSettingEntity;
import org.apache.ibatis.annotations.Param;

public interface SysSettingDao {
    int delete(String guid);

    int insert(SysSettingEntity entity);

    SysSettingEntity select(String guid);

    int update(SysSettingEntity entity);

    /**
     * 输入代码查值
     */
    String getValue(@Param("code") String code);
}