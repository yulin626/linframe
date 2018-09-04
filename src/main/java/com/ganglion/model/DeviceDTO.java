package com.ganglion.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DeviceDTO implements Serializable {

    private String id;
    private String name;
    /**
     * 添加方式
     */
    private String addType;
    /**
     * 设备来源
     */
    private String source;
    /**
     * 项目Id
     */
    private String projectId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 位置关联Id
     */
    private String addressId;
    /**
     * 位置关联名称
     */
    private String addressName;
    /**
     * 位置关联详情
     */
    private String addressDetial;
    /**
     * 父设备
     */
    private String parentId;
    /**
     * 维护人员
     */
    private String maintenanceUser;
    /**
     * 设备Key
     */
    private String deviceKey;
    /**
     * 产品Key
     */
    private String productKey;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 安装状态
     */
    private String installationState;
    /**
     * 维护状态
     */
    private String maintenanceStatus;
    /**
     * 维保时间
     */
    private Date maintenanceTime;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 物的状态 (0 ‑ 未激活，1 ‑ 在线，3 ‑ 离线，8 ‑ 禁用)
     */
    private Integer deviceStatus;
    /**
     * 最近上线时间
     */
    private Long latestOnlineTime;
    /**
     * 物的激活时间
     */
    private Long activeTime;
    /**
     * 设备属性
     */
    private List<DevicePropertiesInfo> DevicePropertiesInfos;
    /**
     * 设备Ids
     */
    private List<String> ids;

    private KeyValuePairInfo keyValueInfo;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Long getLatestOnlineTime() {
        return latestOnlineTime;
    }

    public void setLatestOnlineTime(Long latestOnlineTime) {
        this.latestOnlineTime = latestOnlineTime;
    }

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }

    public List<DevicePropertiesInfo> getDevicePropertiesInfos() {
        return DevicePropertiesInfos;
    }

    public void setDevicePropertiesInfos(List<DevicePropertiesInfo> devicePropertiesInfos) {
        DevicePropertiesInfos = devicePropertiesInfos;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public KeyValuePairInfo getKeyValueInfo() {
        return keyValueInfo;
    }

    public void setKeyValueInfo(KeyValuePairInfo keyValueInfo) {
        this.keyValueInfo = keyValueInfo;
    }
}
