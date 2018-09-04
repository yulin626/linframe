package com.ganglion.controller;

import com.ganglion.model.KeyValuePairInfo;
import com.ganglion.model.PageQueryInfo;
import com.ganglion.msg.ListResultInfo;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.ORGEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/orgEmployee")
public class ORGEmployeeController {
    @Autowired
    private ORGEmployeeService orgEmployeeService;

    /**
     * 获取人员列表
     */
    @CrossOrigin
    @PostMapping(value = "/getEmployeeList")
    public ResultResponse<ListResultInfo<KeyValuePairInfo>> getEmployeeList(@RequestBody PageQueryInfo pageQueryInfo) {
        return orgEmployeeService.getEmployeeList(pageQueryInfo);
    }
}
