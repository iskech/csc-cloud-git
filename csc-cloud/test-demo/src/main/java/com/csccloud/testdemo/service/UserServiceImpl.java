package com.csccloud.testdemo.service;


import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.csccloud.testdemo.entity.User;

import com.csccloud.testdemo.mapper.UserMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-08 16:23
 */
@Service
@Log4j
//@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl {
    @Autowired
    private UserMapper mapper;


    @CacheClear(pre="user{1.name}")
    public void updateSelectiveById(User entity) {
            mapper.updateUser(entity);
    }

    /**@cache表明该方法使用redis 缓存
     * 根据用户名获取用户信息
     * cache redis key 规定 服务名：key user:指定参数值
     * "liu:user:liujx" 本服务key示例
     * @param name
     * @return
     */
    @Cache(key="user{1}")
    public User getUserByUsername(String name){
        User user = new User();
        user.setUsername(name);
       return mapper.queryone(name);
    }

 /**
     * 更新用户名获取用户信息
     * @param name
     * @return
     */
    @CacheClear(pre = "user{1}")
    public User updateUserByUsername(String name){
        log.debug("进入updateUserByUsername 方法 name:"+name);
        User user = getUserByUsername(name);
        user.setUpdTime(new Date());
        mapper.updateUser(user);
       return user ;
    }


    public void updateById(User user) {

        user.setUpdTime(new Date());
        mapper.updateUserById(user);

    }
}
