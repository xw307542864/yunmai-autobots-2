package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.vo.EntCoopShortInfoVo;
import com.logibeat.cloud.common.vo.EntCooperAndDriverVo;
import com.logibeat.cloud.common.vo.EnterpriseVo;
import com.logibeat.cloud.common.vo.FriendShortInfoVo;
import com.logibeat.cloud.core.dto.CooperAndDriverDTO;
import com.logibeat.cloud.core.dto.EntFriendDto;
import com.logibeat.cloud.services.CoopEntService;
import com.logibeat.cloud.util.tools.pageMdl.PageResultDTO;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 企业操作类
 * 
 * @Title: Driver_EnterpriseController
 * @Description:
 * @Company: 运脉科技
 * @author wilson
 * @date 2015年12月11日
 */
@RestController(value = "autobots.EnterpriseController")
@RequestMapping(value = "Driver/Ent/api")
@Scope("prototype")
public class EnterpriseController extends BaseController{

	@Autowired
	protected CoopEntService coopEntService;

	/**
	 * 查找合作企业：新
	 *
	 * @param entFriendDto
	 * @return
	 */
	@RequestMapping(value = "/CoopEnt/FriendsNew")
	@ResponseBody
	public JSONPrompt<PageResultDTO<EntCoopShortInfoVo>> getFriendsNew(EntFriendDto entFriendDto) {
		entFriendDto.setBaseUserId(baseUserId);
		JSONPrompt<PageResultDTO<EntCoopShortInfoVo>> jsonResult = new JSONPrompt<>();
		PageResultDTO<EntCoopShortInfoVo> entCoopShortInfoVos = coopEntService.getFriendsNew(entFriendDto);
		jsonResult.setData(entCoopShortInfoVos);
		return jsonResult;
	}

	/**
	 * 合作企业
	 * 
	 * @param entFriendDto
	 * @return
	 */
	@RequestMapping(value = "/CoopEnt/searchEntFriends")
	@ResponseBody
	public JSONPrompt<List<EntCoopShortInfoVo>> searchEntFriends(EntFriendDto entFriendDto) {
		JSONPrompt<List<EntCoopShortInfoVo>> jsonResult = new JSONPrompt<List<EntCoopShortInfoVo>>();
		entFriendDto.setBaseUserId(baseUserId);
		List<EntCoopShortInfoVo> entCoopShortInfoVos = coopEntService.searchEntFriends(entFriendDto);
		jsonResult.setData(entCoopShortInfoVos);
		return jsonResult;
	}

	/**
	 * 合作企业联系人
	 * 
	 * @return
	 */
	@RequestMapping(value = "/CoopEnt/Contacts")
	@ResponseBody
	public JSONPrompt getContacts(String id) {
		JSONPrompt jsonResult = new JSONPrompt();
		List<FriendShortInfoVo> friendShortInfoVos = coopEntService.getContactList(id,baseUserId);
		jsonResult.setData(friendShortInfoVos);
		return jsonResult;
	}

	/**
	 * 合作企业联系人详细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/CoopEnt/ContactDetail/{id}")
	@ResponseBody
	public JSONPrompt getContactDetail(@PathVariable String id) {
		JSONPrompt jsonResult = new JSONPrompt();
		FriendShortInfoVo friendShortInfoVo = coopEntService.getContactDetail(id);
		jsonResult.setData(friendShortInfoVo);
		return jsonResult;
	}
	
	
	/**
	 * 获取司机所在企业信息(按组织结构显示该企业下司机和企业人员)
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/Enterprise/GetOrgDriverEnt")
	@ResponseBody
	public JSONPrompt<List<EntCooperAndDriverVo>> getOrgDriverEnt(CooperAndDriverDTO cooperAndDriverDtTO) {
		JSONPrompt result = new JSONPrompt<>();
		List<EntCooperAndDriverVo> orgCooperList =coopEntService.getOrgCooperList(cooperAndDriverDtTO,baseRequestDTO);
		result.setData(orgCooperList);
		return result;
		
	}

	/**
	 * 企业司机加入的企业列表
	 * @param cooperAndDriverDtTO
	 * @return
	 */
	@RequestMapping(value = "/Enterprise/GetMyEnterprise")
	@ResponseBody
	@Deprecated
	public JSONPrompt<EnterpriseVo> getMyEnterprise(CooperAndDriverDTO cooperAndDriverDtTO) {
		JSONPrompt result = new JSONPrompt<>();
		List<EnterpriseVo> orgCooperList
		=coopEntService.getMyEnterpriseList(baseRequestDTO);
		result.setData(orgCooperList);
		return result;
		
	}
}
