package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.services.SafeCodeService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "safeCode/api")
@Scope("prototype")
public class SafeCodeController extends BaseController{


    @Autowired
    private SafeCodeService safeCodeService;


    /**
     * 获取司机安全码
     * @param personId
     * @return
     */
    @RequestMapping(value = "/getSafeCode")
    @ResponseBody
    @NotLogin
    public JSONPrompt getSafeCode(String personId){
        return safeCodeService.getSafeCode(personId);
    }
    
    /**
     * 获取司机安全码详情
     * @param personId
     * @return
     */
    @RequestMapping(value = "/getAssisInfo")
    @ResponseBody
    @NotLogin
    public JSONPrompt getAssisInfo(String personId){
        return new JSONPrompt(safeCodeService.safeAssisInfo(personId));
    }


    /**
     * 获取每日一学
     * @param personId
     * @param state 状态 0未学习 1已学习 2已失效,不传为全部
     * @return
     */
    @RequestMapping(value = "/getStudyInfo")
    @ResponseBody
    @NotLogin
    public JSONPrompt getStudyList(String personId,Integer state,Integer pageIndex,Integer pageSize){
        return new JSONPrompt(safeCodeService.getStudyList(personId,state,pageIndex,pageSize));
    }

    /**
     * 获取车辆安全码
     * @return
     */
    @RequestMapping(value = "/getCarSafeCode")
    @ResponseBody
    @NotLogin
    public JSONPrompt getCarSafeCode(String entCarId){
        return safeCodeService.getCarSafeCode(entCarId);
    }
    
    /**
     * 获取车辆安全码原因
     * @return
     */
    @RequestMapping(value = "/getCarSafeCodeReason")
    @ResponseBody
    @NotLogin
    public JSONPrompt getCarSafeCodeReason(String entCarId){
        return new JSONPrompt(safeCodeService.getCarSafeCodeReason(entCarId));
    }



}
