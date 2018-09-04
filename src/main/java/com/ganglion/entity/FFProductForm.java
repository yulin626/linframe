package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "ZM_GM_FFProductForm")
public class FFProductForm extends BaseEntity {

    /**
     * 项目Id
     */
    @Column(name = "ProjectId")
    private String projectId;

    /**
     * 产品Key
     */
    @Column(name = "ProductKey")
    private String productKey;

    /**
     * 产品表单
     */
    private String form;

    /**
     * 表单列
     */
    private String cols;

    /**
     * 飞凤表mid
     */
    private String mid;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getCols() {
        return cols;
    }

    public void setCols(String cols) {
        this.cols = cols;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
