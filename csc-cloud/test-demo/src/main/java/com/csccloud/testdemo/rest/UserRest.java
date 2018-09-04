package com.csccloud.testdemo.rest;


//import com.codingapi.tx.annotation.TxTransaction;
import com.csccloud.testdemo.entity.User;
import com.csccloud.testdemo.feign.FeignTestDemo;

import com.csccloud.testdemo.rabbit.DemoProcessor;
import com.csccloud.testdemo.rabbit.impl.DemoSourceSender;
import com.csccloud.testdemo.rabbit.impl.SourceSender;
import com.csccloud.testdemo.service.UserServiceImpl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Random;

/**
 * @author iskch
 * @create 2017-08-20
 */
@Slf4j
@RestController
@RequestMapping("api")
public class UserRest {
    @Autowired
    private UserServiceImpl userBiz;
    //注入 feignTestDemo 代理实体
    @Autowired
    private FeignTestDemo feignTestDemo;
    @Autowired
    private SourceSender sourceSender;
    @Autowired
    private DemoSourceSender demoSourceSender;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DemoProcessor demoProcessor;
    @Autowired
    @Qualifier(DemoProcessor.OUTPUT)//指明消息发布队列
    private MessageChannel messageChannel;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    //@HystrixCommand
    public @ResponseBody
    User getAllPermission( @RequestParam("name") String name) {

//        try {
//            Thread.sleep(5001);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        User userByUsername = userBiz.getUserByUsername(name);
        sourceSender.send(userByUsername);
        demoSourceSender.send(userByUsername);
        return userByUsername;
    }

    @RequestMapping(value = "/test/update", method = RequestMethod.POST)
    @ResponseBody
    //方法级别注解 表示该方法使用HystixCommand 代理 具备熔断机制功能默认等待允许等待时间1000毫秒
    //@HystrixCommand(commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1100"))
    public String updateUser(String name) {
        log.debug("UserRest: updateUser name: "+name);
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使用feign 调用远程服务接口
        User user = feignTestDemo.getUserByName(name);
        log.error("UserRest: updateUser name222: "+name);
        User user1 = new User();
        userBiz.updateSelectiveById(user);
        log.warn("UserRest: updateUser name333: "+name);
        return user.getUsername();
    }



    @RequestMapping(value = "/test/t", method = RequestMethod.GET)
    @ResponseBody
    //@HystrixCommand
    public
    String gettest(String name) {
        return "你忙吧";
    }


    /**
     * 测试使用ribbon 调用远程服务接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/test/ribbonRestTemplate", method = RequestMethod.GET)
    @ResponseBody
    public User testRibbonTemplate( @RequestParam("name") String name) {
        log.debug("Class :"+UserRest.class+"Method: testRibbonTemplate"+"  params:"+name.toString());
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        ResponseEntity<User> forEntity = restTemplate.getForEntity
                ("http://test-demo2/api/test2/ribbonRestTemplate?name={name}", User.class, map);
        User user = forEntity.getBody();
        return user;
    }



    /**
     * 测试使用feign组件调用远程接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/test/feign", method = RequestMethod.GET)
    @ResponseBody
    public User testfeign( @RequestParam("name") String name) {
        log.debug("Class :"+UserRest.class+"Method: tesfeign"+"params:"+name.toString());
        User user = feignTestDemo.testFeign(name);
        return user;
    }

    /**
     * 测试使用feign组件调用远程接口服务出异常进入feign后备模式
     * @param name
     * @return
     */
    @RequestMapping(value = "/test/feignFallback", method = RequestMethod.GET)
    @ResponseBody
    public User testFeignFallback( @RequestParam("name") String name) {
        log.debug("Class :"+UserRest.class+"Method: testFeignFallback"+"  params:"+name.toString());
        User user = feignTestDemo.testFeignFallback(name);
        return user;
    }

    /**
     * 测试使用hystrix组件 熔断机制
     * @param name
     * @return
     */
    @RequestMapping(value = "/test/feignHystrix", method = RequestMethod.GET)
    @ResponseBody
    //方法级别注解 表示该方法使用HystixCommand 代理 具备熔断机制功能默认等待允许等待时间1000毫秒
    //若调用服务超时则 报 message": "testHystrix timed-out and fallback failed" 错.
    //hystrix 熔断机制 即提升微服务架构 容错能力,当一服务调用失败或者说响应超时可以快速失败该次服务调用,而不是一直调用该服务引起雪崩效应耗尽系统资源
    @HystrixCommand(commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1100"))
    public User testHystrix( @RequestParam("name") String name) {
        log.debug("Class :"+UserRest.class+"Method: testHystrix"+"  params:"+name.toString());
        Random random = new Random();
        int i = random.nextInt(2);
        if(i==0){

        }else if(i==1){
            try {
                //模拟接口调用超时 1111>1100
                Thread.sleep(1111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        User user = feignTestDemo.testFeign(name);
        return user;
    }

    /**
     * 测试使用自定义通道接口 使用SpringCloud Stream 实现事件驱动 即消息机制的封装
     * @param name
     * @return
     */
    @RequestMapping(value = "/test/cloudStream", method = RequestMethod.GET)
    @ResponseBody
    public User testCloudStream( @RequestParam("name") String name) {
        log.debug("Class :"+UserRest.class+"Method: testCloudStream"+"  params:"+name.toString());
        User user = feignTestDemo.testFeign(name);
        if("liujx".equals(name)){
        //使用自定义接口发布 消息 到队列
        demoProcessor.demoOutput().send(MessageBuilder.withPayload(user).build());}else {
        //使用 springCloud 封装接口 MessageChannel发布消息到对列
        messageChannel.send(MessageBuilder.withPayload("使用MessageChannel发布的消息").build());}
        return user;
    }

    /**
     * 测试使用redis缓存
     * @param name
     * @return
     */
    @RequestMapping(value = "/test/redisCache", method = RequestMethod.GET)
    @ResponseBody
    public User testRedisCache( @RequestParam("name") String name) {
        log.debug("Class :"+UserRest.class+"Method: testRedisCache"+"  params:"+name.toString());
        User user = userBiz.getUserByUsername(name);
        return user;
    }

    @RequestMapping(value = "/test/testUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String testUpdateUser(String name) {
        log.debug("Class :"+UserRest.class+"Method: testUpdateUser"+"  params:"+name.toString());
        User user = userBiz.getUserByUsername(name);
        userBiz.updateSelectiveById(user);
        return user.getUsername();
    }

    /**
     * 测试分布式事务
     * @param name
     * @return
     */
    @RequestMapping(value = "/test/transaction", method = RequestMethod.GET)
    @ResponseBody
    //@TxTransaction(isStart = true)
    public User testTransaction( @RequestParam("name") String name) {
        log.debug("Class :"+UserRest.class+"Method: testTransaction"+"  params:"+name.toString());
        User user = userBiz.getUserByUsername(name);
        user.setId(2);
        userBiz.updateById(user);
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        try {

            ResponseEntity<User> forEntity = restTemplate.getForEntity
                    ("http://test-demo2/api/test2/transaction?name={name}", User.class, map);
        }catch (Exception e){}

        return user;
    }

}
