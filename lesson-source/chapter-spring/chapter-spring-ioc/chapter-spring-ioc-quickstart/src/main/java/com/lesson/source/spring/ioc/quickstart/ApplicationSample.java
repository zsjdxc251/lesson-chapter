package com.lesson.source.spring.ioc.quickstart;

import com.lesson.source.spring.ioc.quickstart.configure.CoreConfigure;
import com.lesson.source.spring.ioc.quickstart.service.ExampleService;
import com.lesson.source.spring.ioc.quickstart.service.IUserInfoService;
import com.lesson.source.spring.ioc.quickstart.service.UserInfoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/10/1.
 */
public class ApplicationSample {


    public static void main(String[] args){


        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-beans.xml");

        ExampleService exampleService = applicationContext.getBean(ExampleService.class);

        UserInfoService userInfoService = applicationContext.getBean(UserInfoService.class);

        System.out.println(exampleService);
        System.out.println(userInfoService);




    }
}
