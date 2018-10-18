package com.lesson.microservice.boot.jpa.configure;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EntityScan("com.lesson.microservice.boot.jpa.entity")
// TODO
// FIXME   @EnableJpaRepositories("com.lesson.microservice.boot.jpa.repository") 在系统不使用该注解时spring 是如何加载repository 参考类JpaRepositoriesAutoConfiguration
// import condition
//@EnableJpaRepositories("com.lesson.microservice.boot.jpa.repository")
public class JpaConfigure{



}
