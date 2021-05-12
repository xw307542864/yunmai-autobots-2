package com.logibeat.cloud.common.vo;

import java.sql.Timestamp;
import java.util.List;

/**
 * 
* @ClassName: FriendDrvierDetailVo 
* @Description: 司机详细信息
* @author sean 
* @date 2015年12月18日 上午9:17:43 
* @version 1.0
 */
public class FriendDrvierDetailVo {
    
    private String carID;//车辆信ID
    private String carLengthDictGUID;//车长
    private String coachTypeDictGUID;//车型
    private String carLogo;//车辆图片
    private String plateNumber;//车牌
    private Double ratedLoad;//载重
    private Double ratedVolume;//体积
    private String entPerId;//企业与人员关系id
    private String childAdminType;//企业子账号人员类型（岗位）（枚举） 
    private String jobName;//岗位名称
    private Integer coopType;//合作关系
    private Long coopNum;//合作次数
    private Long  currentTaskNum;//当前任务
    private Integer driverAuditStatus;//司机认证状态
    private String hdpic;//图片地址
    private String imGUID;//通讯Id
    private Boolean isReg;//是否已注册 
    private Boolean isUsual;//是否常用联系人
    private String mobile;//手机号 
    private Integer inviteState;//邀请状态（枚举 我方状态） 
    private Integer inviteType;//邀请类型
    private String nameRemark;//名称备注
    private String niChen;//昵称
    private String personID;//会员ID
    private String lastDynamic;//最新动态  
    private List<String> picList;//动态图片
    private String locationInfo;//地址 
	private Timestamp lastGpsTime;//gps时间
	private Double lat;//纬度 
	private Double lng;//经度 
	private Boolean isGPS;//是否为GPS设备
    private Integer star;//司机星级
    private String message;//处理新联系人备注信息
    private Boolean isFirstDriver;//是否主驾
    private Boolean sendInviteBtn;//发送邀请
    private Boolean sendMessageBtn;//发送消息
    private Boolean addSelfDriverBtn;//添加企业司机
    private Boolean addCoopDriverBtn;//添加外协司机
    private Boolean approvedBtn;//通过验证
    private Boolean sendTaskBtn;// 发任务
    private String logitalkId;//IM ID
    private String displayName;//IM 名称
    private Boolean isImFriend = false;//是否是好友关系
    private List<PersonOrganizationVo> orgList;//所属组织--2016/12/22
	private boolean isAllOrg;//是否所属全部组织--2016/12/22
    private String orgGuid;
    private String orgName;

     

    public Boolean getIsFirstDriver() {
        return isFirstDriver;
    }

    public void setIsFirstDriver(Boolean isFirstDriver) {
        this.isFirstDriver = isFirstDriver;
    }

    public String getChildAdminType() {
        return childAdminType;
    }

    public void setChildAdminType(String childAdminType) {
        this.childAdminType = childAdminType;
    }

    public Integer getCoopType() {
        return coopType;
    }

    public void setCoopType(Integer coopType) {
        this.coopType = coopType;
    }

    public Long getCoopNum() {
        return coopNum;
    }

    public void setCoopNum(Long coopNum) {
        this.coopNum = coopNum;
    }

    public Long getCurrentTaskNum() {
        return currentTaskNum;
    }

    public void setCurrentTaskNum(Long currentTaskNum) {
        this.currentTaskNum = currentTaskNum;
    }

    public Integer getDriverAuditStatus() {
        return driverAuditStatus;
    }

    public void setDriverAuditStatus(Integer driverAuditStatus) {
        this.driverAuditStatus = driverAuditStatus;
    }

    public String getHdpic() {
        return hdpic;
    }

    public void setHdpic(String hdpic) {
        this.hdpic = hdpic;
    }

    public String getImGUID() {
        return imGUID;
    }

    public void setImGUID(String imGUID) {
        this.imGUID = imGUID;
    }

    public Boolean getIsReg() {
        return isReg;
    }

    public void setIsReg(Boolean isReg) {
        this.isReg = isReg;
    }

    public Boolean getIsUsual() {
        return isUsual;
    }

