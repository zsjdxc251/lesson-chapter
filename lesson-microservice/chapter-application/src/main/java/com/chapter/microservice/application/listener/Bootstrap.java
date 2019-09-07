package com.chapter.microservice.application.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * @author zhengshijun
 * @version created on 2019/9/7.
 */
@SpringBootApplication
public class Bootstrap {

    public static void main(String[] args) {

        SpringApplication.run(Bootstrap.class,args);
    }

    @EventListener(classes = SimpleEvent.class)
    public SimpleEvent eventListener(SimpleEvent event){

        System.out.println("eventListener:"+event);

        return null;
    }

    @Bean
    public CustomApplicationEventMulticaster multicaster(){
        CustomApplicationEventMulticaster multicaster = new CustomApplicationEventMulticaster();


        return multicaster;
    }



    @Bean
    public ApplicationRunner applicationRunner(SimpleApplicationEventMulticaster multicaster){
        return args -> {

            multicaster.multicastEvent(new SimpleEvent("null"));

        };
    }
}
