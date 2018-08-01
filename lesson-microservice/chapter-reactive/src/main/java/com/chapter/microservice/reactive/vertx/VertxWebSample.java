package com.chapter.microservice.reactive.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * @author zhengshijun
 * @version created on 2018/8/1.
 */
public class VertxWebSample {

    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();

        // 路由对象
        Router router = Router.router(vertx);

        router.get("/get").handler((context)->{

            HttpServerRequest request = context.request();
            HttpServerResponse response = context.response();
            response.end("Vertx web");

        });

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);



        //vertx.close();
    }
}
