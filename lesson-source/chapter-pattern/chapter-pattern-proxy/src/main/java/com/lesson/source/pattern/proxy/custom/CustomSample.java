package com.lesson.source.pattern.proxy.custom;

import com.lesson.source.pattern.proxy.IUserInfoService;

public class CustomSample {
    public static void main(String[] args){

        IUserInfoService userInfoService = CustomTargetProxy.getInstance(IUserInfoService.class);



    }

}
