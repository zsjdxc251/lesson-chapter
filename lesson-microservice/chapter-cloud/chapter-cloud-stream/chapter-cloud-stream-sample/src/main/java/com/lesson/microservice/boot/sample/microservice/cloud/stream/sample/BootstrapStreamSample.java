package com.lesson.microservice.boot.sample.microservice.cloud.stream.sample;


import com.lesson.microservice.boot.sample.microservice.cloud.stream.sample.source.MessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@SpringBootApplication
@EnableBinding({MessageSource.class})
public class BootstrapStreamSample
{
    public static void main( String[] args ){
        SpringApplication.run(BootstrapStreamSample.class,args);
    }


    /**
     *
     * @see {@link @ServiceActivator(inputChannel = "channel_name_arg0")}
     *
     * @param message
     */
    @StreamListener("channel_name_arg1")
    public void onMesssage(String message){

        System.out.println("activeOnMesssage"+message);

    }

    @StreamListener("channel_name_arg2")
    public void onKafkaMesssage(String message){

        System.out.println("kafkaOnMesssage"+message);

    }


}
