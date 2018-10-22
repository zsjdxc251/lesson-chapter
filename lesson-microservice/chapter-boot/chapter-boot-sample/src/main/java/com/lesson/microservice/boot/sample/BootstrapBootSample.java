package com.lesson.microservice.boot.sample;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.XmlServletWebServerApplicationContext;

/**
 *    org.springframework.boot.SpringApplicationRunListeners#starting
 *         class org.springframework.boot.context.event.ApplicationStartingEvent （starting）
 *    org.springframework.boot.SpringApplication#prepareEnvironment
 *     class org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent （environmentPrepared）
 *
 *
 *   org.springframework.boot.SpringApplication#prepareContext
 *       class org.springframework.boot.context.event.ApplicationPreparedEvent    （contextLoaded）
 *
 *   org.springframework.boot.SpringApplication#refreshContext
 *       class org.springframework.context.event.ContextRefreshedEvent
 *
 *   org.springframework.boot.SpringApplication#refreshContext class
 *       org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent
 *
 *   class org.springframework.boot.context.event.ApplicationStartedEvent （started）
 *
 *   class org.springframework.boot.context.event.ApplicationReadyEvent  （running）
 *
 * @author zhengshijun
 */
@SpringBootApplication
public class BootstrapBootSample
{
    public static void main( String[] args ){

//        ConfigurableApplicationContext applicationContext =
//                SpringApplication.run(BootstrapBootSample.class,args);
//        SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder(BootstrapBootSample.class);
//
//        applicationBuilder.listeners(event -> {
//
//            System.out.println(event.getClass());
//        });
//        applicationBuilder.run(args);

        SpringApplication application = new SpringApplication(BootstrapBootSample.class);
        application.setApplicationContextClass(XmlServletWebServerApplicationContext.class);
        application.addListeners(event -> {
            System.out.println(event.getClass().getSimpleName());
        });
        application.run(args);


    }


}
