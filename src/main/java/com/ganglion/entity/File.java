package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * ZM_DF_File
 */
@Table(name = "ZM_DF_File")
public class File {

    @Id
    private String id;

    @Column(name="IsDelete")
    private Boolean isDelete;

    @Column(name = "OrderIndex")
    private String orderIndex;

    private String remark;

    @Column(name="CreatedBy")
    private String createdBy;
    @Column(name="CreatedTime")
    private Date createdTime;
    @Column(name="CreatedDept")
    private String createdDept;
    @Column(name="UpdatedBy")
    private String updatedBy;
    @Column(name="UpdatedTime")
    private Date updatedTime;
    @Column(name="UpdatedDept")
    private String updatedDept;

    @Column(name="FileName")
    private String fileName;

    private Long size;

    @Column(name = "ExtensionName")
    private String extensionName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedDept() {
        return createdDept;
    }

    public void setCreatedDept(String createdDept) {
        this.createdDept = createdDept;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedDept() {
        return updatedDept;
    }

    public void setUpdatedDept(String updatedDept) {
        this.updatedDept = updatedDept;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getExtensionName() {
        return extensionName;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }
}
