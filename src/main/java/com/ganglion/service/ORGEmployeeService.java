package com.ganglion.service;

import com.ganglion.model.KeyValuePairInfo;
import com.ganglion.model.PageQueryInfo;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;

public interface ORGEmployeeService {
    /**
     * 获取人员列表
     */
    ResultResponse<ListResultInfo<KeyValuePairInfo>> getEmployeeList(PageQueryInfo pageQueryInfo);
}
