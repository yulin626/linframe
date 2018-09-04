package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "ZM_GM_FFWebApp")
public class FFWebApp extends BaseEntity {

    /**
     * 应用Key
     */
    @Column(name = "AppKey")
    private String appKey;

    /**
     * 应用Secret
     */
    @Column(name = "AppSecret")
    private String appSecret;

    /**
     * 项目名称
     */
    @Column(name = "ProjectName")
    private String projectName;

    /**
     * 	项目Id
     */
    @Column(name = "ProjectId")
    private String projectId;

    /**
     * 数据接入类型
     */
    @Column(name = "DataAccessType")
    private String dataAccessType;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDataAccessType() {
        return dataAccessType;
    }

    public void setDataAccessType(String dataAccessType) {
        this.dataAccessType = dataAccessType;
    }
}
