package com.csccloud.testdemo2.rabbit.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SinkReceiver1 {

    @StreamListener("demo_input")
    public void receive(Message<String> message){
        log.debug("远程服务 自定义 springcloud Stream 监听事件 消费消息:"+message.getPayload());
    }
}
