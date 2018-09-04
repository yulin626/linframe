package com.ganglion.model;

public class WorkListQueryInfo extends PageQueryInfo {

    private String workListId;
    private String type;
    private String status;

    public String getWorkListId() {
        return workListId;
    }

    public void setWorkListId(String workListId) {
        this.workListId = workListId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
