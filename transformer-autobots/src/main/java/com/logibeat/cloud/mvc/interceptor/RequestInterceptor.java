package com.logibeat.cloud.mvc.interceptor;

import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.helper.CacheHelper;
import com.logibeat.cloud.util.tools.enumtype.ErrorEnums;
import com.logibeat.cloud.util.tools.exception.TException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private CacheHelper cacheHelper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		log.warn("**********************************{}  start",
				request.getRequestURI());

		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime" + request.getRequestURI(), startTime);

		String headerBaseUserId = request.getHeader(ConstantUtil.BASEUSERID);
		Object sessionBaseUserId = null;
		String baseuserid = StringUtils.isNotBlank(headerBaseUserId)?headerBaseUserId : sessionBaseUserId + "";
		if (cacheHelper.isNotLogin(handler)){
			return true;
		}

		String headerClientType = request.getHeader(ConstantUtil.CLIENTTYPE);
		Object sessionClientType = null;
		String appKey = StringUtils.isNotBlank(headerClientType)?headerClientType + "-" : sessionClientType + "-";
		String personObjectId = cacheHelper.getUserParam(appKey, baseuserid, ConstantUtil.USERID);
		log.warn("用户登录baseuserid={}", baseuserid);
		// 无person,说明没有登录，拦�?
		if (personObjectId == null) {
			throw new TException(ErrorEnums.NOLOGININFO);
		}
		String headerEquipment = request.getHeader(ConstantUtil.EQUIPMENT);
		Object sessionEquipment = null;
		String equipment =  StringUtils.isNotBlank(headerEquipment)?headerEquipment : sessionEquipment + "";
		if (!ConstantUtil.WEB.equals(appKey)) {
			String equipmentId = cacheHelper.getUserParam(appKey, baseuserid, ConstantUtil.EQUIPMENT);
			if (!StringUtils.isBlank(equipmentId) && !equipmentId.equals(equipment)) {
				log.warn("用户{}已在另一设备登录", baseuserid);
				throw new TException(ErrorEnums.SECONDLOGINEQUIPMENT);
			}
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		long startTime = (long) request.getAttribute("startTime" + request.getRequestURI());
		long spaceTime = System.currentTimeMillis() - startTime;
		log.warn("**********************************{} end ,耗时：{}毫秒", request.getRequestURI(),spaceTime);
	}

	public String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();

	}

	public CacheHelper getCacheHelper() {
		return cacheHelper;
	}

	public void setCacheHelper(CacheHelper cacheHelper) {
		this.cacheHelper = cacheHelper;
	}
}