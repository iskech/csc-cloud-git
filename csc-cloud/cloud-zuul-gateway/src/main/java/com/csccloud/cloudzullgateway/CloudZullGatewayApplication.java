package com.csccloud.cloudzullgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@SpringBootApplication
//使用spingcloud zuul 组件代理
@EnableZuulProxy
//使用eureka client 客户端组件
@EnableEurekaClient
@RestController
//单点登录
//@EnableOAuth2Sso
public class CloudZullGatewayApplication {


    //使用该注解才能在该应用中的RestTemplate 具备ribbon的负载功能
    @LoadBalanced
    //标注该方法为应用注入Bean对象
    @Bean
    public RestTemplate getRestTemplate(){
        return  new  RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(CloudZullGatewayApplication.class, args);
    }

//    //@Bean
//    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext oAuth2ClientContext, OAuth2ProtectedResourceDetails details){
//        return new OAuth2RestTemplate(details,oAuth2ClientContext);
//    }

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
