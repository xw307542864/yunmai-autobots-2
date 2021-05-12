package com.logibeat.cloud.services;

import com.logibeat.cloud.vo.AssisInfoVo;
import com.logibeat.cloud.common.vo.CarSafeCodeReasonVo;
import com.logibeat.cloud.common.vo.StudyVo;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;

import java.util.List;

public interface SafeCodeService {


    /**
     * 司机安全码
     * @param personId
     * @return
     */
    JSONPrompt getSafeCode(String personId);
    
    /**
     * 违规/其他
     * @param personId
     * @return
     */
    AssisInfoVo safeAssisInfo(String personId);


    /**
     * 获取司机每日一学
     * @param personId
     * @return
     */
    List<StudyVo> getStudyList(String personId, Integer state, Integer pageIndex, Integer pageSize);
    
    /**
     * 车辆安全码
     * @return
     */
    JSONPrompt getCarSafeCode(String entCarId);
    
    /**
     * 获取车辆安全码原因
     * @return
     */
    List<CarSafeCodeReasonVo> getCarSafeCodeReason(String entCarId);

}
