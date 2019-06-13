package com.lesson.source.mybatis.plus.quickstart.configure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengshijun
 * @version created on 2019/6/13.
 */
@Configuration
@MapperScan("com.lesson.source.mybatis.plus.quickstart.mapper")
public class MybatisConfigure {
}
