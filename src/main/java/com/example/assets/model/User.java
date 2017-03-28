package com.example.assets.model;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/3/5.
 * 用户表
 */

public class User extends DataSupport {
    private int id;
    private String name;//用户名
    private String password;//密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
