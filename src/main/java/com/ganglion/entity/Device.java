package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "ZM_GM_Device")
public class Device extends BaseEntity {
    /**
     * 添加方式
     */
    @Column(name="AddType")
    private String addType;
    /**
     * 设备来源
     */
    private String source;
    /**
     * 项目Id
     */
    @Column(name="ProjectId")
    private String projectId;
    /**
     * 位置关联
     */
    @Column(name="AddressId")
    private String addressId;
    /**
     * 位置关联详情
     */
    @Column(name="AddressDetial")
    private String addressDetial;
    /**
     * 父设备
     */
    @Column(name="ParentId")
    private String parentId;
    /**
     * 维护人员
     */
    @Column(name="MaintenanceUser")
    private String maintenanceUser;
    /**
     * 设备Key
     */
    @Column(name="DeviceKey")
    private String deviceKey;
    /**
     * 产品Key
     */
    @Column(name="ProductKey")
    private String productKey;
    /**
     * 安装状态
     */
    @Column(name="InstallationState")
    private String installationState;
    /**
     * 维护状态
     */
    @Column(name="MaintenanceStatus")
    private String maintenanceStatus;
    /**
     * 维保时间
     */
    @Column(name="MaintenanceTime")
    private Date maintenanceTime;
    /**
     * 设备类型
     */
    @Column(name="DeviceType")
    private String deviceType;

    public String getAddType() {
        return addType;
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressDetial() {
        return addressDetial;
    }

    public void setAddressDetial(String addressDetial) {
        this.addressDetial = addressDetial;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMaintenanceUser() {
        return maintenanceUser;
    }

    public void setMaintenanceUser(String maintenanceUser) {
        this.maintenanceUser = maintenanceUser;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
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

    public Date getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(Date maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
