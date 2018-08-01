package com.chapter.microservice.cloud.config.client.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @author zhengshijun
 * @version created on 2018/8/1.
 */
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {

        builder.up().withDetail("22","22");

    }
}
