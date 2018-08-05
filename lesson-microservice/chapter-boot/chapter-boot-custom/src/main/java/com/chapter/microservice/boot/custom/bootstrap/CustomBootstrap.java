package com.chapter.microservice.boot.custom.bootstrap;


import com.chapter.microservice.boot.api.manager.ActionManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class CustomBootstrap
{
    public static void main( String[] args ) {


        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(CustomBootstrap.class).run(args);



        String[] names = applicationContext.getBeanDefinitionNames();

        System.out.println(StringUtils.join(names,"\n"));


    }

    @ConditionalOnMissingBean(ActionManager.class)
    @Bean
    public ActionManager actionManager(){
        System.out.println("----------------------------------------");
        return new ActionManager();
    }


}
