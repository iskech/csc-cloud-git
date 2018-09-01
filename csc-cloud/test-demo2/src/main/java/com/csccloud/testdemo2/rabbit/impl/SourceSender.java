package com.csccloud.testdemo2.rabbit.impl;



import com.csccloud.testdemo2.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SourceSender {
//    @Autowired
//    private Source source;
//    public void receive(User user){
//        //发布消息
//        source.output().send(MessageBuilder.withPayload(user).build());
//    }
}
