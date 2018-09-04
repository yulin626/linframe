package com.ganglion.service;

import com.ganglion.model.ResourceDTO;
import com.ganglion.model.ResourceQueryInfo;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;

public interface GMResourceService {

    /**
     *  获取SVG文件下载路径
     */
    ResultResponse<String> getFilePath(String resourceId);

    /**
     * 添加项目
     */
    ResultResponse addResource(ResourceDTO resourceDTO);

    /**
     * 编辑项目
     */
    ResultResponse editResource(ResourceDTO resourceDTO);

    /**
     * 删除项目
     */
    ResultResponse deleteResource(String resourceId);

    /**
     * 获取项目
     */
    ResultResponse<ResourceDTO> getResource(String resourceId);

    /**
     * 获取资源列表
     */
    ResultResponse<ListResultInfo<ResourceDTO>> getResourceList(ResourceQueryInfo resourceQueryInfo);
}
