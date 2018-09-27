package com.lesson.distributed.redis.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class RedisSpringSample {

    public static void main(String[] args){


        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(RedisConfigure.class);
        annotationConfigApplicationContext.start();

        RedisTemplate redisTemplate = annotationConfigApplicationContext.getBean(RedisTemplate.class);


        System.out.println(redisTemplate.hasKey("bizcard:backend:checkChangeTemplateCount:companyId:56988a85a33674126c947914"));

      redisTemplate.delete("bizcard:backend:checkChangeTemplateCount:companyId:56988a85a33674126c947914");

    }
}