package com.lesson.project.elasticsearch.spring;

import com.lesson.project.elasticsearch.spring.configure.ElasticsearchConfigure;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/12/4.
 */
public class ElasticsearchSpringBootstrap {

    public static void main(String[] args){


        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(ElasticsearchConfigure.class);







    }

}
