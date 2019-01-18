package com.lesson.microservice.cloud.gateway.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
@EnableDiscoveryClient
public class BootstrapGatewaySample
{
    public static void main( String[] args )
    {
        SpringApplication.run(BootstrapGatewaySample.class,args);
    }


    /**
     * web flux
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){


        return route(GET("/fallback"),request -> ServerResponse.ok().body(Mono.just("fallback function"),String.class));

    }


    /**
     * gateway
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder){

        return locatorBuilder
                .routes().route("service",route->route.path("/service/*").uri("lb://spring-cloud-zookeeper-provider")).build();


    }

}
