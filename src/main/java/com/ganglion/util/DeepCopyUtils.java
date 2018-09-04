package com.ganglion.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.lang.reflect.ParameterizedType;

public class DeepCopyUtils {

    public <From,To> To DeepCopyObject(From from) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        To to =  mapper.map(from, (Class <To>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        return to;
    }
}
