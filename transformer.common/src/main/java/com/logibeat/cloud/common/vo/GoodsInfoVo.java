package com.logibeat.cloud.common.vo;

public class GoodsInfoVo {
	private String GoodsTypeDictGUID; // 货物类型
	private String Name; // 货物名称
	private Double Volume; // 货物体积（单位立方米）
	private Double Weight; // 货物重量(单位kg)
	private String TypeName; // 货物类型名称
	private int Number = 1; // 货物件数
	private String recievesName;//收件人/收货单位
	

	public String getGoodsTypeDictGUID() {
		return GoodsTypeDictGUID;
	}

	public void setGoodsTypeDictGUID(String goodsTypeDictGUID) {
		GoodsTypeDictGUID = goodsTypeDictGUID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Double getVolume() {
		return Volume;
	}

	public void setVolume(Double volume) {
		Volume = volume;
	}

	public Double getWeight() {
		return Weight;
	}

	public void setWeight(Double weight) {
		Weight = weight;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public String getRecievesName() {
		return recievesName;
	}

	public void setRecievesName(String recievesName) {
		this.recievesName = recievesName;
	}

	@Override
	public String toString() {
		return "GoodsInfo [GoodsTypeDictGUID=" + GoodsTypeDictGUID + ", Name=" + Name + ", Volume=" + Volume
				+ ", Weight=" + Weight + ", TypeName=" + TypeName + "]";
	}
}
