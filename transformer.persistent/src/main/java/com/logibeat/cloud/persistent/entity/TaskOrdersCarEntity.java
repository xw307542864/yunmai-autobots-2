package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;

public class TaskOrdersCarEntity extends EntitySerialize {
    private String id;

    private String ordersCID;

    private Integer yyyymmdd;

    private String carID;

    private Byte isSelfCar;

    private String firstDriverPersonID;

    private String secondDriverPersonID;

    private Byte isReceiv;

    private Byte isRefuse;

    private String receivPersonID;

    private Timestamp receivTime;

    private String refusePersonID;

    private Timestamp refuseTime;

    private Timestamp addTime;

    private Timestamp editTime;

    private String editPersonID;

    private Byte isDelete;

    private String entrustOrdersCID;

    private String entrustPersonID;

    private Timestamp entrustTime;

    private Byte isCancleEntrust;

    private String cancleEntrustPersonID;

    private Timestamp cancleEntrustTime;

    private String entrustEntid;

    private Byte state;

    private Byte isShareCar;

    private String shareEntId;

    private String originalcid;

    private Float freightFee;

    private String shippingManifestsId;

    private Integer ordersType;

    private Integer ordersStatus;

    private Timestamp planLeaveTime;

    private Timestamp actualLeaveTime;

    private Timestamp planArriveTime;

    private Timestamp actualArriveTime;

    private Double actualLeaveLat;

    private Double actualLeaveLng;

    private Double actualArriveLat;

    private Double actualArriveLng;

    private Double planMileage;

    private Integer coopType;

    private String coopEntId;

    private Byte isAutoDepart;

    private Byte isAutoArrive;

    private Byte isRead;

    private Integer taskCarType;

    private String outKeyId;

    private Byte isTimeOut;

    private Integer timeOutDuration;

    private String code;

    private String coopShippingManifestsId;

    private String remark;

    private String userJson;

    private Double actualMileage;

    private Integer actualDrivetime;

    private Timestamp finishTime;

    private Integer effectiveTime;

    private String plateNumber;

    private String firstPersonName;

    private String firstPersonMobile;

    private String secondPersonName;

    private String secondPersonMobile;

    private String orgId;

    private Integer exceptionSendTask;

    private Integer exceptionArriveTask;

    //派送单id
    private String deliveryOrderId;
    //揽收单id
    private String collectOrderId;

    //配载单
    private String shippingManifestsNumber;

    private Integer queueNumber; //排队顺序

    public Integer getExceptionSendTask() {
        return exceptionSendTask;
    }

    public void setExceptionSendTask(Integer exceptionSendTask) {
        this.exceptionSendTask = exceptionSendTask;
    }

    public Integer getExceptionArriveTask() {
        return exceptionArriveTask;
    }

