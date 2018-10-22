package com.lesson.source.spring.ioc.quickstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhengshijun
 * @version created on 2018/10/14.
 */

@Service
public class ExampleService   implements IUserInfoService{

    @Autowired
    private UserInfoService userInfoService;

    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    public UserInfoService getUserInfoService() {
        return userInfoService;
    }
}
