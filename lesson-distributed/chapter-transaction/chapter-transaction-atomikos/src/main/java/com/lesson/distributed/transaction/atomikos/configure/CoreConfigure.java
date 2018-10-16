package com.lesson.distributed.transaction.atomikos.configure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhengshijun
 * @version created on 2018/10/16.
 */
@ComponentScan("com.lesson.distributed.transaction.atomikos")
@EnableTransactionManagement
public class CoreConfigure {
}
