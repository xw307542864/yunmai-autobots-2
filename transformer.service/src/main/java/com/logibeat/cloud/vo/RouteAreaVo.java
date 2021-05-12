package com.logibeat.cloud.vo;



import com.logibeat.cloud.persistent.entity.TaskOrdersAreaEntity;

import java.util.List;

/**
 * Created by wilson on 2017/6/17.
 */
public class RouteAreaVo {

    private String startAreaGUID;

    private String startAreaName;

    private String startRegionCode;

    private String startNetworkCode;

    private String endAreaGUID;

    private String endAreaName;

    private String endRegionCode;

    private String endNetworkCode;

    private List<TaskOrdersAreaEntity> areaList;

    public String getStartAreaGUID() {
        return startAreaGUID;
    }

    public void setStartAreaGUID(String startAreaGUID) {
        this.startAreaGUID = startAreaGUID;
    }

    public String getStartAreaName() {
        return startAreaName;
    }

    public void setStartAreaName(String startAreaName) {
        this.startAreaName = startAreaName;
    }

    public String getStartRegionCode() {
        return startRegionCode;
    }

    public void setStartRegionCode(String startRegionCode) {
        this.startRegionCode = startRegionCode;
    }

    public String getStartNetworkCode() {
        return startNetworkCode;
    }

    public void setStartNetworkCode(String startNetworkCode) {
        this.startNetworkCode = startNetworkCode;
    }

    public String getEndAreaGUID() {
        return endAreaGUID;
    }

    public void setEndAreaGUID(String endAreaGUID) {
        this.endAreaGUID = endAreaGUID;
    }

    public String getEndAreaName() {
        return endAreaName;
    }

    public void setEndAreaName(String endAreaName) {
        this.endAreaName = endAreaName;
    }

    public String getEndRegionCode() {
        return endRegionCode;
    }

    public void setEndRegionCode(String endRegionCode) {
        this.endRegionCode = endRegionCode;
    }

    public String getEndNetworkCode() {
        return endNetworkCode;
    }

    public void setEndNetworkCode(String endNetworkCode) {
        this.endNetworkCode = endNetworkCode;
    }

    public List<TaskOrdersAreaEntity> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<TaskOrdersAreaEntity> areaList) {
        this.areaList = areaList;
    }
}
