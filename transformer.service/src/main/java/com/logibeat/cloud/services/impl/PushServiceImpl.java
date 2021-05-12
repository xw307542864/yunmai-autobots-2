package com.logibeat.cloud.services.impl;

import com.google.gson.Gson;
import com.logibeat.cloud.common.cache.util.StringUtils;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.core.dto.PushImDto;
import com.logibeat.cloud.msg.dto.ImSysDto;
import com.logibeat.cloud.msg.enumtype.MessageBizType;
import com.logibeat.cloud.msg.enumtype.MessageType;
import com.logibeat.cloud.msg.extra.CommonExtraDto;
import com.logibeat.cloud.msg.sender.ImMsgSender;
import com.logibeat.cloud.persistent.dao.EnterpriseInfoDao;
import com.logibeat.cloud.persistent.dao.ImUserDao;
import com.logibeat.cloud.persistent.entity.EnterpriseInfoEntity;
import com.logibeat.cloud.persistent.entity.ImUserEntity;
import com.logibeat.cloud.services.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PushServiceImpl implements PushService {

    @Autowired
    private ImUserDao imUserDao;


    @Autowired
    private ImMsgSender imMsgSender;


    @Autowired
    private EnterpriseInfoDao enterpriseInfoDao;



    /**
     * 推送模板
     * @param pushImDto
     */
    @Override
    public void pushImTemplate(PushImDto pushImDto){
        Gson gson = new Gson();
        ImSysDto imSysDto = new ImSysDto();
        Integer type = pushImDto.getType();
        CommonExtraDto commonExtraDto = new CommonExtraDto();
        //企业消息
        if(ConstantUtil.INTEGER_CODE_ONE.equals(type)){
            String entId = pushImDto.getEntId();
            EnterpriseInfoEntity ent = enterpriseInfoDao.select(entId);
            if(null != ent){
                imSysDto.setEntName(ent.getName());
            }
            imSysDto.setEntId(entId);
         }
        Integer bizType = pushImDto.getBizType();
        MessageBizType messageBizType = MessageBizType.find(bizType);
        if(null != messageBizType){
            imSysDto.setTitle(messageBizType.getTitle());
            imSysDto.setContent(messageBizType.getContent());
            imSysDto.setUrl(messageBizType.getUrl());
            commonExtraDto.setUrl(messageBizType.getUrl());
        }
        if(StringUtils.isNotBlank(pushImDto.getTitle())){
            imSysDto.setTitle(pushImDto.getTitle());
        }
        if(StringUtils.isNotBlank(pushImDto.getContent())){
            imSysDto.setContent(pushImDto.getContent());
        }
        if(StringUtils.isNotBlank(pushImDto.getUrl())){
            imSysDto.setUrl(pushImDto.getUrl());
            commonExtraDto.setUrl(pushImDto.getUrl());

        }


        //接收者
        List<String> toUserList = new ArrayList<>();
        List<String> personIdList = pushImDto.getPersonIdList();
        for(String personId : personIdList){
            ImUserEntity imUser = imUserDao.getByPersonId(personId);
            if(null != imUser){
                toUserList.add(imUser.getImId());
            }

        }
        imSysDto.setToUser(toUserList);
        imSysDto.setType(MessageType.COMMON.getValue().toString());
        //时间
        imSysDto.setSendTime(DateUtil.timestamp2Str(DateUtil.getSqlTime()));

        //自定义内容
        commonExtraDto.setClick(pushImDto.isClick());
        commonExtraDto.setBizType(bizType);
        commonExtraDto.setImInfoList(pushImDto.getInfoList());



        imSysDto.setExtra(gson.toJson(commonExtraDto));

        //im推送
        if(toUserList.size()>0) {
            imMsgSender.sendSysPush(imSysDto);
        }

    }
}
