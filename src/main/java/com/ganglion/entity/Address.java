package com.ganglion.entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Table(name = "ZM_GM_Address")
public class Address extends BaseEntity {

    /**
     * 地址类型
     */
    @Column(name="AddressType")
    private String addressType;
    /**
     * 详细地址
     */
    @Column(name = "DetailedAddress")
    private String detailedAddress;
    /**
     * 关联资源
     */
    @Column(name = "AssociatedResources")
    private String associatedResources;
    /**
     * GPS
     */
    @Column(name = "GPS")
    private String gps;
    /**
     * SVG
     */
    @Column(name = "SVG")
    private String svg;
    /**
     * 上级位置
     */
    @Column(name = "ParentId")
    private String parentId;
    /**
     * 所属项目Id
     */
    @Column(name = "ProjectId")
    private String projectId;

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
