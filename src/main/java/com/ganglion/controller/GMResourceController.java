package com.ganglion.controller;

import com.ganglion.model.KeyQuery;
import com.ganglion.model.ResourceDTO;
import com.ganglion.model.ResourceQueryInfo;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.GMResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/gmResource")
public class GMResourceController {

    @Autowired
    private GMResourceService gmResourceService;

    /**
     * 获取SVG文件下载路径
     */
    @CrossOrigin
    @PostMapping(value = "/getFilePath")
    public ResultResponse<String> getFilePath(@RequestBody ResourceDTO resourceDTO) {
        return gmResourceService.getFilePath(resourceDTO.getId());
    }

    /**
     * 新增资源
     */
    @CrossOrigin
    @PostMapping(value = "/addResource")
    public ResultResponse addResource(@RequestBody ResourceDTO resourceDTO) {
        return gmResourceService.addResource(resourceDTO);
    }

    /**
     * 编辑资源
     */
    @CrossOrigin
    @PostMapping(value = "/editResource")
    public ResultResponse editResource(@RequestBody ResourceDTO resourceDTO) {
        return gmResourceService.editResource(resourceDTO);
    }

    /**
     * 删除资源
     */
    @CrossOrigin
    @PostMapping(value = "/deleteResource")
    public ResultResponse deleteResource(@RequestBody KeyQuery keyQuery){
        return gmResourceService.deleteResource(keyQuery.getKey());
    }

    /**
     * 获取资源
     */
    @CrossOrigin
    @PostMapping(value = "/getResource")
    public ResultResponse<ResourceDTO> getResource(@RequestBody ResourceDTO resourceDTO){
        return gmResourceService.getResource(resourceDTO.getId());
    }

    /**
     * 获取资源列表
     */
    @CrossOrigin
    @PostMapping(value = "/getResourceList")
    public ResultResponse<ListResultInfo<ResourceDTO>> getResourceList(@RequestBody ResourceQueryInfo resourceQueryInfo) {
        return gmResourceService.getResourceList(resourceQueryInfo);
    }
}
