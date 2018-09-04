package com.ganglion.mapper;

import com.ganglion.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EmployeeMapper extends Mapper<Employee> {

    @Select("select * from ORG_Employee where EmplName like '%${queryCondition}%'")
    List<Employee> getEmployeeList(@Param("queryCondition") String queryCondition);
}
