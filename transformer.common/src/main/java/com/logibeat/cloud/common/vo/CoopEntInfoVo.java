package com.logibeat.cloud.common.vo;

/**
 * 合作企业信息vo
 * @ClassName: CoopEntInfoVo 
 * @Description: 
 * @author kzz 
 * @date 2017年2月15日 上午10:20:42 
 * @version 1.0
 */
public class CoopEntInfoVo {

	//邀请状态 
	private Integer inviteStatus;
	//邀请类型
	private Integer inviteType;
	//星标
	private Boolean starMark;
	//标签
	private String label;
	//星级
	private String starLevel;
	//留言
	private String leaveMessage;
	//描述
	private String description;
	
	/** 接口人 */
	// 是否显示
	private Boolean displayContact;
	// 我方接口人数量
	private Long myContactNum;
	// 我方接口人名称
	private String myContactName;
	// 我方接口人数量
	private Long heContactNum;
	// 我方接口人名称
	private String heContactName;
	
	/** 合作的任务量*/
	// 发单总数（我方委托给该企业的订单总数）
	private Long totalEntrustOrdersNum;
	// 接单总数（我方为该企业承运的订单总数）
	private Long totalCarrierOrdersNum;
	// 当前接单数/当前任务（我方为该企业承运的未完成订单数）
	private Long currentCarrierOrdersNum;
	// 当前发单数（企业版专用 我方委托给该企业未完成的订单数）
	private Long currentEntrustOrdersNum;
	
	public Integer getInviteStatus() {
		return inviteStatus;
	}
	public void setInviteStatus(Integer inviteStatus) {
		this.inviteStatus = inviteStatus;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}
	public Boolean getDisplayContact() {
		return displayContact;
	}
	public void setDisplayContact(Boolean displayContact) {
		this.displayContact = displayContact;
	}
	public Long getMyContactNum() {
		return myContactNum;
	}
	public void setMyContactNum(Long myContactNum) {
		this.myContactNum = myContactNum;
	}
	public String getMyContactName() {
		return myContactName;
	}
	public void setMyContactName(String myContactName) {
		this.myContactName = myContactName;
	}
	public Long getHeContactNum() {
		return heContactNum;
	}
	public void setHeContactNum(Long heContactNum) {
		this.heContactNum = heContactNum;
	}
	public String getHeContactName() {
		return heContactName;
	}
	public void setHeContactName(String heContactName) {
		this.heContactName = heContactName;
	}
	public Long getTotalEntrustOrdersNum() {
		return totalEntrustOrdersNum;
	}
	public void setTotalEntrustOrdersNum(Long totalEntrustOrdersNum) {
		this.totalEntrustOrdersNum = totalEntrustOrdersNum;
	}
	public Long getTotalCarrierOrdersNum() {
		return totalCarrierOrdersNum;
	}
	public void setTotalCarrierOrdersNum(Long totalCarrierOrdersNum) {
		this.totalCarrierOrdersNum = totalCarrierOrdersNum;
	}
	public Long getCurrentCarrierOrdersNum() {
		return currentCarrierOrdersNum;
	}
	public void setCurrentCarrierOrdersNum(Long currentCarrierOrdersNum) {
		this.currentCarrierOrdersNum = currentCarrierOrdersNum;
	}
	public Long getCurrentEntrustOrdersNum() {
		return currentEntrustOrdersNum;
	}
	public void setCurrentEntrustOrdersNum(Long currentEntrustOrdersNum) {
		this.currentEntrustOrdersNum = currentEntrustOrdersNum;
	}
	public String getLeaveMessage() {
		return leaveMessage;
	}
	public void setLeaveMessage(String leaveMessage) {
		this.leaveMessage = leaveMessage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getInviteType() {
		return inviteType;
	}
	public void setInviteType(Integer inviteType) {
		this.inviteType = inviteType;
	}
	public Boolean getStarMark() {
		return starMark;
	}
	public void setStarMark(Boolean starMark) {
		this.starMark = starMark;
	}

	public enum InviteStatus{

		Unknown(0, "未知"),
	    Pass(1, "已合作"), 
	    Stop(2, "已取消合作"), 
	    Wait(3, "已申请"),
	    Apply(4, "对方已申请"),
		Reapply(5,"对方已取消合作，对方重新申请添加");
	    
	    protected Integer  value;
	    protected String  description;
	    
	    InviteStatus(Integer value, String description){
	        this.value = value;
	        this.description = description;
	    }
	    
	    public Integer getValue(){
	        return value;
	    }

	    public void setValue(Integer value){
	        this.value = value;
	    }

	    public String getDescription(){
	        return description;
	    }

	    public void setDescription(String description){
	        this.description = description;
	    }
	    
	}

	
	
}
