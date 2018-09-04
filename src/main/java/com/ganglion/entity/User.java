package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "ORG_User")
public class User {
    @Id
    @Column(name = "UserName")
    private String userName;
    @Column(name = "UserPwd")
    private String userPwd;
    @Column(name = "EmplID")
    private String emplID;
    @Column(name = "UserLogonTimes")
    private Integer uerLogonTimes;
    @Column(name = "UserNeedChgPwd")
    private Integer userNeedChgPwd;
    @Column(name = "UserDisabled")
    private Integer userDisabled;
    @Column(name = "CreateTime")
    private Date createTime;
    @Column(name = "UpdateTime")
    private Date updateTime;
    @Column(name = "UserLockTime")
    private Date userLockTime;
    @Column(name = "UserAllowAutoUnlock")
    private Integer userAllowAutoUnlock;
    @Column(name = "UserTypeID")
    private String userTypeID;
    @Column(name = "UserPwdLastChangeTime")
    private Date userPwdLastChangeTime;
    @Column(name = "UserPwdHistoryStr")
    private String userPwdHistoryStr;
    @Column(name = "UserLastLogon")
    private Date userLastLogon;
    @Column(name = "UserLastLogonIP")
    private String userLastLogonIP;
    @Column(name = "UserLogonFailTimes")
    private Integer userLogonFailTimes;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEmplID() {
        return emplID;
    }

    public void setEmplID(String emplID) {
        this.emplID = emplID;
    }

    public Integer getUerLogonTimes() {
        return uerLogonTimes;
    }

    public void setUerLogonTimes(Integer uerLogonTimes) {
        this.uerLogonTimes = uerLogonTimes;
    }

    public Integer getUserNeedChgPwd() {
        return userNeedChgPwd;
    }

    public void setUserNeedChgPwd(Integer userNeedChgPwd) {
        this.userNeedChgPwd = userNeedChgPwd;
    }

    public Integer getUserDisabled() {
        return userDisabled;
    }

    public void setUserDisabled(Integer userDisabled) {
        this.userDisabled = userDisabled;
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

    public Date getUserLockTime() {
        return userLockTime;
    }

    public void setUserLockTime(Date userLockTime) {
        this.userLockTime = userLockTime;
    }

    public Integer getUserAllowAutoUnlock() {
        return userAllowAutoUnlock;
    }

    public void setUserAllowAutoUnlock(Integer userAllowAutoUnlock) {
        this.userAllowAutoUnlock = userAllowAutoUnlock;
    }

    public String getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(String userTypeID) {
        this.userTypeID = userTypeID;
    }

    public Date getUserPwdLastChangeTime() {
        return userPwdLastChangeTime;
    }

    public void setUserPwdLastChangeTime(Date userPwdLastChangeTime) {
        this.userPwdLastChangeTime = userPwdLastChangeTime;
    }

    public String getUserPwdHistoryStr() {
        return userPwdHistoryStr;
    }

    public void setUserPwdHistoryStr(String userPwdHistoryStr) {
        this.userPwdHistoryStr = userPwdHistoryStr;
    }

    public Date getUserLastLogon() {
        return userLastLogon;
    }

    public void setUserLastLogon(Date userLastLogon) {
        this.userLastLogon = userLastLogon;
    }

    public String getUserLastLogonIP() {
        return userLastLogonIP;
    }

    public void setUserLastLogonIP(String userLastLogonIP) {
        this.userLastLogonIP = userLastLogonIP;
    }

    public Integer getUserLogonFailTimes() {
        return userLogonFailTimes;
    }

    public void setUserLogonFailTimes(Integer userLogonFailTimes) {
        this.userLogonFailTimes = userLogonFailTimes;
    }
}
