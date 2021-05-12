package com.logibeat.cloud.persistent.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 表 driver_task_cargo
 * 
 * @author wilson
 * @date 2020-04-14
 */
public class DriverTaskCargoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String guid;
	/**  */
	private String taskId;
	/**  */
	private String name;
	/**  */
	private Integer num;
	/**  */
	private Double weight;
	/**  */
	private Double volume;
	/**  */
	private String spec;
	/**  */
	private String material;
	/**  */
	private String pics;
	/**  */
	private String remarks;
	/**  */
	private String takeRemars;
	/** 预留字段 暂时没用 货物暂时只和单据关联 */
	private String taskPointId;

	private Date createTime;

	public void setGuid(String guid) 
	{
		this.guid = guid;
	}

	public String getGuid() 
	{
		return guid;
	}
	public void setTaskId(String taskId) 
	{
		this.taskId = taskId;
	}

	public String getTaskId() 
	{
		return taskId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setNum(Integer num) 
	{
		this.num = num;
	}

	public Integer getNum() 
	{
		return num;
	}
	public void setWeight(Double weight) 
	{
		this.weight = weight;
	}

	public Double getWeight() 
	{
		return weight;
	}
	public void setVolume(Double volume) 
	{
		this.volume = volume;
	}

	public Double getVolume() 
	{
		return volume;
	}
	public void setSpec(String spec) 
	{
		this.spec = spec;
	}

	public String getSpec() 
	{
		return spec;
	}
	public void setMaterial(String material) 
	{
		this.material = material;
	}

	public String getMaterial() 
	{
		return material;
	}
	public void setPics(String pics) 
	{
		this.pics = pics;
	}

	public String getPics() 
	{
		return pics;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}
	public void setTakeRemars(String takeRemars) 
	{
		this.takeRemars = takeRemars;
	}

	public String getTakeRemars() 
	{
		return takeRemars;
	}
	public void setTaskPointId(String taskPointId) 
	{
		this.taskPointId = taskPointId;
	}

	public String getTaskPointId() 
	{
		return taskPointId;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("guid", getGuid())
            .append("taskId", getTaskId())
            .append("name", getName())
            .append("num", getNum())
            .append("weight", getWeight())
            .append("volume", getVolume())
            .append("spec", getSpec())
            .append("material", getMaterial())
            .append("pics", getPics())
            .append("remarks", getRemarks())
            .append("takeRemars", getTakeRemars())
            .append("taskPointId", getTaskPointId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
