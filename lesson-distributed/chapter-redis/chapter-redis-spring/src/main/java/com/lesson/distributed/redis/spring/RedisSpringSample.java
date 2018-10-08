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

        System.out.println(redisTemplate.hasKey("bizcard:backend:"+type+":companyId:5b91f71fe3b3c14d22fa461f"));

      redisTemplate.delete("bizcard:backend:"+type+":companyId:5b91f71fe3b3c14d22fa461f");

    }
}
