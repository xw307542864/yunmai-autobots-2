package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.util.Date;

public class NetworkEntity extends EntitySerialize {
    private String guid;

    private String entid;

    private String networkCode;

    private String networkName;

    private String networkNamePinyin;

    private String networkNamePinyinHead;

    private String networkContactUser;

    private String networkContactPhone;

    private String networkAddress;

    private String networkAreainfo;

    private Double lng;

    private Double lat;

    private String remark;

    private Date addTime;

    private Date editTime;

    private Long editPersonID;

    private Byte isDelete;

    private Integer type;

    private String regionCode;

    private String ownNetworkId;

    private String ownEntId;

    private String ownType;

    private Double muckQuantities;

    private Double muckComplement;

    private String muckTaskPersonID;

    private Byte muckIsNeedSigner;

    private Byte muckState;

    private Integer networkRange;

    private String province;

    private String city;

    private String district;

    private Integer isOpen;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getEntid() {
        return entid;
    }

    public void setEntid(String entid) {
        this.entid = entid;
    }

    public String getNetworkCode() {
        return networkCode;
    }

    public void setNetworkCode(String networkCode) {
        this.networkCode = networkCode;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getNetworkNamePinyin() {
        return networkNamePinyin;
    }

    public void setNetworkNamePinyin(String networkNamePinyin) {
        this.networkNamePinyin = networkNamePinyin;
    }

    public String getNetworkNamePinyinHead() {
        return networkNamePinyinHead;
    }

    public void setNetworkNamePinyinHead(String networkNamePinyinHead) {
        this.networkNamePinyinHead = networkNamePinyinHead;
    }

    public String getNetworkContactUser() {
        return networkContactUser;
    }

    public void setNetworkContactUser(String networkContactUser) {
        this.networkContactUser = networkContactUser;
    }

    public String getNetworkContactPhone() {
        return networkContactPhone;
    }

    public void setNetworkContactPhone(String networkContactPhone) {
        this.networkContactPhone = networkContactPhone;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress;
    }

    public String getNetworkAreainfo() {
        return networkAreainfo;
    }

    public void setNetworkAreainfo(String networkAreainfo) {
        this.networkAreainfo = networkAreainfo;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Long getEditPersonID() {
        return editPersonID;
    }

    public void setEditPersonID(Long editPersonID) {
        this.editPersonID = editPersonID;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getOwnNetworkId() {
        return ownNetworkId;
    }

    public void setOwnNetworkId(String ownNetworkId) {
        this.ownNetworkId = ownNetworkId;
    }

    public String getOwnEntId() {
        return ownEntId;
    }

    public void setOwnEntId(String ownEntId) {
        this.ownEntId = ownEntId;
    }

    public String getOwnType() {
        return ownType;
    }

    public void setOwnType(String ownType) {
        this.ownType = ownType;
    }

    public Double getMuckQuantities() {
        return muckQuantities;
    }

    public void setMuckQuantities(Double muckQuantities) {
        this.muckQuantities = muckQuantities;
    }

    public Double getMuckComplement() {
        return muckComplement;
    }

    public void setMuckComplement(Double muckComplement) {
        this.muckComplement = muckComplement;
    }

    public String getMuckTaskPersonID() {
        return muckTaskPersonID;
    }

    public void setMuckTaskPersonID(String muckTaskPersonID) {
        this.muckTaskPersonID = muckTaskPersonID;
    }

    public Byte getMuckIsNeedSigner() {
        return muckIsNeedSigner;
    }

    public void setMuckIsNeedSigner(Byte muckIsNeedSigner) {
        this.muckIsNeedSigner = muckIsNeedSigner;
    }

    public Byte getMuckState() {
        return muckState;
    }

    public void setMuckState(Byte muckState) {
        this.muckState = muckState;
    }

    public Integer getNetworkRange() {
        return networkRange;
    }

    public void setNetworkRange(Integer networkRange) {
        this.networkRange = networkRange;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }
}