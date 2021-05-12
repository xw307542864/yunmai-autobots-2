package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.core.dto.AppealDto;
import com.logibeat.cloud.core.dto.SyncViolationDto;
import com.logibeat.cloud.core.dto.UpdateViolationStatusDto;
import com.logibeat.cloud.services.PersonViolationService;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处置工单
 *
 */
@Controller
@RequestMapping(value = "violation/api")
@Scope("prototype")
public class PersonViolationController extends BaseController {

    @Autowired
    private PersonViolationService personViolationService;


    /**
     * 创建处置工单
     * @param syncViolationDto
     * @return
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    @NotLogin
    public JSONPrompt create(@RequestBody SyncViolationDto syncViolationDto){
        personViolationService.create(syncViolationDto);
        return new JSONPrompt();
    }


    /**
     * 更新状态
     * @param updateViolationStatusDto
     * @return
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    @NotLogin
    public JSONPrompt updateStatus(@RequestBody UpdateViolationStatusDto updateViolationStatusDto){
        personViolationService.updateStatus(updateViolationStatusDto);
        return new JSONPrompt();

    }



    /**
     * 工单列表
     * @param entId
     * @param personId
     * @param status
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    @NotLogin
    public JSONPrompt list(String entId, String personId, Integer status, Page page){
        return new JSONPrompt(personViolationService.getList(entId,personId,status,page));
    }


    /**
     * 不同状态下的违规数量
     * @param entId
     * @param personId
     * @return
     */
    @RequestMapping(value = "/inCount")
    @ResponseBody
    @NotLogin
    public JSONPrompt inCount(String entId, String personId,Integer status){
        return new JSONPrompt(personViolationService.getInCount(entId,personId,status));
    }


    /**
     * 工单详情
     * @param guid
     * @return
     */
    @RequestMapping(value = "/detail/{guid}")
    @ResponseBody
    @NotLogin
    public JSONPrompt detail(@PathVariable("guid") String guid){
        return  new JSONPrompt(personViolationService.detail(guid));
    }

    /**
     * 申述
     * @return
     */
    @RequestMapping(value = "/appeal")
    @ResponseBody
    @NotLogin
    public JSONPrompt appeal( @RequestBody AppealDto appealDto){
        personViolationService.appeal(appealDto);
        return new JSONPrompt();
    }


    /**
     * 申诉列表
     * @param guid
     * @return
     */
    @RequestMapping(value = "/appealList/{guid}")
    @ResponseBody
    @NotLogin
    public JSONPrompt appealList(@PathVariable("guid") String guid){
        return new JSONPrompt(personViolationService.getAppeal(guid));
    }


    /**
     * 确认
     * @return
     */
    @RequestMapping(value = "/confirm/{guid}")
    @ResponseBody
    @NotLogin
    public JSONPrompt confirm(@PathVariable("guid") String guid){
        personViolationService.confirm(guid);
        return new JSONPrompt();
    }




}
