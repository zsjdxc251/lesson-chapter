package com.chapter.microservice.boot.api;

import com.chapter.microservice.boot.api.service.UserInfoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * @author zhengshijun
 * @version created on 2018/8/6.
 */
@ConditionalOnBean(UserInfoService.class)
public class ApiConfiguration {
}
