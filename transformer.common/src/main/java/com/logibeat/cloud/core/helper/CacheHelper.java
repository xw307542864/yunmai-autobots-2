package com.logibeat.cloud.core.helper;

import com.logibeat.cloud.common.annotation.NotLogin;
import com.logibeat.cloud.core.constant.ConstantUtil;
import com.logibeat.cloud.core.properties.RunMode;
import com.logibeat.cloud.common.cache.RedisCache;
import com.logibeat.cloud.common.cache.enumType.CacheDBType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: CacheHelper
 * @Description: 数据帮助
 * @author sean
 * @date 2015年12月4日 下午3:00:14
 * @version 1.0
 */
@Service
public class CacheHelper {

	private static final Logger logger = LoggerFactory.getLogger(CacheHelper.class);

	@Autowired
	private RedisCache cache;

	@Autowired
	private RunMode runMode;

	public void addCode(String key, String value) {
		logger.info("[redis>>>>>>]加入验证码:key={}, code={}", key, value);
		int t = runMode.getCacheSmsTime();
		cache.setex(key, t, value, CacheDBType.CodeDB);
	}

	public String getCode(String key) {
		String code = cache.get(key, CacheDBType.CodeDB);
		logger.info("[redis>>>>>>]获取验证码:key={}, code={}", key, code);
		return code;
	}

	public void removeCode(String key) {
		cache.del(key, CacheDBType.CodeDB);
	}

	/**
	 * 
	 * @Title: getUserParam
	 * @Description: 获取当前登录用户相关信息
	 * @param ident
	 *            baseuserid
	 * @return
	 * @return String
	 */
	public String getUserParam(String ident, String baseuserid, String key) {
		String result = null;
		String redisKey = ident + baseuserid;
		List<String> list = cache.hmget(redisKey, key, CacheDBType.AuthDB);
		if (list.size() > 0) {
			result = list.get(0);
		}
		logger.info("[redis>>>>>>]获取用户信息:key={}, value={}", redisKey, result);
		return result;
	}

	/**
	 * @param ident
	 *            前缀标识
	 * @param userGUID
	 * @param key
	 * @param value
	 */
	public void setUserParam(String ident, String userGUID, String key, String value) {
		String redisKey = ident + userGUID;
		logger.info("[redis>>>>>>]设置用户信息:redisKey={}, key={}, value={}", redisKey, key, value);
		Map<String, String> dataMap = cache.hgetAll(redisKey, CacheDBType.AuthDB);
		dataMap.put(key, value);
		cache.hmset(redisKey, dataMap, CacheDBType.AuthDB);
	}
	
	/**
	 * 延时
	 * @param redisKey
	 */
	public void expireUserParam(String redisKey, String client) {
		int t = 0;
		if(!client.equals(ConstantUtil.WEB)){
			t = runMode.getCacheAppUserInfoTime();
		} else{
			t = runMode.getCacheWebUserInfoTime();
		}
		
//		logger.info("[redis>>>>>>]延时缓存用户信息:redisKey={}, time={}", redisKey, t);
		cache.expire(redisKey, t, CacheDBType.AuthDB);
	}
	
	public void removeUserParam(String key) {
		cache.del(key, CacheDBType.AuthDB);
	}


	public void removeUserParamByKey(String redisKey ,String delKey){

		cache.hdel(redisKey,delKey,CacheDBType.AuthDB);
	}


	/**
	 * 
	 * @Title: getUser
	 * @Description: 获取登录对象
	 * @param ident
	 * @return
	 * @return Map<String,String>
	 */
	public Map<String, String> getUser(String ident, String baseuserid) {
		Map<String, String> result = null;
		String redisKey = ident + baseuserid;
		result = cache.hgetAll(redisKey, CacheDBType.AuthDB);
		return result;
	}

	public boolean isNotLogin(Object handler) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			NotLogin notLogin = method.getMethod().getAnnotation(NotLogin.class);
			if (notLogin != null) {
				return true;
			}
		}
		return false;
	}
}
