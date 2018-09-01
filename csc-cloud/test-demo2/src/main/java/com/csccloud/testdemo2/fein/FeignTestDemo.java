package com.csccloud.testdemo2.fein;

import com.csccloud.testdemo2.entity.User;
import com.csccloud.testdemo2.fallback.FallBackTemplate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * feign 组件为微服务显示声明调用远程REST 服务提供实现.
 * 实现步骤:1,添加feign jar 包依赖,2.主配置类中贴上@EnableFeignClients注解表明该应用使用feign组件,
 * 3.编写feignclient 接口 ,4.在需要调用远程微服务类中@Autowired注入feignclient 代理对象,即可使用该代理对象调用远程微服务了.
 *
 * 声明该接口为 Feign 组件接口 name=test-demo2 表明该 FeignClient 接口将调用注册在Eureka 中服务名为test-demo2 的服务
 * fallback = FallBackTemplate.class 指明降级方法实现类
 */
@FeignClient(name = "test-demo",fallback = FallBackTemplate.class)
public interface FeignTestDemo {
    @RequestMapping(value = "/api/test/feign", method = RequestMethod.GET)
    @ResponseBody
    User getUserByName(@RequestParam("name") String name);
}
