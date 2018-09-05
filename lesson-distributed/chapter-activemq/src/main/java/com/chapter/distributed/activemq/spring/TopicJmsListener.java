package com.chapter.distributed.activemq.spring;


import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AliasFor;
import org.springframework.jms.annotation.JmsListener;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@JmsListener(destination = StringUtils.EMPTY, containerFactory = "topicContainer")
public @interface TopicJmsListener {

    /**
     *  属性传递性 基于springframework
     *  默认情况 该属性会赋值给派生 注解 同名属性名
     *
     *  如果多个派生注解时 可通过 annotation 指定注解
     *  通过 attribute 指定属性名
     *
     *  可参考 {@code GetMapping}
     *
     * @return String
     */
    @AliasFor(annotation = JmsListener.class,attribute = "destination")
    String destination();
}
