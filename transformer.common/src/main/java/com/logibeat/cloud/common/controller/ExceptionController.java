package com.logibeat.cloud.common.controller;

import com.logibeat.cloud.util.tools.ResponseUtil;
import com.logibeat.cloud.util.tools.enumtype.ErrorEnums;
import com.logibeat.cloud.util.tools.exception.AbstractException;
import com.logibeat.cloud.util.tools.responseMdl.JSONPrompt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常统一处理
 *
 * @author sean
 * @version 1.0
 * @date 2015年12月28日 下午5:22:41
 */
public class ExceptionController implements HandlerExceptionResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {
		if (request.getRequestURI().endsWith(".htm")) {
			// 声明返回对象
			JSONPrompt<?> jsonResult = new JSONPrompt<>();

			if (ex instanceof AbstractException) {
				AbstractException absException = (AbstractException) ex;
				jsonResult.setErrCode(absException.getCode()).setMessage(absException.getMessage());
				logger.error("捕获自定义异常 + {}{} : {}", absException.getModule() == null ? "" : absException.getModule(), absException.getCode(), absException.getMessage());
			} else {
				jsonResult.setErrCode(ErrorEnums.UNKNOWEXCEPTION.getValue()).setMessage(ErrorEnums.UNKNOWEXCEPTION.getDescription());
				logger.error("捕获异常 + ", ex);
			}

			ResponseUtil.writeJsonToClient(response, jsonResult);
			return null;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("exceptionInfo", ex.getMessage());
			return new ModelAndView("exception", map);
		}
	}

}
