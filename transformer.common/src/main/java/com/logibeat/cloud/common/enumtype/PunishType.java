package com.logibeat.cloud.common.enumtype;

public enum PunishType {




    KouTouJingGao("KouTouJingGao","口头警告"),
    YueTanJiaoYu("YueTanJiaoYu","约谈教育"),
    CanJiaXueXi("CanJiaXueXi","参加学习"),
    CanJiaKaoShi("CanJiaKaoShi","参加考试"),
    FaKuan("FaKuan","罚款"),
    KouFen("KouFen","扣分"),
    KaiChu("KaiChu","开除"),
    TinYun("TinYun","停运"),
    AiStudy("AiStudy","AI学习"),
    Persuasion("Persuasion","交通劝导");



    protected String code;
    protected String description;

    PunishType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setValue(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
