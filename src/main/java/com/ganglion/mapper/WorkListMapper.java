package com.ganglion.mapper;

import com.ganglion.entity.WorkList;
import com.ganglion.model.WorkListDTO;
import com.ganglion.model.WorkListQueryInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface WorkListMapper extends Mapper<WorkList> {
    List<WorkListDTO> getWorkListListData(WorkListQueryInfo workListQueryInfo);
    WorkListDTO getWorkListDetailById(@Param("id") String id);
    /**
     * 根据设备获取工单
     */
    List<WorkListDTO> getWorkListsByDevice(@Param("deviceId") String deviceId);
}
