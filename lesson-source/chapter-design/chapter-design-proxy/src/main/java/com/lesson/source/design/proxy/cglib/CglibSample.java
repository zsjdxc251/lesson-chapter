package com.lesson.source.design.proxy.cglib;

import com.lesson.source.design.proxy.IUserInfoService;
import com.lesson.source.design.proxy.UserInfoService;

/**
 *
 *  cglib 可以代理普通类 和 接口
 *
 *
 */
public class CglibSample {

    public static void main(String[] args){

        UserInfoService userInfoService = CglibTargetProxy.getInstance(UserInfoService.class);
        System.out.println(userInfoService.getName());
    }
}
