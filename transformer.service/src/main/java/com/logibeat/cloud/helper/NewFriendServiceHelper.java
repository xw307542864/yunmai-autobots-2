package com.logibeat.cloud.helper;

import com.logibeat.cloud.persistent.entity.NewFriendLogEntity;

public interface NewFriendServiceHelper {

    /**
     * 添加新联系人信息
     * @param objectId 被邀请者id
     * @param inviteObjectId 邀请者id
     * @param inviteType 邀请类型
     * @param entId 企业id
     * @param isCheckCoopDriver 是否关闭验证（默认true）
     * @return
     */
    NewFriendLogEntity addNewFriendLog(String objectId, String inviteObjectId, Integer inviteType,
                                       String entId, Integer isCheckCoopDriver);

    /**
     * 添加新联系人详情
     * @param newFriendLogId
     * @param message
     * @param personId
     */
    void addNewFriendLogDetail(String newFriendLogId, String message, String personId);
}
