package com.csccloud.testdemo.rabbit.impl;


import com.csccloud.testdemo.entity.User;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SourceSender {

    @Autowired
    private Source source;

    public void send(User user){
        //发布消息
        log.debug("-----------------------------------");
        source.output().send(MessageBuilder.withPayload(user).build());
        log.debug("================================"+source.toString());
    }
}
