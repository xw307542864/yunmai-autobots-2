package com.logibeat.cloud.services;



import com.logibeat.cloud.common.vo.CarGpsSourceVo;
import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import com.logibeat.cloud.persistent.entity.DriverTaskPointEntity;

import java.util.List;

/**
 * 任务跟踪
 * @ClassName: TaskTrackService 
 * @Description: 
 * @author kzz 
 * @date 2017年8月14日 下午7:49:45 
 * @version 1.0
 */
public interface TaskTrackService {

	/**
	 * 获取时间段
	 * @param taskId
	 * @return
	 */
	List<CarGpsSourceVo> getTaskHistoryOrbit(String taskId, String consignId);


	/**
	 *计算到达装卸点/任务里程
	 * @param driverTask
	 * @param taskPoint
	 */
	void countMileage(DriverTaskEntity driverTask, DriverTaskPointEntity taskPoint);
}
