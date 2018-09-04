package com.ganglion.service.impl;

import com.ganglion.constant.CommonConstants;
import com.ganglion.entity.Device;
import com.ganglion.entity.WorkList;
import com.ganglion.entity.WorkListDetail;
import com.ganglion.enums.ZM_GM_DeviceInstallationState;
import com.ganglion.enums.ZM_GM_MaintenanceStatus;
import com.ganglion.enums.ZM_GM_WorkState;
import com.ganglion.enums.ZM_GM_WorkType;
import com.ganglion.mapper.DeviceMapper;
import com.ganglion.mapper.WorkListDetailMapper;
import com.ganglion.mapper.WorkListMapper;
import com.ganglion.model.JwtUser;
import com.ganglion.model.WorkListDTO;
import com.ganglion.model.WorkListDetailDTO;
import com.ganglion.model.WorkListQueryInfo;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.GMWorkListService;
import com.ganglion.util.InitEntityExtend;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GMWorkListServiceImpl extends BaseService<WorkList> implements GMWorkListService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private WorkListDetailMapper workListDetailMapper;
    @Autowired
    private WorkListMapper workListMapper;

    /**
     * 新增工单
     */
    @Transactional
    @Override
    public ResultResponse addWorkList(WorkListDTO workListDTO) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        WorkList workList = mapper.map(workListDTO, WorkList.class);
        workList.setStatus(ZM_GM_WorkState.ZM_GM_WorkState_BeDistributed.toString());    //待分发
        InitEntityExtend.initCreateEntity(workList);
        this.insert(workList);

        for (String deviceId : workListDTO.getDeviceIds()) {
            WorkListDetail workListDetail = new WorkListDetail();
            workListDetail.setDeviceId(deviceId);
            workListDetail.setWorkListId(workList.getId());
            //获取设备信息
            Device device = deviceMapper.selectByPrimaryKey(deviceId);
            workListDetail.setName(device.getName());
            //工单类型为设备巡检,变更维保状态
            if (workListDTO.getType() != null && workListDTO.getType().equals(ZM_GM_WorkType.ZM_GM_WorkType_Inspection.toString())) {
                device.setMaintenanceStatus(ZM_GM_MaintenanceStatus.ZM_GM_MaintenanceStatus_Inspection.toString());  //维保状态设为巡检中
                InitEntityExtend.initUpdateEntity(device);
                deviceMapper.updateByPrimaryKey(device);
            }
            //安装工单把安装失败的设备置为未安装
            else if (workListDTO.getType() != null && workListDTO.getType().equals(ZM_GM_WorkType.ZM_GM_WorkType_Install.toString()) && device.getInstallationState() != null && device.getInstallationState().equals(ZM_GM_DeviceInstallationState.ZM_GM_DeviceInstallationState_InstallFailed.toString())) {
                device.setInstallationState(ZM_GM_DeviceInstallationState.ZM_GM_DeviceInstallationState_NotInstalled.toString());  //待安装
                InitEntityExtend.initUpdateEntity(device);
                deviceMapper.updateByPrimaryKey(device);
            }

            InitEntityExtend.initCreateEntity(workListDetail);
            workListDetailMapper.insert(workListDetail);
        }

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 更新工单设备说明信息
     */
    @Override
    public ResultResponse updateWLDeviceState(WorkListDetailDTO workListDetailDTO) {
        ResultResponse result = new ResultResponse();

        WorkListDetail workListDetail = workListDetailMapper.getWorkListDetailByIds(workListDetailDTO);
        if (workListDetail == null) {
            result.setStatus(0);
            result.setMessage(CommonConstants.ERROR);
            return result;
        }

        workListDetail.setState(workListDetailDTO.getState());
        InitEntityExtend.initUpdateEntity(workListDetail);
        workListDetailMapper.updateByPrimaryKey(workListDetail);

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 更新工单设备问题信息
     */
    @Override
    public ResultResponse updateWLDeviceQuestion(WorkListDetailDTO workListDetailDTO) {
        ResultResponse result = new ResultResponse();

        WorkListDetail workListDetail = workListDetailMapper.getWorkListDetailByIds(workListDetailDTO);
        if (workListDetail == null) {
            result.setStatus(0);
            result.setMessage(CommonConstants.ERROR);
            return result;
        }

        workListDetail.setQuestion(workListDetailDTO.getQuestion());
        InitEntityExtend.initUpdateEntity(workListDetail);
        workListDetailMapper.updateByPrimaryKey(workListDetail);

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 获取工单列表
     */
    @Override
    public ResultResponse<ListResultInfo<WorkListDTO>> getWorkListListData(WorkListQueryInfo workListQueryInfo) {
        Page page = PageHelper.startPage(workListQueryInfo.getPageNumber(), workListQueryInfo.getPageSize(), true);
        List<WorkListDTO> list = workListMapper.getWorkListListData(workListQueryInfo);


        ResultResponse<ListResultInfo<WorkListDTO>> result = new ResultResponse<>();
        ListResultInfo<WorkListDTO> listResultInfo = new ListResultInfo<>();
        listResultInfo.setList(list);
        listResultInfo.setCount(page.getTotal());

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(listResultInfo);

        return result;
    }

    /**
     * 获取工单及详情信息
     */
    @Override
    public ResultResponse<WorkListDTO> getWorkListDetail(WorkListQueryInfo workListQueryInfo) {
        String workListId = workListQueryInfo.getWorkListId();
        WorkListDTO workList = workListMapper.getWorkListDetailById(workListId);

        if (workList != null) {
            for (WorkListDetailDTO anWorkListDetailDTO : workList.getDeviceList()) {
                anWorkListDetailDTO.setIsDeal(((workList.getType() != null && workList.getType().equals(ZM_GM_WorkType.ZM_GM_WorkType_Install.toString()) && !anWorkListDetailDTO.getInstallationState().equals(ZM_GM_DeviceInstallationState.ZM_GM_DeviceInstallationState_NotInstalled.toString())) || (workList.getType() != null && workList.getType().equals(ZM_GM_WorkType.ZM_GM_WorkType_Inspection.toString()) &&
                        !anWorkListDetailDTO.getMaintenanceStatus().equals(ZM_GM_MaintenanceStatus.ZM_GM_MaintenanceStatus_Inspection.toString()))) ? true : false);
            }
        }

        ResultResponse<WorkListDTO> result = new ResultResponse<>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(workList);
        return result;
    }

    /**
     * 分发工单
     */
    @Override
    public ResultResponse distributeWorkLists(WorkListDTO workListDTO) {
        for (String id : workListDTO.getWorkListIds()) {
            WorkList workList = this.selectByKey(id);
            workList.setReceiver(workListDTO.getReceiver());
            workList.setStatus(ZM_GM_WorkState.ZM_GM_WorkState_Pending.toString());

            InitEntityExtend.initUpdateEntity(workList);
            this.updateByKey(workList);
        }

        ResultResponse result = new ResultResponse<>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 废弃工单
     */
    @Transactional
    @Override
    public ResultResponse discardWorkList(List<String> ids) {
        for (String id : ids) {
            WorkList workList = this.selectByKey(id);
            workList.setStatus(ZM_GM_WorkState.ZM_GM_WorkState_Obsolete.toString());
            workList.setCloseTime(new Date());

            InitEntityExtend.initUpdateEntity(workList);
            this.updateByKey(workList);
        }

        Map<String, Object> params = new HashMap<>(3);
        params.put("workListIds", ids);
        //获取工单下安装失败的设备列表
        List<Device> deviceList = deviceMapper.getWFInstallFailedDeviceList(params);
        for (Device device : deviceList) {
            device.setInstallationState(ZM_GM_DeviceInstallationState.ZM_GM_DeviceInstallationState_NotInstalled.toString());

            deviceMapper.updateByPrimaryKey(device);
        }

        ResultResponse result = new ResultResponse<>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 移动端根据设备获取工单
     */
    @Override
    public ResultResponse<List<WorkListDTO>> getWorkListsByDevice(String deviceId) {
        List<WorkListDTO> workListDTOList = workListMapper.getWorkListsByDevice(deviceId);
        Device device = deviceMapper.selectByPrimaryKey(deviceId);

        for (WorkListDTO workListDTO : workListDTOList) {
            workListDTO.setIsDeal((workListDTO.getStatus().equals(ZM_GM_WorkState.ZM_GM_WorkState_Resolved.toString())
                    || (device.getMaintenanceStatus() == null && !device.getInstallationState().equals(ZM_GM_DeviceInstallationState.ZM_GM_DeviceInstallationState_NotInstalled.toString()))
                    || (device.getInstallationState().equals(ZM_GM_DeviceInstallationState.ZM_GM_DeviceInstallationState_Installed.toString()) && !device.getMaintenanceStatus().equals(ZM_GM_MaintenanceStatus.ZM_GM_MaintenanceStatus_Inspection.toString())))
                    ? true : false);
        }

        ResultResponse<List<WorkListDTO>> result = new ResultResponse<>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(workListDTOList);
        return result;
    }

    /**
     * 移动端获取工单设备信息
     */
    @Override
    public ResultResponse<WorkListDetailDTO> getWLDeviceInfo(WorkListDetailDTO workListDetailDTO) {
        WorkListDetail workListDetail = workListDetailMapper.getWorkListDetailByIds(workListDetailDTO);
        Device device = deviceMapper.selectByPrimaryKey(workListDetailDTO.getDeviceId());

        WorkListDetailDTO workListDetailDTO1 = new WorkListDetailDTO();
        workListDetailDTO1.setDeviceId(workListDetail.getDeviceId());
        workListDetailDTO1.setName(workListDetail.getName());
        workListDetailDTO1.setState(workListDetail.getState());
        workListDetailDTO1.setQuestion(workListDetail.getQuestion());
        workListDetailDTO1.setAttachmentIds(workListDetail.getAttachmentIds());
        workListDetailDTO1.setMaintenanceStatus(device.getMaintenanceStatus());

        ResultResponse<WorkListDetailDTO> result = new ResultResponse<>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(workListDetailDTO1);
        return result;
    }

    /**
     * 移动端处理巡检工单设备
     */
    @Override
    public ResultResponse dealInspectionWL(WorkListDetailDTO workListDetailDTO) {
        ResultResponse result = new ResultResponse();
        //工单详情
        WorkListDetail workListDetail = workListDetailMapper.getWorkListDetailByIds(workListDetailDTO);
        if (workListDetail == null) {
            result.setStatus(0);
            result.setMessage("不存在工单详情！");
            return result;
        }
        //设备
        Device device = deviceMapper.selectByPrimaryKey(workListDetailDTO.getDeviceId());
        if (device == null || device.getDelete().equals(true)) {
            result.setStatus(0);
            result.setMessage("不存在设备！");
            return result;
        }
        //工单
        WorkList workList = workListMapper.selectByPrimaryKey(workListDetailDTO.getWorkListId());
        if (workList == null || workList.getDelete().equals(true)) {
            result.setStatus(0);
            result.setMessage("不存在工单！");
            return result;
        }

        //工单下的设备还存在巡检中的
        List<String> devicesIdList = workListDetailMapper.getWorkListDetailListById(workList.getId()).stream().map(a -> a.getDeviceId()).collect(Collectors.toList());
        List<Device> deviceList = deviceMapper.getDeviceByMaintenanceStatus(ZM_GM_MaintenanceStatus.ZM_GM_MaintenanceStatus_Inspection.toString());
        if (deviceList.stream().filter(a -> !a.getId().equals(device.getId()) && devicesIdList.contains(a.getId())).collect(Collectors.toList()).size() > 0) {
            workList.setStatus(ZM_GM_WorkState.ZM_GM_WorkState_Process.toString()); //工单状态处理中
        } else {
            workList.setStatus(ZM_GM_WorkState.ZM_GM_WorkState_Resolved.toString());    //工单状态已解决
            workList.setCloseTime(new Date());
        }
        //工单
        InitEntityExtend.initUpdateEntity(workList);
        this.updateByKey(workList);

        //工单详情
        workListDetail.setQuestion(workListDetailDTO.getQuestion());    //问题
        workListDetail.setState(workListDetailDTO.getState());  //说明
        workListDetail.setAttachmentIds(workListDetailDTO.getAttachmentIds());
        InitEntityExtend.initUpdateEntity(workListDetail);
        workListDetailMapper.updateByPrimaryKey(workListDetail);

        //设备
        device.setMaintenanceStatus(workListDetailDTO.getMaintenanceStatus());
        device.setMaintenanceTime(new Date());  //维保时间
        InitEntityExtend.initUpdateEntity(device);
        deviceMapper.updateByPrimaryKey(device);

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 移动端根据状态返回当前人员的工单列表
     */
    @Override
    public ResultResponse<List<WorkListDTO>> getWorkListByReceiver(WorkListDTO workListDTO) {
        JwtUser userDetails = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<WorkList> workListList = this.selectAll().stream()
                .filter(a->a.getDelete().equals(false)&&a.getStatus().equals(workListDTO.getStatus())&&a.getReceiver().equals(userDetails.getEmplId())).sorted(Comparator.comparing(WorkList::getCreatedTime).reversed()).collect(Collectors.toList());
        List<WorkListDTO> workListDTOList = new ArrayList<>();
        for(WorkList workList:workListList){
            WorkListDTO workListDTO1 = new WorkListDTO();
            workListDTO1.setId(workList.getId());
            workListDTO1.setNo(workList.getNo());
            workListDTO1.setType(workList.getType());
            workListDTO1.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(workList.getCreatedTime()));
            workListDTOList.add(workListDTO1);
        }

        ResultResponse<List<WorkListDTO>> result = new ResultResponse<>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(workListDTOList);
        return result;
    }

    /**
     * 移动端处理工单
     */
    @Override
    public ResultResponse dealWorkList(WorkListDTO workListDTO) {
        ResultResponse result = new ResultResponse();
        WorkList workList = this.selectByKey(workListDTO.getId());
        if (workList==null){
            result.setStatus(0);
            result.setMessage(CommonConstants.ERROR);
            return result;
        }

        workList.setStatus(workListDTO.getStatus());
        //已解决或者已废弃
        if (workListDTO.getStatus().equals(ZM_GM_WorkState.ZM_GM_WorkState_Resolved.toString())||workListDTO.getStatus().equals(ZM_GM_WorkState.ZM_GM_WorkState_Obsolete.toString())){
            workList.setCloseTime(new Date());
        }
        InitEntityExtend.initUpdateEntity(workList);
        this.updateByKey(workList);

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }
}
