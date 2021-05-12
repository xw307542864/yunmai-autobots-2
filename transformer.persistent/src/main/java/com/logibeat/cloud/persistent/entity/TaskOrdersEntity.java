package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;

public class TaskOrdersEntity extends EntitySerialize {
    private String ordersCID;

    private String entID;

    private Integer yyyymmdd;

    private Byte isFirstOrders;

    private Byte isEndOrders;

    private Byte isGoodsOrders;

    private Byte isCarrierOrders;

    private String carrierPersonId;

    private String carrierEntID;

    private String carrierInfo;

    private Byte isCarOrders;

    private String entrustPersonId;

    private Byte iscancleEntrust;

    private Byte isEntrustOrders;

    private Integer entrustType;

    private Timestamp entrustTime;

    private String entrustEntID;

    private String entrustInfo;

    private Byte isSplit;

    private String parentOrdersCID;

    private String name;

    private String code;

    private Byte isUpStream;

    private Byte isDownStream;

    private String startAreaGUID;

    private String endAreaGUID;

    private Timestamp startAreaPlanLeavTime;

    private Timestamp endAreaPlanArriveTime;

    private Timestamp startAreaActualLeavTime;

    private Timestamp endAreaActualArriveTime;

    private Integer statute;

    private Double volume;

    private Double weight;

    private String goodsRemark;

    private String ordersRemark;

    private String remark;

    private Integer ordersStatus;

    private Byte isCancle;

    private Timestamp addTime;

    private Timestamp editTime;

    private String editPersonID;

    private Byte isDelete;

    private String createPersonId;

    private String createPersonInfo;

    private String originalcid;

    private Integer entrustStatus;

    private Long createorder;

    private Double lng;

    private Double lat;

    private Timestamp gpstime;

    private Byte isRead;

    private Integer ordersType;

    private Double actualMileage;

    private Long actualDrivetime;

    private Integer duration;

    private Byte isManual;

    private Integer expectsMileage;

    private String startAreaName;

    private String endAreaName;

    private String startAreaCode;

    private String endAreaCode;

    private Timestamp finishTime;

    private String ownOrderId;

    private String ownType;

    private Integer isAuto;

    private String lineId;

    private Byte isAllowChange;

    private Byte isReport;

    private String actualEndAreaCode;

    private Float freightFee;

    private Float unloadingFee;

    private Byte isException;

    private Byte isNeedSign;

    private Byte isSign;

    private String signPersonId;

    private String isPrint;

    private Double muckQuantities;

    private String carId;

    private String plateNumber;

    private String startRegionCode;

    private String endRegionCode;

    private String firstPersonId;

    private String firstPersonName;

    private String firstPersonMobile;

    private String secondPersonId;

    private String secondPersonName;

    private String secondPersonMobile;

    public String getOrdersCID() {
        return ordersCID;
    }

    public void setOrdersCID(String ordersCID) {
        this.ordersCID = ordersCID;
    }

    public String getEntID() {
        return entID;
    }

    public void setEntID(String entID) {
        this.entID = entID;
    }

    public Integer getYyyymmdd() {
        return yyyymmdd;
    }

    public void setYyyymmdd(Integer yyyymmdd) {
        this.yyyymmdd = yyyymmdd;
    }

    public Byte getIsFirstOrders() {
        return isFirstOrders;
    }

    public void setIsFirstOrders(Byte isFirstOrders) {
        this.isFirstOrders = isFirstOrders;
    }

    public Byte getIsEndOrders() {
        return isEndOrders;
    }

    public void setIsEndOrders(Byte isEndOrders) {
        this.isEndOrders = isEndOrders;
    }

    public Byte getIsGoodsOrders() {
        return isGoodsOrders;
    }

    public void setIsGoodsOrders(Byte isGoodsOrders) {
        this.isGoodsOrders = isGoodsOrders;
    }

    public Byte getIsCarrierOrders() {
        return isCarrierOrders;
    }

    public void setIsCarrierOrders(Byte isCarrierOrders) {
        this.isCarrierOrders = isCarrierOrders;
    }

    public String getCarrierPersonId() {
        return carrierPersonId;
    }

    public void setCarrierPersonId(String carrierPersonId) {
        this.carrierPersonId = carrierPersonId;
    }

    public String getCarrierEntID() {
        return carrierEntID;
    }

    public void setCarrierEntID(String carrierEntID) {
        this.carrierEntID = carrierEntID;
    }

    public Byte getIsCarOrders() {
        return isCarOrders;
    }

    public void setIsCarOrders(Byte isCarOrders) {
        this.isCarOrders = isCarOrders;
    }

    public String getEntrustPersonId() {
        return entrustPersonId;
    }

    public void setEntrustPersonId(String entrustPersonId) {
        this.entrustPersonId = entrustPersonId;
    }

