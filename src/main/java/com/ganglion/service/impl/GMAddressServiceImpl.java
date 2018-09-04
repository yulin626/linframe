package com.ganglion.service.impl;

import com.ganglion.constant.CommonConstants;
import com.ganglion.entity.Address;
import com.ganglion.mapper.AddressMapper;
import com.ganglion.mapper.DeviceMapper;
import com.ganglion.model.*;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.GMAddressService;
import com.ganglion.util.InitEntityExtend;
import com.ganglion.util.TreeUtils;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GMAddressServiceImpl extends BaseService<Address> implements GMAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    /**
     * 通过项目id获取所有地址树
     */
    @Override
    public ResultResponse<List<AddressDTO>> getAllAddressByProjectId(String projectId) {

        List<AddressDTO> list = new ArrayList<>();
        List<Address> addressList = addressMapper.getAllAddressByProjectId(projectId);

        for (Address anAddressList : addressList) {
            AddressDTO info = new AddressDTO();
            info.setId(anAddressList.getId());
            info.setParentId(anAddressList.getParentId());
            info.setName(anAddressList.getName());
            info.setAddressType(anAddressList.getAddressType());
            info.setResourceId(anAddressList.getAssociatedResources());
            list.add(info);
        }
        list = TreeUtils.recursionInitChild(list);

        ResultResponse<List<AddressDTO>> result = new ResultResponse<>();

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(list);
        return result;
    }

    /**
     * 获取一级子地址列表
     */
    @Override
    public ResultResponse<List<AddressDTO>> getChildAddressList(AddressDeviceQuery query) {

        List<AddressDTO> result = addressMapper.getChildAddressByParentId(query.getParentId());

        //设备项目不为空
        if (query.getDeviceProjects() != null && !"".equals(query.getDeviceProjects())) {
            result = result.stream()
                    .filter(r -> r.getProjectId() != null && !r.getProjectId().equals("") && query.getDeviceProjects().contains(r.getProjectId()))
                    .collect(Collectors.toList());

            result.forEach(r -> r.setRelationDeviceList(r.getRelationDeviceList().stream().filter(item -> item.getProjectId() != null && !item.getProjectId().equals("") && query.getDeviceProjects().contains(item.getProjectId())).collect(Collectors.toList())));
        }

        ResultResponse<List<AddressDTO>> postResult = new ResultResponse<>();

        postResult.setStatus(1);
        postResult.setMessage(CommonConstants.SUCCESS);
        postResult.setData(result);

        return postResult;
    }

    /**
     * 获取所有子地址设备列表
     */
    @Override
    public ResultResponse<List<AddressDTO>> getAllChildRelationDeviceList(AddressDeviceQuery query) {

        List<AddressDTO> result = new ArrayList<>();
        List<AddressDTO> addressDTOList = addressMapper.getChildAddressByParentId(query.getParentId()).stream().collect(Collectors.toList());

        //设备项目不为空
        if (query.getDeviceProjects() != null && !"".equals(query.getDeviceProjects())) {
            addressDTOList = addressDTOList.stream().filter(a -> a.getProjectId() != null && !"".equals(a.getProjectId()) && query.getDeviceProjects().contains(a.getProjectId())).collect(Collectors.toList());
        }

        List<Address> addressList = addressMapper.selectAll();
        List<String> childIds;
        for (AddressDTO dto : addressDTOList) {
            childIds = new ArrayList<>();
            //获取所有子地址列表
            getChildrenAddressIds(dto.getId(), childIds, addressList);
            List<DeviceDTO> deviceList = deviceMapper.getDeviceInAddressId(childIds);
            dto.setRelationDeviceList(deviceList);
            result.add(dto);
        }

        //设备类型不为空
        if (query.getDeviceTypes() != null && !"".equals(query.getDeviceTypes())) {
            result.forEach(r -> r.setRelationDeviceList(r.getRelationDeviceList().stream().filter(item -> item.getDeviceType() != null && !"".equals(item.getDeviceType()) && query.getDeviceTypes().contains(item.getDeviceType())).collect(Collectors.toList())));
        }

        ResultResponse<List<AddressDTO>> postResult = new ResultResponse<>();

        postResult.setStatus(1);
        postResult.setMessage(CommonConstants.SUCCESS);
        postResult.setData(result);
        return postResult;
    }

    private void getChildrenAddressIds(String parentId, List<String> childIds, List<Address> addressList) {
        if (parentId != null && !"".equals(parentId)) {
            //包括父
            childIds.add(parentId);
        }
        List<String> list = addressList.stream().filter(a -> parentId.equals(a.getParentId()) && a.getDelete() != true).map(a -> a.getId()).collect(Collectors.toList());
        for (String addressId : list) {
            getChildrenAddressIds(addressId, childIds, addressList);
        }
    }

    /**
     * 获取地址
     */
    @Override
    public ResultResponse getAddress(KeyQuery keyQuery) {
        Address address = this.selectByKey(keyQuery.getKey());

        //深度拷贝
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        AddressDTO addressDTO = mapper.map(address, AddressDTO.class);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(addressDTO);
        return result;
    }

    /**
     * 删除地址
     */
    @Override
    public ResultResponse deleteAddress(KeyQuery keyQuery) {

        Integer deviceWithAddressCount = addressMapper.getDeviceWithAddressCount(keyQuery.getKeys());

        if (deviceWithAddressCount > 0) {
            ResultResponse result = new ResultResponse();
            result.setStatus(0);
            result.setMessage("该地址被设备关联，请先处理设备关联！");
            return result;
        }

        JwtUser userDetails = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, Object> params = new HashMap<>(3);
        params.put("updatedBy", userDetails.getEmplId());
        params.put("updatedDept", "0");
        params.put("updatedTime", new Date());
        params.put("keys", keyQuery.getKeys());
        Integer deletedCount = addressMapper.deleteAddress(params);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 添加地址
     */
    @Override
    public ResultResponse addAddress(AddressDTO addressDTO) {
        //深度拷贝
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Address address = mapper.map(addressDTO, Address.class);

        InitEntityExtend.initCreateEntity(address);

        this.insert(address);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 编辑地址
     */
    @Override
    public ResultResponse editAddress(AddressDTO addressDTO) {
        Address address = this.selectByKey(addressDTO.getId());
        address.setName(addressDTO.getName());
        address.setAddressType(addressDTO.getAddressType());
        address.setDetailedAddress(addressDTO.getDetailedAddress());
        address.setAssociatedResources(addressDTO.getAssociatedResources());
        address.setGps(addressDTO.getGps());
        address.setSvg(addressDTO.getSvg());
        InitEntityExtend.initUpdateEntity(address);

        this.updateByKey(address);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

}
