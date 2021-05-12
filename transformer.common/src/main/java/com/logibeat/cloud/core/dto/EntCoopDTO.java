package com.logibeat.cloud.core.dto;

import com.logibeat.cloud.core.dto.BaseRequestDTO;
import com.logibeat.cloud.core.dto.EntBusinessInfoDTO;

import java.util.List;

/**
 * Created by wilson on 2017/2/15.
 */
public class EntCoopDTO extends BaseRequestDTO {

    /**
     * 合作伙伴企业ID
     */
    private String entId;

    /**
     * 企业工商信息
     */
    private EntBusinessInfoDTO entBusinessInfo;

    /**
     * 标签
     */
    private List<String> lableIdList;

    /**
     * 星级
     */
    private String starLevelId;

    /**
     * 描述
     */
    private String remark;

    /**
     * 验证信息
     */
    private String message;

    /**
     * 接口人
     */
    private List<EntCoopContactsDTO> contactsList;


    /**
     * 1：通过验证 0：忽略
     */
    private Byte isAccept;


    /**
     * 新联系人GUID（兼容老版本）
     */
    private String NewFriendGUID;
    
    private String ownType;


    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public EntBusinessInfoDTO getEntBusinessInfo() {
        return entBusinessInfo;
    }

    public void setEntBusinessInfo(EntBusinessInfoDTO entBusinessInfo) {
        this.entBusinessInfo = entBusinessInfo;
    }

    public List<String> getLableIdList() {
        return lableIdList;
    }

    public void setLableIdList(List<String> lableIdList) {
        this.lableIdList = lableIdList;
    }

    public String getStarLevelId() {
        return starLevelId;
    }

    public void setStarLevelId(String starLevelId) {
        this.starLevelId = starLevelId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<EntCoopContactsDTO> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<EntCoopContactsDTO> contactsList) {
        this.contactsList = contactsList;
    }


    public Byte getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(Byte isAccept) {
        this.isAccept = isAccept;
    }

    public String getNewFriendGUID() {
        return NewFriendGUID;
    }

    public void setNewFriendGUID(String newFriendGUID) {
        NewFriendGUID = newFriendGUID;
    }

	public String getOwnType() {
		return ownType;
	}

	public void setOwnType(String ownType) {
		this.ownType = ownType;
	}
    
    
}


