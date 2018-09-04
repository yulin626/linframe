package com.ganglion.model;

public class ResourceQueryInfo extends PageQueryInfo {

    /**
     * 资源类型
     */
    private String type;
    /**
     * 所属项目
     */
    private String projectId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
