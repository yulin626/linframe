package com.ganglion.controller;

import com.ganglion.model.KeyQuery;
import com.ganglion.model.KeyValuePairInfo;
import com.ganglion.model.PageQueryInfo;
import com.ganglion.model.ProjectDTO;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.GMProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/gmProject")
public class GMProjectController {

    @Autowired
    private GMProjectService gmProjectService;

    /**
     * 获取所有应用
     */
    @CrossOrigin
    @PostMapping(value = "/getAllProjects")
    public ResultResponse<List<KeyValuePairInfo>> getAllProjects() {
        return gmProjectService.getAllProjects();
    }

    /**
     * 新增项目
     */
    @CrossOrigin
    @PostMapping(value = "/addProject")
    public ResultResponse addProject(@RequestBody ProjectDTO projectDTO) {
        return gmProjectService.addProject(projectDTO);
    }

    /**
     * 编辑项目
     */
    @CrossOrigin
    @PostMapping(value = "/editProject")
    public ResultResponse editProject(@RequestBody ProjectDTO projectDTO) {
        return gmProjectService.editProject(projectDTO);
    }

    /**
     * 删除项目
     */
    @CrossOrigin
    @PostMapping(value = "/deleteProject")
    public ResultResponse deleteProject(@RequestBody KeyQuery keyQuery){
        return gmProjectService.deleteProject(keyQuery.getKey());
    }

    /**
     * 获取项目
     */
    @CrossOrigin
    @PostMapping(value = "/getProject")
    public ResultResponse<ProjectDTO> getProject(@RequestBody ProjectDTO projectDTO){
        return gmProjectService.getProject(projectDTO.getId());
    }

    /**
     * 获取项目列表
     */
    @CrossOrigin
    @PostMapping(value = "/getProjectList")
    public ResultResponse<ListResultInfo<ProjectDTO>> getProjectList(@RequestBody PageQueryInfo pageQuery) {
        return gmProjectService.getProjectList(pageQuery);
    }
}
