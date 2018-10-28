package com.lesson.distributed.rabbitmq.spring.config;



import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

/**
 * @author zhengshijun
 * @version created on 2018/10/28.
 */
public class ProviderConfigure {




    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate amqpTemplate = new RabbitTemplate(connectionFactory);

        return amqpTemplate;
    }

}
