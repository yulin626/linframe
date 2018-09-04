package com.ganglion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {

    @RequestMapping("/welcome")
    public Map<String,Object> welcome(@RequestParam(value="name",defaultValue = "World") String name){
        Map<String,Object> result = new HashMap<>();
        result.put("msg","welcome to ganglion service!");
        return  result;
    }

    public  Map<String,Object> error(){
        Map<String,Object> result = new HashMap<>();
        result.put("error","接口调用发生错误!");
        return  result;
    }
}
