package com.ganglion.mapper;

import com.ganglion.entity.Project;
import com.ganglion.model.PageQueryInfo;
import com.ganglion.model.ProjectDTO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProjectMapper extends Mapper<Project> {

    List<ProjectDTO> getProjectList(PageQueryInfo pageQuery);
}
