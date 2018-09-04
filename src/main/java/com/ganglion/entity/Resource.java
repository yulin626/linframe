package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * ZM_GM_Resource
 */
@Table(name = "ZM_GM_Resource")
public class Resource extends BaseEntity {

    /**
     * 资源类型
     */
    private String type;
    /**
     * 所属项目Id
     */
    @Column(name = "ProjectId")
    private String projectId;
    /**
     * SVG资源
     */
    private String svg;

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

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }
}
