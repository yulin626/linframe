package com.ganglion.controller;

import com.ganglion.model.*;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.GMDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/gmDevice")
public class GMDeviceController {
    @Autowired
    private GMDeviceService gmDeviceService;

    /**
     * 获取产品列表
     */
    @CrossOrigin
    @PostMapping(value = "/getAllProducts")
    public ResultResponse<List<KeyValuePairInfo>> getAllProducts() {
        ResultResponse<List<KeyValuePairInfo>> resultResponse = null;
        try {
            resultResponse = gmDeviceService.getAllProducts();
        } catch (UnsupportedEncodingException e) {

        }
        return resultResponse;
    }

    /**
     * 获取设备列表
     */
    @CrossOrigin
    @PostMapping(value = "/getDeviceList")
    public ResultResponse<ListResultInfo<DeviceDTO>> getDeviceList(@RequestBody DeviceQueryInfo queryInfo) {
        return gmDeviceService.getDeviceList(queryInfo);
    }

    /**
     * 拉取设备
     */
    @CrossOrigin
    @PostMapping(value = "/pullDevice")
    public ResultResponse pullDevice(@RequestBody DeviceDTO deviceDTO) {
        return gmDeviceService.pullDevice(deviceDTO.getProductKey());
    }

    /**
     * 通过设备Id获取设备详情
     */
    @CrossOrigin
    @PostMapping(value = "/getDeviceDetail")
    public ResultResponse<DeviceDTO> getDeviceDetailById(@RequestBody DeviceDTO deviceDTO) {
        return gmDeviceService.getDeviceDetailById(deviceDTO.getId());
    }

    /**
     * 更新设备附属信息
     */
    @CrossOrigin
    @PostMapping(value = "/editDeviceSubsidiaryInfo")
    public ResultResponse editDeviceSubsidiaryInfo(@RequestBody DeviceDTO deviceDTO) {
        return gmDeviceService.editDeviceSubsidiaryInfo(deviceDTO);
    }

    /**
     * 批量更新设备附属信息
     */
    @CrossOrigin
    @PostMapping(value = "/batchEditDeviceSubsidiaryInfo")
    public ResultResponse batchEditDeviceSubsidiaryInfo(@RequestBody DeviceDTO deviceDTO) {
        return gmDeviceService.batchEditDeviceSubsidiaryInfo(deviceDTO);
    }

    /**
     * 删除设备
     */
    @CrossOrigin
    @PostMapping(value = "/deleteDevices")
    public ResultResponse deleteDevices(@RequestBody KeyQuery keyQuery) {
        return gmDeviceService.deleteDevices(keyQuery.getKeys());
    }

    /**
     * 导出设备Ids
     */
    @CrossOrigin
    @RequestMapping(value = "/exportEXL")
    public ResponseEntity<byte[]> exportDeviceIdsExcel(String keys, HttpServletResponse response) {
        List<String> keyList = Arrays.asList(keys.split(","));
        return gmDeviceService.exportDeviceIdsExcel(keyList, response);
    }

    /**
     * 根据设备Id获取设备名称
     */
    @CrossOrigin
    @PostMapping(value = "/getDeviceNameById")
    public ResultResponse<ListResultInfo<String>> getDeviceNameById(@RequestBody KeyQuery keyQuery) {
        return gmDeviceService.getDeviceNameById(keyQuery.getKeys());
    }

    /**
     * 获取飞凤产品列表
     */
    @CrossOrigin
    @PostMapping(value = "/getFFProductList")
    public ResultResponse<List<KeyValuePairInfo>> getFFProductList(@RequestBody FFViewQueryInfo ffViewQueryInfo) {
        return gmDeviceService.getFFProductList(ffViewQueryInfo);
    }

    /**
     * 获取飞凤项目信息列表
     */
    @CrossOrigin
    @PostMapping(value = "/getFFProjectList")
    public ResultResponse<List<FFWebAppInfo>> getFFProjectList() {
        return gmDeviceService.getFFProjectList();
    }

    /**
     * 获取飞凤产品设备数据列表
     */
    @CrossOrigin
    @PostMapping(value = "/getFFProductDeviceDataList")
    public ResultResponse<List<FFDeviceDataInfo>> getFFProductDeviceDataList(@RequestBody FFViewQueryInfo ffViewQueryInfo) {
        return gmDeviceService.getFFProductDeviceDataList(ffViewQueryInfo);
    }

    /**
     * 移动端更新安装状态
     */
    @CrossOrigin
    @PostMapping(value = "/updateDeviceInstallationState")
    public ResultResponse updateDeviceInstallationState(@RequestBody DeviceDTO deviceDTO) {
        return gmDeviceService.updateDeviceInstallationState(deviceDTO);
    }
}
