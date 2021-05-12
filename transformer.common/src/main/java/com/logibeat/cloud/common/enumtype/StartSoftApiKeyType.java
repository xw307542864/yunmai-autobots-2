package com.logibeat.cloud.common.enumtype;


public enum StartSoftApiKeyType {






    V8(8,"星软V8系", "https://v5.gpskk.com/rest/","0","153677","05265573-8094-488E-A7C4-AE8BA27D05A7"),


    GOV(2,"星软政协系统","http://gov.xingruan.net/mc6/","0","110002","4DC94A60-0EF8-4CDB-B782-458EDA2210CA");





    private Integer code;

    private String description;

    private String rootUrl;

    private String uid;

    private String cid;

    private String apiKey;

    StartSoftApiKeyType(Integer code, String description, String rootUrl, String uid, String cid, String apiKey) {
        this.code = code;
        this.description = description;
        this.rootUrl = rootUrl;
        this.uid = uid;
        this.cid = cid;
        this.apiKey = apiKey;
    }


    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public String getUid() {
        return uid;
    }

    public String getCid() {
        return cid;
    }

    public String getApiKey() {
        return apiKey;
    }




}
