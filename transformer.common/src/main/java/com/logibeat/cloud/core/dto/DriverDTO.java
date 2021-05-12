package com.logibeat.cloud.core.dto;

import java.sql.Timestamp;

/**
 * Created by xiang.liu on 2016/4/22.
 * 
 * ps: 由于调用数据库不规范，故暂时抽象出来，下一个版本优化；
 */
public class DriverDTO {

    public String id;//会员id
    private String hDpic;//头像
    private String niChen;//昵称
    private String personId;//personid
    private String name;//姓名
    private String mobiles;//手机号
    private String socialLic;//身份证号
    private String drivingLic;//驾驶证号
    private String carId;//车辆id
    private String plateNumber;//车牌号
    private String coachTypeDictGuid;//厢型id
    private String carLengthDictGuid;//车长id
    private Double ratedLoad;//额定载重
    private Double ratedVolume;//额定体积
    private String cardrivingLic;//行驶证号
    //驾驶证图片
    private String drivingPics;//行驶证图片
    private String photos;//车辆图片
    private String entName;//已加入企业名称
    private String State;//账号锁定状态
    private byte DriverAuditStatus;//司机认证状态
    private byte CarAuditStatus;//车辆图片认证状态
    private byte DrivingAuditStatus;//行驶证认证状态
    private String address;//车辆最新地址
    private Timestamp lasttime;//车辆最新地址更新时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gethDpic() {
        return hDpic;
    }

    public void sethDpic(String hDpic) {
        this.hDpic = hDpic;
    }

    public String getNiChen() {
        return niChen;
    }

    public void setNiChen(String niChen) {
        this.niChen = niChen;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getSocialLic() {
        return socialLic;
    }

    public void setSocialLic(String socialLic) {
        this.socialLic = socialLic;
    }

    public String getDrivingLic() {
        return drivingLic;
    }

    public void setDrivingLic(String drivingLic) {
        this.drivingLic = drivingLic;
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

    public String getCoachTypeDictGuid() {
        return coachTypeDictGuid;
    }

    public void setCoachTypeDictGuid(String coachTypeDictGuid) {
        this.coachTypeDictGuid = coachTypeDictGuid;
    }

    public String getCarLengthDictGuid() {
        return carLengthDictGuid;
    }

    public void setCarLengthDictGuid(String carLengthDictGuid) {
        this.carLengthDictGuid = carLengthDictGuid;
    }

    public Double getRatedLoad() {
        return ratedLoad;
    }

    public void setRatedLoad(Double ratedLoad) {
        this.ratedLoad = ratedLoad;
    }

    public Double getRatedVolume() {
        return ratedVolume;
    }

    public void setRatedVolume(Double ratedVolume) {
        this.ratedVolume = ratedVolume;
    }

    public String getCardrivingLic() {
        return cardrivingLic;
    }

    public void setCardrivingLic(String cardrivingLic) {
        this.cardrivingLic = cardrivingLic;
    }

    public String getDrivingPics() {
        return drivingPics;
    }

    public void setDrivingPics(String drivingPics) {
        this.drivingPics = drivingPics;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public byte getDriverAuditStatus() {
        return DriverAuditStatus;
    }

    public void setDriverAuditStatus(byte driverAuditStatus) {
        DriverAuditStatus = driverAuditStatus;
    }

    public byte getCarAuditStatus() {
        return CarAuditStatus;
    }

    public void setCarAuditStatus(byte carAuditStatus) {
        CarAuditStatus = carAuditStatus;
    }

    public byte getDrivingAuditStatus() {
        return DrivingAuditStatus;
    }

    public void setDrivingAuditStatus(byte drivingAuditStatus) {
        DrivingAuditStatus = drivingAuditStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getLasttime() {
        return lasttime;
    }

    public void setLasttime(Timestamp lasttime) {
        this.lasttime = lasttime;
    }
}
