package com.lesson.source.mybatis.spring.boot;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/12/21.
 */
@SpringBootApplication
public class MybatisBootstrap {

    public static void main(String[] args){

        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(MybatisBootstrap.class,args);

        System.out.println(StringUtils.join(applicationContext.getBeanDefinitionNames(),"\n"));
    }
}
