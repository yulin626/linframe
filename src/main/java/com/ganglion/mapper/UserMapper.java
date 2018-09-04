package com.ganglion.mapper;

import com.ganglion.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    @Select("select * from ORG_User where UserName = #{userName}")
    User findByUsername(@Param("userName")String userName);
}
