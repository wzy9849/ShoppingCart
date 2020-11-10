package com.wzy.services.impl;

import com.wzy.dao.Impl.UserInfoDaoImpl;
import com.wzy.dao.UserInfoDao;
import com.wzy.entity.UserInfo;
import com.wzy.services.UserInfoService;

import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoDao userInfoDao = new UserInfoDaoImpl();

    @Override
    public List<UserInfo> queryAllUserInfo() {
        List<UserInfo> list = userInfoDao.queryAllUserInfo();
        return list;
    }

    @Override
    public UserInfo queryUserInfoById(int id) {
        return userInfoDao.queryUserInfoById(id);
    }

    @Override
    public UserInfo queryUserInfoByUsername(String username) {
        return userInfoDao.queryUserInfoByUsername(username);
    }

    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        return  userInfoDao.addUserInfo(userInfo);
    }

    @Override
    public boolean isExist(String username) {
        return userInfoDao.userInfoIsExist(username);
    }

    @Override
    public boolean isCorrect(String username, String password) {
        return userInfoDao.isCorrect(username, password);
    }
}
