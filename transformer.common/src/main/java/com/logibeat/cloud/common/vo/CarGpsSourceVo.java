package com.logibeat.cloud.common.vo;

import java.util.List;

/**
 * Created by wilson on 2016/12/29.
 */
public class CarGpsSourceVo {

    private String source;

    private List<CarGpsOperationVo> operationVoList;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<CarGpsOperationVo> getOperationVoList() {
        return operationVoList;
    }

    public void setOperationVoList(List<CarGpsOperationVo> operationVoList) {
        this.operationVoList = operationVoList;
    }
}
