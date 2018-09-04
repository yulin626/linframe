package com.ganglion.service;

import com.ganglion.model.WorkListDTO;
import com.ganglion.model.WorkListDetailDTO;
import com.ganglion.model.WorkListQueryInfo;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;

import java.util.List;

public interface GMWorkListService {
    /**
     * 新增工单
     */
    ResultResponse addWorkList(WorkListDTO workListDTO);

    /**
     * 更新工单设备说明信息
     */
    ResultResponse updateWLDeviceState(WorkListDetailDTO workListDetailDTO);

    /**
     * 更新工单设备问题信息
     */
    ResultResponse updateWLDeviceQuestion(WorkListDetailDTO workListDetailDTO);

    /**
     * 获取工单列表
     */
    ResultResponse<ListResultInfo<WorkListDTO>> getWorkListListData(WorkListQueryInfo workListQueryInfo);

    /**
     * 获取工单及详情信息
     */
    ResultResponse<WorkListDTO> getWorkListDetail(WorkListQueryInfo workListQueryInfo);

    /**
     * 分发工单
     */
    ResultResponse distributeWorkLists(WorkListDTO workListDTO);

    /**
     * 废弃工单
     */
    ResultResponse discardWorkList(List<String> ids);

    /**
     * 移动端根据设备获取工单
     */
    ResultResponse<List<WorkListDTO>> getWorkListsByDevice(String deviceId);

    /**
     * 移动端获取工单设备信息
     */
    ResultResponse<WorkListDetailDTO> getWLDeviceInfo(WorkListDetailDTO workListDetailDTO);

    /**
     * 移动端处理巡检工单设备
     */
    ResultResponse dealInspectionWL(WorkListDetailDTO workListDetailDTO);

    /**
     * 移动端根据状态返回当前人员的工单列表
     */
    ResultResponse<List<WorkListDTO>> getWorkListByReceiver(WorkListDTO workListDTO);

    /**
     * 移动端处理工单
     */
    ResultResponse dealWorkList(WorkListDTO workListDTO);
}
