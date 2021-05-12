package com.logibeat.cloud.services;




import com.logibeat.cloud.common.vo.EntClassLineHistoryVo;

import java.util.List;

public interface EntClassLineService {

    /**
     * 获得历史线路
     *
     * @param entId
     * @param baseUserId
     * @return
     */
    List<EntClassLineHistoryVo> getHistoryLine(String entId, String baseUserId);

}
