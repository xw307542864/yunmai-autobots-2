package com.logibeat.cloud.services;

import com.logibeat.cloud.common.vo.NewFriendInfoVo;
import com.logibeat.cloud.common.vo.NewFriendSearchInfoVo;
import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.core.dto.NewFriendDto;

import java.util.List;

public interface NewFriendService {
	
	/**
     * 查找好友
     * @param keyWord
     * @return
     */
    List<NewFriendSearchInfoVo> searchNewFriend(String keyWord, String personId);


	/**
     * 获取新联系人总数
     * @param baseUserId
     * @return
     */
    long getNewFriendCount(String baseUserId);

    /**
     * 新联系人列表
     * @param baseUserId
     * @return
     */
    List<NewFriendInfoVo> getMyNewFriend(String baseUserId);

    /**
     * 操作新联系
     * @param newFriendDto, baseRequestDTO
     * @return
     */
    void  handleNewFriend(NewFriendDto newFriendDto, BaseRequestDTO baseRequestDTO);

    Object getStrangerInfo(String guid);

    void deleteNewFriendLog(String newFriendGuid);

}
