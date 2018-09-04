package com.ganglion.mapper;

import com.ganglion.entity.WorkListDetail;
import com.ganglion.model.WorkListDetailDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface WorkListDetailMapper extends Mapper<WorkListDetail> {

    /**
     * 根据工单Id和设备Id获取工单详情
     */
    @Select("select * from ZM_GM_WorkListDetail where WorkListId = #{workListId} and DeviceId = #{deviceId} and IsDelete!=1")
    WorkListDetail getWorkListDetailByIds(WorkListDetailDTO workListDetailDTO);

    /**
     * 根据设备Id获取安装工单详情
     */
    @Select("select b.* from ZM_GM_WorkList a " +
            "inner join ZM_GM_WorkListDetail b on a.Id = b.WorkListId " +
            "where a.Type = 'ZM_GM_WorkType_Install' and a.IsDelete != 1 and a.Status != 'ZM_GM_WorkState_Obsolete' and a.Status != 'ZM_GM_WorkState_Resolved' and b.DeviceId = #{deviceId}")
    WorkListDetail getInstallWorkListById(@Param("deviceId") String deviceId);

    /**
     * 根据工单Id获取工单详情列表
     */
    @Select("select * from ZM_GM_WorkListDetail where WorkListId = #{workListId} and IsDelete != 1")
    List<WorkListDetail> getWorkListDetailListById(@Param("workListId") String workListId);

    /**
     * 获取待分发、待接单、处理中的工单详情设备
     */
    @Select("select distinct a.DeviceId from ZM_GM_WorkListDetail a " +
            "inner join ZM_GM_WorkList b on a.WorkListId = b.Id " +
            "where b.IsDelete != 1" +
            "and (b.Status = 'ZM_GM_WorkState_BeDistributed' or b.Status = 'ZM_GM_WorkState_Pending' or b.Status = 'ZM_GM_WorkState_Process')")
    List<String> WorkListDetailListByState();
}
