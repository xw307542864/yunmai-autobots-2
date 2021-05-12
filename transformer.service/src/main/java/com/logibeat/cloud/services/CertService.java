package com.logibeat.cloud.services;


import com.logibeat.cloud.common.vo.CertVo;
import com.logibeat.cloud.core.dto.CertDto;
import com.logibeat.cloud.core.dto.SyncCertDto;

import java.util.List;

public interface CertService {
    void add(CertDto certDto);

    void insertAllPersonCard();

    void insertAllDriverCard();

    void update(CertDto certDto);
    /**
     * 查询司机所有的认证
     * @param personId
     * @return
     */
    List<CertVo> list(String personId);

    CertVo detail(String guid);

    void del(String guid);
    
    void sync(SyncCertDto syncCertDto);


}
