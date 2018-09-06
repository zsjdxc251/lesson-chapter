package com.lesson.distributed.dubbo.annotation.driver;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author zhengshijun
 * @version created on 2018/9/6.
 */
@EnableDubboConfig(multiple = true)
@DubboComponentScan
@PropertySources(
        value = {@PropertySource("classpath:application.properties")}
)
public class BootstrapDubboAnnotationDriver {

    public static void main(String[] args){

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(BootstrapDubboAnnotationDriver.class);

    }
}
