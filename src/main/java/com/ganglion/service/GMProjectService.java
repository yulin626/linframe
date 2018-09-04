package com.ganglion.service;

import com.ganglion.model.KeyValuePairInfo;
import com.ganglion.model.PageQueryInfo;
import com.ganglion.model.ProjectDTO;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;

import java.util.List;

public interface GMProjectService {

    /**
     * 获取所有应用
     */
    ResultResponse<List<KeyValuePairInfo>> getAllProjects();

    /**
     * 添加项目
     */
    ResultResponse addProject(ProjectDTO projectDTO);

    /**
     * 编辑项目
     */
    ResultResponse editProject(ProjectDTO projectDTO);

    /**
     * 删除项目
     */
    ResultResponse deleteProject(String projectId);

    /**
     * 获取项目
     */
    ResultResponse<ProjectDTO> getProject(String projectId);

    /**
     * 获取项目列表
     */
    ResultResponse<ListResultInfo<ProjectDTO>> getProjectList(PageQueryInfo pageQuery);
}