    public void setExceptionArriveTask(Integer exceptionArriveTask) {
        this.exceptionArriveTask = exceptionArriveTask;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdersCID() {
        return ordersCID;
    }

    public void setOrdersCID(String ordersCID) {
        this.ordersCID = ordersCID;
    }

    public Integer getYyyymmdd() {
        return yyyymmdd;
    }

    public void setYyyymmdd(Integer yyyymmdd) {
        this.yyyymmdd = yyyymmdd;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Byte getIsSelfCar() {
        return isSelfCar;
    }

    public void setIsSelfCar(Byte isSelfCar) {
        this.isSelfCar = isSelfCar;
    }

    public String getFirstDriverPersonID() {
        return firstDriverPersonID;
    }

    public void setFirstDriverPersonID(String firstDriverPersonID) {
        this.firstDriverPersonID = firstDriverPersonID;
    }

    public String getSecondDriverPersonID() {
        return secondDriverPersonID;
    }

    public void setSecondDriverPersonID(String secondDriverPersonID) {
        this.secondDriverPersonID = secondDriverPersonID;
    }

    public Byte getIsReceiv() {
        return isReceiv;
    }

    public void setIsReceiv(Byte isReceiv) {
        this.isReceiv = isReceiv;
    }

    public Byte getIsRefuse() {
        return isRefuse;
    }

    public void setIsRefuse(Byte isRefuse) {
        this.isRefuse = isRefuse;
    }

    public String getReceivPersonID() {
        return receivPersonID;
    }

    public void setReceivPersonID(String receivPersonID) {
        this.receivPersonID = receivPersonID;
    }

    public Timestamp getReceivTime() {
        return receivTime;
    }

    public void setReceivTime(Timestamp receivTime) {
        this.receivTime = receivTime;
    }

    public String getRefusePersonID() {
        return refusePersonID;
    }

    public void setRefusePersonID(String refusePersonID) {
        this.refusePersonID = refusePersonID;
    }

    public Timestamp getRefuseTime() {
        return refuseTime;
    }

    public void setRefuseTime(Timestamp refuseTime) {
        this.refuseTime = refuseTime;
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

    public String getEntrustOrdersCID() {
        return entrustOrdersCID;
    }

    public void setEntrustOrdersCID(String entrustOrdersCID) {
        this.entrustOrdersCID = entrustOrdersCID;
    }

    public String getEntrustPersonID() {
        return entrustPersonID;
    }

    public void setEntrustPersonID(String entrustPersonID) {
        this.entrustPersonID = entrustPersonID;
    }

    public Timestamp getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Timestamp entrustTime) {
        this.entrustTime = entrustTime;
    }

    public Byte getIsCancleEntrust() {
        return isCancleEntrust;
    }

    public void setIsCancleEntrust(Byte isCancleEntrust) {
        this.isCancleEntrust = isCancleEntrust;
    }

    public String getCancleEntrustPersonID() {
        return cancleEntrustPersonID;
    }

    public void setCancleEntrustPersonID(String cancleEntrustPersonID) {
        this.cancleEntrustPersonID = cancleEntrustPersonID;
    }

    public Timestamp getCancleEntrustTime() {
        return cancleEntrustTime;
    }

    public void setCancleEntrustTime(Timestamp cancleEntrustTime) {
        this.cancleEntrustTime = cancleEntrustTime;
    }

    public String getEntrustEntid() {
        return entrustEntid;
    }

    public void setEntrustEntid(String entrustEntid) {
        this.entrustEntid = entrustEntid;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getIsShareCar() {
        return isShareCar;
    }

    public void setIsShareCar(Byte isShareCar) {
        this.isShareCar = isShareCar;
    }

    public String getShareEntId() {
        return shareEntId;
    }

    public void setShareEntId(String shareEntId) {
        this.shareEntId = shareEntId;
    }

    public String getOriginalcid() {
        return originalcid;
    }

    public void setOriginalcid(String originalcid) {
        this.originalcid = originalcid;
    }

    public Float getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(Float freightFee) {
        this.freightFee = freightFee;
    }

    public String getShippingManifestsId() {
        return shippingManifestsId;
    }

    public void setShippingManifestsId(String shippingManifestsId) {
        this.shippingManifestsId = shippingManifestsId;
    }

    public Integer getOrdersType() {
        return ordersType;
    }

    public void setOrdersType(Integer ordersType) {
        this.ordersType = ordersType;
    }

    public Integer getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(Integer ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public Timestamp getPlanLeaveTime() {
        return planLeaveTime;
    }

    public void setPlanLeaveTime(Timestamp planLeaveTime) {
        this.planLeaveTime = planLeaveTime;
    }

    public Timestamp getActualLeaveTime() {
        return actualLeaveTime;
    }

    public void setActualLeaveTime(Timestamp actualLeaveTime) {
        this.actualLeaveTime = actualLeaveTime;
    }

    public Timestamp getPlanArriveTime() {
        return planArriveTime;
    }

    public void setPlanArriveTime(Timestamp planArriveTime) {
        this.planArriveTime = planArriveTime;
    }

    public Timestamp getActualArriveTime() {
        return actualArriveTime;
    }

    public void setActualArriveTime(Timestamp actualArriveTime) {
        this.actualArriveTime = actualArriveTime;
    }

    public Double getPlanMileage() {
        return planMileage;
    }

    public void setPlanMileage(Double planMileage) {
        this.planMileage = planMileage;
    }

    public Integer getCoopType() {
        return coopType;
    }

    public void setCoopType(Integer coopType) {
        this.coopType = coopType;
    }

    public String getCoopEntId() {
        return coopEntId;
    }

    public void setCoopEntId(String coopEntId) {
        this.coopEntId = coopEntId;
    }

    public Byte getIsAutoDepart() {
        return isAutoDepart;
    }

    public void setIsAutoDepart(Byte isAutoDepart) {
        this.isAutoDepart = isAutoDepart;
    }

    public Byte getIsAutoArrive() {
        return isAutoArrive;
    }

    public void setIsAutoArrive(Byte isAutoArrive) {
        this.isAutoArrive = isAutoArrive;
    }

    public Integer getTaskCarType() {
        return taskCarType;
    }

    public void setTaskCarType(Integer taskCarType) {
        this.taskCarType = taskCarType;
    }

    public String getOutKeyId() {
        return outKeyId;
    }

    public void setOutKeyId(String outKeyId) {
        this.outKeyId = outKeyId;
    }

    public Byte getIsTimeOut() {
        return isTimeOut;
    }

    public void setIsTimeOut(Byte isTimeOut) {
        this.isTimeOut = isTimeOut;
    }

    public Integer getTimeOutDuration() {
        return timeOutDuration;
    }

    public void setTimeOutDuration(Integer timeOutDuration) {
        this.timeOutDuration = timeOutDuration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCoopShippingManifestsId() {
        return coopShippingManifestsId;
    }

    public void setCoopShippingManifestsId(String coopShippingManifestsId) {
        this.coopShippingManifestsId = coopShippingManifestsId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserJson() {
        return userJson;
    }

    public void setUserJson(String userJson) {
        this.userJson = userJson;
    }

    public Double getActualMileage() {
        return actualMileage;
    }

    public void setActualMileage(Double actualMileage) {
        this.actualMileage = actualMileage;
    }

    public Integer getActualDrivetime() {
        return actualDrivetime;
    }

    public void setActualDrivetime(Integer actualDrivetime) {
        this.actualDrivetime = actualDrivetime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Integer effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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

    public Double getActualLeaveLat() {
        return actualLeaveLat;
    }

    public void setActualLeaveLat(Double actualLeaveLat) {
        this.actualLeaveLat = actualLeaveLat;
    }

    public Double getActualLeaveLng() {
        return actualLeaveLng;
    }

    public void setActualLeaveLng(Double actualLeaveLng) {
        this.actualLeaveLng = actualLeaveLng;
    }

    public Double getActualArriveLat() {
        return actualArriveLat;
    }

    public void setActualArriveLat(Double actualArriveLat) {
        this.actualArriveLat = actualArriveLat;
    }

    public Double getActualArriveLng() {
        return actualArriveLng;
    }

    public void setActualArriveLng(Double actualArriveLng) {
        this.actualArriveLng = actualArriveLng;
    }

    public String getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public String getCollectOrderId() {
        return collectOrderId;
    }

    public void setCollectOrderId(String collectOrderId) {
        this.collectOrderId = collectOrderId;
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public String getShippingManifestsNumber() {
        return shippingManifestsNumber;
    }

    public void setShippingManifestsNumber(String shippingManifestsNumber) {
        this.shippingManifestsNumber = shippingManifestsNumber;
    }


    public Integer getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(Integer queueNumber) {
        this.queueNumber = queueNumber;
    }
}