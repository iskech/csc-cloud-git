package com.csccloud.testdemo2.rest;



import com.csccloud.testdemo2.entity.User;
import com.csccloud.testdemo2.fein.FeignTestDemo;
import com.csccloud.testdemo2.service.UserBiz;
import feign.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author iskech
 * @create 2017-08-20
 */
@Slf4j
@RestController
@RequestMapping("api")
public class UserRest {
    @Autowired
    private UserBiz userBiz;

    @Autowired
    private FeignTestDemo feignTestDemo;

    //@Cache(key="permission")
    @RequestMapping(value = "/test2/queryone", method = RequestMethod.POST)
    public @ResponseBody
    User getAllPermission(@RequestParam("name") String name) {
        log.debug("进入test-demo2 queryone");
        if (name != null) {
            int i =1/0;
        }
        return userBiz.getUserByUsername(name);
    }

    @RequestMapping(value = "/test2/update", method = RequestMethod.POST)
    public @ResponseBody
    String updateUser(String name) {
        if (name != null) {
        //    throw new RuntimeException();
        }
        userBiz.updateUserByUsername(name);
        return name;
    }


    /**
     * 测试使用ribbon 调用远程服务接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/test2/ribbonRestTemplate", method = RequestMethod.GET)
    @ResponseBody
    //@HystrixCommand
    public User testRibbonTemplate( @RequestParam("name") String name) {
        log.debug("Class :"+UserRest.class+"Method: testRibbonTemplate"+"  params:"+name.toString());
        User user = userBiz.getUserByUsername(name);
        return user;
    }

    /**
     * 测试使用feign组件调用远程接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/test2/feign", method = RequestMethod.GET)
    @ResponseBody
    public User testFeign(String name) {
        log.debug("Class :"+UserRest.class+"   Method: tesfeign"+"   params:"+name.toString());
        User user = userBiz.getUserByUsername(name);
        return user;
    }

    /**
     * 测试使用feign组件调用远程接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/test2/feignFallback", method = RequestMethod.GET)
    @ResponseBody
    public User testFeignFallBack(String name) {
        log.debug("Class :"+UserRest.class+"   Method: testFeignFallBack"+"   params:" +name.toString());
        //模拟被调用服务出异常
        int i =1/0;
        User user = new User();
        return user;
    }


    /**
     * 测试分布式事务
     * @param name
     * @return
     */
    @RequestMapping(value = "/test2/transaction", method = RequestMethod.GET)
    @ResponseBody
    //@TxTransaction
    public User testTransaction( @RequestParam("name") String name) {
        log.debug("Class :"+UserRest.class+"Method: testTransaction"+"  params:"+name.toString());
            //模拟出异常
        int  i=1/0;
        return null;
    }


}
