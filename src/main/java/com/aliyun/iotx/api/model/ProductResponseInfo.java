package com.aliyun.iotx.api.model;

import java.io.Serializable;

/**
 * 获取飞凤产品列表返回对象（/thing/product/list/get）
 */
public class ProductResponseInfo implements Serializable {

    private String accessMethod;
    private Long gmtModified;
    private String productModel;
    private String modifier;
    private String productKey;
    private String categoryName;
    private String creator;
    private String productId;
    private String dataFormat;
    private String netType;
    private String aliyunCommodityCode;
    private String productSecret;
    private String categoryKey;
    private String gmtCreate;
    private String nodeType;
    private String domain;
    private String name;
    private String tenantId;
    private String region;
    private String ownerDomain;
    private String rbacTenantId;
    private String categoryId;
    private String status;
    private String description;

    public String getAccessMethod() {
        return accessMethod;
    }

    public void setAccessMethod(String accessMethod) {
        this.accessMethod = accessMethod;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getAliyunCommodityCode() {
        return aliyunCommodityCode;
    }

    public void setAliyunCommodityCode(String aliyunCommodityCode) {
        this.aliyunCommodityCode = aliyunCommodityCode;
    }

    public String getProductSecret() {
        return productSecret;
    }

    public void setProductSecret(String productSecret) {
        this.productSecret = productSecret;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOwnerDomain() {
        return ownerDomain;
    }

    public void setOwnerDomain(String ownerDomain) {
        this.ownerDomain = ownerDomain;
    }

    public String getRbacTenantId() {
        return rbacTenantId;
    }

    public void setRbacTenantId(String rbacTenantId) {
        this.rbacTenantId = rbacTenantId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //    public String scriptDraftCode;
//    public String extendProperties;
//    public String connectMode;
//    public String deviceNumLimit;
//    public String image;
//    public String scriptId;
//    public String appEui;
//    public String classType;
}
