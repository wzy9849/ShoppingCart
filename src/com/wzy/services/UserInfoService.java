package com.wzy.services;

import com.wzy.entity.UserInfo;

import java.sql.SQLException;
import java.util.List;

public interface UserInfoService {
    List<UserInfo> queryAllUserInfo();

    UserInfo queryUserInfoById(int id);

    UserInfo queryUserInfoByUsername(String username);

    boolean addUserInfo(UserInfo userInfo);

    boolean isExist(String username);

    boolean isCorrect(String username,String password);
}
