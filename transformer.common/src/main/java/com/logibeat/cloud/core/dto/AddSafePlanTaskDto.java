package com.logibeat.cloud.core.dto;

public class AddSafePlanTaskDto {





    /**
     * 企业id
     */
    private String entId;

    /**
     * 司机id
     */
    private String personId;

    /**
     * 司机手机号
     */
    private String personPhone;

    /**
     * 课件/试卷id
     */
    private String dataId;

    /**
     * 有效期---开始时间
     */
    private String startTime;

    /**
     * 有效期---结束时间
     */
    private String endTime;

    /**
     *  业务类型 100：运脉添加    200：星软违规 300：星软增分
     */
    private Integer bizType;


    /**
     * 100 学习计划 200 考试计划
     */
    private Integer taskType;

    /**
     * 平台
     */
    private String platformType="100";


    /**
     * 违规 id
     */
    private String safeRelationId;


    /**
     * 违规id（运脉）  李亮废弃
     */
    private String violationId;

    /**
     * 违规司机id(运脉)  李亮废弃
     */
    private String violationDriverId;

    private Integer studyType = 100;//类型 100学习 200每日一学 300考试 400AI学习
    
    private Integer relationSafeCode = 0;//是否关联安全码
    
    public Integer getStudyType() {
		return studyType;
	}

	public void setStudyType(Integer studyType) {
		this.studyType = studyType;
	}

	public Integer getRelationSafeCode() {
		return relationSafeCode;
	}

	public void setRelationSafeCode(Integer relationSafeCode) {
		this.relationSafeCode = relationSafeCode;
	}

	public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getSafeRelationId() {
        return safeRelationId;
    }

    public void setSafeRelationId(String safeRelationId) {
        this.safeRelationId = safeRelationId;
    }

    public String getViolationId() {
        return violationId;
    }

    public void setViolationId(String violationId) {
        this.violationId = violationId;
    }

    public String getViolationDriverId() {
        return violationDriverId;
    }

    public void setViolationDriverId(String violationDriverId) {
        this.violationDriverId = violationDriverId;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }
}
