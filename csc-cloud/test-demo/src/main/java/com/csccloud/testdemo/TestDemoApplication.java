package com.csccloud.testdemo;


import com.ace.cache.EnableAceCache;
import com.csccloud.testdemo.rabbit.DemoProcessor;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//声明该应用作为Eureka 客户端
@EnableEurekaClient
//使用 cache
@EnableAceCache
//声明该应用使用 Feign组件
@EnableFeignClients
//声明使用断路器
@EnableCircuitBreaker
//允许服务不重启刷新配置
//@RefreshScope
//绑定cloud stream 中定义的发布者 Source ,绑定自定义的通道接口
@EnableBinding({Source.class,Sink.class,DemoProcessor.class})
//@EnableHystrix
public class TestDemoApplication {

    //使用该注解才能在该应用中的RestTemplate 具备ribbon的负载功能
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
        SpringApplication.run(TestDemoApplication.class, args);
    }
}
