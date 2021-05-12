package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.core.dto.PushImDto;
import com.logibeat.cloud.services.PushService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "push/api")
@Scope("prototype")
public class PushController {


    @Autowired
    private PushService pushService;

    /**
     * 公用推送消息
     * @param pushImDto
     * @return
     */
    @RequestMapping(value = "/pushImTemplate")
    @ResponseBody
    @NotLogin
    public JSONPrompt pushImTemplate(@RequestBody PushImDto pushImDto){
        pushService.pushImTemplate(pushImDto);
        return new JSONPrompt();
    }



}
