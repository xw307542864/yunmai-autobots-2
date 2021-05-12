package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.services.TrafficAdviseService;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 交通劝导
 *
 */
@Controller
@RequestMapping(value = "trafficAdvise/api")
@Scope("prototype")
public class TrafficAdviseController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(TrafficAdviseController.class);

	@Autowired
	private TrafficAdviseService trafficAdviseService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
    @ResponseBody
    @NotLogin
    public JSONPrompt list(String personId, Integer relationSafeCode,String entId, Page page){
		logger.debug("test====="+trafficAdviseService);
        return new JSONPrompt(trafficAdviseService.getList(personId,relationSafeCode,entId,page));
    }
	
	/**
	 * 打卡
	 */
	@RequestMapping(value = "/clockIn")
    @ResponseBody
	public JSONPrompt clockIn(String adviseId,Double lng,Double lat,String picUrl,String address,String placeName) {
		trafficAdviseService.clockIn(adviseId, lng, lat, picUrl,address,placeName);
		 return new JSONPrompt();
	}
	
	/**
	 * 回执
	 */
	@RequestMapping(value = "/receipt")
    @ResponseBody
	public JSONPrompt receipt(String adviseId, String picUrl) {
		trafficAdviseService.receipt(adviseId,picUrl);
		 return new JSONPrompt();
	}
	
	/**
     * 详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    @NotLogin
    public JSONPrompt detail(String adviseId){
        return  new JSONPrompt(trafficAdviseService.detail(adviseId));
    }
    
    /**
     * 待执行+执行中+未完成的数量
     */
    @RequestMapping(value = "/getAdviseNum")
    @ResponseBody
    @NotLogin
    public JSONPrompt getAdviseNum(String personId){
        return  new JSONPrompt(trafficAdviseService.getAdviseNum(personId));
    }
    
    /**
     * 处理过期数据
     * @return
     */
    @RequestMapping(value = "/handleExpireData")
    @ResponseBody
    @NotLogin
    public JSONPrompt handleExpireData(){
    	trafficAdviseService.handleExpireData();
        return  new JSONPrompt();
    }
    
    /**
     * 每天凌晨校验执行中数据
     * @return
     */
    @RequestMapping(value = "/checkExecuteData")
    @ResponseBody
    @NotLogin
    public JSONPrompt checkExecuteData(){
    	trafficAdviseService.checkExecuteData();
        return  new JSONPrompt();
    }
}
