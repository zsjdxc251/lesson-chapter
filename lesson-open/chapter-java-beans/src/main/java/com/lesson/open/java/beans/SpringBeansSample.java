package com.lesson.open.java.beans;

import com.lesson.open.java.beans.model.UserInfo;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/8/27.
 */
public class SpringBeansSample {

    public static void main(String[] args){
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-context.xml");

        UserInfo userInfo = classPathXmlApplicationContext.getBean(UserInfo.class);
        System.out.println(userInfo);

    }
}
