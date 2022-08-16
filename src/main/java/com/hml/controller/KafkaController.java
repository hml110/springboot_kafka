package com.hml.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hml
 * @version 1.0
 * @description: TODO 创建调用生产者
 * @date 2022/8/16 9:35
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;


    @PostMapping("/send")
    public String sendMsg(){
        kafkaProducer.send("11111111");
        return "send success!";
    }


}
