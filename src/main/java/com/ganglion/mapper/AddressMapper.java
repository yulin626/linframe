package com.ganglion.mapper;

import com.ganglion.entity.Address;
import com.ganglion.model.AddressDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface AddressMapper extends Mapper<Address> {

    List<Address> getAllAddressByProjectId(String projectId);
    Integer getDeviceWithAddressCount(List<String> keys);
    Integer deleteAddress(Map<String, Object> params);
    List<AddressDTO> getChildAddressByParentId(@Param("parentId") String parentId);
}
