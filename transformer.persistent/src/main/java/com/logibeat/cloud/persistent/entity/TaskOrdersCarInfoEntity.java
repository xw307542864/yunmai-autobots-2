package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.math.BigDecimal;
import java.util.Date;

public class TaskOrdersCarInfoEntity extends EntitySerialize {
    private String id;

    private String ordercarGuid;

    private Integer consignNum;

    private String goodsSpec;

    private BigDecimal totalFreight;

    private BigDecimal payFreight;

    private BigDecimal topayFreight;

    private BigDecimal paybackFreight;

    private BigDecimal monthlyFreight;

    private BigDecimal freight;

    private BigDecimal payFare;

    private BigDecimal topayFare;

    private BigDecimal paybackFare;

    private BigDecimal monthlyFare;

    private String originatNetworkId;

    private String originatNetworkName;

    private String originatCity;

    private String originatCityCode;

    private String originatAddress;

    private String originatContact;

    private String originatPhone;

    private Double originatLat;

    private Double originatLng;

    private String destinationNetworkId;

    private String destinationNetworkName;

    private String destinationCity;

    private String destinationCityCode;

    private String destinationAddress;

    private String destinationContact;

    private String destinationPhone;

    private Double destinationLat;

    private Double destinationLng;

    private Date createTime;

    private String createPersonId;

    private Date updateTime;

    private String updatePersonId;

    private String entrustEntId;

    private String entrustEntName;

    private String coopEntId;

    private String outDestinationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdercarGuid() {
        return ordercarGuid;
    }

    public void setOrdercarGuid(String ordercarGuid) {
        this.ordercarGuid = ordercarGuid;
    }

    public Integer getConsignNum() {
        return consignNum;
    }

    public void setConsignNum(Integer consignNum) {
        this.consignNum = consignNum;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public BigDecimal getTotalFreight() {
        return totalFreight;
    }

    public void setTotalFreight(BigDecimal totalFreight) {
        this.totalFreight = totalFreight;
    }

    public BigDecimal getPayFreight() {
        return payFreight;
    }

    public void setPayFreight(BigDecimal payFreight) {
        this.payFreight = payFreight;
    }

    public BigDecimal getTopayFreight() {
        return topayFreight;
    }

    public void setTopayFreight(BigDecimal topayFreight) {
        this.topayFreight = topayFreight;
    }

    public BigDecimal getPaybackFreight() {
        return paybackFreight;
    }

    public void setPaybackFreight(BigDecimal paybackFreight) {
        this.paybackFreight = paybackFreight;
    }

    public BigDecimal getMonthlyFreight() {
        return monthlyFreight;
    }

    public void setMonthlyFreight(BigDecimal monthlyFreight) {
        this.monthlyFreight = monthlyFreight;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getPayFare() {
        return payFare;
    }

    public void setPayFare(BigDecimal payFare) {
        this.payFare = payFare;
    }

    public BigDecimal getTopayFare() {
        return topayFare;
    }

    public void setTopayFare(BigDecimal topayFare) {
        this.topayFare = topayFare;
    }

    public BigDecimal getPaybackFare() {
        return paybackFare;
    }

    public void setPaybackFare(BigDecimal paybackFare) {
        this.paybackFare = paybackFare;
    }

    public BigDecimal getMonthlyFare() {
        return monthlyFare;
    }

    public void setMonthlyFare(BigDecimal monthlyFare) {
        this.monthlyFare = monthlyFare;
    }

    public String getOriginatNetworkId() {
        return originatNetworkId;
    }

    public void setOriginatNetworkId(String originatNetworkId) {
        this.originatNetworkId = originatNetworkId;
    }

    public String getOriginatNetworkName() {
        return originatNetworkName;
    }

    public void setOriginatNetworkName(String originatNetworkName) {
        this.originatNetworkName = originatNetworkName;
    }

    public String getOriginatCity() {
        return originatCity;
    }

    public void setOriginatCity(String originatCity) {
        this.originatCity = originatCity;
    }

    public String getOriginatCityCode() {
        return originatCityCode;
    }

    public void setOriginatCityCode(String originatCityCode) {
        this.originatCityCode = originatCityCode;
    }

    public String getOriginatAddress() {
        return originatAddress;
    }

    public void setOriginatAddress(String originatAddress) {
        this.originatAddress = originatAddress;
    }

    public String getOriginatContact() {
        return originatContact;
    }

    public void setOriginatContact(String originatContact) {
        this.originatContact = originatContact;
    }

    public String getOriginatPhone() {
        return originatPhone;
    }

    public void setOriginatPhone(String originatPhone) {
        this.originatPhone = originatPhone;
    }

    public Double getOriginatLat() {
        return originatLat;
    }

    public void setOriginatLat(Double originatLat) {
        this.originatLat = originatLat;
    }

    public Double getOriginatLng() {
        return originatLng;
    }

    public void setOriginatLng(Double originatLng) {
        this.originatLng = originatLng;
    }

    public String getDestinationNetworkId() {
        return destinationNetworkId;
    }

    public void setDestinationNetworkId(String destinationNetworkId) {
        this.destinationNetworkId = destinationNetworkId;
    }

    public String getDestinationNetworkName() {
        return destinationNetworkName;
    }

    public void setDestinationNetworkName(String destinationNetworkName) {
        this.destinationNetworkName = destinationNetworkName;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationCityCode() {
        return destinationCityCode;
    }

    public void setDestinationCityCode(String destinationCityCode) {
        this.destinationCityCode = destinationCityCode;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDestinationContact() {
        return destinationContact;
    }

    public void setDestinationContact(String destinationContact) {
        this.destinationContact = destinationContact;
    }

    public String getDestinationPhone() {
        return destinationPhone;
    }

    public void setDestinationPhone(String destinationPhone) {
        this.destinationPhone = destinationPhone;
    }

    public Double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(Double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public Double getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(Double destinationLng) {
        this.destinationLng = destinationLng;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(String createPersonId) {
        this.createPersonId = createPersonId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePersonId() {
        return updatePersonId;
    }

    public void setUpdatePersonId(String updatePersonId) {
        this.updatePersonId = updatePersonId;
    }

    public String getEntrustEntId() {
        return entrustEntId;
    }

    public void setEntrustEntId(String entrustEntId) {
        this.entrustEntId = entrustEntId;
    }

    public String getEntrustEntName() {
        return entrustEntName;
    }

    public void setEntrustEntName(String entrustEntName) {
        this.entrustEntName = entrustEntName;
    }

    public String getCoopEntId() {
        return coopEntId;
    }

    public void setCoopEntId(String coopEntId) {
        this.coopEntId = coopEntId;
    }

    public String getOutDestinationId() {
        return outDestinationId;
    }

    public void setOutDestinationId(String outDestinationId) {
        this.outDestinationId = outDestinationId;
    }
}