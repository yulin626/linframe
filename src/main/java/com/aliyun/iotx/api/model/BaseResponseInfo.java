package com.aliyun.iotx.api.model;

import java.io.Serializable;

public class BaseResponseInfo<T> implements Serializable {

    private String code;

    private T data;

    private String id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
