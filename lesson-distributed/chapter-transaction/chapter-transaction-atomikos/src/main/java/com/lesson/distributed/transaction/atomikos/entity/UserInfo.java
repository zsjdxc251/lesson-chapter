package com.lesson.distributed.transaction.atomikos.entity;

/**
 * @author zhengshijun
 * @version created on 2018/10/16.
 */
public class UserInfo {

    private String username;

    private String password;


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
}
