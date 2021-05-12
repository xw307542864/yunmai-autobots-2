package com.logibeat.cloud.msg.enumtype;

public enum MessageBizType {
    NEW_TASK_PUSH(1000,"新任务提醒","您有一个新任务。",SmsType.NewTask.getValue(),""),
    RETURN_TASK_PUSH(2000,"任务撤回提醒","您有一个任务被企业撤回。",SmsType.TaskRecall.getValue(),""),
    FINISH_TASK_PUSH(3000,"任务完成通知","任务已完成，感谢您的保驾护航，建议您保障休息时间。",SmsType.TaskComplete.getValue(),""),
    AUDIT_FAIL_PUSH(4000,"认证失败提醒","您提交的认证复核失败，请重新认证！",SmsType.CertificationFail.getValue(),""),
    AUDIT_SUBMIT_PUSH(5000,"认证提交通知","您的认证已提交，请耐心等待审核结果！",SmsType.SubmitCertification.getValue(),""),
    AUDIT_SUCCESS_PUSH(6000,"认证成功通知","恭喜您，您的认证已审核通过!",SmsType.CertificationSuccess.getValue(),""),
    WAIL_EXPIRE_PUSH(7000,"即将到期提醒","为避免到期后部分功能无法使用造成您的损失，请及时处理！",SmsType.CertificationWillExpire.getValue(),""),
    EXPIRE_PUSH(8000,"证件过期提醒","您的证件已过期，部分功能已暂停使用，请重新认证！",SmsType.CertificationExpired.getValue(),""),
    NOCAR_APPROVAL_SUBMIT_PUSH(9000,"审核进度通知","您申请加入entName ，已提交成功需企业审核，请耐心等待哦！",SmsType.ApplicationAccession.getValue(),""),
    NOCAR_APPROVAL_SUCCESS_PUSH(10000,"审核进度通知","您申请加入的entName，审核已通过。",SmsType.SuccJoinSms.getValue(),""),
    NOCAR_APPROVAL_FAIL_PUSH(11000,"审核进度通知","您申请加入的entName，审核不通过。" ,SmsType.failureJoinSms.getValue(),""),
    NEW_PLAN_CAR_PUSH(12000,"预约提醒","您有一个新预约单，请及时查看处理。",SmsType.NewAppointmentSlip.getValue(),""),
    PLAN_CAR_DELETE_PUSH(13000,"预约删除提醒","您有一个新预约单被企业撤回。",SmsType.ReservationFormWithdrawal.getValue(),""),
    PLAN_CAR_CHANGE_PUSH(14000,"预约单通知","您的预约单状态有变更，请查看！。",SmsType.AmendmentofReservationForm.getValue(),""),
    DRIVERADDCAR_AUDIT_SUBMIT_PUSH(15000,"审核进度通知","您添加的车辆，需人工审核请耐心等待！",SmsType.AddVehicleAudit.getValue(),""),
    DRIVERADDENTCAR_AUDIT_SUCCESS_PUSH(16000,"审核进度通知","您添加的车辆，运脉审核通过，现移交至企业进行企业审核！",SmsType.VehicleAuditSuccess.getValue(),""),
    DRIVERADDSELFCAR_AUDIT_SUCCESS_PUSH(17000,"审核进度通知","您添加的车辆审核成功，您可通过“发现”功能寻找企业合作！",SmsType.EntVehicleAuditSuccess.getValue(),""),
    DRIVERADDCAR_AUDIT_FAIL_PUSH(18000,"审核进度通知","您添加的车辆，复核失败！" ,SmsType.VehicleAuditFail.getValue(),""),
    DRIVERADDENTCAR_APPROVAL_SUCCESS_PUSH(19000,"审核进度通知","您添加的车辆企业已审批，已编制为企业车辆！！",SmsType.VehicleCategorizedEntVehicle.getValue(),""),
    DRIVERADDENTCAR_APPROVAL_FAIL_PUSH(20000,"审核进度通知","您添加的车辆企业审核不通过，请于您车辆所属企业咨询！" ,SmsType.EntAuditNotPass.getValue(),""),
    NOCAR_APPROVAL_SUBMIT_ENT_PUSH(21000,"新运力提醒","有司机申请加入您的企业，等待您的审批",null,""),
    HAVECAR_APPROVAL_SUBMIT_ENT_PUSH(22000,"新运力提醒","有车辆申请加入您的企业，等待您的审批",null,""),
    HAVECAR_APPROVAL_SUBMIT_PUSH(23000,"审核进度通知","您申请加入entName提交成功，企业审核中。",SmsType.ApplyJoinEntCommitSuc.getValue(),""),
    HAVECAR_APPROVAL_SUBMIT_FAIL(24000,"审核进度通知","您申请加入entName已有企业审核结果，请查看！",SmsType.EntAuditDriverJoinResult.getValue(),""),
    HAVECAR_APPROVAL_SUBMIT_SUCCESS(25000,"审核进度通知","您申请加入entName已有企业审核结果，请查看！",SmsType.EntAuditDriverJoinResult.getValue(),""),
    QUEUE_APPOINT(26000,"预约排队通知" ,"您已成功预约排队！",null,""),
    QUEUE_PRE(27000,"预约排队通知" ,"您已进入准备队列！",null,""),
    QUEUE_LOAD(28000,"预约排队通知" ,"您可进行装货！",null,""),
    QUEUE_FINISH(29000,"预约排队通知" ,"您的装货已完毕，系统已退出排队！",null,""),
    GUARENTEE_AUDIT_FAIL_PUSH(30000,"认证审核通知","很抱歉，您的认证审核未通过！",SmsType.CertificationFail.getValue(),""),


    PERSON_VIOLATION(45000,"违规处理提醒","您有一个违规处理清单,请确认！",null,""),

    DELETE_FACE(46000,"人脸识别删除提醒","",null,""),
    DELETE_VOLICE(47000,"个性语音删除提醒","",null,""),
    ENT_INVITE(48000,"企业邀请通知","",null,""),
    
    
    CERT_ADD(51000,"证件颁发通知","您成功获取一个证件！",null,""),
    CERT_UPDATE(52000,"换证成功通知","您有证件换证成功！",null,""),
    CERT_DEL(53000,"证件注销通知","您的证件已被注销",null,"");




    private Integer type;
    private String title;
    private String content;
    private String smsType;
    private String url;



    private MessageBizType(Integer type, String title, String  content,String smsType,String url) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.smsType = smsType;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public  Integer getType(){return type;}

    public  String getSmsType(){return  smsType;}


    public String getUrl() {
        return url;
    }



    public static MessageBizType find(Integer type) {
        for (MessageBizType obj : MessageBizType.values()) {
            Integer value = obj.type;
            if (value.equals(type)) {
                return obj;
            }
        }
        return null;
    }
}
