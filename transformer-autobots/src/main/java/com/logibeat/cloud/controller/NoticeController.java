package com.logibeat.cloud.controller;

import com.logibeat.cloud.common.vo.NoticeInfoVo;
import com.logibeat.cloud.services.TaskNoticeService;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 消息
 * 
 * @ClassName: NoticeController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Wilson
 * @date 2016年3月25日 下午1:26:55
 *
 */
@RestController
@RequestMapping(value = "Driver/Im/api/DrvNotice")
@Scope("prototype")
public class NoticeController extends BaseController {

	@Autowired
	protected TaskNoticeService taskNoticeService;

	@RequestMapping(value = "/NoticeList")
	@ResponseBody
	public JSONPrompt getNoticeList(String time, boolean isUp) {
		JSONPrompt jsonResult = new JSONPrompt();
		List<NoticeInfoVo> noticeInfoVos = taskNoticeService.getNoticeList(time, isUp, baseUserId);
		jsonResult.setData(noticeInfoVos);
		return jsonResult;
	}
}
