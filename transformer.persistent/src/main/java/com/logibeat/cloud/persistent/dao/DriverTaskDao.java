package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.DriverTaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  数据层
 * 
 * @author wilson
 * @date 2020-04-14
 */
public interface DriverTaskDao {
	/**
     * 查询信息
     * 
     * @param guid ID
     * @return 信息
     */
	 DriverTaskEntity selectDriverTaskById(String guid);



	DriverTaskEntity selectDriverTaskByKeyId(String taskKeyId);


	DriverTaskEntity selectDriverTaskByKeyIdAndVehicleId(@Param("taskKeyId") String taskKeyId,@Param("vehicleId") String vehicleId);

	/**
     * 查询列表
     * 
     * @param driverTask 信息
     * @return 集合
     */
	 List<DriverTaskEntity> selectDriverTaskList(DriverTaskEntity driverTask);
	
	/**
     * 新增
     * 
     * @param driverTask 信息
     * @return 结果
     */
	 int insertDriverTask(DriverTaskEntity driverTask);
	
	/**
     * 修改
     * 
     * @param driverTask 信息
     * @return 结果
     */
	 int updateDriverTask(DriverTaskEntity driverTask);
	
	/**
     * 删除
     * 
     * @param guid ID
     * @return 结果
     */
	 int deleteDriverTaskById(String guid);
	
	/**
     * 批量删除
     * 
     * @param guids 需要删除的数据ID
     * @return 结果
     */
	 int deleteDriverTaskByIds(String[] guids);


	/**
	 * 当前在途的任务
	 * @param currentTaskId
	 * @return
	 */
	 DriverTaskEntity queryRunningTask(@Param("personId") String personId, @Param("currentTaskId") String currentTaskId);


	/**
	 * 正在执行中的任务
	 * @param personId
	 * @param currentTaskId
	 * @return
	 */
	 DriverTaskEntity queryExecutingTask(@Param("personId") String personId, @Param("currentTaskId") String currentTaskId);

	
}