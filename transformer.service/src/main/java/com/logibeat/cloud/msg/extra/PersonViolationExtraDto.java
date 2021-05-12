package com.logibeat.cloud.msg.extra;

public class PersonViolationExtraDto {


    /**
     * 违规类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
