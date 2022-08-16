package com.hml.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author hml
 * @version 1.0
 * @description: TODO
 * @date 2022/8/16 9:11
 */


@Slf4j
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    //自定义topic
    public static final String TOPIC_TEST = "topic.test";

    public static final String TOPIC_GROUP1 = "topic.group1";

    public static final String TOPIC_GROUP2 = "topic.group2";

    /**
     * 消息发送
     */
    public void send(Object obj){
        log.info("kafka消息准备发送消息为：{}", JSONObject.toJSONString(obj));
        //发送消息
        ListenableFuture<SendResult<String,Object>> future = kafkaTemplate.send(TOPIC_TEST,obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                //发送失败处理
                log.error("Kafka生产者,发送消息失败：Tompic = {}, errMsg = {}", TOPIC_TEST, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                //成功的处理
                log.info("Kafka生产者,发送消息成功：Tompic = {}, msg = {}", TOPIC_TEST, result.toString());
            }
        });
    }
}
