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

        String type = "checkSyncToCardCount";

        System.out.println(redisTemplate.hasKey("bizcard:backend:"+type+":companyId:5bbb1004a7d9ca542fffe8df"));

      redisTemplate.delete("bizcard:backend:"+type+":companyId:5bbb1004a7d9ca542fffe8df");

    }
}
