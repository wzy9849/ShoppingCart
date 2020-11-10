package com.wzy.dao.Impl;

import com.wzy.dao.UserInfoDao;
import com.wzy.entity.UserInfo;
import com.wzy.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDaoImpl implements UserInfoDao {

    @Override
    public List<UserInfo> queryAllUserInfo() {

        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select id,username,password,phone,address from userInfo";
        List<UserInfo> list = null;
        // 调用方法
        try {
            list = (List) queryRunner.query(sql, new BeanListHandler(UserInfo.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public UserInfo queryUserInfoById(int id) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select id,username,password,phone,address from userInfo where id = ?";
        UserInfo userInfo = null;
        try {
            userInfo = (UserInfo) queryRunner.query(sql, id, new BeanHandler(UserInfo.class));
            return userInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    @Override
    public UserInfo queryUserInfoByUsername(String username) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select id,username,password,phone,address from userInfo where username = ?";
        UserInfo userInfo = null;
        try {
            userInfo = (UserInfo) queryRunner.query(sql, username, new BeanHandler(UserInfo.class));
            return userInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());

        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        String phone = userInfo.getPhone();
        String address = userInfo.getAddress();
        String sql = "insert into userInfo(username,password,phone,address) values(?,?,?,?)";

        int row = 0;
        try {
            row = queryRunner.update(sql,
                    new Object[]{username, password, phone, address});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("row = " + row);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean userInfoIsExist(String username) {
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "select id,username,password,phone,address from userInfo where username = ?";
        List userInfo = null;
        try {
            userInfo = (List) queryRunner.query(sql, username, new BeanListHandler(UserInfo.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!userInfo.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCorrect(String username, String password) {
        List<UserInfo> list = queryAllUserInfo();
        List<String> listNames = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            listNames.add(list.get(i).getUsername());
        }
        boolean isCorrect = list.get(listNames.indexOf(username)).getPassword().equals(password);
        return isCorrect;
    }
}
