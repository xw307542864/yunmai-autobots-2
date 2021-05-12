package com.logibeat.cloud.dto.task;

public class DriverTaskCargoDto {

    /**
     * 货物名称
     */
    private String name;

    /**
     * 货物数量
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
     * 装/卸 货点（后期可能会有需求 预留字段）
     */
    private String taskPointId;


    /**
     * 备注
     */
    private String remark;

    /**
     * 提货备注
     */
    private String takeRemark;

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

    public String getTaskPointId() {
        return taskPointId;
    }

    public void setTaskPointId(String taskPointId) {
        this.taskPointId = taskPointId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTakeRemark() {
        return takeRemark;
    }

    public void setTakeRemark(String takeRemark) {
        this.takeRemark = takeRemark;
    }
}
