package com.ganglion.model;

import java.util.List;

public interface BaseTreeInfo<T> {
    List<T> getChildren();

    void setChildren(List<T> tList);

    String getId();

    void setId(String id);

    String getParentId();

    void setParentId(String parentId);
}
