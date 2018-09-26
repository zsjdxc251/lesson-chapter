package com.chapter.microservice.application.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.Lifecycle;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.event.SmartApplicationListener;

/**
 *
 * @see  {@link Lifecycle}
 * @see  {@link EventListener}
 * @see  {@link SmartApplicationListener}
 * @author zhengshijun
 * @version created on 2018/7/20.
 */


public class ApplicationEventSample {

    public static void main(String[] args){

        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

        eventMulticaster.addApplicationListener(event->{

            if (event instanceof CustomEvent){
                System.out.println(event);

            } else if (event instanceof PayloadApplicationEvent){
                System.out.println(PayloadApplicationEvent.class.cast(event).getPayload());
            }



        });


        eventMulticaster.multicastEvent(new CustomEvent<Integer>(12));

        eventMulticaster.multicastEvent(new PayloadApplicationEvent<String>("2","welcome"));



    }

    static class CustomEvent<T> extends ApplicationEvent {

        public CustomEvent(T source) {
            super(source);
        }
    }
}
