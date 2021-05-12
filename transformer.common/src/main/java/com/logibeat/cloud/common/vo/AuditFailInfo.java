package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;

public class AuditFailInfo {

    private String auditMessage;

    private Timestamp auditTime;


    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }

    public Timestamp getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }
}
