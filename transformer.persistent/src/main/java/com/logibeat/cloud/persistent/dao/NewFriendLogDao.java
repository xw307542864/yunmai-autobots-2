package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.NewFriendLogEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewFriendLogDao {
    int delete(String newFriendGUID);

    int insert(NewFriendLogEntity entity);

    NewFriendLogEntity select(String newFriendGUID);

    int update(NewFriendLogEntity entity);

    /**
     * 获取新联系人总数
     * @param personId
     * @return
     */
    long getNewCount(@Param("personId") String personId);

    /**
     * 获取新朋友信息
     */
    List<NewFriendLogEntity> getNewFriend(@Param("personId")String personId);

    NewFriendLogEntity getNewEntityById(@Param("friendObjectId")String friendObjectId, @Param("objectId")String objectId);

    int updateNewFriendForDriver(@Param("inviteState")Integer inviteState, @Param("remark")String remark,
                                 @Param("handlePersonID")String handlePersonID, @Param("objectID")String objectID,
                                 @Param("friendObjectID")String friendObjectID);

    /**
     * 获取企业邀请司机并未被处理的新联系人信息
     * @param friendObjectId
     * @param objectId
     * @return
     */
    NewFriendLogEntity getNewFriendForDriver(@Param("friendObjectId")String friendObjectId, @Param("objectId")String objectId);

     /**
     * 获取司机申请合作企业并未被处理的新联系人信息
     * @param personId
     * @param entId
     * @return
     */
    NewFriendLogEntity getNewFriendForCarToEnt(@Param("personId")String personId, @Param("entId")String entId);

    /**
     * 获取新联系人信息
     * @param objectId 被邀请人id
     * @param friendObjectId 邀请人id
     * @param inviteType 邀请类型
     * @param inviteStatus 邀请状态
     * @return
     */
    NewFriendLogEntity getNewFriendByStatusAndType(@Param("objectId")String objectId, @Param("friendObjectId")String friendObjectId,
                                                   @Param("inviteType")Integer inviteType, @Param("inviteStatus")Integer inviteStatus);
    
    public NewFriendLogEntity getNewFriendCar(@Param("objectId") String objectId,
											            @Param("friendObjectId") String friendObjectId,
											            @Param("carId") String carId);
}