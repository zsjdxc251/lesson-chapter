package com.lesson.microservice.boot.jpa;


import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author  zhengshijun
 */
@SpringBootApplication(scanBasePackages = {"com.lesson.microservice.boot.jpa.configure","com.lesson.microservice.boot.jpa.controller"})
@EnableTransactionManagement(proxyTargetClass = true)
//@ComponentScan({"com.lesson.microservice.boot.jpa.configure","com.lesson.microservice.boot.jpa.controller"})
public class BootstrapBootJpa {

    public static void main(String[] args){


        ConfigurableApplicationContext applicationContext
        = SpringApplication.run(BootstrapBootJpa.class,args);

        System.out.println(applicationContext.getClass().getName());
        System.out.println(StringUtils.join(applicationContext.getBeanDefinitionNames(),"\n"));
    }

}
