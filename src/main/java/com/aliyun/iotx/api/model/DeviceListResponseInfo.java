package com.aliyun.iotx.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * 获取飞凤产品设备列表返回对象（/thing/device/list）
 */
public class DeviceListResponseInfo implements Serializable {

    private Integer totalNum;

    private List<DeviceListDetailDTO> items;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public List<DeviceListDetailDTO> getItems() {
        return items;
    }

    public void setItems(List<DeviceListDetailDTO> items) {
        this.items = items;
    }
}
