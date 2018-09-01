package com.csccloud.clouderueka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//SpringBoot 组合注解，声明该应用为SpringBoot项目，并使用自动装配
@SpringBootApplication
//该模块作为 eureka 服务注册中心
@EnableEurekaServer
public class CloudEruekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudEruekaApplication.class, args);
    }
}
