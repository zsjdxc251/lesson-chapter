package com.chapter.distributed.activemq.spring;


import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.annotation.JmsListener;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@JmsListener(destination = StringUtils.EMPTY,containerFactory = "topicContainer")
public @interface TopicJmsListener {

    String destination();
}
