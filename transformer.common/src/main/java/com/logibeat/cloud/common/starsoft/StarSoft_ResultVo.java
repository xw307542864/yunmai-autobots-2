package com.logibeat.cloud.common.starsoft;


public class StarSoft_ResultVo<T> {

    private T Data;

    private boolean Suc;

    private String Info;


    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public boolean isSuc() {
        return Suc;
    }

    public void setSuc(boolean suc) {
        Suc = suc;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }


}

