package com.ganglion.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 *  ZM_GM_WorkList
 */
@Table(name = "ZM_GM_WorkList")
public class WorkList extends BaseEntity {
    /**
     * 工单号
     */
    private String no;
    /**
     * 所属项目
     */
    @Column(name="ProjectId")
    private String projectId;
    /**
     * 工单状态
     */
    private String status;
    /**
     * 工单类型
     */
    private String type;
    /**
     * 接单人员
     */
    private String receiver;
    /**
     * 关闭时间
     */
    @Column(name="CloseTime")
    private Date closeTime;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
}
