package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 *  ZM_GM_WorkListDetail
 */
@Table(name = "ZM_GM_WorkListDetail")
public class WorkListDetail extends BaseEntity {
    /**
     * 设备Id
     */
    @Column(name="DeviceId")
    private String deviceId;
    /**
     * 工单Id
     */
    @Column(name="WorkListId")
    private String workListId;
    /**
     * 问题
     */
    private String question;
    /**
     * 附件Ids
     */
    @Column(name="AttachmentIds")
    private String attachmentIds;
    /**
     * 说明
     */
    private String state;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getWorkListId() {
        return workListId;
    }

    public void setWorkListId(String workListId) {
        this.workListId = workListId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
