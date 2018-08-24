package com.lesson.microservice.boot.sample.entity;

import javax.validation.constraints.NotNull;

/**
 * @author zhengshijun
 * @version created on 2018/8/24.
 */
public class UserInfo {

    @NotNull(message = "{user.info.id}")
    private String id;

    private String username;

    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
