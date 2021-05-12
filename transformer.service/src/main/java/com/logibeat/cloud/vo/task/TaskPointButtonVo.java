package com.logibeat.cloud.vo.task;

public class TaskPointButtonVo {


    /**
     * 详情按钮
     */
    private boolean detailButton=true;


    /**
     * 去这里按钮
     */
    private boolean goHereButton=true;


    /**
     * 联系发货人按钮
     */
    private boolean contactSendButton;

    /**
     * 联系托运人按钮
     */
    private boolean contactEntrustButton=true;


    /**
     * 去装货按钮
     */
    private boolean goLoadButton;


    /**
     * 到达按钮
     */
    private boolean arriveButton;


    /**
     * 开始装货
     */
    private boolean startLoadButton;

    /**
     * 完成装货
     */
    private boolean finishLoadButton;


    /**
     * 去卸货按钮
     */
    private boolean goUnloadButton;

    /**
     * 联系收货人
     */
    private boolean contactRecieveButton;


    /**
     * 开始卸货按钮
     */
    private boolean startUnloadButton;


    /**
     * 完成卸货按钮
     */
    private boolean finishUnloadButton;


    /**
     * 签收按钮
     */
    private boolean signButton;

    /**
     * 签收详情
     */
    private boolean signDetailButton;


    /**
     * 上报事件
     */
    private boolean reportEventButton;


    public boolean isDetailButton() {
        return detailButton;
    }

    public void setDetailButton(boolean detailButton) {
        this.detailButton = detailButton;
    }

    public boolean isGoHereButton() {
        return goHereButton;
    }

    public void setGoHereButton(boolean goHereButton) {
        this.goHereButton = goHereButton;
    }

    public boolean isContactSendButton() {
        return contactSendButton;
    }

    public void setContactSendButton(boolean contactSendButton) {
        this.contactSendButton = contactSendButton;
    }

    public boolean isContactEntrustButton() {
        return contactEntrustButton;
    }

    public void setContactEntrustButton(boolean contactEntrustButton) {
        this.contactEntrustButton = contactEntrustButton;
    }

    public boolean isGoLoadButton() {
        return goLoadButton;
    }

    public void setGoLoadButton(boolean goLoadButton) {
        this.goLoadButton = goLoadButton;
    }

    public boolean isArriveButton() {
        return arriveButton;
    }

    public void setArriveButton(boolean arriveButton) {
        this.arriveButton = arriveButton;
    }

    public boolean isStartLoadButton() {
        return startLoadButton;
    }

    public void setStartLoadButton(boolean startLoadButton) {
        this.startLoadButton = startLoadButton;
    }

    public boolean isFinishLoadButton() {
        return finishLoadButton;
    }

    public void setFinishLoadButton(boolean finishLoadButton) {
        this.finishLoadButton = finishLoadButton;
    }

    public boolean isGoUnloadButton() {
        return goUnloadButton;
    }

    public void setGoUnloadButton(boolean goUnloadButton) {
        this.goUnloadButton = goUnloadButton;
    }

    public boolean isContactRecieveButton() {
        return contactRecieveButton;
    }

    public void setContactRecieveButton(boolean contactRecieveButton) {
        this.contactRecieveButton = contactRecieveButton;
    }

    public boolean isStartUnloadButton() {
        return startUnloadButton;
    }

    public void setStartUnloadButton(boolean startUnloadButton) {
        this.startUnloadButton = startUnloadButton;
    }

    public boolean isFinishUnloadButton() {
        return finishUnloadButton;
    }

    public void setFinishUnloadButton(boolean finishUnloadButton) {
        this.finishUnloadButton = finishUnloadButton;
    }

    public boolean isSignButton() {
        return signButton;
    }

    public void setSignButton(boolean signButton) {
        this.signButton = signButton;
    }


    public boolean isSignDetailButton() {
        return signDetailButton;
    }

    public void setSignDetailButton(boolean signDetailButton) {
        this.signDetailButton = signDetailButton;
    }

    public boolean isReportEventButton() {
        return reportEventButton;
    }

    public void setReportEventButton(boolean reportEventButton) {
        this.reportEventButton = reportEventButton;
    }
}
