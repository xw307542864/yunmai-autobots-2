package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.SyspersonCertEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Desc: Dao类
 * Date: 2020-12-07
 */
public interface SyspersonCertDao {
    int insert(SyspersonCertEntity syspersonCert);

    SyspersonCertEntity findById(String guid);

    List<SyspersonCertEntity> findByPerson(@Param("personId") String presonId);

    List<SyspersonCertEntity> findByPersonList(@Param("personIdList") List<String> personList,@Param("certType") String certType);

    SyspersonCertEntity findByType(@Param("personId") String personId,@Param("certType") String certType);

    int update(SyspersonCertEntity syspersonCert);

    int delete(String guid);

    int count(SyspersonCertEntity syspersonCert);

    List<SyspersonCertEntity> paging(SyspersonCertEntity syspersonCert);
    
    SyspersonCertEntity findByStartsoftCertId(@Param("startsoftCertId") String startsoftCertId);
}
