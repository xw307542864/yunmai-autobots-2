package com.logibeat.cloud.services;



import com.logibeat.cloud.common.vo.NoticeInfoVo;

import java.util.List;

/**
 * 动态服务类
 * @author wangxp
 * @date 2016年3月18日
 * @version 1.0
 */
public interface TaskNoticeService {

	List<NoticeInfoVo> getNoticeList(String time, boolean isUp, String baseUserId);

}
