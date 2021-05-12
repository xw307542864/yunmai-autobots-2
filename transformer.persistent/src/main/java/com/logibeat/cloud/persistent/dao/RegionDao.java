package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.RegionEntity;
import org.apache.ibatis.annotations.Param;

public interface RegionDao {
    int delete(Integer regionId);

    int insert(RegionEntity entity);

    RegionEntity select(Integer regionId);

    int update(RegionEntity entity);

    /**
     * 按照区域code查询
     * @param code
     * @return
     */
    RegionEntity getRegionByCode(@Param("code") String code);
}