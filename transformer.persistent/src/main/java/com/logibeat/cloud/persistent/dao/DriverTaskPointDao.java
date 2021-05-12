package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.DriverTaskPointEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  数据层
 * 
 * @author wilson
 * @date 2020-04-14
 */
public interface DriverTaskPointDao {
	/**
     * 查询信息
     * 
     * @param guid ID
     * @return 信息
     */
	 DriverTaskPointEntity selectDriverTaskPointById(String guid);
	
	/**
     * 查询列表
     * 
     * @param driverTaskPoint 信息
     * @return 集合
     */
	 List<DriverTaskPointEntity> selectDriverTaskPointList(DriverTaskPointEntity driverTaskPoint);



	 List<DriverTaskPointEntity> getPointListByRelationId(String relationId);
	
	/**
     * 新增
     * 
     * @param driverTaskPoint 信息
     * @return 结果
     */
	 int insertDriverTaskPoint(DriverTaskPointEntity driverTaskPoint);
	
	/**
     * 修改
     * 
     * @param driverTaskPoint 信息
     * @return 结果
     */
	 int updateDriverTaskPoint(DriverTaskPointEntity driverTaskPoint);
	
	/**
     * 删除
     * 
     * @param guid ID
     * @return 结果
     */
	 int deleteDriverTaskPointById(String guid);
	
	/**
     * 批量删除
     * 
     * @param guids 需要删除的数据ID
     * @return 结果
     */
	 int deleteDriverTaskPointByIds(String[] guids);





	/**
	 * 已完成卸货的卸货点数量
	 * @param taskId
	 * @param relationId
	 * @return
	 */
	 Long finishUnloadCount(@Param("taskId") String taskId, @Param("relationId") String relationId, @Param("currentPontId") String currentPontId);


	/**
	 * 判断 装卸点对应的单据 除 当前装卸点之外  未完成的装卸点数
	 * @param taskId
	 * @param relationId
	 * @param currentPontId
	 * @return
	 */
	 Long finishPointByRelationId(@Param("taskId") String taskId, @Param("relationId") String relationId, @Param("currentPontId") String currentPontId);


	/**
	 * 装卸点对应的单据 未到达的装卸点数
	 * @param taskId
	 * @param relationId
	 * @return
	 */
	 Long arrivePointByRelationId(@Param("taskId") String taskId, @Param("relationId") String relationId);
	/**
	 * 已到达装货点/开始装货/已到达卸货点/开始卸货 的装卸点
	 * @param taskId
	 * @return
	 */
	 List<DriverTaskPointEntity> arriveList(@Param("taskId")String taskId);


	/**
	 * 根据装卸点顺序获取装卸点
	 * @param taskId
	 * @param sort
	 * @return
	 */
	 DriverTaskPointEntity getPointBySort(@Param("taskId") String taskId,@Param("sort") Integer sort);


	/**
	 * 更新签收信息
	 * @param consignOrderId
	 * @return
	 */
	int sign(String consignOrderId);


	/**
	 * 获取第一个装点
	 * @param taskId
	 * @return
	 */
	DriverTaskPointEntity getFirstPoint(@Param("taskId") String taskId);


	/**
	 * 根据任务id删除
	 * @param taskId
	 * @return
	 */
	int deleteByTaskId(String taskId);



	List<DriverTaskPointEntity> excetedPointList(String taskId);


	/**
	 * 待签收的点
	 * @param taskId
	 * @return
	 */
	Long waitSignCount(String taskId);


	/**
	 * 待签收点
	 * @param taskId
	 * @return
	 */
	List<DriverTaskPointEntity> waitSignList(String taskId);


}