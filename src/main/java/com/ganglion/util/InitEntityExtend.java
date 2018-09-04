package com.ganglion.util;

import com.ganglion.entity.BaseEntity;
import com.ganglion.model.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.UUID;

public class InitEntityExtend{

    public static<T extends BaseEntity> void initCreateEntity(T entity) {
        JwtUser userDetails = (JwtUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        entity.setId(UUID.randomUUID().toString());
        entity.setDelete(false);
        entity.setCreatedBy(userDetails.getEmplId());
        entity.setCreatedDept("0");
        entity.setCreatedTime(new Date());
        entity.setUpdatedBy(userDetails.getEmplId());
        entity.setUpdatedDept("0");
        entity.setUpdatedTime(new Date());
    }

    public static<T extends BaseEntity> void initUpdateEntity(T entity) {
        JwtUser userDetails = (JwtUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        entity.setUpdatedBy(userDetails.getEmplId());
        entity.setUpdatedDept("0");
        entity.setUpdatedTime(new Date());
    }
}
