package com.logibeat.cloud.core.dto;


/**
 * 
* @ClassName: HandleNewFriendInfoDTO 
* @Description: 处理好友请求
* @author karl 
* @date 2015年12月25日 上午11:14:18 
* @version 1.0
 */
public class HandleNewFriendInfoDTO {

	/**
	 * 新联系人GUID
	 */
	private String NewFriendGUID;
	/**
	 * 邀请状态（枚举）
	 */
	private Integer inviteState;
	/**
	 * 留言
	 */
	private String message;
	/**
	 * 是否共享位置信息（司机版才用到）
	 */
	private Byte isShareGps;
	/**
	 * 是否接受
	 */
	private Byte isAccept;
	
	private String coopId;
	
	
	public String getCoopId() {
        return coopId;
    }
    public void setCoopId(String coopId) {
        this.coopId = coopId;
    }
    public String getNewFriendGUID() {
		return NewFriendGUID;
	}
	public void setNewFriendGUID(String newFriendGUID) {
		NewFriendGUID = newFriendGUID;
	}
	public Integer getInviteState() {
		return inviteState;
	}
	public void setInviteState(Integer inviteState) {
		this.inviteState = inviteState;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Byte getIsShareGps() {
		return isShareGps;
	}
	public void setIsShareGps(Byte isShareGps) {
		this.isShareGps = isShareGps;
	}
	public Byte getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(Byte isAccept) {
		this.isAccept = isAccept;
	}
	
}
