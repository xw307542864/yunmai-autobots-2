package com.logibeat.cloud.controller;

import com.logibeat.cloud.persistent.entity.PlanCarOrderEntity;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 请车单（预约单）
 */
@RequestMapping(value = "api/plancarOrder")
@Scope("prototype")
public class PlanCarOrderController extends  BaseController {


    /**
     * 生成请车单
     * @param planCarOrder
     * @return
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public JSONPrompt create(@RequestBody PlanCarOrderEntity planCarOrder){



        return  null;
    }
}
