package com.lesson.source.mybatis.spring;

import com.lesson.source.mybatis.spring.configure.CoreConfigure;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
public class MybatisSpringSample {

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CoreConfigure.class);

        System.out.println(StringUtils.join(applicationContext.getBeanDefinitionNames(),"\n"));

    }
}
