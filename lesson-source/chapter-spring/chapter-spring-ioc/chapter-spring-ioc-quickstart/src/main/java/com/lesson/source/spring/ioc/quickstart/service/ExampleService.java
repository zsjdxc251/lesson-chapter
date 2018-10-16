package com.lesson.source.spring.ioc.quickstart.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhengshijun
 * @version created on 2018/10/14.
 */
public class ExampleService {

    //@Autowired
    private UserInfoService userInfoService;



    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public String toString() {
        return "userInfoService:"+userInfoService;
    }
}
