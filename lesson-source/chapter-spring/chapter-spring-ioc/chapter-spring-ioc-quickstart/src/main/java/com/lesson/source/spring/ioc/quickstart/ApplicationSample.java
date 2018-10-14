package com.lesson.source.spring.ioc.quickstart;

import com.lesson.source.spring.ioc.quickstart.service.ExampleService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/10/1.
 */
public class ApplicationSample {


    public static void main(String[] args){


        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-beans.xml");


        ExampleService exampleService = (ExampleService)applicationContext.getBean("exampleService");
        System.out.println(exampleService);

    }
}
