package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.common.vo.EntCoopInfoDriverVo;
import com.logibeat.cloud.services.CoopEntService;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import com.logibeat.cloud.util.tools.pageMdl.PageResultDTO;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * 合作企业
 * 
 * @Title: CoopEntController
 * @Description:
 * @Company: 运脉科技
 * @author wilson
 * @date 2015年12月10日
 */
@Controller(value = "autobots.CoopEntController")
@RequestMapping(value = "Driver/Im/api/CoopEnt")
@Scope("prototype")
public class CoopEntController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(CoopEntController.class);
	
	@Autowired
	protected CoopEntService coopEntService;

	/**
	 * 查找企业:新
	 * @param keyWord
	 * @return
	 */
	@RequestMapping(value = "/SearchByEnt")
	@ResponseBody
	@NotLogin
	public JSONPrompt<PageResultDTO<EntCoopInfoDriverVo>> searchByEnt(String keyWord, Page page) {
		JSONPrompt<PageResultDTO<EntCoopInfoDriverVo>> jsonResult = new JSONPrompt<>();
		PageResultDTO<EntCoopInfoDriverVo> entCoopInfoDriverVo = coopEntService.searchEnt(keyWord, baseUserId, page);

		jsonResult.setData(entCoopInfoDriverVo);
		return jsonResult;
	}
	
	
	/**
	 * 设置星标
	 * @param isStarMark
	 * @return
	 */
	@RequestMapping(value = "/SetStarMark/{id}")
	@ResponseBody
	public  JSONPrompt<?> setStarMark(@PathVariable String id, boolean isStarMark){
		JSONPrompt<?> jsonResult = new JSONPrompt<>();
		coopEntService.setStarMark(id, baseUserId, isStarMark);
		return jsonResult;
	}

    /**
     *
     * @Description TODO   根据运脉号搜索企业 ,返回 ID   新合作伙伴 1.5迭代
     * @param @param yunMaiCode
     * @param @return 参数
     * @return JSONPrompt<?> 返回类型
     * @throws
     */
    @RequestMapping(value = "/SearchByYunMaiCode")
    @ResponseBody
    public JSONPrompt<Map<String, String>> getYunMai(String yunMaiCode){
    	JSONPrompt<Map<String, String>> jsonResult = new JSONPrompt<>();
    	Map<String, String> map = new HashMap<>();
    	String id = coopEntService.getYunMai(yunMaiCode);
    	map.put("id", id);
    	jsonResult.setData(map);
    	return jsonResult;
    }


	/**
	 * 获取司机和企业的关系（企业司机/外协司机）
	 * @param entId
	 * @return
     */
	@RequestMapping(value = "/getDriverCoopType")
	@ResponseBody
	@NotLogin
	public JSONPrompt getDriverCoopType(String entId){
		return  new JSONPrompt(coopEntService.getDriverCoopType(baseUserId,entId));
	}




	/**
	 * 司机同意加入企业
	 * @param confirmGuid
	 * @return
	 */
	@RequestMapping(value = "/joinEnt")
	@ResponseBody
	public JSONPrompt joinEnt(String confirmGuid,Integer status){
		coopEntService.joinEnt(confirmGuid,status);
		return new JSONPrompt();
	}

}
