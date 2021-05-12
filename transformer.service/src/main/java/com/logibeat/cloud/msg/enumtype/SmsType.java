package com.logibeat.cloud.msg.enumtype;

public enum SmsType {

    InformationSms ("InformationSms", "共享车辆详情"),
    NotificationSms("NotificationSms", "派单通知"),
    failureJoinSms("failureJoinSms", "申请加入失败"),
    SuccJoinSms("SuccJoinSms", "申请加入成功"),
    ClaimfailureSms("ClaimfailureSms", "认领失败"),
    ClaimSuccSms("ClaimSuccSms", "认领成功"),
    RecommendedleadershipSms("RecommendedleadershipSms", "推荐给领导"),
    TaskTrackingSms("TaskTrackingSms", "任务跟踪"),
    EnterprisesSms("EnterprisesSms", "企业入驻"),
    ViewSharedCarSms("ViewSharedCarSms", "查看共享车辆信息"),
    TaskInformSms("TaskInformSms", "任务通知"),
    ForcedtoUpdateSms("ForcedtoUpdateSms", "App强制更新下载通知短信20160920"),
    StopSms("StopSms", "停机维护公告短信"),
    TaskDetailsSms("TaskDetailsSms", "任务详情"),
    TaskManagerSms("TaskManagerSms", "任务提醒"),
    InvitedSms("InvitedSms", "邀请加入运脉"),
    BusinessInvitationSms("BusinessInvitationSms", "企业邀请司机"),
    DriversInviteFriendsSms("DriversInviteFriendsSms", "司机邀请好友"),
    invitationSms("invitationSms", "邀请"),
    NoticeSms("NoticeSms", "公告短信"),
    UnkownSms("unkown", "未知"),
    REGISTER("register", "验证码"),
    EndVoiceSms("EndVoiceSms","到达GPS异常语音提醒"),
    StartVoiceSms("StartVoiceSms","发车GPS异常语音提醒"),
    EndSms("EndSms","到达GPS异常短信提醒"),
    StartSms("StartSms","发车GPS异常短信提醒"),
    OrderReminderSms("OrderReminderSms","下单提醒短信"),
    AccountApplicationSuc("AccountApplicationSuc","开户申请审核通过"),
    AccountApplicationFail("AccountApplicationFail","开户申请审核不通过"),
    AccountApplicationSucProbation("AccountApplicationSucProbation","已审核,通过(试用)"),
    invitationCoopateSms("invitationCoopateSms","邀请个人客户"),
    GiftPackage("GiftPackage","大礼包"),
    TaskComplete("TaskComplete","任务完成提醒"),
    TaskRecall("TaskRecall","任务撤回"),
    NewTask("NewTask","新任务提醒"),
    SubmitCertification("SubmitCertification","提交认证"),
    CertificationExpired("CertificationExpired","证件过期提醒"),
    CertificationWillExpire("CertificationWillExpire","证件即将到期提醒"),
    CertificationSuccess("CertificationSuccess","认证成功"),
    CertificationFail("CertificationFail","认证失败"),
    ApplicationAccession("ApplicationAccession","申请加入"),

    NewAppointmentSlip("NewAppointmentSlip","新预约单提醒"),
    ReservationFormWithdrawal("ReservationFormWithdrawal","预约单撤回"),
    AmendmentofReservationForm("AmendmentofReservationForm","预约单修改'"),
    AddVehicleAudit("AddVehicleAudit","添加车辆审核"),
    VehicleAuditSuccess("VehicleAuditSuccess","平台审核车辆成功"),
    VehicleAuditFail("VehicleAuditFail","平台审核企业车辆失败"),
    EntVehicleAuditSuccess("EntVehicleAuditSuccess","平台审核自有车辆成功"),
    VehicleCategorizedEntVehicle("VehicleCategorizedEntVehicle","车辆编为企业车辆"),
    EntAuditNotPass("EntAuditNotPass","企业审核车辆不通过"),
    EntAddDriver("EntAddDriver","企业添加司机"),
    EntAddVehicle("EntAddVehicle","企业添加车辆"),
    EntDistributionVehicle("EntDistributionVehicle","企业分配车辆"),
    ApplyJoinEntCommitSuc("ApplyJoinEntCommitSuc","有车司机申请加入企业，提交成功"),
    EntAuditDriverJoinResult("EntAuditDriverJoinResult","有车司机申请加入企业，企业审核结果"),
    EntAlreadyJoin("EntAlreadyJoin","有车司机申请加入企业，企业审核结果");

    private String value;
    private String description;

    SmsType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
}
