package com.ganglion.mapper;

import com.ganglion.entity.FFProductForm;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FFProductFormMapper extends Mapper<FFProductForm> {

    @Select("select * from ZM_GM_FFProductForm where (ProductKey ='' or ProductKey is null) and ProjectId = #{projectId}")
    List<FFProductForm> getProductFormListByProjectId(@Param("projectId") String projectId);
}
