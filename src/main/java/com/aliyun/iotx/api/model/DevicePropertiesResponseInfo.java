package com.aliyun.iotx.api.model;

import java.io.Serializable;

public class DevicePropertiesResponseInfo implements Serializable {

    /**
     * 物的标识符
     */
    private String iotId;
    /**
     * 属性上报批次id
     */
    private String batchId;
    /**
     * 属性标识符
     */
    private String attribute;
    /**
     * 属性值
     */
    private Object value;
    /**
     * 属性采集时间
     */
    private Long gmtModified;

    public String getIotId() {
        return iotId;
    }

    public void setIotId(String iotId) {
        this.iotId = iotId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
