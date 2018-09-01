package com.csccloud.testdemo2.rabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义接口
 */
public interface DemoProcessor {
    String INPUT = "demo_input";
    String OUTPUT = "demo_output";

    @Input(INPUT)
    SubscribableChannel demoInput();
//自定义MessageChannel
    @Output(OUTPUT)
    MessageChannel demoOutput();


}
