package com.logibeat.cloud.vo;


import com.logibeat.cloud.common.vo.ViolationVo;
import com.logibeat.cloud.vo.startsoft.StarSoft_OthersVo;

import java.util.List;

public class AssisInfoVo {

    private List<ViolationVo> violationList;

   // private List<StarSoft_SafeStudyVo> studyList;

    private List<StarSoft_OthersVo> otherList;


    public List<ViolationVo> getViolationList() {
        return violationList;
    }

    public void setViolationList(List<ViolationVo> violationList) {
        this.violationList = violationList;
    }

//    public List<StarSoft_SafeStudyVo> getStudyList() {
//        return studyList;
//    }
//
//    public void setStudyList(List<StarSoft_SafeStudyVo> studyList) {
//        this.studyList = studyList;
//    }

    public List<StarSoft_OthersVo> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<StarSoft_OthersVo> otherList) {
        this.otherList = otherList;
    }
}
