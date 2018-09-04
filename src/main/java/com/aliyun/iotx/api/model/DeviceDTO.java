package com.aliyun.iotx.api.model;

import java.io.Serializable;

public class DeviceDTO implements Serializable {
    /**
     * 物的激活时间
     */
    private Long activeTime;
    /**
     * 物的产品标识符
     */
    private String productKey;
    /**
     * 物的mac地址
     */
    private String mac;
    /**
     * 物的标识符
     */
    private String iotId;
    /**
     * 物的名称
     */
    private String name;
    /**
     * 物的昵称
     */
    private String nickname;
    /**
     * 物的sdk版本
     */
    private String sdkVersion;
    /**
     * 物的sn
     */
    private String sn;
    /**
     * 物的固件版本号
     */
    private String firmwareVersion;
    /**
     * 物的状态 (0 ‑ 未激活，1 ‑在线，3 ‑ 离线，8 ‑ 禁用)
     */
    private Integer status;

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
