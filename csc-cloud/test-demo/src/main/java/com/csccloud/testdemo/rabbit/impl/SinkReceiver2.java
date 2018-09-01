package com.csccloud.testdemo.rabbit.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SinkReceiver2 {

    // 监听 binding 为 Processor.INPUT 的消息
    @StreamListener(Processor.INPUT)
    public void input(Message<String> message) {
        log.error(">>>>>>>>>>>>>>>>>>>>22222"+message.getPayload());
    }

}
