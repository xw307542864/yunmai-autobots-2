package com.logibeat.cloud.services;

import com.logibeat.cloud.common.vo.DriverScoreVo;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;


public interface SafeScoreService {

    DriverScoreVo getDriverScore(String personId, String entId);

    JSONPrompt getDriverScoreRecord(String personId, Integer year,String entId);


    JSONPrompt getSafeScoreSet(String entId);
}
