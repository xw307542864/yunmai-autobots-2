package com.logibeat.cloud.common.enumtype;

public enum  StarSoftApiType {

    APPEAL(50,"司机违规申诉","api/Alarm/ViolationAppeal"),
    SUREVIOLATION(51,"司机违规确认","api/Alarm/ViolationConfirm"),
    SCAN(52,"司机违规已读","api/Alarm/ViolationRead"),
    DRIVERJOINENT(53,"司机确认加入","api/Driver/DriverConfirm"),
    GETSAFESCORESET(54,"获取安全分设置","/AddSuggestion"),
    GETDRIVERSCORERECORD(55,"获取安全分流水","api/Alarm/GetDriverScoreRecord"),
    GETDRIVERSCORE(56,"获取安全分","api/Alarm/GetDriverScore"),
    GETSAFECODE(57,"获取司机安全码","api/SafeCode/GetSafeCode"),
    GETASSISINFO(58,"获取安全码详情","api/SafeCode/GetAssistInfo"),
    GET_CAR_SAFE_CODE(59,"获取车辆安全码","api/SafeCode/GetCarSafeCode"),
    GET_CAR_SAFE_CODE_REASON(60,"获取车辆安全码异常原因","api/SafeCode/GetCarSafeCodeRecord"),
    VIOLATION_PERSUASION(61,"违规劝导信息反馈","api/Alarm/ViolationPersuasion"),
    VIOLATION_RECEIPT(62,"违规劝导回执单","api/Alarm/ViolationReceipt");


    StarSoftApiType(Integer type,String description,String url){

        this.type = type;
        this.description = description;
        this.url = url;
    }

    private String url;

    private String description;

    private Integer type;

    public String getUrl() {
        return  url;
    }
    public Integer getType() {
        return  type;
    }
}
