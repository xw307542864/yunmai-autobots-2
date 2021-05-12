package com.logibeat.cloud.services.impl;

import com.logibeat.cloud.common.enumtype.OrdersType;
import com.logibeat.cloud.common.vo.NoticeInfoVo;
import com.logibeat.cloud.core.constant.DateUtil;
import com.logibeat.cloud.persistent.dao.EnterpriseInfoDao;
import com.logibeat.cloud.persistent.dao.TaskOrdersAreaDao;
import com.logibeat.cloud.persistent.dao.TaskOrdersCarDao;
import com.logibeat.cloud.persistent.dao.TaskOrdersDao;
import com.logibeat.cloud.persistent.entity.EnterpriseInfoEntity;
import com.logibeat.cloud.persistent.entity.TaskOrdersAreaEntity;
import com.logibeat.cloud.persistent.entity.TaskOrdersCarEntity;
import com.logibeat.cloud.persistent.entity.TaskOrdersEntity;
import com.logibeat.cloud.services.TaskNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务通知服务实现类
 * @author wangxp
 * @date 2016年3月18日
 * @version 1.0
 */
@Service
public class TaskNoticeServiceImpl implements TaskNoticeService {

    private static final Logger log = LoggerFactory.getLogger(TaskNoticeServiceImpl.class);
    
    @Autowired
    private EnterpriseInfoDao enterpriseInfoDao;

    @Autowired
    private TaskOrdersDao taskOrdersDao;

    @Autowired
    private TaskOrdersAreaDao taskOrdersAreaDao;

    @Autowired
    private TaskOrdersCarDao taskOrdersCarDao;



	/**
	 * 司机端任务提醒
	 */
	@Override
	public List<NoticeInfoVo> getNoticeList(String time, boolean isUp, String baseUserId) {
		List<NoticeInfoVo> resultList = new ArrayList<NoticeInfoVo>();
		List<TaskOrdersCarEntity> taskOrdersCarList = taskOrdersCarDao.getNoticeList(time, isUp, baseUserId);
		for(TaskOrdersCarEntity taskOrdersCarEntity : taskOrdersCarList){
            Integer taskCarType = taskOrdersCarEntity.getTaskCarType();
		    if(OrdersType.CreateRoute.getValue().equals(taskCarType)
                    || OrdersType.CreateRouteNoEnt.getValue().equals(taskCarType)){
		        continue;
            }
			NoticeInfoVo noticeInfoVo = new NoticeInfoVo();
			StringBuffer contentBuffer= new StringBuffer();
			//订单ID
			String orderCid = taskOrdersCarEntity.getOrdersCID();
			//订单主体
			TaskOrdersEntity taskOrdersEntity = taskOrdersDao.select(orderCid);
			noticeInfoVo.setTitle("新任务提醒");
			noticeInfoVo.setRelationGUID(taskOrdersCarEntity.getId());
			noticeInfoVo.setSendTime(DateUtil.dateTOString(taskOrdersCarEntity.getEntrustTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
			noticeInfoVo.setCarId(taskOrdersCarEntity.getId());
			
			EnterpriseInfoEntity enterpriseInfoEntity = enterpriseInfoDao.select(taskOrdersCarEntity.getEntrustEntid());
			contentBuffer.append(enterpriseInfoEntity.getName());
			contentBuffer.append("给你发了一条新任务");
			contentBuffer.append("<br>");
			
			String startAreaGuid = taskOrdersEntity.getStartAreaGUID();
			TaskOrdersAreaEntity startArea = taskOrdersAreaDao.select(startAreaGuid);
			contentBuffer.append(startArea.getName());
			contentBuffer.append("-");
			
			String endtAreaGuid = taskOrdersEntity.getEndAreaGUID();
			TaskOrdersAreaEntity endArea = taskOrdersAreaDao.select(endtAreaGuid);
			contentBuffer.append(endArea.getName());
			contentBuffer.append("<br>");
			
			if(taskOrdersEntity.getDuration() == 0){
				contentBuffer.append(DateUtil.dateTOString(taskOrdersEntity.getEndAreaPlanArriveTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
			}
			else{
				contentBuffer.append(DateUtil.dateTOString(taskOrdersEntity.getStartAreaPlanLeavTime(),DateUtil.YYYY_MM_DD_HH_mm_ss));
				contentBuffer.append(",时效");
                Integer duration = taskOrdersEntity.getDuration();
                if(null != duration){
                    int hour = duration/60;
                    int minute = duration%60;
                    if(hour>0){
                        contentBuffer.append(hour);
                        contentBuffer.append("小时");
                    }
                    if(minute>0){
                        contentBuffer.append(minute);
                        contentBuffer.append("分钟");
                    }
                }
                else{
                    contentBuffer.append(taskOrdersEntity.getStatute()).append("小时");
                }
			}
			noticeInfoVo.setContent(contentBuffer.toString());
			resultList.add(noticeInfoVo);
			
		}
		return resultList;
	}

}
