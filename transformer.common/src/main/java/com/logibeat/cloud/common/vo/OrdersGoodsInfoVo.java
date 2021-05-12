package com.logibeat.cloud.common.vo;

/**
 * 
* @ClassName: OrdersGoodsInfoVo 
* @Description: 订单货物信息
* @author sean 
* @date 2016年1月11日 上午9:50:19 
* @version 1.0
 */
public class OrdersGoodsInfoVo {
    
    private String GoodsTypeDictGUID;  //货物类型  
    private String Name;        //货物名称  
    private Double Volume;    //货物体积（单位立方米）  
    private Double Weight;    //货物重量(单位kg)  
    private String TypeName;  //货物类型名称
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
    
}
