package com.ganglion.model;

import com.ganglion.entity.Address;

import java.io.Serializable;
import java.util.List;

public class AddressDeviceQuery implements Serializable {
    private String parentId;
    private String deviceProjects;
    private String deviceTypes;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDeviceProjects() {
        return deviceProjects;
    }

    public void setDeviceProjects(String deviceProjects) {
        this.deviceProjects = deviceProjects;
    }

    public String getDeviceTypes() {
        return deviceTypes;
    }

    public void setDeviceTypes(String deviceTypes) {
        this.deviceTypes = deviceTypes;
    }
}
