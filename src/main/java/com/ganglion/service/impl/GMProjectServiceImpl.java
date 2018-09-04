package com.ganglion.service.impl;

import com.ganglion.constant.CommonConstants;
import com.ganglion.entity.Project;
import com.ganglion.mapper.*;
import com.ganglion.model.KeyValuePairInfo;
import com.ganglion.model.PageQueryInfo;
import com.ganglion.model.ProjectDTO;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.GMProjectService;
import com.ganglion.util.InitEntityExtend;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GMProjectServiceImpl extends BaseService<Project> implements GMProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private WorkListMapper workListMapper;


    /**
     * 获取所有应用
     */
    @Override
    public ResultResponse<List<KeyValuePairInfo>> getAllProjects() {
        List<KeyValuePairInfo> list = new ArrayList<>();
        List<Project> projectList = this.selectAll().stream().filter(a-> a.getDelete().equals(false)).collect(Collectors.toList());
        for (int i = 0; i < projectList.size(); i++) {
            KeyValuePairInfo info = new KeyValuePairInfo();
            info.setKey(projectList.get(i).getId());
            info.setValue(projectList.get(i).getName());
            list.add(info);
        }

        ResultResponse<List<KeyValuePairInfo>> result = new ResultResponse<List<KeyValuePairInfo>>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(list);
        return result;
    }

    /**
     * 添加项目
     */
    @Override
    public ResultResponse addProject(ProjectDTO projectDTO) {
        //深度拷贝
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Project project = mapper.map(projectDTO, Project.class);

        InitEntityExtend.initCreateEntity(project);

        this.insert(project);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 编辑项目
     */
    @Override
    public ResultResponse editProject(ProjectDTO projectDTO) {
        Project project = this.selectByKey(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setIntroduction(projectDTO.getIntroduction());
        project.setRemark(projectDTO.getRemark());

        InitEntityExtend.initUpdateEntity(project);

        this.updateByKey(project);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 删除项目
     */
    @Override
    public ResultResponse deleteProject(String projectId) {
        ResultResponse result = new ResultResponse();
        //地址Check
        if(addressMapper.getAllAddressByProjectId(projectId).size()>0){
            result.setStatus(0);
            result.setMessage("地址中存在该项目，不可删除！");
            return result;
        }
        //资源Check
        if(resourceMapper.selectAll().stream().filter(a->projectId.equals(a.getProjectId())&&a.getDelete().equals(false)).collect(Collectors.toList()).size()>0){
            result.setStatus(0);
            result.setMessage("资源中存在该项目，不可删除！");
            return result;
        }
        //设备Check
        if (deviceMapper.selectAll().stream().filter(a->projectId.equals(a.getProjectId())&&a.getDelete().equals(false)).collect(Collectors.toList()).size()>0){
            result.setStatus(0);
            result.setMessage("设备中存在该项目，不可删除！");
            return result;
        }
        //工单Check
        if (workListMapper.selectAll().stream().filter(a->projectId.equals(a.getProjectId())&&a.getDelete().equals(false)).collect(Collectors.toList()).size()>0){
            result.setStatus(0);
            result.setMessage("工单中存在该项目，不可删除！");
            return result;
        }

        this.deleteByKey(projectId);

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 获取项目
     */
    @Override
    public ResultResponse<ProjectDTO> getProject(String projectId) {
        Project project = this.selectByKey(projectId);
        //深度拷贝
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        ProjectDTO projectDTO = mapper.map(project, ProjectDTO.class);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(projectDTO);
        return result;
    }

    /**
     * 获取项目列表
     */
    @Override
    public ResultResponse<ListResultInfo<ProjectDTO>> getProjectList(PageQueryInfo pageQuery) {
        //分页查询
        Page page = PageHelper.startPage(pageQuery.getPageNumber(), pageQuery.getPageSize(), true);
        List<ProjectDTO> projectList = projectMapper.getProjectList(pageQuery);

        ResultResponse<ListResultInfo<ProjectDTO>> result = new ResultResponse();
        ListResultInfo<ProjectDTO> listResult = new ListResultInfo<>();
        listResult.setList(projectList);
        listResult.setCount(page.getTotal());

        result.setData(listResult);
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }
}
