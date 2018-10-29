package com.lesson.distributed.rabbitmq.spring.config;

import com.lesson.distributed.rabbitmq.spring.consumer.ConsumerListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengshijun
 * @version created on 2018/10/28.
 */
@Configuration
@ComponentScan("com.lesson.distributed.rabbitmq.spring.consumer")
public class ConsumerConfigure {

    @Autowired
    private ConsumerListener consumerListener;


    @Bean
    public Queue queue(RabbitAdmin rabbitAdmin){
        Queue queue = new Queue("queue_name_info",true);
        queue.setAdminsThatShouldDeclare(rabbitAdmin);
        return queue;
    }

    @Bean
    public DirectExchange directExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange directExchange = new DirectExchange("logs.error");
        directExchange.setAdminsThatShouldDeclare(rabbitAdmin);
        return directExchange;
    }
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange,RabbitAdmin rabbitAdmin){
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("*.info");
        binding.setAdminsThatShouldDeclare(rabbitAdmin);
        return binding;
    }

    @Bean
    public AbstractMessageListenerContainer simpleRabbitListenerContainer(ConnectionFactory connectionFactory){
        AbstractMessageListenerContainer simpleRabbitListenerContainerFactory
                = new SimpleMessageListenerContainer();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setMessageListener(consumerListener);
        simpleRabbitListenerContainerFactory.setQueueNames("queue_name_info");
        return simpleRabbitListenerContainerFactory;

    }


    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory
                = new SimpleRabbitListenerContainerFactory();

        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);

        return simpleRabbitListenerContainerFactory;

    }


}
