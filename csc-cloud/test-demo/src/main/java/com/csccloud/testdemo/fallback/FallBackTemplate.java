package com.csccloud.testdemo.fallback;

import com.csccloud.testdemo.entity.User;
import com.csccloud.testdemo.fein.FeignTestDemo;
import com.csccloud.testdemo.rest.UserRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Slf4j
public class FallBackTemplate implements FeignTestDemo {
    /**
     * feign 组件对hystix 熔断回退 功能的支持.
     * 实现步骤:1,引入hystix jar 包,2,在主配置类中 贴上注解 @EnableCircuitBreaker或者@EnableHystrix
     * 3,需要在配置文件开启 feign 对hystirx的支持,4编写降级类,即feignclient 接口实现类 并指定 fallback=实现类.class
     * 该类即为 feignclient 实现类 当feignclient调用声明的远程服务出错 则回调该类实现方法.实现微服务后备模式.
     * 微服务后备模式最佳方实现方式.
     * @param name
     * @return
     */
    @Override
    public User getUserByName(@RequestParam("name") String name) {
        log.debug("demo1降级方法fallback :后备模式");
        User user = new User();
        user.setUsername("降级方法fallback :后备模式");
        user.setId(5);
        user.setName(name);
        return  user;
    }

    @Override
    public User testFeign(String name) {
        return null;
    }

    @Override
    public User testFeignFallback(String name) {
        log.debug("Class :"+UserRest.class+"   Method: testFeignFallBack"+"   params:" +name.toString());
        User user = new User();
        user.setUsername("降级方法 testFeignFallback :后备模式");
        user.setId(5);
        user.setName(name);
        return  user;
    }
}
