package com.logibeat.cloud.services;


import com.logibeat.cloud.vo.FriendDetailVo;
import com.logibeat.cloud.vo.FriendInfoVo;

/**
 * 
 * @ClassName: FriendService
 * @Description: 联系人服务
 * @author sean
 * @date 2015年12月8日 上午9:55:05
 * @version 1.0
 */
public interface FriendService {


	FriendDetailVo getFriendDetail(String flag, String personId, String mobile, String baseUserId);

	FriendInfoVo getFriendInfo(String friendId, String entId, String personId, String imGuid);


}
