package com.lesson.source.design.proxy.jdk;

import com.google.common.io.Files;
import com.lesson.source.design.proxy.IUserInfoService;
import sun.misc.ProxyGenerator;

import java.io.File;

/**
 * jdk 代理只能代理接口
 *
 *
 */
public class JdkSample {

    public static void main(String[] args){

        IUserInfoService userInfoService = JdkTargetProxy.getInstance(IUserInfoService.class);

        System.out.println(userInfoService);

        // 把生成的动态代理类生成文件
        byte[] bytes =
                ProxyGenerator.generateProxyClass(userInfoService.getClass().getSimpleName(),new Class[]{userInfoService.getClass()});


        try {
            Files.write(bytes,new File("D:\\Temp\\"+userInfoService.getClass().getSimpleName()+".class"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
