package com.ganglion.controller;

import com.ganglion.constant.CommonConstants;
import com.ganglion.model.WorkListDTO;
import com.ganglion.model.WorkListDetailDTO;
import com.ganglion.model.WorkListQueryInfo;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.GMWorkListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/gmWorkList")
public class GMWorkListController {

    @Autowired
    private GMWorkListService gmWorkListService;

    /**
     * 新增工单
     */
    @CrossOrigin
    @PostMapping(value = "/addWorkList")
    public ResultResponse addWorkList(@RequestBody WorkListDTO workListDTO) {
        return gmWorkListService.addWorkList(workListDTO);
    }

    /**
     * 获取工单号
     */
    @CrossOrigin
    @PostMapping(value = "/getWorkListNo")
    public ResultResponse getWorkListNo(){
        ResultResponse result = new ResultResponse();
        result.setData(String.format("WL_No.%s",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())));
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 更新工单设备说明信息
     */
    @CrossOrigin
    @PostMapping(value = "/updateWLDeviceState")
    public ResultResponse updateWLDeviceState(@RequestBody WorkListDetailDTO workListDetailDTO){
        return gmWorkListService.updateWLDeviceState(workListDetailDTO);
    }

    /**
     * 更新工单设备问题信息
     */
    @CrossOrigin
    @PostMapping(value = "/updateWLDeviceQuestion")
    public ResultResponse updateWLDeviceQuestion(@RequestBody WorkListDetailDTO workListDetailDTO){
        return gmWorkListService.updateWLDeviceQuestion(workListDetailDTO);
    }

    /**
     * 获取工单列表
     */
    @CrossOrigin
    @PostMapping(value = "/getWorkListListData")
    public ResultResponse<ListResultInfo<WorkListDTO>> getWorkListList(@RequestBody WorkListQueryInfo workListQueryInfo){
        return gmWorkListService.getWorkListListData(workListQueryInfo);
    }

    /**
     * 获取工单及详情信息
     */
    @CrossOrigin
    @PostMapping(value = "/getWorkListDetail")
    public ResultResponse<WorkListDTO> getWorkListDetail(@RequestBody WorkListQueryInfo workListQueryInfo){
        return gmWorkListService.getWorkListDetail(workListQueryInfo);
    }

    /**
     * 分发工单
     */
    @CrossOrigin
    @PostMapping(value = "/distributeWorkLists")
    public ResultResponse distributeWorkLists(@RequestBody WorkListDTO workListDTO){
        return gmWorkListService.distributeWorkLists(workListDTO);
    }

    /**
     * 废弃工单
     */
    @CrossOrigin
    @PostMapping(value = "/discardWorkList")
    public ResultResponse discardWorkList(@RequestBody WorkListDTO workListDTO){
        return gmWorkListService.discardWorkList(workListDTO.getWorkListIds());
    }

    /**
     * 移动端根据设备获取工单
     */
    @CrossOrigin
    @PostMapping(value = "/getWorkListsByDevice")
    public ResultResponse<List<WorkListDTO>> getWorkListsByDevice(@RequestBody WorkListDetailDTO workListDetailDTO){
        return gmWorkListService.getWorkListsByDevice(workListDetailDTO.getDeviceId());
    }

    /**
     * 移动端获取工单设备信息
     */
    @CrossOrigin
    @PostMapping(value = "/getWLDeviceInfo")
    public ResultResponse<WorkListDetailDTO> getWLDeviceInfo(@RequestBody WorkListDetailDTO workListDetailDTO){
        return gmWorkListService.getWLDeviceInfo(workListDetailDTO);
    }

    /**
     * 移动端处理巡检工单设备
     */
    @CrossOrigin
    @PostMapping(value = "/dealInspectionWL")
    public ResultResponse dealInspectionWL(@RequestBody WorkListDetailDTO workListDetailDTO){
        return gmWorkListService.dealInspectionWL(workListDetailDTO);
    }

    /**
     * 移动端根据状态返回当前人员的工单列表
     */
    @CrossOrigin
    @PostMapping(value = "/getWorkListByReceiver")
    public ResultResponse<List<WorkListDTO>> getWorkListByReceiver(@RequestBody WorkListDTO workListDTO){
        return gmWorkListService.getWorkListByReceiver(workListDTO);
    }

    /**
     * 移动端处理工单
     */
    @CrossOrigin
    @PostMapping(value = "/dealWorkList")
    public ResultResponse dealWorkList(@RequestBody WorkListDTO workListDTO){
        return gmWorkListService.dealWorkList(workListDTO);
    }
}
