package com.ganglion.model;

import java.io.Serializable;
import java.util.List;

public class WorkListDTO implements Serializable {

    private String id;
    private String name;
    private Boolean isDelete;
    private String orderIndex;
    private String remark;

    /**
     * 工单号
     */
    private String no;
    /**
     * 所属项目
     */
    private String projectId;

    /**
     * 所属项目名称
     */
    private String projectName;
    /**
     * 工单状态
     */
    private String status;
    /**
     * 工单类型
     */
    private String type;
    /**
     * 接单人员
     */
    private String receiver;
    /**
     * 接单人员姓名
     */
    private String receiverName;
    /**
     * 关闭时间
     */
    private String closeTime;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 设备Ids
     */
    private List<String> deviceIds;
    /**
     * 设备列表
     */
    private List<WorkListDetailDTO>  deviceList;
    /**
     * 工单Ids
     */
    private List<String> workListIds;
    /**
     * 工单是否处理
     */
    private Boolean isDeal;

    private String updatedTime;

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

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<String> getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) {
        this.deviceIds = deviceIds;
    }

    public List<WorkListDetailDTO> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<WorkListDetailDTO> deviceList) {
        this.deviceList = deviceList;
    }

    public List<String> getWorkListIds() {
        return workListIds;
    }

    public void setWorkListIds(List<String> workListIds) {
        this.workListIds = workListIds;
    }

    public Boolean getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(Boolean isDeal) {
        this.isDeal = isDeal;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
