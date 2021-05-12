package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.PersonViolationAppealEntity;

import java.util.List;

/**
 *  数据层
 * 
 * @author wilson
 * @date 2020-03-12
 */
public interface PersonViolationAppealDao {
	/**
     * 查询信息
     * 
     * @param guid ID
     * @return 信息
     */
	 PersonViolationAppealEntity selectPersonViolationAppealById(String guid);
	
	/**
     * 查询列表
     * 
     * @param personViolationAppeal 信息
     * @return 集合
     */
	 List<PersonViolationAppealEntity> selectPersonViolationAppealList(PersonViolationAppealEntity personViolationAppeal);
	
	/**
     * 新增
     * 
     * @param personViolationAppeal 信息
     * @return 结果
     */
	 int insertPersonViolationAppeal(PersonViolationAppealEntity personViolationAppeal);
	
	/**
     * 修改
     * 
     * @param personViolationAppeal 信息
     * @return 结果
     */
	 int updatePersonViolationAppeal(PersonViolationAppealEntity personViolationAppeal);
	
	/**
     * 删除
     * 
     * @param guid ID
     * @return 结果
     */
	 int deletePersonViolationAppealById(String guid);
	
	/**
     * 批量删除
     * 
     * @param guids 需要删除的数据ID
     * @return 结果
     */
	 int deletePersonViolationAppealByIds(String[] guids);
	
}