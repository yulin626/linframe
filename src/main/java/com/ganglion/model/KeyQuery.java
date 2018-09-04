package com.ganglion.model;

import java.io.Serializable;
import java.util.List;

public class KeyQuery implements Serializable {

    private String key;

    private List<String> keys;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }
}
