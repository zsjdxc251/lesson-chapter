package com.lesson.optimize.mysql.configure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhengshijun
 * @version created on 2019/6/14.
 */
@Configuration
@EntityScan("com.lesson.optimize.mysql.entity")
@EnableTransactionManagement
public class JpaConfigure {
}
