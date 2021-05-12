package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.SyspersonInfoEntity;

import java.util.List;

/**
 * Desc: Dao类
 * Date: 2020-12-08
 */
public interface SyspersonInfoDao {
    int insert(SyspersonInfoEntity syspersonInfo);

    SyspersonInfoEntity findByPersonId(String guid);

    int update(SyspersonInfoEntity syspersonInfo);

    int delete(String guid);

    int count(SyspersonInfoEntity syspersonInfo);

    List<SyspersonInfoEntity> paging(SyspersonInfoEntity syspersonInfo);
}
