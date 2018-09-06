package com.lesson.source.design.proxy.custom;

import com.google.common.io.Files;
import com.lesson.source.design.proxy.IUserInfoService;
import com.lesson.source.design.proxy.jdk.JdkTargetProxy;
import sun.misc.ProxyGenerator;

import java.io.File;

public class CustomSample {
    public static void main(String[] args){

        IUserInfoService userInfoService = CustomTargetProxy.getInstance(IUserInfoService.class);



    }

}
