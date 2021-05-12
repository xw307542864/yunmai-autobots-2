package com.logibeat.cloud.persistent.dao;

import com.logibeat.cloud.persistent.entity.PersonViolationEntity;

import java.util.List;

public interface PersonViolationDao {


    /**
     * 查询信息
     *
     * @param guid ID
     * @return 信息
     */
     PersonViolationEntity selectPersonViolationById(String guid);

    /**
     * 查询列表
     *
     * @param personViolation 信息
     * @return 集合
     */
     List<PersonViolationEntity> selectPersonViolationList(PersonViolationEntity personViolation);

    /**
     * 新增
     *
     * @param personViolation 信息
     * @return 结果
     */
     int insertPersonViolation(PersonViolationEntity personViolation);

    /**
     * 修改
     *
     * @param personViolation 信息
     * @return 结果
     */
     int updatePersonViolation(PersonViolationEntity personViolation);

    /**
     * 删除
     *
     * @param guid ID
     * @return 结果
     */
     int deletePersonViolationById(String guid);

    /**
     * 批量删除
     *
     * @param guids 需要删除的数据ID
     * @return 结果
     */
     int deletePersonViolationByIds(String[] guids);
}
