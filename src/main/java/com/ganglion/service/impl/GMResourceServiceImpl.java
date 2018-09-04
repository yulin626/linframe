package com.ganglion.service.impl;

import com.ganglion.constant.CommonConstants;
import com.ganglion.entity.File;
import com.ganglion.entity.Resource;
import com.ganglion.mapper.AddressMapper;
import com.ganglion.mapper.FileMapper;
import com.ganglion.mapper.ResourceMapper;
import com.ganglion.model.ResourceDTO;
import com.ganglion.model.ResourceQueryInfo;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.GMResourceService;
import com.ganglion.util.InitEntityExtend;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GMResourceServiceImpl extends BaseService<Resource> implements GMResourceService {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 获取SVG文件下载路径
     */
    @Override
    public ResultResponse<String> getFilePath(String resourceId) {
        Resource resource = this.selectByKey(resourceId);
        File file = fileMapper.selectByPrimaryKey(resource.getSvg());

        String path;
        if (file == null)
            path = "";
        else
            path = file.getId() + file.getExtensionName();

        ResultResponse<String> result = new ResultResponse<String>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(path);

        return result;
    }

    /**
     * 添加资源
     */
    @Override
    public ResultResponse addResource(ResourceDTO resourceDTO) {
        //深度拷贝
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Resource resource = mapper.map(resourceDTO, Resource.class);

        InitEntityExtend.initCreateEntity(resource);

        this.insert(resource);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 编辑资源
     */
    @Override
    public ResultResponse editResource(ResourceDTO resourceDTO) {

        Resource resource = this.selectByKey(resourceDTO.getId());
        resource.setName(resourceDTO.getName());
        resource.setProjectId(resourceDTO.getProjectId());
        resource.setType(resourceDTO.getType());
        resource.setSvg(resourceDTO.getSvg());

        InitEntityExtend.initUpdateEntity(resource);

        this.updateByKey(resource);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 删除资源
     */
    @Override
    public ResultResponse deleteResource(String resourceId) {
        ResultResponse result = new ResultResponse();
        //地址Check
        if(addressMapper.selectAll().stream().filter(a->resourceId.equals(a.getAssociatedResources())&&a.getDelete().equals(false)).collect(Collectors.toList()).size()>0){
            result.setStatus(0);
            result.setMessage("地址中存在该资源，不可删除！");
            return result;
        }

        this.deleteByKey(resourceId);

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 获取资源
     */
    @Override
    public ResultResponse<ResourceDTO> getResource(String resourceId) {
        Resource resource = this.selectByKey(resourceId);
        //深度拷贝
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        ResourceDTO resourceDTO = mapper.map(resource, ResourceDTO.class);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(resourceDTO);
        return result;
    }

    /**
     * 获取资源列表
     */
    @Override
    public ResultResponse<ListResultInfo<ResourceDTO>> getResourceList(ResourceQueryInfo resourceQueryInfo) {
        //分页查询
        Page page = PageHelper.startPage(resourceQueryInfo.getPageNumber(), resourceQueryInfo.getPageSize(), true);
        List<ResourceDTO> resourceList = resourceMapper.getResourceList(resourceQueryInfo);

        ResultResponse<ListResultInfo<ResourceDTO>> result = new ResultResponse();
        ListResultInfo<ResourceDTO> listResultInfo = new ListResultInfo<>();
        listResultInfo.setList(resourceList);
        listResultInfo.setCount(page.getTotal());

        result.setData(listResultInfo);
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);

        return result;
    }
}
