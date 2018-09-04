package com.aliyun.iotx.api.model;

import java.io.Serializable;

public class ProductPropertiesResponseInfo implements Serializable {

    /**
     * 物的产品属性标识符
     */
    private String identifier;
    /**
     * 是否为标准属性
     */
    private Boolean std;
    /**
     * 是否可读写
     */
    private String rwFlag;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 属性描述
     */
    private String description;
    /**
     * 物的产品标识符
     */
    private String productKey;
    /**
     * 是否为必填
     */
    private String required;
    /**
     * 物的属性名称
     */
    private String name;
    /**
     * 物的属性id
     */
    private String propertyId;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Boolean getStd() {
        return std;
    }

    public void setStd(Boolean std) {
        this.std = std;
    }

    public String getRwFlag() {
        return rwFlag;
    }

    public void setRwFlag(String rwFlag) {
        this.rwFlag = rwFlag;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
}
