package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.common.enumtype.InviteType;
import com.logibeat.cloud.common.vo.*;
import com.logibeat.cloud.core.dto.EntBusinessInfoDTO;
import com.logibeat.cloud.core.dto.NewFriendDto;
import com.logibeat.cloud.services.CoopEntService;
import com.logibeat.cloud.services.FriendService;
import com.logibeat.cloud.services.NewFriendService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import com.logibeat.cloud.vo.FriendDetailVo;
import com.logibeat.cloud.vo.FriendInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 司机端好友控制类
 *
 * @author wilson
 * @Title: Drver_FriendController
 * @Description:
 * @Company: 运脉科技
 * @date 2015年12月11日
 */
@Controller(value = "autobots.FriendController")
@RequestMapping(value = "Driver/Im/api")
@Scope("prototype")
public class FriendController extends BaseController {

	@Autowired
	protected NewFriendService newFriendService;

	@Autowired
	protected CoopEntService coopEntService;

	@Autowired
	protected FriendService friendService;


	/**
	 * @return JSONPrompt
	 * @Title: getNewFriendCount
	 * @Description: 获取新联系人数量
	 */
	@RequestMapping(value = "/Friend/GetNewFriendCount")
	@ResponseBody
	public JSONPrompt getNewFriendCount() {
		JSONPrompt jsonResult = new JSONPrompt();
		Long count = newFriendService.getNewFriendCount(baseUserId);
		jsonResult.setTotal(count);
		return jsonResult;
	}

	/**
	 * @return JSONPrompt
	 * @Title: getNewFriend
	 * @Description: 获取新联系人列表
	 */
	@RequestMapping(value = "/Friend/GetNewFriend")
	@ResponseBody
	public JSONPrompt<List<NewFriendInfoVo>> getNewFriendList() {
		return new JSONPrompt<List<NewFriendInfoVo>>(newFriendService.getMyNewFriend(baseUserId));
	}

	/**
	 * 获取新联系人信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/Friend/GetStrangerInfo/{id}")
	@ResponseBody
	public JSONPrompt getStrangerInfo(@PathVariable String id) {
		JSONPrompt jsonResult = new JSONPrompt();
		Object object = newFriendService.getStrangerInfo(id);
		jsonResult.setData(object);
		return jsonResult;
	}

	/**
	 * 查找好友
	 *
	 * @return
	 */
	@RequestMapping(value = "/DrvFriend/SearchNewFriend")
	@ResponseBody
	public JSONPrompt searchNewFriend(String keyWord) {
		JSONPrompt jsonResult = new JSONPrompt();
		List<NewFriendSearchInfoVo> resultList = newFriendService.searchNewFriend(keyWord, baseUserId);
		jsonResult.setData(resultList);
		return jsonResult;
	}

	/**
	 * 操作新朋友
	 *
	 * @return
	 */
	@RequestMapping(value = "/Friend/HandleNewFriend")
	@ResponseBody
	public JSONPrompt handleNewFriend(NewFriendDto newFriendDto) {
		newFriendDto.setBaseUserId(baseUserId);
		newFriendService.handleNewFriend(newFriendDto, baseRequestDTO);
		return new JSONPrompt<>();
	}



	/**
	 * 合作企业详细
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/DrvFriend/EntDetail/{id}")
	@ResponseBody
	public JSONPrompt getEntDetail(@PathVariable String id) {
		JSONPrompt jsonResult = new JSONPrompt();
		EntCoopInfoVo entCoopInfoVo = coopEntService.getCoopEntDetail(id, baseUserId);
		jsonResult.setData(entCoopInfoVo);
		return jsonResult;
	}

	/**
	 * 建立合作
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/DrvFriend/AddEntCoop/{id}")
	@ResponseBody
	@NotLogin
	public JSONPrompt<String> addEntCoop(@PathVariable String id, Integer inviteType, EntBusinessInfoDTO entBusinessInfoDTO ) {
		JSONPrompt<String> isCheckCoopDriverJP = new JSONPrompt<String>();
		inviteType= InviteType.CarToEnt.getValue();
	    String isCheckCoopDriver = coopEntService.addEntCoop(id, inviteType, baseRequestDTO,entBusinessInfoDTO);
	    isCheckCoopDriverJP.setData(isCheckCoopDriver);
		return isCheckCoopDriverJP;
	}

	/**
	 * 取消合作
	 */
	@RequestMapping(value = "/DrvFriend/CancleEntCoop/{id}")
	@ResponseBody
	public JSONPrompt<?> cancleEntCoop(@PathVariable String id) {
		coopEntService.cancleEntCoop(id, baseUserId);
		return new JSONPrompt<>();
	}

	/**
	 * 退出当前企业
	 */
	@RequestMapping(value = "/DrvFriend/QuitEnt")
	@ResponseBody
	public JSONPrompt quitEnt(String entId) {
		coopEntService.quitEnt(baseUserId, entId);
		return new JSONPrompt<>();
	}

	/**
	 * 位置开关共享
	 */
	@RequestMapping(value = "/DrvFriend/ShareGps/{id}")
	@ResponseBody
	public JSONPrompt shareGps(@PathVariable String id, boolean isShare) {
		JSONPrompt jsonResult = new JSONPrompt();
		coopEntService.shareGps(isShare, id, baseUserId);
		return jsonResult;
	}

	/**
	 * 
	 * @Title: deleteNewFriend
	 * @Description: 删除新朋友信息
	 * @return
	 * @return JSONPrompt
	 */
	@RequestMapping(value = "/DrvFriend/DeleteNewFriend")
	@ResponseBody
	public JSONPrompt<?> deleteNewFriend(String newId) {
		newFriendService.deleteNewFriendLog(newId);
		return new JSONPrompt<>();
	}


	/**
	 * 根据GUID获取人员信息
	 *
	 * @param @param
	 *            imGuid
	 * @param @return
	 *            设定文件
	 * @return JSONPrompt 返回类型
	 * @throws @Title:
	 *             getDriverDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 */
	@RequestMapping(value = "/Friend/GetStrangerDetail")
	@ResponseBody
	public JSONPrompt getStrangerDetail(String personId,String imGuid,String entId) {
		JSONPrompt jsonResult = new JSONPrompt();
		FriendInfoVo friendInfoVo = friendService.getFriendInfo(personId,entId,baseUserId, imGuid);
		jsonResult.setData(friendInfoVo);
		return jsonResult;
	}

    /**
     * 获取好友(司机)信息
     *
     * @return
     */
    @RequestMapping(value = "/DrvFriend/Detail")
    @ResponseBody
    public JSONPrompt getFriendDetail(String flag, String personId, String mobile) {
        JSONPrompt jsonResult = new JSONPrompt();
        FriendDetailVo friendDetail = friendService.getFriendDetail(flag, personId, mobile, baseUserId);
        jsonResult.setData(friendDetail);
        return jsonResult;
    }







}
