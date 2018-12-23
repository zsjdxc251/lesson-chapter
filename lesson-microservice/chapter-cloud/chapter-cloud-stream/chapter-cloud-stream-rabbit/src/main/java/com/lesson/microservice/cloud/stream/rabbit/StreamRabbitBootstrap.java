package com.lesson.microservice.cloud.stream.rabbit;

import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.GenericMessage;

/**
 *
 *   Rabbitmq stream 示例
 * @author zhengshijun
 * @version created on 2018/12/22.
 */
@SpringBootApplication
@EnableBinding({ChannalManager.class})
public class StreamRabbitBootstrap {



    public static void main(String[] args){


        new SpringApplicationBuilder(StreamRabbitBootstrap.class).logStartupInfo(true).run(args);


    }


    @Autowired
    private ChannalManager channalManager;


    @StreamListener("channel_name_arg1")
    public void listener1(String string){

        System.out.println("channel_name_arg1"+Thread.currentThread().getName()+"-"+string);


    }


    @EventListener(ApplicationReadyEvent.class)
    public void systemListener(ApplicationReadyEvent event){
        channalManager.outputChannel().send(new GenericMessage<>("启动了"));

    }

    /**
     *  设置RabbitMQ 连接broker 时客户端名称
     *
     * @return
     */
    @Bean
    public ConnectionNameStrategy customeClientName(){

        return (connectionFactory)->{

            return Thread.currentThread().getName()+"-client_name";
        };
    }
//    @StreamListener("channel_name_arg2")
//    public void listener2(String string){
//        System.out.println("channel_name_arg2"+Thread.currentThread().getName()+"-"+string);
//
//    }


//    @Bean
//    public ApplicationRunner source(Source source) {
//        return (arguments)->{
//
//            source.output().send(new GenericMessage<>("hello world"));
//
//        };
//    }
//
//
//    @StreamListener("input")
//    public void listener(String string){
//
//        System.out.println(string);
//    }
//
//    @StreamListener("input")
//    public void listener2(String string){
//
//        System.out.println(string);
//    }

//    @Bean
//    public ApplicationRunner sink(Sink sink) {
//
//
//        return args -> {
//            sink.input().subscribe(message -> {
//
//
//
//                System.out.println(message.getPayload().toString());
//            });
//
//        };
//
//    }






}
