package com.lesson.source.spring.aop.quickstart.service;

import org.springframework.stereotype.Service;

/**
 * @author zhengshijun
 * @version created on 2018/10/26.
 */
@Service
public class UserInfoService implements IUserService{


    @Override
    public String getName(){
        System.out.println("");
        return "";
    }



}
