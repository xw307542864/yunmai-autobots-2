package com.logibeat.cloud.core.dto;

import java.util.List;

public class ViolationInfoDto {


    private String type;


    private String value;


    private Integer number;

    private List<ViolationDetail> violationDetailList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<ViolationDetail> getViolationDetailList() {
        return violationDetailList;
    }

    public void setViolationDetailList(List<ViolationDetail> violationDetailList) {
        this.violationDetailList = violationDetailList;
    }
}
