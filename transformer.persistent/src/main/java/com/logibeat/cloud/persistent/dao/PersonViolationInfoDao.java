package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.PersonViolationInfoEntity;

import java.util.List;

/**
 *  数据层
 * 
 * @author wilson
 * @date 2020-03-12
 */
public interface PersonViolationInfoDao {
	/**
     * 查询信息
     * 
     * @param guid ID
     * @return 信息
     */
	 PersonViolationInfoEntity selectPersonViolationInfoById(String guid);
	
	/**
     * 查询列表
     * 
     * @param personViolationInfo 信息
     * @return 集合
     */
	 List<PersonViolationInfoEntity> selectPersonViolationInfoList(PersonViolationInfoEntity personViolationInfo);
	
	/**
     * 新增
     * 
     * @param personViolationInfo 信息
     * @return 结果
     */
	 int insertPersonViolationInfo(PersonViolationInfoEntity personViolationInfo);
	
	/**
     * 修改
     * 
     * @param personViolationInfo 信息
     * @return 结果
     */
	 int updatePersonViolationInfo(PersonViolationInfoEntity personViolationInfo);
	
	/**
     * 删除
     * 
     * @param guid ID
     * @return 结果
     */
	 int deletePersonViolationInfoById(String guid);
	
	/**
     * 批量删除
     * 
     * @param guids 需要删除的数据ID
     * @return 结果
     */
	 int deletePersonViolationInfoByIds(String[] guids);



	 int deleteByViolationId(String violationId);
	
}