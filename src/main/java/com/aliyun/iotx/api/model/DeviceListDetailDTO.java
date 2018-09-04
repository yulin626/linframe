package com.aliyun.iotx.api.model;

import java.io.Serializable;

public class DeviceListDetailDTO implements Serializable {

    /**
     * 数据修改时间
     */
    private Long gmtModified;
    /**
     * 设备激活时间
     */
    private Long activeTime;
    /**
     * 设备创建时间
     */
    private Long gmtCreate;
    /**
     * 产品key
     */
    private String productKey;
    /**
     * 上一次设备状态
     */
    private Integer statusLast;
    /**
     * mac地址
     */
    private String mac;
    /**
     * 设备secret
     */
    private String deviceSecret;
    /**
     * 设备唯一标识符
     */
    private String iotId;
    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备类型
     */
    private String thingType;
    /**
     * sdk版本
     */
    private String sdkVersion;
    /**
     * 设备sn
     */
    private String sn;
    /**
     * 区域
     */
    private String region;
    /**
     * 固件版本号
     */
    private String firmwareVersion;
    /**
     * 租户id
     */
    private String rbacTenantId;
    /**
     * 设备状态
     */
    private Integer status;

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public Integer getStatusLast() {
        return statusLast;
    }

    public void setStatusLast(Integer statusLast) {
        this.statusLast = statusLast;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDeviceSecret() {
        return deviceSecret;
    }

    public void setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
    }

    public String getIotId() {
        return iotId;
    }

    public void setIotId(String iotId) {
        this.iotId = iotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThingType() {
        return thingType;
    }

    public void setThingType(String thingType) {
        this.thingType = thingType;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getRbacTenantId() {
        return rbacTenantId;
    }

    public void setRbacTenantId(String rbacTenantId) {
        this.rbacTenantId = rbacTenantId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
