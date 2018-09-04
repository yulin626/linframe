package com.ganglion.mapper;

import com.ganglion.entity.FFWebApp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FFWebAppMapper extends Mapper<FFWebApp> {

    @Select("select * from ZM_GM_FFWebApp where ProjectId = #{projectId}")
    FFWebApp getWebAppByProjectId(@Param("projectId") String projectId);

    @Select("select * from ZM_GM_FFWebApp where IsDelete !=1 ")
    List<FFWebApp> getWebAppList();

}
