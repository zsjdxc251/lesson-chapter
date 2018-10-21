package com.lesson.source.spring.ioc.quickstart.configure;

import com.lesson.source.spring.ioc.quickstart.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/10/19.
 */
@Component
public class CoreConfigure {


    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor(){

        return new CustomBeanFactoryPostProcessor();
    }

}
