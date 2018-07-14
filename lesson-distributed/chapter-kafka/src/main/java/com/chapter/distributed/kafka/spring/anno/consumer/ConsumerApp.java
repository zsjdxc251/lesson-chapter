package com.chapter.distributed.kafka.spring.anno.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/7/14.
 */
public class ConsumerApp {

    public static void main(String[] args){
        ApplicationContext app = new AnnotationConfigApplicationContext(ConsumerConfigure.class);

    }
}
