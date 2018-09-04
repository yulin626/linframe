package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "ORG_Employee")
public class Employee {

    @Id
    @Column(name = "EmplID")
    private String emplID;
    @Column(name = "EmplNO")
    private String emplNO;
    @Column(name = "EmplName")
    private String emplName;
    @Column(name = "EmplNameL")
    private String emplNameL;
    @Column(name = "EmplNameF")
    private String emplNameF;
    @Column(name = "EmplSex")
    private String emplSex;
    @Column(name = "EmplBirth")
    private Date emplBirth;
    @Column(name = "EmplEnabled")
    private Integer emplEnabled;
    @Column(name = "ManagerID")
    private String managerID;
    @Column(name = "DeptID")
    private String deptID;
    @Column(name = "AttendDate")
    private Date attendDate;
    @Column(name = "TimeZone")
    private Integer timeZone;
    private String locale;
    @Column(name = "ThirdPartyImage")
    private String thirdPartyImage;
    @Column(name = "DisableTime")
    private Date disableTime;
    @Column(name = "CreateTime")
    private Date createTime;
    @Column(name = "UpdateTime")
    private Date updateTime;
    @Column(name = "OfficeAddr")
    private String officeAddr;

    public String getEmplID() {
        return emplID;
    }

    public void setEmplID(String emplID) {
        this.emplID = emplID;
    }

    public String getEmplNO() {
        return emplNO;
    }

    public void setEmplNO(String emplNO) {
        this.emplNO = emplNO;
    }

    public String getEmplName() {
        return emplName;
    }

    public void setEmplName(String emplName) {
        this.emplName = emplName;
    }

    public String getEmplNameL() {
        return emplNameL;
    }

    public void setEmplNameL(String emplNameL) {
        this.emplNameL = emplNameL;
    }

    public String getEmplNameF() {
        return emplNameF;
    }

    public void setEmplNameF(String emplNameF) {
        this.emplNameF = emplNameF;
    }

    public String getEmplSex() {
        return emplSex;
    }

    public void setEmplSex(String emplSex) {
        this.emplSex = emplSex;
    }

    public Date getEmplBirth() {
        return emplBirth;
    }

    public void setEmplBirth(Date emplBirth) {
        this.emplBirth = emplBirth;
    }

    public Integer getEmplEnabled() {
        return emplEnabled;
    }

    public void setEmplEnabled(Integer emplEnabled) {
        this.emplEnabled = emplEnabled;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public Date getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(Date attendDate) {
        this.attendDate = attendDate;
    }

    public Integer getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Integer timeZone) {
        this.timeZone = timeZone;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getThirdPartyImage() {
        return thirdPartyImage;
    }

    public void setThirdPartyImage(String thirdPartyImage) {
        this.thirdPartyImage = thirdPartyImage;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
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

    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr;
    }
}
