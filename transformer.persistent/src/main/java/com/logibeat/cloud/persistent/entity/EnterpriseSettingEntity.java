package com.logibeat.cloud.persistent.entity;

import com.logibeat.cloud.persistent.entity.serialize.EntitySerialize;

import java.sql.Timestamp;

public class EnterpriseSettingEntity extends EntitySerialize {
	
	private static final long serialVersionUID = 1L;
	
	private String guid;
    private String entId;
    private Byte isCheckCoopEnt = new Byte("1");//企业建立合作时需要验证
    private Byte isCheckCoopDriver = new Byte("1");//加为本企业外协运力需要验证
    private Byte isCheckSelfDriver = new Byte("1");//司机加入企业
    
    private Integer IsSearch = 1;//是否可以搜索到该企业
    private Byte isAutoSendCar = new Byte("0");//是否自动确认发车,0为否,1为是
    private Byte isAutoArrival = new Byte("0");//是否自动确认到达,0为否,1为是
    private String autoNumber = "0";//货号自动生成
    private String letterOff;
    private String letter;
    private String dateOff;
    private String dateFormat;
    private String serialNumberOff = "1";
    private String serialNumber = "5";
    private String loopOff = "1";
    private String loopRule = "1";
    private String isShip;//显示配载单开关
    private String isConsign;//显示托运方开关
    private String isInputNumber;//显示手工单号开关
    private Integer isRepeat = 0;//录入手工单号是否可以重复（0：否，1：是）
    
    private Integer isUpLogink=1;//国家货运平台数据上传设置
    private String queueShowType = "3";//排队看板展示模式
    
    private Timestamp crteateTime;
    private Timestamp updateTime;
    
    private Integer isSign = 0;//是否签收
    
    private Integer getAdressType = 1;//获取收获地址类型 1只调用常用 2只调用历史 3同时调用常用和历史
    
    
    
	public Integer getIsSign() {
		return isSign;
	}
	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}
	public Integer getGetAdressType() {
		return getAdressType;
	}
	public void setGetAdressType(Integer getAdressType) {
		this.getAdressType = getAdressType;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
	}
	public Byte getIsCheckCoopEnt() {
		return isCheckCoopEnt;
	}
	public void setIsCheckCoopEnt(Byte isCheckCoopEnt) {
		this.isCheckCoopEnt = isCheckCoopEnt;
	}
	public Byte getIsCheckCoopDriver() {
		return isCheckCoopDriver;
	}
	public void setIsCheckCoopDriver(Byte isCheckCoopDriver) {
		this.isCheckCoopDriver = isCheckCoopDriver;
	}
	public Byte getIsCheckSelfDriver() {
		return isCheckSelfDriver;
	}
	public void setIsCheckSelfDriver(Byte isCheckSelfDriver) {
		this.isCheckSelfDriver = isCheckSelfDriver;
	}
	public Integer getIsSearch() {
		return IsSearch;
	}
	public void setIsSearch(Integer isSearch) {
		IsSearch = isSearch;
	}
	public Byte getIsAutoSendCar() {
		return isAutoSendCar;
	}
	public void setIsAutoSendCar(Byte isAutoSendCar) {
		this.isAutoSendCar = isAutoSendCar;
	}
	public Byte getIsAutoArrival() {
		return isAutoArrival;
	}
	public void setIsAutoArrival(Byte isAutoArrival) {
		this.isAutoArrival = isAutoArrival;
	}
	public String getAutoNumber() {
		return autoNumber;
	}
	public void setAutoNumber(String autoNumber) {
		this.autoNumber = autoNumber;
	}
	public String getLetterOff() {
		return letterOff;
	}
	public void setLetterOff(String letterOff) {
		this.letterOff = letterOff;
	}
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public String getDateOff() {
		return dateOff;
	}
	public void setDateOff(String dateOff) {
		this.dateOff = dateOff;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public String getSerialNumberOff() {
		return serialNumberOff;
	}
	public void setSerialNumberOff(String serialNumberOff) {
		this.serialNumberOff = serialNumberOff;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getLoopOff() {
		return loopOff;
	}
	public void setLoopOff(String loopOff) {
		this.loopOff = loopOff;
	}
	public String getLoopRule() {
		return loopRule;
	}
	public void setLoopRule(String loopRule) {
		this.loopRule = loopRule;
	}
	public String getIsShip() {
		return isShip;
	}
	public void setIsShip(String isShip) {
		this.isShip = isShip;
	}
	public String getIsConsign() {
		return isConsign;
	}
	public void setIsConsign(String isConsign) {
		this.isConsign = isConsign;
	}
	public String getIsInputNumber() {
		return isInputNumber;
	}
	public void setIsInputNumber(String isInputNumber) {
		this.isInputNumber = isInputNumber;
	}
	public Integer getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(Integer isRepeat) {
		this.isRepeat = isRepeat;
	}
	public Timestamp getCrteateTime() {
		return crteateTime;
	}
	public void setCrteateTime(Timestamp crteateTime) {
		this.crteateTime = crteateTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getIsUpLogink() {
		return isUpLogink;
	}
	public void setIsUpLogink(Integer isUpLogink) {
		this.isUpLogink = isUpLogink;
	}
	public String getQueueShowType() {
		return queueShowType;
	}
	public void setQueueShowType(String queueShowType) {
		this.queueShowType = queueShowType;
	}
}

