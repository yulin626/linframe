package com.aliyun.iotx.api.model;

import java.io.Serializable;

/**
 * 获取飞凤物的详情返回对象（/thing/device/detail/get）
 */
public class DeviceDetailResponseInfo implements Serializable {

    /**
     * 最近上线时间
     */
    private Long latestOnlineTime;
    /**
     * lora设备设备地址
     */
    private String devAddress;
    /**
     * 非lora设备设备ip
     */
    private String clientIp;
    /**
     * 设备激活时间
     */
    private Long activeTime;
    /**
     * 物的产品信息
     */
    private ProductInfoDTO productInfoDTO;
    /**
     * 物的基本信息
     */
    private DeviceDTO deviceDTO;
    /**
     * lora设备appEui
     */
    private String appEui;

    public Long getLatestOnlineTime() {
        return latestOnlineTime;
    }

    public void setLatestOnlineTime(Long latestOnlineTime) {
        this.latestOnlineTime = latestOnlineTime;
    }

    public String getDevAddress() {
        return devAddress;
    }

    public void setDevAddress(String devAddress) {
        this.devAddress = devAddress;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }

    public ProductInfoDTO getProductInfoDTO() {
        return productInfoDTO;
    }

    public void setProductInfoDTO(ProductInfoDTO productInfoDTO) {
        this.productInfoDTO = productInfoDTO;
    }

    public DeviceDTO getDeviceDTO() {
        return deviceDTO;
    }

    public void setDeviceDTO(DeviceDTO deviceDTO) {
        this.deviceDTO = deviceDTO;
    }

    public String getAppEui() {
        return appEui;
    }

    public void setAppEui(String appEui) {
        this.appEui = appEui;
    }
}
