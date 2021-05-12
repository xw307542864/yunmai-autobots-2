package com.logibeat.cloud.persistent.dao;
import com.logibeat.cloud.persistent.entity.EnterpriseCensorEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wudi on 2018/4/11.
 */
@Repository
public interface EnterpriseCensorDao {
    /**
     * 新增企业审核记录
     * @param censor
     * @return
     */
    int insert(EnterpriseCensorEntity censor);

    EnterpriseCensorEntity getCensorEntityByEntId(@Param("entId") String entId);
}
