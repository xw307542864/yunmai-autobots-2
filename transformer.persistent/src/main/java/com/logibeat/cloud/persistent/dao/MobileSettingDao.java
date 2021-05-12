package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.MobileSettingEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MobileSettingDao {
    int delete(String id);

    int insert(MobileSettingEntity entity);

    MobileSettingEntity select(String id);

    int update(MobileSettingEntity entity);

    MobileSettingEntity getMobileSettingByModel(@Param("phoneModel") String phoneModel, @Param("isTask") Byte isTask);

    List<MobileSettingEntity> getMobileSettingList();

    List<MobileSettingEntity> getListByModel(@Param("phoneModel") String phoneModel);
}