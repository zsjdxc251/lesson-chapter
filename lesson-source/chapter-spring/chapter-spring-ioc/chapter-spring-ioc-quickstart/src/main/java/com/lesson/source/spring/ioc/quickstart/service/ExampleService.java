package com.lesson.source.spring.ioc.quickstart.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author zhengshijun
 * @version created on 2018/10/14.
 */
@Service
public class ExampleService   implements IUserInfoService{


    @Autowired
    private UserInfoService userInfoService;
    @PostConstruct
    public void init(){

        System.out.println("初始化");

    }

    @PreDestroy
    public void destry(){

        System.out.println("初始化");

    }
}
