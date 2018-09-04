package com.ganglion.mapper;

import com.ganglion.entity.EmplRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EmplRoleMapper extends Mapper<EmplRole> {

    @Select("select * from ORG_EmplRole where EmplID = #{emplId}")
    List<EmplRole> getRoleListByEmpl(@Param("emplId")String emplId);
}
