package com.lesson.source.spring.ioc.quickstart;

import com.lesson.source.spring.ioc.quickstart.configure.CoreConfigure;
import com.lesson.source.spring.ioc.quickstart.service.ExampleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.nio.cs.ext.MacCentralEurope;

import java.lang.reflect.Method;

/**
 * @author zhengshijun
 * @version created on 2018/10/1.
 */
public class ApplicationSample {


    public static void main(String[] args) throws InterruptedException{


        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(CoreConfigure.class);

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring-beans.xml");
        applicationContext.addApplicationListener(event -> {
            System.out.println(event.getClass().getSimpleName());
        });

        applicationContext.setParent(annotationConfigApplicationContext);
        ExampleService exampleService = (ExampleService)applicationContext.getBean("exampleService");


        for(Method method : exampleService.getClass().getMethods()){
            System.out.println(method);
        }








    }
}
