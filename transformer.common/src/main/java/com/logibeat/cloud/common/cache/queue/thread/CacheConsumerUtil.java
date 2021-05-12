package com.logibeat.cloud.common.cache.queue.thread;

import com.logibeat.cloud.common.cache.annotation.CacheList.CacheListType;
import com.logibeat.cloud.common.cache.annotation.CacheOperate.CacheOperateType;
import com.logibeat.cloud.common.cache.entity.CacheOperateEntity;
import com.logibeat.cloud.common.cache.entity.CacheOperateListEntity;
import com.logibeat.cloud.common.cache.entity.CacheOperateObjectEntity;
import com.logibeat.cloud.common.cache.util.JsonMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheConsumerUtil {
	
	protected static String getCacheKey(String key, String[] addition, Object[] args) {
		String value = "";
		int i = 0;
		for (String addi : addition) {
			if (addi.toString().indexOf("#") > -1) {
				value += getFieldValue(addi.replaceFirst("#", ""), args[0]) + ".";
			} else {
				value += args[i].toString() + ".";
				i++;
			}
		}
		String newKey = key + value;
		String result = value.contains("notatall..") ? null : newKey.substring(0, newKey.length() - 1);
		return result;
	}

	protected static String getFieldValue(String addition, Object arg) {
		Field fields[] = arg.getClass().getDeclaredFields();// 获得对象所有属性
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			String fieldType = fields[i].getGenericType().toString();
			if (fieldName.equals(addition)) {
				if (fieldType.equals("class java.lang.Integer") || fieldType.equals("class java.lang.String") || fieldType.equals("class java.lang.Long")) {
					try {
						Method m = arg.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
						Object value = (Object) m.invoke(arg);
						return value == null ? "notatall.." : value.toString();
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}
	
	protected static CacheOperateEntity getCacheOperateEntity(String value, String key, Object arg, Class classType, CacheOperateType cacheOperateType){
		if(arg instanceof ArrayList){
			return null;
		}

		CacheOperateObjectEntity cacheOperateEntity = new CacheOperateObjectEntity();
		cacheOperateEntity.setValue(value);
		cacheOperateEntity.setKey(key);
		cacheOperateEntity.setClassType(classType);
		cacheOperateEntity.setCacheOperateType(cacheOperateType);

		String argJson = JsonMapper.toNonDefaultJson(arg);
		cacheOperateEntity.setEntity(argJson);
		return cacheOperateEntity;
	}

	protected static CacheOperateEntity getCacheOperateEntity(String key, String id, Object arg, Class classType, CacheListType cacheListType){
		CacheOperateListEntity cacheOperateEntity = new CacheOperateListEntity();
		cacheOperateEntity.setKey(key);
		cacheOperateEntity.setClassType(classType);
		cacheOperateEntity.setCacheListType(cacheListType);
		cacheOperateEntity.setEntity(arg);
		cacheOperateEntity.setId(id);
		return cacheOperateEntity;
	}

	protected static Map<String, String> getCacheMap(String addition, Object arg){
		List<Object> args = new ArrayList<>();
		if(arg instanceof ArrayList){
			args = (List<Object>)arg;
		} else{
			args.add(arg);
		}
		
		Map<String, String> cacheMap = new HashMap<>();
		for(Object object: args){
			String mapKey = CacheConsumerUtil.getFieldValue(addition, object);
			String mapValue = JsonMapper.toNonDefaultJson(object);
			cacheMap.put(mapKey, mapValue);
		}
		return cacheMap;
	}

}
