package com.ganglion.model;

import java.io.Serializable;

public class PageQueryInfo implements Serializable {

    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 当前页码
     */
    private Integer pageNumber;
    /**
     * 查询条件
     */
    private String queryCondition;
    /**
     * 排序列名
     */
    private String sortColumn;
    /**
     * 排序asc顺序，desc逆序
     */
    private String sortDirection;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
