package com.chapter.microservice.reactive;


import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.function.Consumer;

public class FluxSample {

    public static void main(String[] args){


//        Flux<String> flux = Flux.generate((value, sink)->{
//
//            return "";
//        });


        Flux.generate(()->{

            return 0;
        },(value,sink)->{


            if (value == 3){
                sink.next(value);
            }

            return value+1;
        }).subscribe((value)->{
           System.out.println(value);
        });
//                .publishOn(Schedulers.elastic()).subscribe(System.out::println,System.out::println,()->{
//            System.out.println("完成");
//        });



//        Flow.


    }
}
