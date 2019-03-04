package com.lesson.source.spring.async.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author zhengshijun
 * @version created on 2019/3/4.
 */
@Configuration
public class ThreadPoolConfigure {


    @Bean
    public AsyncTaskExecutor executor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // 核心线程数
        executor.setCorePoolSize(5);

        executor.setMaxPoolSize(50);

        // 使用超过最大线程数之后，存活时间
        executor.setKeepAliveSeconds(3000);

        // 缓冲队列大小 阻塞队列
        executor.setQueueCapacity(2000);
        return executor;

    }
}
