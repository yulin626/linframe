package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "ORG_Role")
public class Role {
    @Id
    @Column(name = "RoleID")
    private String roleID;
    @Column(name = "RoleName")
    private String roleName;
    @Column(name = "RoleDescr")
    private String roleDescr;
    @Column(name = "CreateTime")
    private Date createTime;
    @Column(name = "UpdateTime")
    private Date updateTime;

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescr() {
        return roleDescr;
    }

    public void setRoleDescr(String roleDescr) {
        this.roleDescr = roleDescr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
