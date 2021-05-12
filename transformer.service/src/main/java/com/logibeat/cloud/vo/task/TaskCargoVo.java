package com.logibeat.cloud.vo.task;

public class TaskCargoVo {

    /**
     * 名称
     */
    private String name;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 重量
     */
    private Double weight;

    /**
     * 体积
     */
    private Double volume;


    /**
     * 规格
     */
    private String spec;

    /**
     * 材质
     */
    private String material;

    /**
     * 图片
     */
    private String pics;


    /**
     * 货物备注
     */
    private String remarks;


    /**
     * 提货备注
     */
    private String takeRemarks;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTakeRemarks() {
        return takeRemarks;
    }

    public void setTakeRemarks(String takeRemarks) {
        this.takeRemarks = takeRemarks;
    }
}
