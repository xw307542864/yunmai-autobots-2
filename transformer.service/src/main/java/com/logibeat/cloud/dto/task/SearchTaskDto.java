package com.logibeat.cloud.dto.task;

import com.logibeat.cloud.util.tools.pageMdl.Page;

public class SearchTaskDto extends Page {

    private String personId;

    private String entId;


    private Integer status;


    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
