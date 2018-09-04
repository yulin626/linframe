package com.ganglion.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用接口
 */
@Service
public interface IService<T> {

    T selectByKey(Object key);

    int insert(T entity);

    int deleteByKey(Object key);

    int updateByKey(T entity);

    List<T> selectAll();
}
