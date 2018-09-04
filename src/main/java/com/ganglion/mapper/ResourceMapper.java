package com.ganglion.mapper;

import com.ganglion.entity.Resource;
import com.ganglion.model.ResourceDTO;
import com.ganglion.model.ResourceQueryInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResourceMapper extends Mapper<Resource> {

    List<ResourceDTO> getResourceList(ResourceQueryInfo resourceQueryInfo);
}
