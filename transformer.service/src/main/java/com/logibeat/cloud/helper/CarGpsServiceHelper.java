package com.logibeat.cloud.helper;

import com.logibeat.cloud.common.vo.CarGpsSourceVo;

import java.util.List;

public interface CarGpsServiceHelper {

    /**
     * 任务跟踪
     * @param ordersCid
     * @return
     */
    List<CarGpsSourceVo> getTaskHistoryOrbit(String ordersCid);
}
