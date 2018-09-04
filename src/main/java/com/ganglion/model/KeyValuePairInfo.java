package com.ganglion.model;

public class KeyValuePairInfo<TKey, TValue> {

    private TKey key ;
    private TValue value ;

    public TKey getKey() {
        return key;
    }

    public void setKey(TKey key) {
        this.key = key;
    }

    public TValue getValue() {
        return value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }
}
