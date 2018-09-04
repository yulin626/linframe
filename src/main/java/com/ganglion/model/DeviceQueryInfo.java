package com.ganglion.model;

public class DeviceQueryInfo extends PageQueryInfo {

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
     * 安装状态
     */
    private String installationState;
    /**
     * 维保状态
     */
    private String maintenanceStatus;
    /**
     * 是否查询列表(去掉飞凤实时请求)
     */
    private Boolean isSelect;

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

    public String getInstallationState() {
        return installationState;
    }

    public void setInstallationState(String installationState) {
        this.installationState = installationState;
    }

    public Boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Boolean select) {
        isSelect = select;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(String maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }
}
