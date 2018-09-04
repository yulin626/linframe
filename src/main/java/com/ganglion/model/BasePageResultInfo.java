package com.ganglion.model;

import java.io.Serializable;
import java.util.Date;

public class BasePageResultInfo implements Serializable {

    private int RowNumber;
    private Date UpdatedTime;

    public int getRowNumber() {
        return RowNumber;
    }

    public void setRowNumber(int rowNumber) {
        RowNumber = rowNumber;
    }

    public Date getUpdatedTime() {
        return UpdatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        UpdatedTime = updatedTime;
    }
}
