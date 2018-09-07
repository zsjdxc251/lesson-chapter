package com.lesson.source.pattern.proxy.cglib;

import com.lesson.source.pattern.proxy.UserInfoService;

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
