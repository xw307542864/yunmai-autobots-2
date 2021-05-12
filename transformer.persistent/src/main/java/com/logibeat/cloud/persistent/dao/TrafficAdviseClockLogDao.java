package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.TrafficAdviseClockLogEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  数据层
 * 
 * @author wilson
 * @date 2021-03-26
 */
public interface TrafficAdviseClockLogDao 
{
	/**
     * 查询信息
     * 
     * @param guid ID
     * @return 信息
     */
	public TrafficAdviseClockLogEntity selectTrafficAdviseClockLogById(String guid);
	
	/**
     * 查询列表
     * 
     * @param trafficAdviseClockLog 信息
     * @return 集合
     */
	public List<TrafficAdviseClockLogEntity> selectTrafficAdviseClockLogList(TrafficAdviseClockLogEntity trafficAdviseClockLog);
	
	/**
     * 新增
     * 
     * @param trafficAdviseClockLog 信息
     * @return 结果
     */
	public int insertTrafficAdviseClockLog(TrafficAdviseClockLogEntity trafficAdviseClockLog);
	
	/**
     * 修改
     * 
     * @param trafficAdviseClockLog 信息
     * @return 结果
     */
	public int updateTrafficAdviseClockLog(TrafficAdviseClockLogEntity trafficAdviseClockLog);
	
	/**
     * 删除
     * 
     * @param guid ID
     * @return 结果
     */
	public int deleteTrafficAdviseClockLogById(String guid);
	
	/**
     * 批量删除
     * 
     * @param guids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTrafficAdviseClockLogByIds(String[] guids);
	
	public List<TrafficAdviseClockLogEntity> selectByAdviseId(@Param("adviseId")String adviseId,@Param("curDate")String curDate);
	
}