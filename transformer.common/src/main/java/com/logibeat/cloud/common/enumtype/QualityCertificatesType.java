package com.logibeat.cloud.common.enumtype;

public enum QualityCertificatesType {

    ZTCYSZGZS("6501", "渣土车运输资格证书"),

    WHPYSZGZS("6502", "危化品运输资格证书"),

    HNTYSZGZS("6503", "混凝土运输资格证书"),

    QTYSZGZS("6504", "其它车运输资格证书");


    protected String  value;
    protected String  description;

    QualityCertificatesType(String value, String description){
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
