package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.DriverTaskCargoEntity;

import java.util.List;

/**
 *  数据层
 * 
 * @author wilson
 * @date 2020-04-14
 */
 public interface DriverTaskCargoDao {
	/**
     * 查询信息
     * 
     * @param guid ID
     * @return 信息
     */
	 DriverTaskCargoEntity selectDriverTaskCargoById(String guid);



	/**
     * 查询列表
     * 
     * @param driverTaskCargo 信息
     * @return 集合
     */
	 List<DriverTaskCargoEntity> selectDriverTaskCargoList(DriverTaskCargoEntity driverTaskCargo);
	
	/**
     * 新增
     * 
     * @param driverTaskCargo 信息
     * @return 结果
     */
	 int insertDriverTaskCargo(DriverTaskCargoEntity driverTaskCargo);
	
	/**
     * 修改
     * 
     * @param driverTaskCargo 信息
     * @return 结果
     */
	 int updateDriverTaskCargo(DriverTaskCargoEntity driverTaskCargo);
	
	/**
     * 删除
     * 
     * @param guid ID
     * @return 结果
     */
	 int deleteDriverTaskCargoById(String guid);
	
	/**
     * 批量删除
     * 
     * @param guids 需要删除的数据ID
     * @return 结果
     */
	 int deleteDriverTaskCargoByIds(String[] guids);


	/**
	 * 根据任务删除
	 * @param taskId
	 * @return
	 */
	int deleteByTaskId(String taskId);



	
}