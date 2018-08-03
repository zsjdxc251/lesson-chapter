package com.chapter.microservice.cloud.config.client;


import com.chapter.microservice.cloud.config.client.health.CustomHealthIndicator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author zhengshijun
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan("com.chapter.microservice.cloud.config.client")
public class BootstrapClient implements ApplicationContextAware
{

    private static final Logger log = LoggerFactory.getLogger(BootstrapClient.class);

    private ApplicationContext applicationContext;

    public static void main( String[] args )
    {

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(BootstrapClient.class).listeners((event)->{
            if (event instanceof RefreshEvent){
                log.info("RefreshEvent:{}",event);
            }

        }).run(args);
        // 基于事件驱动刷新配置信息
        applicationContext.publishEvent(new RefreshEvent("custom","",""));
        ContextRefresher contextRefresher = applicationContext.getBean(ContextRefresher.class);
        // 通过 ContextRefresher 刷新 配置信息
        log.info("contextRefresher:{}",contextRefresher.refresh());
    }




    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     *  每次三秒刷新一次
     *  @Scheduled(fixedDelay=3000,initialDelay = 3000)
     */
    public void autoRefresher(){
        log.info("autoRefresher");
        applicationContext.publishEvent(new RefreshEvent("custom","",""));

    }

    @Bean
    public CustomHealthIndicator healthIndicator(){
        return new CustomHealthIndicator();
    }
}
