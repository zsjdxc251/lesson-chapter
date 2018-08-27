package com.chapter.microservice.boot.custom.bootstrap;


import com.chapter.microservice.boot.api.manager.ActionManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CustomBootstrap
{

    public static void main( String[] args ) {


        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(CustomBootstrap.class).run(args);

        System.out.println(StringUtils.join(applicationContext.getBeanDefinitionNames(),"\n"));

    }

//    @ConditionalOnMissingBean(ActionManager.class)
//    @Bean
//    public ActionManager actionManager(){
//        System.out.println("----------------------------------------");
//        return new ActionManager();
//    }


}
