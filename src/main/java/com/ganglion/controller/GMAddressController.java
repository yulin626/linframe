package com.ganglion.controller;

import com.ganglion.model.AddressDTO;
import com.ganglion.model.AddressDeviceQuery;
import com.ganglion.model.KeyQuery;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.GMAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/gmAddress")
public class GMAddressController {

    @Autowired
    private GMAddressService gmAddressService;

    /**
     * 通过项目id获取所有地址树
     */
    @CrossOrigin
    @PostMapping(value = "/getAllAddressByProjectId")
    public ResultResponse<List<AddressDTO>> getAllAddressByProjectId(@RequestBody AddressDTO addressDTO) {
        return gmAddressService.getAllAddressByProjectId(addressDTO.getProjectId());
    }

    /**
     * 获取一级子地址列表
     */
    @CrossOrigin
    @PostMapping(value = "/getChildAddressList")
    public ResultResponse<List<AddressDTO>> GetChildAddressList(@RequestBody AddressDeviceQuery query) {
        return gmAddressService.getChildAddressList(query);
    }

    /**
     * 获取所有子地址设备列表
     */
    @CrossOrigin
    @PostMapping(value = "/getAllChildRelationDeviceList")
    public ResultResponse<List<AddressDTO>> GetAllChildRelationDeviceList(@RequestBody AddressDeviceQuery query) {
        return gmAddressService.getAllChildRelationDeviceList(query);
    }

    /**
     * 获取地址
     */
    @CrossOrigin
    @PostMapping(value = "/getAddress")
    public ResultResponse getAddress(@RequestBody KeyQuery keyQuery) {
        return gmAddressService.getAddress(keyQuery);
    }

    /**
     * 删除地址
     */
    @CrossOrigin
    @PostMapping(value = "/deleteAddress")
    public ResultResponse deleteAddress(@RequestBody KeyQuery keyQuery) {
        return gmAddressService.deleteAddress(keyQuery);
    }

    /**
     * 新增地址
     */
    @CrossOrigin
    @PostMapping(value = "/addAddress")
    public ResultResponse addAddress(@RequestBody AddressDTO addressDTO) {
        return gmAddressService.addAddress(addressDTO);
    }

    /**
     * 编辑地址
     */
    @CrossOrigin
    @PostMapping(value = "/editAddress")
    public ResultResponse editAddress(@RequestBody AddressDTO addressDTO) {
        return gmAddressService.editAddress(addressDTO);
    }
}