    public void setIsUsual(Boolean isUsual) {
        this.isUsual = isUsual;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getInviteState() {
        return inviteState;
    }

    public void setInviteState(Integer inviteState) {
        this.inviteState = inviteState;
    }

    public String getNameRemark() {
        return nameRemark;
    }

    public void setNameRemark(String nameRemark) {
        this.nameRemark = nameRemark;
    }

    public String getNiChen() {
        return niChen;
    }

    public void setNiChen(String niChen) {
        this.niChen = niChen;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getLastDynamic() {
        return lastDynamic;
    }

    public void setLastDynamic(String lastDynamic) {
        this.lastDynamic = lastDynamic;
    }

    public String getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(String locationInfo) {
		this.locationInfo = locationInfo;
	}

	public Timestamp getLastGpsTime() {
		return lastGpsTime;
	}

	public void setLastGpsTime(Timestamp lastGpsTime) {
		this.lastGpsTime = lastGpsTime;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Boolean getIsGPS() {
		return isGPS;
	}

	public void setIsGPS(Boolean isGPS) {
		this.isGPS = isGPS;
	}

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
    
    
    public String getCarID()
    {
        return carID;
    }

    public void setCarID(String carID)
    {
        this.carID = carID;
    }

    public String getCarLengthDictGUID() {
        return carLengthDictGUID;
    }

    public void setCarLengthDictGUID(String carLengthDictGUID) {
        this.carLengthDictGUID = carLengthDictGUID;
    }

    public String getCoachTypeDictGUID()
    {
        return coachTypeDictGUID;
    }

    public void setCoachTypeDictGUID(String coachTypeDictGUID)
    {
        this.coachTypeDictGUID = coachTypeDictGUID;
    }

    public String getCarLogo()
    {
        return carLogo;
    }

    public void setCarLogo(String carLogo)
    {
        this.carLogo = carLogo;
    }

    public String getPlateNumber()
    {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber)
    {
        this.plateNumber = plateNumber;
    }

    public Double getRatedLoad()
    {
        return ratedLoad;
    }

    public void setRatedLoad(Double ratedLoad)
    {
        this.ratedLoad = ratedLoad;
    }

    public Double getRatedVolume()
    {
        return ratedVolume;
    }

    public void setRatedVolume(Double ratedVolume)
    {
        this.ratedVolume = ratedVolume;
    }

	public Boolean getSendInviteBtn() {
		return sendInviteBtn;
	}

	public void setSendInviteBtn(Boolean sendInviteBtn) {
		this.sendInviteBtn = sendInviteBtn;
	}

	public Boolean getSendMessageBtn() {
		return sendMessageBtn;
	}

	public void setSendMessageBtn(Boolean sendMessageBtn) {
		this.sendMessageBtn = sendMessageBtn;
	}

	public Boolean getAddSelfDriverBtn() {
		return addSelfDriverBtn;
	}

	public void setAddSelfDriverBtn(Boolean addSelfDriverBtn) {
		this.addSelfDriverBtn = addSelfDriverBtn;
	}

	public Boolean getAddCoopDriverBtn() {
		return addCoopDriverBtn;
	}

	public void setAddCoopDriverBtn(Boolean addCoopDriverBtn) {
		this.addCoopDriverBtn = addCoopDriverBtn;
	}

	public Boolean getApprovedBtn() {
		return approvedBtn;
	}

	public void setApprovedBtn(Boolean approvedBtn) {
		this.approvedBtn = approvedBtn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public Integer getInviteType() {
		return inviteType;
	}

	public void setInviteType(Integer inviteType) {
		this.inviteType = inviteType;
	}

	public Boolean getSendTaskBtn() {
		return sendTaskBtn;
	}

	public void setSendTaskBtn(Boolean sendTaskBtn) {
		this.sendTaskBtn = sendTaskBtn;
	}

	public String getEntPerId() {
		return entPerId;
	}

	public void setEntPerId(String entPerId) {
		this.entPerId = entPerId;
	}

	public String getLogitalkId() {
		return logitalkId;
	}

	public void setLogitalkId(String logitalkId) {
		this.logitalkId = logitalkId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Boolean getIsImFriend() {
		return isImFriend;
	}

	public void setIsImFriend(Boolean isImFriend) {
		this.isImFriend = isImFriend;
	}

	public List<PersonOrganizationVo> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<PersonOrganizationVo> orgList) {
		this.orgList = orgList;
	}

	public boolean getIsAllOrg() {
		return isAllOrg;
	}

	public void setIsAllOrg(boolean isAllOrg) {
		this.isAllOrg = isAllOrg;
	}


	public String getOrgGuid() {
		return orgGuid;
	}

	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
