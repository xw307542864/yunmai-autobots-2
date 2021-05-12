package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.services.DriverTaskCargoService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 派车单货物控制层
 */
@Controller
@RequestMapping(value = "driverTask/api")
@Scope("prototype")
public class DriverTaskCargoController {

    @Autowired
    private DriverTaskCargoService driverTaskCargoService;


    /**
     *任务单货物
     * @return
     */
    @RequestMapping(value = "/taskCargo/{taskId}")
    @ResponseBody
    @NotLogin
    public JSONPrompt taskCargo(@PathVariable String taskId){
        return driverTaskCargoService.taskCargo(taskId);
    }


    /**
     * 装卸点货物
     * @return
     */
    @RequestMapping(value = "/pointCargo/{pointId}")
    @ResponseBody
    @NotLogin
    public JSONPrompt pointCargo(@PathVariable String pointId){

        return driverTaskCargoService.pointCargo(pointId);
    }
}
