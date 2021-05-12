package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.TrafficAdviseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  数据层
 * 
 * @author wilson
 * @date 2021-03-26
 */
public interface TrafficAdviseDao 
{
	/**
     * 查询信息
     * 
     * @param guid ID
     * @return 信息
     */
	public TrafficAdviseEntity selectTrafficAdviseById(String guid);
	
	/**
     * 查询列表
     * 
     * @param trafficAdvise 信息
     * @return 集合
     */
	public List<TrafficAdviseEntity> selectTrafficAdviseList(TrafficAdviseEntity trafficAdvise);
	
	/**
     * 新增
     * 
     * @param trafficAdvise 信息
     * @return 结果
     */
	public int insertTrafficAdvise(TrafficAdviseEntity trafficAdvise);
	
	/**
     * 修改
     * 
     * @param trafficAdvise 信息
     * @return 结果
     */
	public int updateTrafficAdvise(TrafficAdviseEntity trafficAdvise);
	
	/**
     * 删除
     * 
     * @param guid ID
     * @return 结果
     */
	public int deleteTrafficAdviseById(String guid);
	
	/**
     * 批量删除
     * 
     * @param guids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTrafficAdviseByIds(String[] guids);
	
	public int getAdviseNum(@Param("personId") String personId);
	
	public List<TrafficAdviseEntity> queryExpireData();//查询过期数据
	
	public List<TrafficAdviseEntity> queryExecuteData();//查询执行中数据
	
	public void updateByRelationInfo(@Param("relationInfo") String relationInfo);
	
}