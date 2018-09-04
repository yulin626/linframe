package com.ganglion.model;

import java.io.Serializable;
import java.util.List;

public class AddressDTO implements Serializable, BaseTreeInfo<AddressDTO> {

    private String id;
    private String name;
    private String text;
    private String parentId;
    private String addressType;
    private String addressTypeName;
    private String remark;
    private List<DeviceDTO> relationDeviceList;
    private String detailedAddress;
    private String associatedResources;
    private String gps;
    private String svg;
    private String projectId;
    private String resourceId;
    private List<AddressDTO> children;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressTypeName() {
        return addressTypeName;
    }

    public void setAddressTypeName(String addressTypeName) {
        this.addressTypeName = addressTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DeviceDTO> getRelationDeviceList() {
        return relationDeviceList;
    }

    public void setRelationDeviceList(List<DeviceDTO> relationDeviceList) {
        this.relationDeviceList = relationDeviceList;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getAssociatedResources() {
        return associatedResources;
    }

    public void setAssociatedResources(String associatedResources) {
        this.associatedResources = associatedResources;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<AddressDTO> getChildren() {
        return children;
    }

    public void setChildren(List<AddressDTO> children) {
        this.children = children;
    }
}
