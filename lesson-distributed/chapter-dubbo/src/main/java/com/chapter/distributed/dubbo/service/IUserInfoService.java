package com.chapter.distributed.dubbo.service;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public interface IUserInfoService {


    String getName(String lastName);


    String getAge(int age);
}
