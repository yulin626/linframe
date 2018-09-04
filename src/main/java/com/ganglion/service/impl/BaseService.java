package com.ganglion.service.impl;

import com.ganglion.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper(){ return mapper; }

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int insert(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int deleteByKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int updateByKey(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }
}
