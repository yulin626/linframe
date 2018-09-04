package com.ganglion.model;

import java.io.Serializable;

public class WorkListDetailDTO implements Serializable {

    private String id;
    private String name;
    private String deviceName;
    private Boolean isDelete;
    private Boolean isDeal;
    private String orderIndex;
    private String remark;
    private String addressId;
    private String installationState;
    private String maintenanceStatus;

    /**
     * 设备Id
     */
    private String deviceId;
    /**
     * 工单Id
     */
    private String workListId;
    /**
     * 问题
     */
    private String question;
    /**
     * 附件Ids
     */
    private String attachmentIds;
    /**
     * 说明
     */
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Boolean getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(Boolean isDeal) {
        this.isDeal = isDeal;
    }

    public String getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(String orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

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

    public String getInstallationState() {
        return installationState;
    }

    public void setInstallationState(String installationState) {
        this.installationState = installationState;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }
}
