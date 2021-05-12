package com.logibeat.cloud.services;

import com.logibeat.cloud.common.vo.AppealVo;
import com.logibeat.cloud.common.vo.ViolationVo;
import com.logibeat.cloud.core.dto.AppealDto;
import com.logibeat.cloud.core.dto.SyncViolationDto;
import com.logibeat.cloud.core.dto.UpdateViolationStatusDto;
import com.logibeat.cloud.util.tools.pageMdl.Page;

import java.util.List;

public interface PersonViolationService {


    /**
     * 创建工单
     * @param syncViolationDto
     */
    void create(SyncViolationDto syncViolationDto);


    /**
     * 更新状态
     * @param updateViolationStatusDto
     */
    void updateStatus(UpdateViolationStatusDto updateViolationStatusDto);

    /**
     * 查询工单
     * @param entId
     * @param personId
     * @param status
     * @return
     */
    List<ViolationVo> getList(String entId, String personId, Integer status, Page page);



    Integer getInCount(String entId, String personId, Integer status);


    /**
     * 详情
     * @param violationId
     * @return
     */
    ViolationVo detail(String violationId);


    /**
     * 申述
     * @param appealDto
     */
    void appeal(AppealDto appealDto);

    /**
     * 完成
     * @param violationId
     */
    void confirm(String violationId);


    List<AppealVo>  getAppeal(String violationId);




}
