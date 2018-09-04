package com.aliyun.iotx.api.model;

import java.io.Serializable;

public class ProductInfoDTO implements Serializable {
    /**
     * 产品网络类型
     */
    private String netType;
    /**
     * 产品描述
     */
    private String description;
    /**
     * 产品标识符
     */
    private String productKey;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 节点类型
     */
    private String nodeType;

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
