package com.chapter.microservice.boot.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class UserInfoService implements ImportBeanDefinitionRegistrar {

    
    private static final Logger log = LoggerFactory.getLogger(UserInfoService.class);


    public UserInfoService(){
        log.info("UserInfoService init");
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {


        //

        // annotainSet  获取引入该类注解上面标记类上面标记的所有注解
       // metadata.getAttributesMap 获取引入该类注解上面所有包含派生性注解上面的属性值
        // 如果应用在注解上面
        log.info("metadata:{}",metadata);

    }
}
