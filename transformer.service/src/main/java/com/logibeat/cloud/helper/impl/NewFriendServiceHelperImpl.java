package com.logibeat.cloud.helper.impl;

import com.logibeat.cloud.common.enumtype.InviteState;
import com.logibeat.cloud.common.enumtype.InviteType;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.properties.CommonProperties;
import com.logibeat.cloud.helper.NewFriendServiceHelper;
import com.logibeat.cloud.persistent.dao.NewFriendLogDetailDao;
import com.logibeat.cloud.persistent.entity.NewFriendLogDetailEntity;
import com.logibeat.cloud.persistent.entity.NewFriendLogEntity;
import com.logibeat.cloud.util.tools.RandomTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NewFriendServiceHelperImpl implements NewFriendServiceHelper {

	private static final Logger logger = LoggerFactory.getLogger(NewFriendServiceHelperImpl.class);

	@Autowired
	private NewFriendLogDetailDao newFriendLogDetailDao;

	@Override
	public NewFriendLogEntity addNewFriendLog(String objectId, String inviteObjectId, Integer inviteType,
											  String entId, Integer isCheckCoopDriver) {
		// 被邀请者是否企业
		Byte isEnt = ConstantUtil.BYTE_FALSE;
		// 邀请者是否企业
		Byte isFriendEnt = ConstantUtil.BYTE_FALSE;
		NewFriendLogEntity newFriendLogEntity = new NewFriendLogEntity();
		newFriendLogEntity.setNewFriendGUID(RandomTool.getGUId());
		newFriendLogEntity.setYyyymmdd(Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date())));
		if (InviteType.DriverToEnt.getValue() == inviteType || InviteType.CarToEnt.getValue() == inviteType) {
			isEnt = ConstantUtil.BYTE_TRUE;
		}
		newFriendLogEntity.setIsEnt(isEnt);
		newFriendLogEntity.setObjectID(objectId); // 被邀请者（企业或司机id）
		newFriendLogEntity.setEntID(entId); // 被邀请者企业ID(司机加企业好友)
		newFriendLogEntity.setIsFriendEnt(isFriendEnt);// 邀请者是否企业
		newFriendLogEntity.setFriendObjectID(inviteObjectId);// 邀请者(企业id或者司机id)
		newFriendLogEntity.setInvitePersonID(inviteObjectId);// 邀请者(司机id
		// 或者企业管理员id)
		newFriendLogEntity.setInviteType(inviteType);
		newFriendLogEntity.setIsDelete(ConstantUtil.BYTE_FALSE);
		//开关关闭直接建立合作，默认打开
		if (isCheckCoopDriver == CommonProperties.INT_ONE) {
			newFriendLogEntity.setInviteState(InviteState.Wait.getValue());
		}else{
			newFriendLogEntity.setInviteState(InviteState.Pass.getValue());
		}
		newFriendLogEntity.setIsHandle(ConstantUtil.BYTE_FALSE);
		newFriendLogEntity.setAddTime(new Timestamp(new Date().getTime()));
		newFriendLogEntity.setEditTime(new Timestamp(new Date().getTime()));
		newFriendLogEntity.setIsRead(ConstantUtil.BYTE_FALSE);
		return newFriendLogEntity;
	}

	@Override
	public void addNewFriendLogDetail(String newFriendLogId, String message, String personId) {
		// 添加详情
		NewFriendLogDetailEntity newFriendLogDetail = new NewFriendLogDetailEntity();
		newFriendLogDetail.setAddTime(DateUtil.getSqlTime());
		newFriendLogDetail.setId(RandomTool.getGUId());
		newFriendLogDetail.setNewfriendlogId(newFriendLogId);
		newFriendLogDetail.setPersonId(personId);
		newFriendLogDetail.setRemark(message);
		newFriendLogDetailDao.insert(newFriendLogDetail);
	}
}
