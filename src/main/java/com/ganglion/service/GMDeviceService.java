package com.ganglion.service;

import com.ganglion.model.*;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface GMDeviceService {

    /**
     * 获取所有应用
     */
    ResultResponse<List<KeyValuePairInfo>> getAllProducts() throws UnsupportedEncodingException;

    /**
     * 获取设备列表
     */
    ResultResponse<ListResultInfo<DeviceDTO>> getDeviceList(DeviceQueryInfo queryInfo);

    /**
     * 拉取设备
     */
    ResultResponse pullDevice(String productKey);

    /**
     * 通过设备Id获取设备详情
     */
    ResultResponse<DeviceDTO> getDeviceDetailById(String deviceId);

    /**
     * 更新设备附属信息
     */
    ResultResponse editDeviceSubsidiaryInfo(DeviceDTO deviceDTO);

    /**
     * 批量更新设备附属信息
     */
    ResultResponse batchEditDeviceSubsidiaryInfo(DeviceDTO deviceDTO);

    /**
     * 删除设备
     */
    ResultResponse deleteDevices(List<String> ids);

    /**
     * 导出设备Ids
     */
    ResponseEntity<byte[]> exportDeviceIdsExcel(List<String> ids, HttpServletResponse response);

    /**
     * 根据设备Id获取设备名称
     */
    ResultResponse<ListResultInfo<String>> getDeviceNameById(List<String> ids);

    /**
     * 获取飞凤产品列表
     */
    ResultResponse<List<KeyValuePairInfo>> getFFProductList(FFViewQueryInfo ffViewQueryInfo);

    /**
     * 获取飞凤项目信息列表
     */
    ResultResponse<List<FFWebAppInfo>> getFFProjectList();

    /**
     * 获取飞凤产品设备数据列表
     */
    ResultResponse<List<FFDeviceDataInfo>> getFFProductDeviceDataList(FFViewQueryInfo ffViewQueryInfo);

    /**
     * 移动端更新安装状态
     */
    ResultResponse updateDeviceInstallationState(DeviceDTO deviceDTO);
}
