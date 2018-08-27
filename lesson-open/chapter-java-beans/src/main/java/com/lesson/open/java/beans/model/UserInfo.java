package com.lesson.open.java.beans.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author zhengshijun
 * @version created on 2018/8/27.
 */
public class UserInfo extends AbstractModel{

    private String id;

    private String username;

    private String password;

    private Integer age;

    @JSONField(format="yyyy-MM-dd")
    private Date createTime;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer findAge(){
        return age;
    }
}
