package com.logibeat.cloud.vo.startsoft;

import java.util.List;

public class StarSoft_AssisInfoVo {


    private List<StarSoft_ViolationInfoVo> violations;


    private List<StarSoft_SafeStudyVo> safeStudys;


    private List<StarSoft_OthersVo> others;

    public List<StarSoft_ViolationInfoVo> getViolations() {
        return violations;
    }

    public void setViolations(List<StarSoft_ViolationInfoVo> violations) {
        this.violations = violations;
    }

    public List<StarSoft_SafeStudyVo> getSafeStudys() {
        return safeStudys;
    }

    public void setSafeStudys(List<StarSoft_SafeStudyVo> safeStudys) {
        this.safeStudys = safeStudys;
    }

    public List<StarSoft_OthersVo> getOthers() {
        return others;
    }

    public void setOthers(List<StarSoft_OthersVo> others) {
        this.others = others;
    }
}
