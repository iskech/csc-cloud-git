package com.csccloud.testdemo.rabbit.impl;


import com.csccloud.testdemo.rabbit.DemoProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class DemoSinkReceiver1 {

    // 监听 binding 为 Processor.INPUT 的消息
    @StreamListener(DemoProcessor.INPUT)
    public void input(Message<String> message) {
        //消费自定义接口xxxoo 组demo_queen 队列中消息. 队列分组可以 防止同队列下消息被多个服务监听消费
        log.debug("本地服务 自定义 springcloud Stream 监听事件 消费消息:"+message.getPayload());
    }

}
