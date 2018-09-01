package com.csccloud.testdemo.mapper;



import com.csccloud.testdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper  {
    public List<User> selectMemberByGroupId(@Param("groupId") int groupId);
    public List<User> selectLeaderByGroupId(@Param("groupId") int groupId);

    User queryone(String name);

    void updateUser(User user);

    void updateUserById(User user);
}