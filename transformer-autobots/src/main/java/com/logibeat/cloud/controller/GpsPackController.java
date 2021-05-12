package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.services.GpsPackService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "Driver/Gps/api/Post")
@Scope("prototype")
public class GpsPackController {
	
	@Autowired
	protected GpsPackService gpsPackService;

	@RequestMapping(value = "/GpsPack")
	@ResponseBody
	public JSONPrompt postGps(String gpsInfo, @RequestHeader("baseuserid") String baseUserId){
		gpsPackService.packGps(gpsInfo, baseUserId);
		return new JSONPrompt(); 
	}


	@RequestMapping(value = "/GpsPackTime")
	@ResponseBody
	@NotLogin
	public JSONPrompt getGpsPackTime(HttpServletRequest request,String gpsInfo,@RequestHeader("baseuserid") String baseUserId){
		String userAgent = request.getHeader("user-agent");
		return  new JSONPrompt(gpsPackService.getGpsPackTime(baseUserId,userAgent));
	}
	
	@RequestMapping(value = "/GpsSetTime")
	@ResponseBody
	@NotLogin
	public JSONPrompt getGpsSetTime(HttpServletRequest request,String gpsInfo,@RequestHeader("baseuserid") String baseUserId){
//		String userAgent = request.getHeader("user-agent");
//		userAgent = "";
		String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";
		userAgent = request.getHeader("user-agent");
		return  new JSONPrompt(gpsPackService.getGpsSetTime(baseUserId,userAgent));
	}
}
