package com.logibeat.cloud.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @ClassName: FriendSearchVo
 * @Description: 联系人搜索回传
 * @author zhangf
 * @date 2016年8月22日 上午9:59:01
 * @version 1.0
 */
public class FriendSearchVo {

	private String friendId;
	private String friendMobileNumber;
	private String friendName;
	private String plateNumber;

	@JsonIgnoreProperties
	private String carId;

	@JsonIgnoreProperties
	private String guid;

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getFriendMobileNumber() {
		return friendMobileNumber;
	}

	public void setFriendMobileNumber(String friendMobileNumber) {
		this.friendMobileNumber = friendMobileNumber;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friendId == null) ? 0 : friendId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendSearchVo other = (FriendSearchVo) obj;
		if (friendId == null) {
			if (other.friendId != null)
				return false;
		} else if (!friendId.equals(other.friendId))
			return false;
		return true;
	}
}
