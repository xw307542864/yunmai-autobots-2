package com.logibeat.cloud.common.vo;

public class DriverTaskItemVo {

    private Integer waitDepartNumber;

    private boolean showRed;
    
    private Integer noReadNum;
    
    public Integer getNoReadNum() {
		return noReadNum;
	}

	public void setNoReadNum(Integer noReadNum) {
		this.noReadNum = noReadNum;
	}

	public Integer getWaitDepartNumber() {
        return waitDepartNumber;
    }

    public void setWaitDepartNumber(Integer waitDepartNumber) {
        this.waitDepartNumber = waitDepartNumber;
    }

    public boolean isShowRed() {
        return showRed;
    }

    public void setShowRed(boolean showRed) {
        this.showRed = showRed;
    }
}
