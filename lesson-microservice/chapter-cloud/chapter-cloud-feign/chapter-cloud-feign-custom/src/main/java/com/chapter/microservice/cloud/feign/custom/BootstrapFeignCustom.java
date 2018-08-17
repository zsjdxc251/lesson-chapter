package com.chapter.microservice.cloud.feign.custom;


import com.chapter.microservice.cloud.feign.custom.annotation.CustomEnableFeignClients;
import com.chapter.microservice.cloud.feign.custom.service.InvokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@CustomEnableFeignClients(clients = {InvokerService.class})
@EnableDiscoveryClient
public class BootstrapFeignCustom
{
    public static void main( String[] args )
    {

        SpringApplication.run(BootstrapFeignCustom.class,args);

    }


    @LoadBalanced
    @Bean
    public RestTemplate loadBalancedRestTemplate(){

        return new RestTemplate();
    }
}
