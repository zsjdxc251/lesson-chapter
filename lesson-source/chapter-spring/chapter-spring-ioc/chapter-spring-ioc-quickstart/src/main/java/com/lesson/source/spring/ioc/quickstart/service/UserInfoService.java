package com.lesson.source.spring.ioc.quickstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhengshijun
 * @version created on 2018/10/14.
 */

@Service
public class UserInfoService implements IUserInfoService{




    @Autowired
    private ExampleService exampleService;


    public void setExampleService(ExampleService exampleService) {
        this.exampleService = exampleService;
    }
}
