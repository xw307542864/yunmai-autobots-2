package com.logibeat.cloud.common.cache.queue;

import com.logibeat.cloud.common.cache.entity.CacheOperateEntity;
import com.logibeat.cloud.common.cache.entity.CacheOperateQueueEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CacheOperateQueue {

	final static BlockingQueue<CacheOperateQueueEntity> queue = new ArrayBlockingQueue<>(9999);

	public static void putList(String random, List<CacheOperateEntity> operateList) {
		try {
			queue.put(new CacheOperateQueueEntity(random, operateList));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void put(String random, CacheOperateEntity operate) {
		try {
			List<CacheOperateEntity> operateList = new ArrayList<>();
			operateList.add(operate);
			queue.put(new CacheOperateQueueEntity(random, operateList));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static CacheOperateQueueEntity take() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
