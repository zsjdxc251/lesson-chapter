package com.chapter.microservice.reactive.vertx;

import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *  vertx event 事件驱动型
 * @author zhengshijun
 * @version created on 2018/8/1.
 */
public class VertxEventBusSample {

    private static final Logger log = LoggerFactory.getLogger(VertxEventBusSample.class);

    public static void main(String[] args){

        Vertx vertx = Vertx.vertx();
        vertx.eventBus().consumer("address-id",(event)->{
            Object body = event.body();
            if (body instanceof String){
                String bodyString = String.class.cast(event.body());
                log.info("bodyString:{}",bodyString);
            }


        });

        vertx.eventBus().publish("address-id","hellowld");

        vertx.close();
    }
}
