package com.wzy.dao;

import com.wzy.entity.UserInfo;

import java.sql.SQLException;
import java.util.List;

public interface UserInfoDao {
    //查询所有用户
    List<UserInfo> queryAllUserInfo() ;
    //根据用户id查询用户
    UserInfo queryUserInfoById(int id);
    //根据用户名查询用户
    UserInfo queryUserInfoByUsername(String username);
    //添加用户
    boolean addUserInfo(UserInfo userInfo);
    //判断用户名是否存在
    boolean userInfoIsExist(String username) ;
    //判读用户名与密码是否匹配正确
    boolean isCorrect(String username, String password);
}
