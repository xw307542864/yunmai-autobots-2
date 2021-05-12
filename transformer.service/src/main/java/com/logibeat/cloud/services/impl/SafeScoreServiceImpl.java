package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.cache.util.StringUtils;
import com.logibeat.cloud.common.starsoft.StarSoft_DriverScoreRecordVo;
import com.logibeat.cloud.common.starsoft.StarSoft_DriverScoreVo;
import com.logibeat.cloud.common.starsoft.StarSoft_SafeScoreSetVo;
import com.logibeat.cloud.common.vo.DriverScoreVo;
import com.logibeat.cloud.errorenum.EnterpriseErrorEnums;
import com.logibeat.cloud.msg.sender.StarSoftSender;
import com.logibeat.cloud.persistent.dao.EntStarSoftDao;
import com.logibeat.cloud.persistent.entity.EntStarSoftEntity;
import com.logibeat.cloud.services.SafeScoreService;
import com.logibeat.cloud.util.tools.DateUtils;
import com.logibeat.cloud.util.tools.exception.ServiceException;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SafeScoreServiceImpl implements SafeScoreService {

    @Autowired
    private StarSoftSender starSoftSender;

    @Autowired
    private EntStarSoftDao entStarSoftDao;


    /**
     * 获取司机安全分
     * @param personId
     * @return
     */
    @Override
    public DriverScoreVo getDriverScore(String personId, String entId){
        DriverScoreVo driverSafeScoreVo = new DriverScoreVo();
        EntStarSoftEntity entStar = entStarSoftDao.getByStar(entId);
        if(null != entStar){
            StarSoft_DriverScoreVo starSoft_resultVo = starSoftSender.getDriverScore(personId,entStar.getStarsoftId());
            if(null != starSoft_resultVo){
                driverSafeScoreVo.setAddScore(starSoft_resultVo.getAddScore());
                driverSafeScoreVo.setBaseScore(starSoft_resultVo.getBaseScore());
                driverSafeScoreVo.setDeductScore(starSoft_resultVo.getDeductScore());
                driverSafeScoreVo.setTotalScore(starSoft_resultVo.getCurrentScore());
                driverSafeScoreVo.setSafeScore(starSoft_resultVo.getBaseScore()-starSoft_resultVo.getDeductScore());
            }
        }
        return  driverSafeScoreVo;

    }


    /**
     * 获取司机安全分
     * @param personId
     * @return
     */
    @Override
    public JSONPrompt getDriverScoreRecord(String personId, Integer year,String entId){
        JSONPrompt jsonPrompt = new JSONPrompt();
        String startTime = year+"-01-01";
        String endTime = year+"-12-31";
        EntStarSoftEntity entStar = entStarSoftDao.getByStar(entId);
        if(null != entStar){
           List<StarSoft_DriverScoreRecordVo> starSoft_resultVo =
                    starSoftSender.getDriverScoreRecord(personId,startTime,endTime,entStar.getStarsoftId());
            if(null != starSoft_resultVo){
                List<StarSoft_DriverScoreRecordVo> resultList = new ArrayList<>();
                for(StarSoft_DriverScoreRecordVo starSoft_driverScoreRecordVo : starSoft_resultVo){
                    starSoft_driverScoreRecordVo.setMonth(DateUtils.formatDate(starSoft_driverScoreRecordVo.getCreateTime(), "M"));
                    resultList.add(starSoft_driverScoreRecordVo);
                }
                jsonPrompt.setData(resultList);
            }
        }
        return jsonPrompt;
    }


    /**
     * 获取安全分 设置
     * @param entId
     * @return
     */
    @Override
    public JSONPrompt getSafeScoreSet(String entId){
        JSONPrompt jsonPrompt = new JSONPrompt();
        if(StringUtils.isBlank(entId)){
            throw new ServiceException(EnterpriseErrorEnums.ENT_FIND_FAILURE);
        }
        EntStarSoftEntity starSoft = entStarSoftDao.getByStar(entId);
        if(null != starSoft){
            StarSoft_SafeScoreSetVo starSoft_resultVo = starSoftSender.getSafeScoreSet(starSoft.getStarsoftId());
            if(null != starSoft_resultVo){
                    jsonPrompt.setData(starSoft_resultVo);
            }
        }
        return jsonPrompt;
    }
}
