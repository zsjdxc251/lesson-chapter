package com.lesson.source.spring.aop.quickstart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhengshijun
 * @version created on 2018/10/26.
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@ComponentScan("com.lesson.source.spring.aop.quickstart")
public class CoreConfigure {


}
