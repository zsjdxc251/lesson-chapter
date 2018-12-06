package com.chapter.distributed.dubbo.consumer;

import com.chapter.distributed.dubbo.service.IUserInfoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class ConsumerMain {

    public static void main(String[] args) throws Exception{


        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("7/consumer.xml");
        classPathXmlApplicationContext.start();


        IUserInfoService userInfoService = (IUserInfoService)classPathXmlApplicationContext.getBean("userInfoService");

        System.out.println(userInfoService.getName("zsj"));
//        for (int i= 0;i<10;i++) {
//            System.out.println(userInfoService.getName("zsj"));
//            TimeUnit.SECONDS.sleep(1);
//        }
    }
}
