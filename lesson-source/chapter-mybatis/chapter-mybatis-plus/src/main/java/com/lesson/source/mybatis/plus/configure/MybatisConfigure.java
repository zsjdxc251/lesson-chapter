package com.lesson.source.mybatis.plus.configure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengshijun
 * @version created on 2019/2/28.
 */
@Configuration
@MapperScan("com.lesson.source.mybatis.plus.ant.mapper")
public class MybatisConfigure {
}
