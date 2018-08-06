package com.chapter.microservice.cloud.zookeeper.api.model;

/**
 * @author zhengshijun
 * @version created on 2018/8/6.
 */
public class UserInfo extends BaseEntry {

    private String userName;

    private String password;

    private String address;

    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
