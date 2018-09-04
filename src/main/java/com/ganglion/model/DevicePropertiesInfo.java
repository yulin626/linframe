package com.ganglion.model;

import com.aliyun.iotx.api.model.ProductPropertiesResponseInfo;

import java.io.Serializable;

public class DevicePropertiesInfo extends ProductPropertiesResponseInfo implements Serializable {

    /**
     * 属性值
     */
    private Object value;

    /**
     * 属性采集时间
     */
    private String gmtModified;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }
}
