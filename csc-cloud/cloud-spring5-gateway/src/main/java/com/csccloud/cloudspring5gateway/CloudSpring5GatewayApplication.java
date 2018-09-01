package com.csccloud.cloudspring5gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients

public class CloudSpring5GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudSpring5GatewayApplication.class, args);
    }
}
