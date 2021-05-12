package com.logibeat.cloud.common.vo;

//司机信息
public class DriverInfoVo {

	/**
	 * 司机的车辆信息
	 */
	private CarBaseInfoVo carinfovo;
	
	/**
	 * 司机信息
	 */
	private DriverBaseInfoVo driverinfovo;
	
	/**
	 * 司机与企业信息
	 */
	private CooperateDriverVo cooperatePerVo;
	
	/**
	 * 按钮
	 */
	private DriverInfoButtonVo driverInfoButtonVo;
	
	public CarBaseInfoVo getCarinfovo() {
		return carinfovo;
	}

	public void setCarinfovo(CarBaseInfoVo carinfovo) {
		this.carinfovo = carinfovo;
	}

	public DriverInfoButtonVo getDriverInfoButtonVo() {
		return driverInfoButtonVo;
	}

	public void setDriverInfoButtonVo(DriverInfoButtonVo driverInfoButtonVo) {
		this.driverInfoButtonVo = driverInfoButtonVo;
	}

	public DriverBaseInfoVo getDriverinfovo() {
		return driverinfovo;
	}

	public void setDriverinfovo(DriverBaseInfoVo driverinfovo) {
		this.driverinfovo = driverinfovo;
	}

	public CooperateDriverVo getCooperatePerVo() {
		return cooperatePerVo;
	}

	public void setCooperatePerVo(CooperateDriverVo cooperatePerVo) {
		this.cooperatePerVo = cooperatePerVo;
	}
	
}
