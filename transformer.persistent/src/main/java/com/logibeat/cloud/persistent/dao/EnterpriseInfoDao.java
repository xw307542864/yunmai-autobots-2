package com.logibeat.cloud.persistent.dao;

import org.apache.ibatis.annotations.Param;
import com.logibeat.cloud.persistent.entity.*;
import java.util.List;

public interface EnterpriseInfoDao {
    int delete(String id);

    int insert(EnterpriseInfoEntity entity);

    EnterpriseInfoEntity select(String id);

    int update(EnterpriseInfoEntity entity);

    EnterpriseInfoEntity getDriverShamEnt(@Param("personId") String personId);

    List<EnterpriseInfoEntity> searchEnt(@Param("keyWord")String keyWord);
    
    List<EnterpriseInfoEntity> getEntInfoByEntName(@Param("entName")String entName);

    List<EnterpriseInfoEntity> getEnterpriseInfo(@Param("personId")String personId, @Param("keyword")String keyword);

    EnterpriseInfoEntity getEntByRegNumber(@Param("regNumber")String regNumber);

    /**
     * 查询外部平台同步过来的企业
     * @param ownEntId
     * @return
     */
    EnterpriseInfoEntity getEntByZtoEntId(@Param("ownEntId")String ownEntId);

    /**
     *
     * @param ymNumber
     * @return
     */
    EnterpriseInfoEntity getYmNumber(@Param("ymNumber")String ymNumber);
}