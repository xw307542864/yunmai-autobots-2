package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.DictEntity;
import org.apache.ibatis.annotations.Param;

public interface DictDao {
    int delete(String guid);

    int insert(DictEntity entity);

    DictEntity select(String guid);

    int update(DictEntity entity);

    /**
     * 依据 guid 获取数据字典项名字
     * @param guid
     * @return
     */
    String getDictNameByGuid(@Param("guid") String guid);
}