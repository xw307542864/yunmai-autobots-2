package com.logibeat.cloud.services;


import com.logibeat.cloud.common.vo.*;
import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.core.dto.CooperAndDriverDTO;
import com.logibeat.cloud.core.dto.EntBusinessInfoDTO;
import com.logibeat.cloud.core.dto.EntFriendDto;
import com.logibeat.cloud.util.tools.pageMdl.Page;
import com.logibeat.cloud.util.tools.pageMdl.PageResultDTO;

import java.util.List;

/**
 * 合作企业操作接口
* @Title: CoopEntService
* @Description:
* @Company: 运脉科技
* @author   wilson
* @date     2015年12月10日
 */
public interface CoopEntService {

	/**
	 * 司机端查找企业
	 * @param keyWord
	 * @param baseUserId
	 * @return List<EntCoopShortInfoVo>
	 * @Title: searchEnt
	 */
	PageResultDTO<EntCoopInfoDriverVo> searchEnt(String keyWord, String baseUserId, Page page);

	/**
	 * 获取合作企业：新
	 *
	 * @param entFriendDto
	 * @return List<EntCoopShortInfoVo>
	 * @Title: getFriends
	 */
	PageResultDTO<EntCoopShortInfoVo> getFriendsNew(EntFriendDto entFriendDto);

	/**
	 * 搜索合作企业
	 *
	 * @param entFriendDto
	 * @return List<EntCoopShortInfoVo>
	 * @Title: searchEntFriends
	 */
	List<EntCoopShortInfoVo> searchEntFriends(EntFriendDto entFriendDto);

	/**
	 * 设置星标企业
	 *
	 * @param entId
	 * @param personId
	 * @param isStarMark void
	 * @Title: setStarMark
	 */
	void setStarMark(String entId, String personId, boolean isStarMark);

	/**
	 *  获取司机所属企业列表
	 * @param personId
	 * @return
	 */
	List<DriverEntVo> getDriverEntList(String personId);

	/**
	 * 司机申请关注企业
	 *
	 * @param entId
	 * @param inviteType
	 * @Title: addEntCoop
	 */
	String addEntCoop(String entId, Integer inviteType, BaseRequestDTO baseRequestDTO, EntBusinessInfoDTO entBusinessInfoDTO);

	/**
	 * 企业详细详细
	 *
	 * @param entId
	 * @param baseUserId
	 * @return EntCoopInfoVo
	 * @Title: getCoopEntDetail
	 */
	@Deprecated
	EntCoopInfoVo getCoopEntDetail(String entId, String baseUserId);

	/**
	 * 司机取消关注企业
	 *
	 * @param entId
	 * @param baseUserId void
	 * @Title: cancleEntCoop
	 */
	void cancleEntCoop(String entId, String baseUserId);

	/**
	 * 司机退出当前企业（目前先直接退出，后期行为会稍作改动）
	 *
	 * @param baseUserId
	 * @param entId      void
	 * @Title: quitEnt
	 */
	void quitEnt(String baseUserId, String entId);

	/**
	 * 位置共享
	 *
	 * @param share
	 * @param entId
	 * @param baseUserId void
	 * @Title: shareGps
	 */
	void shareGps(boolean share, String entId, String baseUserId);


	List<DriverEntVo> getCoopEntList(String baseUserId);

	/**
	 * 获取司机所在的企业列表，以组织架构形式展现
	 *
	 * @return List<DriverEntVo>
	 * @Title: getDriverEntList
	 */
	List<EntCooperAndDriverVo> getOrgCooperList(CooperAndDriverDTO cooperAndDriverDtTO, BaseRequestDTO baseRequestDTO);

	/**
	 * 企业司机所在企业列表
	 *
	 * @param baseRequestDTO
	 * @return
	 */
	@Deprecated
	List<EnterpriseVo> getMyEnterpriseList(BaseRequestDTO baseRequestDTO);




    String getYunMai(String yunMaiCode);


	Integer getDriverCoopType(String baseUserId, String entId);

	/**
	 * 查询企业联系人列表
	 */
	List<FriendShortInfoVo> getContactList(String entId, String personId);

	// 企业联系人详细
	FriendShortInfoVo getContactDetail(String personId);



	void joinEnt(String confirmGuid,Integer status);
}
