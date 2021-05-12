package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.vo.InterestLabelVo;
import com.logibeat.cloud.services.InterestLabelService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "Driver/discover/api")
@Scope("prototype")
public class InterestLabelController extends BaseController {

    @Autowired
    protected InterestLabelService interestLabelService;


    @RequestMapping(value = "/addInterestLabel")
    @ResponseBody
    public JSONPrompt addInterestLabel(String label) {
        JSONPrompt jsonResult = new JSONPrompt();
        interestLabelService.addInterestLabel(baseUserId, label);
        return jsonResult;
    }

    @RequestMapping(value = "/getInterestLabel")
    @ResponseBody
    public JSONPrompt getInterestLabel() {
        JSONPrompt jsonResult = new JSONPrompt();
        InterestLabelVo interestLabel = interestLabelService.getInterestLabel(baseUserId);
        jsonResult.setData(interestLabel);
        return jsonResult;
    }


}
