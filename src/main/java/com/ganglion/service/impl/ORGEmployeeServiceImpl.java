package com.ganglion.service.impl;

import com.ganglion.constant.CommonConstants;
import com.ganglion.entity.Employee;
import com.ganglion.mapper.EmployeeMapper;
import com.ganglion.model.KeyValuePairInfo;
import com.ganglion.model.PageQueryInfo;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.ORGEmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ORGEmployeeServiceImpl extends BaseService<Employee> implements ORGEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取人员列表
     */
    @Override
    public ResultResponse<ListResultInfo<KeyValuePairInfo>> getEmployeeList(PageQueryInfo pageQueryInfo) {
        Page page = PageHelper.startPage(pageQueryInfo.getPageNumber(), pageQueryInfo.getPageSize(), true);
        List<Employee> employeeList = employeeMapper.getEmployeeList(pageQueryInfo.getQueryCondition());

        List<KeyValuePairInfo> list = new ArrayList<>();
        for (Employee employee:employeeList){
            KeyValuePairInfo info = new KeyValuePairInfo();
            info.setKey(employee.getEmplID());
            info.setValue(employee.getEmplName());
            list.add(info);
        }

        ResultResponse<ListResultInfo<KeyValuePairInfo>> result = new ResultResponse<>();
        ListResultInfo<KeyValuePairInfo> listResult = new ListResultInfo<>();
        listResult.setList(list);
        listResult.setCount(page.getTotal());

        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        result.setData(listResult);

        return result;
    }
}
