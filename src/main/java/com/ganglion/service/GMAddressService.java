package com.ganglion.service;

import com.ganglion.model.AddressDTO;
import com.ganglion.model.AddressDeviceQuery;
import com.ganglion.model.KeyQuery;
import com.ganglion.msg.ResultResponse;

import java.util.List;

public interface GMAddressService {

    /**
     *  通过projectId获取所有地址
     */
    ResultResponse<List<AddressDTO>> getAllAddressByProjectId(String projectId);

    /**
     *  获取一级子地址列表
     */
    ResultResponse<List<AddressDTO>> getChildAddressList(AddressDeviceQuery query);

    /**
     *  获取所有子地址设备列表
     */
    ResultResponse<List<AddressDTO>> getAllChildRelationDeviceList(AddressDeviceQuery query);

    /**
     * 获取地址
     */
    ResultResponse getAddress(KeyQuery addressDTO);

    /**
     * 删除地址
     */
    ResultResponse deleteAddress(KeyQuery addressDTO);

    /**
     * 新增地址
     */
    ResultResponse addAddress(AddressDTO addressDTO);

    /**
     * 编辑地址
     */
    ResultResponse editAddress(AddressDTO addressDTO);
}
