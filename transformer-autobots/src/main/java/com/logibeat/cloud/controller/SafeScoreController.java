package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.services.SafeScoreService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 安全分
 */
@Controller
@RequestMapping(value = "safeScore/api")
@Scope("prototype")
public class SafeScoreController extends BaseController {


    @Autowired
    private SafeScoreService safeScoreService;




    /**
     * 司机安全分
     * @param personId
     * @return
     */
    @RequestMapping(value = "/driverScore")
    @ResponseBody
    @NotLogin
    public JSONPrompt driverScore(String personId,String entId){
        return new JSONPrompt(safeScoreService.getDriverScore(personId,entId));

    }


    /**
     * 司机安全分流水
     * @param personId
     * @return
     */
    @RequestMapping(value = "/driverScoreRecord")
    @ResponseBody
    @NotLogin
    public JSONPrompt driverScoreRecord(String personId,Integer year,String entId){
        return safeScoreService.getDriverScoreRecord(personId,year,entId);
    }


    /**
     * 获取安全分设置
     * @param entId
     * @return
     */
    @RequestMapping(value = "/safeScoreSet")
    @ResponseBody
    @NotLogin
    public JSONPrompt safeScoreSet( String entId){
        return safeScoreService.getSafeScoreSet(entId);
    }

}
