package com.csccloud.testdemo2;

import com.ace.cache.EnableAceCache;
import com.csccloud.testdemo2.rabbit.DemoProcessor;
import feign.Logger;
import lombok.extern.log4j.Log4j;
import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.Message;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableAceCache
//声明该应用使用 Feign组件
@EnableFeignClients
//声明使用断路器
@EnableCircuitBreaker
@EnableBinding({DemoProcessor.class})
@EnableHystrixDashboard
@Log4j
@ComponentScan(basePackages={"com.csccloud.testdemo2"})
@EnableCasClient // 开启CA
public class TestDemo2Application {
    //使用该注解才能在该应用中的RestTemplate 具备ribboon的负载功能
    @LoadBalanced
    //标注该方法为应用注入Bean对象
    @Bean
    public RestTemplate getRestTemplate(){
        return  new  RestTemplate();
    }
    @Bean
    Logger.Level feignLoggerLevel(){
        //设置全局feign日志级别
        return Logger.Level.FULL;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestDemo2Application.class, args);
    }


}
