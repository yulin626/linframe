package com.ganglion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Server Send Event服务端发送事件
 */
@RestController
public class SseController {

    @RequestMapping(value = "/push",produces = "text/event-stream")
    public String push(){
        Random r = new Random();
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "data:Testing 1,2,3"+r.nextInt()+"\n\n";
    }
}