    public Byte getIscancleEntrust() {
        return iscancleEntrust;
    }

    public void setIscancleEntrust(Byte iscancleEntrust) {
        this.iscancleEntrust = iscancleEntrust;
    }

    public Byte getIsEntrustOrders() {
        return isEntrustOrders;
    }

    public void setIsEntrustOrders(Byte isEntrustOrders) {
        this.isEntrustOrders = isEntrustOrders;
    }

    public Integer getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(Integer entrustType) {
        this.entrustType = entrustType;
    }

    public Timestamp getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Timestamp entrustTime) {
        this.entrustTime = entrustTime;
    }

    public String getEntrustEntID() {
        return entrustEntID;
    }

    public void setEntrustEntID(String entrustEntID) {
        this.entrustEntID = entrustEntID;
    }

    public Byte getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(Byte isSplit) {
        this.isSplit = isSplit;
    }

    public String getParentOrdersCID() {
        return parentOrdersCID;
    }

    public void setParentOrdersCID(String parentOrdersCID) {
        this.parentOrdersCID = parentOrdersCID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getIsUpStream() {
        return isUpStream;
    }

    public void setIsUpStream(Byte isUpStream) {
        this.isUpStream = isUpStream;
    }

    public Byte getIsDownStream() {
        return isDownStream;
    }

    public void setIsDownStream(Byte isDownStream) {
        this.isDownStream = isDownStream;
    }

    public String getStartAreaGUID() {
        return startAreaGUID;
    }

    public void setStartAreaGUID(String startAreaGUID) {
        this.startAreaGUID = startAreaGUID;
    }

    public String getEndAreaGUID() {
        return endAreaGUID;
    }

    public void setEndAreaGUID(String endAreaGUID) {
        this.endAreaGUID = endAreaGUID;
    }

    public Timestamp getStartAreaPlanLeavTime() {
        return startAreaPlanLeavTime;
    }

    public void setStartAreaPlanLeavTime(Timestamp startAreaPlanLeavTime) {
        this.startAreaPlanLeavTime = startAreaPlanLeavTime;
    }

    public Timestamp getEndAreaPlanArriveTime() {
        return endAreaPlanArriveTime;
    }

    public void setEndAreaPlanArriveTime(Timestamp endAreaPlanArriveTime) {
        this.endAreaPlanArriveTime = endAreaPlanArriveTime;
    }

    public Timestamp getStartAreaActualLeavTime() {
        return startAreaActualLeavTime;
    }

    public void setStartAreaActualLeavTime(Timestamp startAreaActualLeavTime) {
        this.startAreaActualLeavTime = startAreaActualLeavTime;
    }

    public Timestamp getEndAreaActualArriveTime() {
        return endAreaActualArriveTime;
    }

    public void setEndAreaActualArriveTime(Timestamp endAreaActualArriveTime) {
        this.endAreaActualArriveTime = endAreaActualArriveTime;
    }

    public Integer getStatute() {
        return statute;
    }

    public void setStatute(Integer statute) {
        this.statute = statute;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark;
    }

    public String getOrdersRemark() {
        return ordersRemark;
    }

    public void setOrdersRemark(String ordersRemark) {
        this.ordersRemark = ordersRemark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(Integer ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public Byte getIsCancle() {
        return isCancle;
    }

    public void setIsCancle(Byte isCancle) {
        this.isCancle = isCancle;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public String getEditPersonID() {
        return editPersonID;
    }

    public void setEditPersonID(String editPersonID) {
        this.editPersonID = editPersonID;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    public String getCreatePersonInfo() {
        return createPersonInfo;
    }

    public void setCreatePersonInfo(String createPersonInfo) {
        this.createPersonInfo = createPersonInfo;
    }

    public String getOriginalcid() {
        return originalcid;
    }

    public void setOriginalcid(String originalcid) {
        this.originalcid = originalcid;
    }

    public Integer getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(Integer entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public Long getCreateorder() {
        return createorder;
    }

    public void setCreateorder(Long createorder) {
        this.createorder = createorder;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Timestamp getGpstime() {
        return gpstime;
    }

    public void setGpstime(Timestamp gpstime) {
        this.gpstime = gpstime;
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public Integer getOrdersType() {
        return ordersType;
    }

    public void setOrdersType(Integer ordersType) {
        this.ordersType = ordersType;
    }

    public Double getActualMileage() {
        return actualMileage;
    }

    public void setActualMileage(Double actualMileage) {
        this.actualMileage = actualMileage;
    }

    public Long getActualDrivetime() {
        return actualDrivetime;
    }

    public void setActualDrivetime(Long actualDrivetime) {
        this.actualDrivetime = actualDrivetime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Byte getIsManual() {
        return isManual;
    }

    public void setIsManual(Byte isManual) {
        this.isManual = isManual;
    }

    public Integer getExpectsMileage() {
        return expectsMileage;
    }

    public void setExpectsMileage(Integer expectsMileage) {
        this.expectsMileage = expectsMileage;
    }

    public String getStartAreaName() {
        return startAreaName;
    }

    public void setStartAreaName(String startAreaName) {
        this.startAreaName = startAreaName;
    }

    public String getEndAreaName() {
        return endAreaName;
    }

    public void setEndAreaName(String endAreaName) {
        this.endAreaName = endAreaName;
    }

    public String getStartAreaCode() {
        return startAreaCode;
    }

    public void setStartAreaCode(String startAreaCode) {
        this.startAreaCode = startAreaCode;
    }

    public String getEndAreaCode() {
        return endAreaCode;
    }

    public void setEndAreaCode(String endAreaCode) {
        this.endAreaCode = endAreaCode;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public String getOwnOrderId() {
        return ownOrderId;
    }

    public void setOwnOrderId(String ownOrderId) {
        this.ownOrderId = ownOrderId;
    }

    public String getOwnType() {
        return ownType;
    }

    public void setOwnType(String ownType) {
        this.ownType = ownType;
    }

    public Integer getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(Integer isAuto) {
        this.isAuto = isAuto;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public Byte getIsAllowChange() {
        return isAllowChange;
    }

    public void setIsAllowChange(Byte isAllowChange) {
        this.isAllowChange = isAllowChange;
    }

    public Byte getIsReport() {
        return isReport;
    }

    public void setIsReport(Byte isReport) {
        this.isReport = isReport;
    }

    public String getActualEndAreaCode() {
        return actualEndAreaCode;
    }

    public void setActualEndAreaCode(String actualEndAreaCode) {
        this.actualEndAreaCode = actualEndAreaCode;
    }

    public Float getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(Float freightFee) {
        this.freightFee = freightFee;
    }

    public Float getUnloadingFee() {
        return unloadingFee;
    }

    public void setUnloadingFee(Float unloadingFee) {
        this.unloadingFee = unloadingFee;
    }

    public Byte getIsException() {
        return isException;
    }

    public void setIsException(Byte isException) {
        this.isException = isException;
    }

    public Byte getIsNeedSign() {
        return isNeedSign;
    }

    public void setIsNeedSign(Byte isNeedSign) {
        this.isNeedSign = isNeedSign;
    }

    public Byte getIsSign() {
        return isSign;
    }

    public void setIsSign(Byte isSign) {
        this.isSign = isSign;
    }

    public String getSignPersonId() {
        return signPersonId;
    }

    public void setSignPersonId(String signPersonId) {
        this.signPersonId = signPersonId;
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    public Double getMuckQuantities() {
        return muckQuantities;
    }

    public void setMuckQuantities(Double muckQuantities) {
        this.muckQuantities = muckQuantities;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStartRegionCode() {
        return startRegionCode;
    }

    public void setStartRegionCode(String startRegionCode) {
        this.startRegionCode = startRegionCode;
    }

    public String getEndRegionCode() {
        return endRegionCode;
    }

    public void setEndRegionCode(String endRegionCode) {
        this.endRegionCode = endRegionCode;
    }

    public String getFirstPersonId() {
        return firstPersonId;
    }

    public void setFirstPersonId(String firstPersonId) {
        this.firstPersonId = firstPersonId;
    }

    public String getFirstPersonName() {
        return firstPersonName;
    }

    public void setFirstPersonName(String firstPersonName) {
        this.firstPersonName = firstPersonName;
    }

    public String getFirstPersonMobile() {
        return firstPersonMobile;
    }

    public void setFirstPersonMobile(String firstPersonMobile) {
        this.firstPersonMobile = firstPersonMobile;
    }

    public String getSecondPersonId() {
        return secondPersonId;
    }

    public void setSecondPersonId(String secondPersonId) {
        this.secondPersonId = secondPersonId;
    }

    public String getSecondPersonName() {
        return secondPersonName;
    }

    public void setSecondPersonName(String secondPersonName) {
        this.secondPersonName = secondPersonName;
    }

    public String getSecondPersonMobile() {
        return secondPersonMobile;
    }

    public void setSecondPersonMobile(String secondPersonMobile) {
        this.secondPersonMobile = secondPersonMobile;
    }

    public String getCarrierInfo() {
        return carrierInfo;
    }

    public void setCarrierInfo(String carrierInfo) {
        this.carrierInfo = carrierInfo;
    }

    public String getEntrustInfo() {
        return entrustInfo;
    }

    public void setEntrustInfo(String entrustInfo) {
        this.entrustInfo = entrustInfo;
    }
}