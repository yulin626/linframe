package com.ganglion.model;

public class FFWebAppInfo {

    /**
     * 项目Id
     */
    private String projectId;
    /**
     * 项目名称
     */
    private String ProjectName;
    /**
     * 产品数量
     */
    private Integer ProductCount;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public Integer getProductCount() {
        return ProductCount;
    }

    public void setProductCount(Integer productCount) {
        ProductCount = productCount;
    }
}
