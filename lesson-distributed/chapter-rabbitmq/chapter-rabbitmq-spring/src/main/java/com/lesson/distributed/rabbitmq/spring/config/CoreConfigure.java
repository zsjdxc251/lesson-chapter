package com.lesson.distributed.rabbitmq.spring.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengshijun
 * @version created on 2018/10/27.
 */
@Configuration
public class CoreConfigure {

//    @Bean
//    public RabbitConnectionFactoryBean rabbitConnectionFactory() {
//
//        RabbitConnectionFactoryBean rabbitConnectionFactory = new RabbitConnectionFactoryBean();
//
//        rabbitConnectionFactory.setVirtualHost("/");
//
//        rabbitConnectionFactory.setUsername("guest");
//        rabbitConnectionFactory.setPassword("guest");
//        rabbitConnectionFactory.setHost("121.196.232.248");
//        rabbitConnectionFactory.setPort(5672);
//        rabbitConnectionFactory.setConnectionTimeout(5000);
//        return rabbitConnectionFactory;
//    }


    @Bean
    public ConnectionFactory connectionFactory(){

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setHost("121.196.232.248");
        connectionFactory.setPort(5672);
        connectionFactory.setConnectionTimeout(5000);
        return connectionFactory;
    }


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        return rabbitAdmin;
    }




}
