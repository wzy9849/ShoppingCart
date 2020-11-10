package com.wzy.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String address;

    public UserInfo() {
    }

    public UserInfo(int id, String username, String password, String phone, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                '}';
    }
}
