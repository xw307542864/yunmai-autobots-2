package com.logibeat.cloud.common.vo;

public class StudyVo {

    private String planId;

    private String planName;

    private String penaltyOrg;

    private String planTime;

    private String planType;

    private String cycleId;
    
    private String studyFinishTime;
    
    private String className;
    
    private String status;//0未学 1已学 2已失效
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStudyFinishTime() {
		return studyFinishTime;
	}

	public void setStudyFinishTime(String studyFinishTime) {
		this.studyFinishTime = studyFinishTime;
	}

	public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPenaltyOrg() {
        return penaltyOrg;
    }

    public void setPenaltyOrg(String penaltyOrg) {
        this.penaltyOrg = penaltyOrg;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }


    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
    }
}
