package com.ganglion.msg;

import java.util.List;

public class ListResultInfo<T> {
    private long count;

    private List<T> list;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
