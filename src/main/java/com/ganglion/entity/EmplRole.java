package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "ORG_EmplRole")
public class EmplRole {
    @Column(name = "EmplID")
    private String emplID;
    @Column(name = "RoleID")
    private String roleID;
    @Column(name = "CreateTime")
    private Date createTime;

    public String getEmplID() {
        return emplID;
    }

    public void setEmplID(String emplID) {
        this.emplID = emplID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
