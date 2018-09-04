package com.ganglion.mapper;

import com.ganglion.entity.Device;
import com.ganglion.model.DeviceDTO;
import com.ganglion.model.DeviceQueryInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface DeviceMapper extends Mapper<Device> {

    List<DeviceDTO> getDeviceList(DeviceQueryInfo queryInfo);

    List<DeviceDTO> getDeviceInAddressId(List<String> addressIds);

    @Select("select * from ZM_GM_Device where IsDelete!=1 and ProductKey = #{productKey}")
    List<DeviceDTO> getDeviceListByProductKey(@Param("productKey") String productKey);

    DeviceDTO getDeviceById(@Param("id")String id);

    void deleteDeviceByIds(Map<String, Object> params);

    /**
     * 获取工单下安装失败的设备列表
     */
    List<Device> getWFInstallFailedDeviceList(Map<String, Object> workListIds);

    /**
     * 获取未安装设备列表
     */
    List<Device> getNotInstallDeviceList(Map<String, Object> deviceIds);

    /**
     * 根据维保状态获取设备
     */
    @Select("select * from ZM_GM_Device where IsDelete !=1 and MaintenanceStatus = #{maintenanceStatus}")
    List<Device> getDeviceByMaintenanceStatus(@Param("maintenanceStatus") String maintenanceStatus);
}
