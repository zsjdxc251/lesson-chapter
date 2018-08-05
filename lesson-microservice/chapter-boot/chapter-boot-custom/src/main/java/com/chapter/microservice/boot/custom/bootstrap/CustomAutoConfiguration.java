package com.chapter.microservice.boot.custom.bootstrap;


import com.chapter.microservice.boot.api.manager.ActionManager;
import com.chapter.microservice.boot.custom.properties.CustomProperties;
import com.chapter.microservice.boot.api.service.UserInfoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(CustomProperties.class)
// 上下文容器中如果有这个实体并
@ConditionalOnBean(ActionManager.class)
// 如果引入类该类
// @ConditionalOnClass
// 与 @ConditionalOnBean 相反
//@ConditionalOnMissingBean

// open.eureka=true
//ConditionalOnProperty

// @ConditionalOnNotWebApplication（不是web应用）

// true 才会实例化bean
// @ConditionalOnExpression
//@Conditional
@Import({UserInfoService.class})
public class CustomAutoConfiguration {



}
