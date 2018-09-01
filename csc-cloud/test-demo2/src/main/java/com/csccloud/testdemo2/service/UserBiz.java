package com.csccloud.testdemo2.service;


import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.csccloud.testdemo2.entity.User;

import com.csccloud.testdemo2.mapper.UserMapper;
import com.csccloud.testdemo2.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.backoff.Sleeper;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-08 16:23
 */
@Service
@Slf4j
//@Transactional(rollbackFor = Exception.class)
public class UserBiz  {
    @Autowired
    private UserMapper mapper;


    @CacheClear(pre="user{1.username}")
    public void updateSelectiveById(User entity) {

    }

    /**
     * 根据用户名获取用户信息
     * cache redis key 规定 服务名：key user:指定参数值
     * @param username
     * @return
     */
    //@Cache(key="user{1}")
    public User getUserByUsername(String username) throws RuntimeException {
        log.debug("test-demo2 getUserByUsername ");

        User user = mapper.queryone(username);
        user.setUsername("通过test-demo2客服端修改名字:"+username);
        return user;
    }

 /**
     * 更新用户名获取用户信息
     * @param username
     * @return
     */
    @CacheClear(pre = "user{1}")
    public User updateUserByUsername(String username){
        log.debug("test-demo2 updte");


        User user = getUserByUsername(username);
        user.setUpdTime(new Date());
        mapper.updateUser(user);
       return user ;
    }


}
