package com.ganglion.service.impl;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.aliyun.iotx.api.client.IoTApiRequest;
import com.aliyun.iotx.api.client.SyncApiClient;
import com.aliyun.iotx.api.model.*;
import com.ganglion.constant.CommonConstants;
import com.ganglion.entity.*;
import com.ganglion.enums.ZM_GM_DeviceInstallationState;
import com.ganglion.enums.ZM_GM_WorkState;
import com.ganglion.mapper.*;
import com.ganglion.model.DeviceDTO;
import com.ganglion.model.*;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.GMDeviceService;
import com.ganglion.util.InitEntityExtend;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wanglei on 18/07/18.
 */

@Service
public class GMDeviceServiceImpl extends BaseService<Device> implements GMDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private FFWebAppMapper ffWebAppMapper;
    @Autowired
    private FFProductFormMapper ffProductFormMapper;
    @Autowired
    private WorkListMapper workListMapper;
    @Autowired
    private WorkListDetailMapper workListDetailMapper;

    /**
     * 获取所有应用
     */
    @Override
    public ResultResponse<List<KeyValuePairInfo>> getAllProducts() {

        List<ProductResponseInfo> prList = getFFReleaseProductList(CommonConstants.APP_KEY, CommonConstants.APP_SECRET);
        List<KeyValuePairInfo> kvList = new ArrayList<>();
        KeyValuePairInfo kv;
        for (ProductResponseInfo info : prList) {
            kv = new KeyValuePairInfo();
            kv.setKey(info.getProductKey());
            kv.setValue(info.getName());
            kvList.add(kv);
        }

        ResultResponse<List<KeyValuePairInfo>> result = new ResultResponse<>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(kvList);
        return result;
    }

    /**
     * 获取设备列表
     */
    @Override
    public ResultResponse<ListResultInfo<DeviceDTO>> getDeviceList(DeviceQueryInfo queryInfo) {
        //Page page = PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize(), true);
        Integer startIndex = (queryInfo.getPageNumber() - 1) * queryInfo.getPageSize();
        List<DeviceDTO> list = deviceMapper.getDeviceList(queryInfo);

        //查询逻辑,去除飞凤请求
        if (queryInfo.getIsSelect() == null || queryInfo.getIsSelect().equals(false)) {
            if (queryInfo.getInstallationState() != null) {
                list = list.stream().filter(a -> queryInfo.getInstallationState().equals(a.getInstallationState())).collect(Collectors.toList());
            }
            for (DeviceDTO device : list.stream().skip(startIndex).limit(queryInfo.getPageSize()).collect(Collectors.toList())) {
                DeviceDetailResponseInfo deviceDetail = getFFDeviceDetail(device.getProductKey(), device.getDeviceKey(), CommonConstants.APP_KEY, CommonConstants.APP_SECRET);
                if (deviceDetail != null) {
                    device.setLatestOnlineTime(deviceDetail.getLatestOnlineTime());
                    device.setDeviceStatus(deviceDetail.getDeviceDTO().getStatus());
                }
            }
        }
        //查询逻辑，有业务处理
        else {
            List<String> deviceIds = workListDetailMapper.WorkListDetailListByState();
            list = list.stream().filter(a -> !deviceIds.contains(a.getId())).collect(Collectors.toList());
            //安装状态不为空
            if (queryInfo.getInstallationState() != null) {
                if (ZM_GM_DeviceInstallationState.ZM_GM_DeviceInstallationState_NotInstalled.toString().equals(queryInfo.getInstallationState())) {
                    list = list.stream().filter(a -> a.getInstallationState().equals(queryInfo.getInstallationState())
                            || ZM_GM_DeviceInstallationState.ZM_GM_DeviceInstallationState_InstallFailed.toString().equals(a.getInstallationState())).collect(Collectors.toList());
                } else {
                    list = list.stream().filter(a -> a.getInstallationState().equals(queryInfo.getInstallationState())).collect(Collectors.toList());
                }
            }
        }

        ResultResponse<ListResultInfo<DeviceDTO>> result = new ResultResponse<>();
        ListResultInfo<DeviceDTO> listResult = new ListResultInfo<>();
        listResult.setList(list.stream().skip(startIndex).limit(queryInfo.getPageSize()).collect(Collectors.toList()));
        listResult.setCount(list.size());

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(listResult);
        return result;
    }

    /**
     * 拉取设备
     */
    @Override
    public ResultResponse pullDevice(String productKey) {

        //获取当前产品的设备总数
        Integer deviceCount = getFFDeviceCount(productKey, CommonConstants.APP_KEY, CommonConstants.APP_SECRET);
        Integer pageSize = 5000;
        Double allOffset = Math.ceil(new Double(deviceCount) / pageSize);
        //当前产品的设备列表
        List<DeviceListDetailDTO> allList = new ArrayList<>();
        for (int i = 0; i < allOffset; i++) {
            DeviceListResponseInfo dl = getFFDeviceListByProductKey(productKey, i, pageSize, CommonConstants.APP_KEY, CommonConstants.APP_SECRET);
            if (dl.getTotalNum() > 0) {
                allList.addAll(dl.getItems());
            }
        }

        //当前数据库的产品列表
        List<DeviceDTO> deviceList = deviceMapper.getDeviceListByProductKey(productKey);
        //待插入数据库的信息
        List<DeviceListDetailDTO> insertList = allList.stream().filter(a -> !deviceList.stream().map(b -> b.getDeviceKey()).collect(Collectors.toList()).contains(a.getName())).collect(Collectors.toList());
        insertList.forEach(a -> {
            Device device = new Device();
            device.setName(a.getName());
            device.setDeviceKey(a.getName());
            device.setAddType("ZM_GM_AddType_Pull");
            device.setSource("ZM_GM_Source_FeiFeng");
            device.setProductKey(a.getProductKey());
            device.setInstallationState(ZM_GM_DeviceInstallationState.ZM_GM_DeviceInstallationState_NotInstalled.toString());

            InitEntityExtend.initCreateEntity(device);
            this.insert(device);
        });

        ResultResponse result = new ResultResponse<>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 通过设备Id获取设备详情
     */
    @Override
    public ResultResponse<DeviceDTO> getDeviceDetailById(String deviceId) {

        ResultResponse<DeviceDTO> result = new ResultResponse<>();
        DeviceDTO device = deviceMapper.getDeviceById(deviceId);

        if (device == null) {
            result.setStatus(0);
            result.setMessage(CommonConstants.ERROR);
            return result;
        }
        //获取飞凤产品属性
        List<ProductPropertiesResponseInfo> productProperties = getFFProductProperties(device.getProductKey(), CommonConstants.APP_KEY, CommonConstants.APP_SECRET);
        //获取飞凤物的详情
        DeviceDetailResponseInfo deviceDetail = getFFDeviceDetail(device.getProductKey(), device.getDeviceKey(), CommonConstants.APP_KEY, CommonConstants.APP_SECRET);
        if (deviceDetail != null) {
            device.setActiveTime(deviceDetail.getActiveTime());
            device.setLatestOnlineTime(deviceDetail.getLatestOnlineTime());
            device.setProductKey(deviceDetail.getProductInfoDTO().getProductKey());
            device.setProductName(deviceDetail.getProductInfoDTO().getName());
            device.setDeviceStatus(deviceDetail.getDeviceDTO().getStatus());
        }

        //获取飞凤物的全量属性快照数据
        List<DevicePropertiesResponseInfo> deviceProperties = getFFDeviceProperties(device.getProductKey(), device.getDeviceKey(), CommonConstants.APP_KEY, CommonConstants.APP_SECRET);

        List<DevicePropertiesInfo> dpList = new ArrayList<>();
        DevicePropertiesInfo dp;
        for (ProductPropertiesResponseInfo pp : productProperties) {
            dp = new DevicePropertiesInfo();
            dp.setIdentifier(pp.getIdentifier());
            dp.setDataType(pp.getDataType());
            dp.setDescription(pp.getDescription());
            dp.setName(pp.getName());
            dp.setRequired(pp.getRequired());
            dp.setRwFlag(pp.getRwFlag());
            dp.setStd(pp.getStd());

            if (deviceProperties != null && deviceProperties.size() > 0) {
                DevicePropertiesResponseInfo deviceProperty = deviceProperties.stream().filter(a -> a.getAttribute().equals(pp.getIdentifier())).collect(Collectors.toList()).get(0);
                if (deviceProperty != null) {
                    dp.setValue(deviceProperty.getValue());
                    if (deviceProperty.getGmtModified() > 0) {
                        dp.setGmtModified(deviceProperty.getGmtModified().toString());
                    }
                }
            }
            dpList.add(dp);
        }
        device.setDevicePropertiesInfos(dpList);

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(device);
        return result;
    }

    /**
     * 更新设备附属信息
     */
    @Override
    public ResultResponse editDeviceSubsidiaryInfo(DeviceDTO deviceDTO) {

        Device device = this.selectByKey(deviceDTO.getId());
        device.setProjectId(deviceDTO.getProjectId());
        device.setAddressId(deviceDTO.getAddressId());
        device.setMaintenanceUser(deviceDTO.getMaintenanceUser());
        device.setAddressDetial(deviceDTO.getAddressDetial());

        InitEntityExtend.initUpdateEntity(device);
        this.updateByKey(device);

        ResultResponse result = new ResultResponse<>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 批量更新设备附属信息
     */
    @Override
    public ResultResponse batchEditDeviceSubsidiaryInfo(DeviceDTO deviceDTO) {
        for (String id : deviceDTO.getIds()) {
            Device device = this.selectByKey(id);
            try {
                Field f = device.getClass().getDeclaredField(deviceDTO.getKeyValueInfo().getKey().toString());
                f.setAccessible(true);
                f.set(device, deviceDTO.getKeyValueInfo().getValue().toString());
                //清空所属项目的时,去除位置关联
                if ("projectId".equals(f.getName())){
                    device.setAddressId("");
                    device.setAddressDetial("");
                }
            } catch (Exception ex) {
            }
            InitEntityExtend.initUpdateEntity(device);
            this.updateByKey(device);
        }
        ResultResponse result = new ResultResponse<>();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 删除设备
     */
    @Override
    public ResultResponse deleteDevices(List<String> ids) {
        ResultResponse result = new ResultResponse<>();

        //工单Check
        if (workListDetailMapper.selectAll().stream().filter(a -> ids.contains(a.getDeviceId()) && a.getDelete().equals(false)).collect(Collectors.toList()).size() > 0) {
            result.setStatus(0);
            result.setMessage("该设备被工单关联，不可删除！");
            return result;
        }

        JwtUser userDetails = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> params = new HashMap<>(3);
        params.put("updatedBy", userDetails.getEmplId());
        params.put("updatedDept", "0");
        params.put("updatedTime", new Date());
        params.put("ids", ids);

        deviceMapper.deleteDeviceByIds(params);

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 导出设备Ids
     */
    @Override
    public ResponseEntity<byte[]> exportDeviceIdsExcel(List<String> ids, HttpServletResponse response) {
        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFRow row = null;
        HSSFCell cell = null;
        // 建立新的sheet对象（excel的表单） 并设置sheet名字
        HSSFSheet sheet = wb.createSheet("DeviceIds");
        //创建列头
        row = sheet.createRow(0);
        row.createCell(0, CellType.STRING).setCellValue("设备Id");
        row.createCell(1, CellType.STRING).setCellValue("设备名称");
        row.createCell(2, CellType.STRING).setCellValue("设备状态");

        for (int i = 0; i < ids.size(); i++) {
            Device device = this.selectByKey(ids.get(i));
            Integer status = -1;
            DeviceDetailResponseInfo deviceDetail = getFFDeviceDetail(device.getProductKey(), device.getDeviceKey(), CommonConstants.APP_KEY, CommonConstants.APP_SECRET);
            if (deviceDetail != null) {
                status = deviceDetail.getDeviceDTO().getStatus();
            }

            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(ids.get(i));
            row.createCell(1).setCellValue(device == null ? "" : device.getName());
            row.createCell(2).setCellValue(status == 0 ? "未激活" : status == 1 ? "在线" : status == 3 ? "离线" : status == 8 ? "禁用" : new String());
        }

        // 文件名
        StringBuffer fileName = new StringBuffer("exportDeviceIds_");
        fileName.append(System.currentTimeMillis() / 1000);
        fileName.append(".xls");

        String filePath = this.getClass().getResource("/").getPath() + "\\fileUpload\\";

        try {
            //输入excel
            FileOutputStream os = new FileOutputStream(filePath + fileName);
            wb.write(os);
            os.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        java.io.File file = new java.io.File(filePath, fileName.toString());

        HttpHeaders headers = new HttpHeaders();
        String downloadFileName = new String();
        try {
            downloadFileName = new String(fileName.toString().getBytes("UTF-8"), "ISO-8859-1");  //下载显示的文件名，解决中文名称乱码问题
        } catch (Exception e) {

        }
        headers.setContentDispositionFormData("attachment", downloadFileName);  //告知浏览器以下载方式打开
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); //设置MIME类型application/octet-stream ： 二进制流数据
        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {

        }
        return new ResponseEntity<byte[]>(null);
    }

    /**
     * 根据设备Id获取设备名称
     */
    @Override
    public ResultResponse<ListResultInfo<String>> getDeviceNameById(List<String> ids) {
        List<String> nameList = new ArrayList<>();
        for (String id : ids) {
            Device device = this.selectByKey(id);
            nameList.add(device.getName());
        }
        ResultResponse<ListResultInfo<String>> result = new ResultResponse<>();
        ListResultInfo<String> listResult = new ListResultInfo<>();
        listResult.setList(nameList);
        listResult.setCount(nameList.size());

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(listResult);
        return result;
    }

    /**
     * 获取飞凤产品列表
     */
    @Override
    public ResultResponse<List<KeyValuePairInfo>> getFFProductList(FFViewQueryInfo ffViewQueryInfo) {
        FFWebApp ffWebApp = ffWebAppMapper.getWebAppByProjectId(ffViewQueryInfo.getFfProjectId());
        ResultResponse<List<KeyValuePairInfo>> result = new ResultResponse<>();
        if (ffWebApp == null) {
            result.setMessage(CommonConstants.ERROR);
            result.setStatus(0);
            return result;
        }
        //获取飞凤已发布产品列表
        List<ProductResponseInfo> productResponseList = getFFReleaseProductList(ffWebApp.getAppKey(), ffWebApp.getAppSecret());
        List<KeyValuePairInfo> keyValueList = new ArrayList<>();
        KeyValuePairInfo kv;
        for (ProductResponseInfo info : productResponseList) {
            kv = new KeyValuePairInfo();
            kv.setKey(info.getProductKey());
            kv.setValue(info.getName());
            keyValueList.add(kv);
        }

        List<FFProductForm> ffProductFormList = ffProductFormMapper.getProductFormListByProjectId(ffViewQueryInfo.getFfProjectId());
        for (FFProductForm info : ffProductFormList) {
            kv = new KeyValuePairInfo();
            kv.setKey(info.getId());
            kv.setValue(info.getName());
            keyValueList.add(kv);
        }

        result.setMessage(CommonConstants.SUCCESS);
        result.setStatus(1);
        result.setData(keyValueList);
        return result;
    }

    /**
     * 获取飞凤项目信息列表
     */
    @Override
    public ResultResponse<List<FFWebAppInfo>> getFFProjectList() {
        List<FFWebApp> ffWebAppInfoList = ffWebAppMapper.getWebAppList();

        List<FFWebAppInfo> infoList = new ArrayList<>();
        FFWebAppInfo info;
        for (FFWebApp item : ffWebAppInfoList) {
            info = new FFWebAppInfo();
            info.setProjectId(item.getProjectId());
            info.setProjectName(item.getProjectName());

            List<ProductResponseInfo> productResponseInfoList = getFFReleaseProductList(item.getAppKey(), item.getAppSecret());
            Integer count = productResponseInfoList.size();
            info.setProductCount(count + ffProductFormMapper.getProductFormListByProjectId(item.getProjectId()).size());
            infoList.add(info);
        }

        ResultResponse<List<FFWebAppInfo>> result = new ResultResponse<>();
        result.setMessage(CommonConstants.SUCCESS);
        result.setStatus(1);
        result.setData(infoList);
        return result;
    }

    /**
     * 获取飞凤产品设备数据列表
     */
    @Override
    public ResultResponse<List<FFDeviceDataInfo>> getFFProductDeviceDataList(FFViewQueryInfo ffViewQueryInfo) {
        return null;
    }

    /**
     * 移动端更新安装状态
     */
    @Transactional
    @Override
    public ResultResponse updateDeviceInstallationState(DeviceDTO deviceDTO) {
        Device device = this.selectByKey(deviceDTO.getId());
        ResultResponse result = new ResultResponse<>();
        if (device == null || device.getDelete().equals(true)) {
            result.setMessage("设备不存在！");
            result.setStatus(0);
            return result;
        }

        WorkListDetail workListDetail = workListDetailMapper.getInstallWorkListById(device.getId());
        if (workListDetail == null) {
            result.setMessage(String.format("设备%s不存在安装工单！", device.getName()));
            result.setStatus(0);
            return result;
        }

        //设备
        device.setInstallationState(deviceDTO.getInstallationState());
        InitEntityExtend.initUpdateEntity(device);
        this.updateByKey(device);
        //工单详情
        InitEntityExtend.initUpdateEntity(workListDetail);
        workListDetailMapper.updateByPrimaryKey(workListDetail);

        //工单
        WorkList workList = workListMapper.selectByPrimaryKey(workListDetail.getWorkListId());
        List<String> wlDeviceIds = workListDetailMapper.getWorkListDetailListById(workList.getId()).stream().filter(a -> !a.getDeviceId().equals(deviceDTO.getId())).map(a -> a.getDeviceId()).collect(Collectors.toList());

        List<Device> deviceList = new ArrayList<>();
        if (wlDeviceIds.size() > 0) {
            Map<String, Object> params = new HashMap<>(3);
            params.put("deviceIds", wlDeviceIds);
            deviceList = deviceMapper.getNotInstallDeviceList(params);
        }

        if (deviceList.size() > 0) {
            workList.setStatus(ZM_GM_WorkState.ZM_GM_WorkState_Process.toString()); //工单状态处理中
        } else {
            workList.setStatus(ZM_GM_WorkState.ZM_GM_WorkState_Resolved.toString()); //工单状态已解决
            workList.setCloseTime(new Date());
        }

        InitEntityExtend.initUpdateEntity(workList);
        workListMapper.updateByPrimaryKey(workList);

        result.setMessage(CommonConstants.SUCCESS);
        result.setStatus(1);
        return result;
    }

    /**
     * 通用接口
     */
    private ApiResponse postIotApiRequest(String path, String apiVer, List<KeyValuePairInfo> lstParams, String appKey, String appSecret) {

        if (appKey == null || appKey.equals("")) {
            appKey = CommonConstants.APP_KEY;
            appSecret = CommonConstants.APP_SECRET;
        }

        SyncApiClient syncClient = SyncApiClient.newBuilder()
                .appKey(appKey)
                .appSecret(appSecret)
                .build();

        IoTApiRequest request = new IoTApiRequest();
        //设置api的版本
        request.setApiVer(apiVer);

        //设置接口的参数
        Iterator iterator = lstParams.iterator();
        while (iterator.hasNext()) {
            KeyValuePairInfo pairInfo = (KeyValuePairInfo) iterator.next();
            request.putParam(pairInfo.getKey().toString(), pairInfo.getValue());
        }

        //请求参数域名、path、request
        ApiResponse response = null;
        try {
            response = syncClient.postBody(CommonConstants.HOST, path, request, true);
        } catch (UnsupportedEncodingException e) {

        }
        return response;
    }

    /**
     * 获取飞凤已发布产品列表
     */
    private List<ProductResponseInfo> getFFReleaseProductList(String appKey, String appSecret) {

        List<ProductResponseInfo> result = new ArrayList<>();
        BaseResponseInfo<List<ProductResponseInfo>> bodyObj;

        //装在设备类型
        List<String> lstNodeType = new LinkedList<String>();
        lstNodeType.add("GATEWAY");
        lstNodeType.add("DEVICE");
        for (int i = 0; i < lstNodeType.size(); i++) {

            List<KeyValuePairInfo> lstParams = new ArrayList<>();
            //添加请求参数
            KeyValuePairInfo keyValue = new KeyValuePairInfo();
            //分页
            keyValue.setKey("pageNo");
            keyValue.setValue(1);
            lstParams.add(keyValue);
            //数据量
            keyValue = new KeyValuePairInfo();
            keyValue.setKey("pageSize");
            keyValue.setValue(80);
            lstParams.add(keyValue);
            //产品状态---目前只取已发布产品
            keyValue = new KeyValuePairInfo();
            keyValue.setKey("status");
            keyValue.setValue("RELEASE_STATUS");
            lstParams.add(keyValue);
            //节点类型
            keyValue = new KeyValuePairInfo();
            keyValue.setKey("nodeType");
            keyValue.setValue(lstNodeType.get(i));
            lstParams.add(keyValue);
            ApiResponse response = postIotApiRequest("/thing/product/list/get", "1.0.0", lstParams, appKey, appSecret);
            String body = null;
            try {
                body = new String(response.getBody(), "utf-8");
            } catch (UnsupportedEncodingException e) {
            }

            bodyObj = JSON.parseObject(body, new TypeReference<BaseResponseInfo<List<ProductResponseInfo>>>() {
            });
            result.addAll(bodyObj.getData());
        }
        return result;
    }

    /**
     * 获取飞凤物的详情
     */
    private DeviceDetailResponseInfo getFFDeviceDetail(String productKey, String deviceKey, String appKey, String appSecret) {
        List<KeyValuePairInfo> lstParams = new ArrayList<>();
        //添加请求参数
        KeyValuePairInfo keyValue;
        //产品Key
        keyValue = new KeyValuePairInfo();
        keyValue.setKey("productKey");
        keyValue.setValue(productKey);
        lstParams.add(keyValue);
        //物的名称
        keyValue = new KeyValuePairInfo();
        keyValue.setKey("deviceName");
        keyValue.setValue(deviceKey);
        lstParams.add(keyValue);

        ApiResponse response = postIotApiRequest("/thing/device/detail/get", "1.1.0", lstParams, appKey, appSecret);
        String body = null;
        try {
            body = new String(response.getBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        return (JSON.parseObject(body, new TypeReference<BaseResponseInfo<DeviceDetailResponseInfo>>() {
        })).getData();
    }

    /**
     * 获取飞凤物的数量
     */
    private Integer getFFDeviceCount(String productKey, String appKey, String appSecret) {
        List<KeyValuePairInfo> lstParams = new ArrayList<>();
        //添加请求参数
        KeyValuePairInfo keyValue;
        //产品Key
        keyValue = new KeyValuePairInfo();
        keyValue.setKey("productKey");
        keyValue.setValue(productKey);
        lstParams.add(keyValue);

        ApiResponse response = postIotApiRequest("/thing/device/count", "1.1.0", lstParams, appKey, appSecret);
        String body = null;
        try {
            body = new String(response.getBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        return (JSON.parseObject(body, new TypeReference<BaseResponseInfo<Integer>>() {
        })).getData();
    }

    /**
     * 获取飞凤产品设备列表
     */
    private DeviceListResponseInfo getFFDeviceListByProductKey(String productKey, Integer offset, Integer pageSize, String appKey, String appSecret) {
        List<KeyValuePairInfo> lstParams = new ArrayList<>();
        //添加请求参数
        KeyValuePairInfo keyValue;
        //产品Key
        keyValue = new KeyValuePairInfo();
        keyValue.setKey("productKey");
        keyValue.setValue(productKey);
        lstParams.add(keyValue);
        //分页大小
        keyValue = new KeyValuePairInfo();
        keyValue.setKey("pageSize");
        keyValue.setValue(pageSize);
        lstParams.add(keyValue);
        //起始位置
        keyValue = new KeyValuePairInfo();
        keyValue.setKey("offset");
        keyValue.setValue(offset);
        lstParams.add(keyValue);

        ApiResponse response = postIotApiRequest("/thing/device/list", "1.0.0", lstParams, appKey, appSecret);
        String body = null;
        try {
            body = new String(response.getBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        return (JSON.parseObject(body, new TypeReference<BaseResponseInfo<DeviceListResponseInfo>>() {
        })).getData();
    }

    /**
     * 获取飞凤产品属性
     */
    private List<ProductPropertiesResponseInfo> getFFProductProperties(String productKey, String appKey, String appSecret) {
        List<KeyValuePairInfo> lstParams = new ArrayList<>();
        //添加请求参数
        KeyValuePairInfo keyValue;
        //产品Key
        keyValue = new KeyValuePairInfo();
        keyValue.setKey("productKey");
        keyValue.setValue(productKey);
        lstParams.add(keyValue);

        ApiResponse response = postIotApiRequest("/thing/product/properties/get", "1.0.0", lstParams, appKey, appSecret);
        String body = null;
        try {
            body = new String(response.getBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        return (JSON.parseObject(body, new TypeReference<BaseResponseInfo<List<ProductPropertiesResponseInfo>>>() {
        })).getData();
    }

    /**
     * 获取飞凤物的全量属性快照数据
     */
    private List<DevicePropertiesResponseInfo> getFFDeviceProperties(String productKey, String deviceName, String appKey, String appSecret) {
        List<KeyValuePairInfo> lstParams = new ArrayList<>();
        //添加请求参数
        KeyValuePairInfo keyValue;
        //产品标识符
        keyValue = new KeyValuePairInfo();
        keyValue.setKey("productKey");
        keyValue.setValue(productKey);
        lstParams.add(keyValue);
        //物的名称
        keyValue = new KeyValuePairInfo();
        keyValue.setKey("deviceName");
        keyValue.setValue(deviceName);
        lstParams.add(keyValue);

        ApiResponse response = postIotApiRequest("/thing/device/properties/query", "1.1.0", lstParams, appKey, appSecret);
        String body = null;
        try {
            body = new String(response.getBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        return (JSON.parseObject(body, new TypeReference<BaseResponseInfo<List<DevicePropertiesResponseInfo>>>() {
        })).getData();
    }
}

