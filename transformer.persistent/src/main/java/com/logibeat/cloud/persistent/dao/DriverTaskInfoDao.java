package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.DriverTaskInfoEntity;

import java.util.List;

/**
 *  数据层
 * 
 * @author wilson
 * @date 2020-04-14
 */
public interface DriverTaskInfoDao {
	/**
     * 查询信息
     * 
     * @param guid ID
     * @return 信息
     */
	 DriverTaskInfoEntity selectDriverTaskInfoById(String guid);


	/**
	 * 查询
	 * @param taskId
	 * @return
	 */
	DriverTaskInfoEntity selectDriverTaskInfoByTaskId(String taskId);

	
	/**
     * 查询列表
     * 
     * @param driverTaskInfo 信息
     * @return 集合
     */
	 List<DriverTaskInfoEntity> selectDriverTaskInfoList(DriverTaskInfoEntity driverTaskInfo);
	
	/**
     * 新增
     * 
     * @param driverTaskInfo 信息
     * @return 结果
     */
	 int insertDriverTaskInfo(DriverTaskInfoEntity driverTaskInfo);
	
	/**
     * 修改
     * 
     * @param driverTaskInfo 信息
     * @return 结果
     */
	 int updateDriverTaskInfo(DriverTaskInfoEntity driverTaskInfo);
	
	/**
     * 删除
     * 
     * @param guid ID
     * @return 结果
     */
	 int deleteDriverTaskInfoById(String guid);
	
	/**
     * 批量删除
     * 
     * @param guids 需要删除的数据ID
     * @return 结果
     */
	 int deleteDriverTaskInfoByIds(String[] guids);


	 int deleteDriverTaskInfoByTaskId(String taskId);


}