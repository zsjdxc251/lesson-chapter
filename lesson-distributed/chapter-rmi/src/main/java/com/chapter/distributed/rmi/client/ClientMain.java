package com.chapter.distributed.rmi.client;

import com.chapter.distributed.rmi.service.IUserInfoService;

import java.rmi.Naming;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class ClientMain {

    public static void main(String[] args) throws Exception{
        // 8080 默认使用 1099
        IUserInfoService userInfoService = (IUserInfoService)Naming.lookup("rmi://127.0.0.1:8080/Hello");

        System.out.println(userInfoService.getName("zsj"));

    }
}
