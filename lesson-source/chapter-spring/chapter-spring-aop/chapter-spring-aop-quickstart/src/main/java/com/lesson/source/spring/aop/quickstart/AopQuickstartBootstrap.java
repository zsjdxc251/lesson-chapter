package com.lesson.source.spring.aop.quickstart;

import com.lesson.source.spring.aop.quickstart.config.CoreConfigure;
import com.lesson.source.spring.aop.quickstart.service.IUserService;
import com.lesson.source.spring.aop.quickstart.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/10/26.
 */
public class AopQuickstartBootstrap {



    public static void main(String[] args){


        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(CoreConfigure.class);



        IUserService userInfoService =
        applicationContext.getBean(IUserService.class);


        System.out.println(StringUtils.join(applicationContext.getBeanDefinitionNames(),"\n"));

        userInfoService =
                applicationContext.getBean(UserInfoService.class);



    }
}
