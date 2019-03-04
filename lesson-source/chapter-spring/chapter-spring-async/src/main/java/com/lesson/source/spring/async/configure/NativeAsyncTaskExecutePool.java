package com.lesson.source.spring.async.configure;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;

/**
 * @author zhengshijun
 * @version created on 2019/3/4.
 */
@Configuration
public class NativeAsyncTaskExecutePool implements AsyncConfigurer {


	@Override
	public Executor getAsyncExecutor() {
		return null;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return null;
	}
}
