package com.chapter.microservice.application.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/7/20.
 */
public class SpringEventSample {

    public static void main(String[] args){


        GenericApplicationContext applicationContext = new GenericApplicationContext();

//        applicationContext.addApplicationListener(event -> {
//
//            System.err.println(event.getSource()+"="+event.getClass().getName());
//        });


//        applicationContext.addApplicationListener(new CustomRefreshedListener());
//
//        applicationContext.addApplicationListener(new CustomClosedEventListener());

        applicationContext.addApplicationListener(new CustomEventListener());

        applicationContext.refresh();

        applicationContext.publishEvent("http");

        applicationContext.publishEvent(new CustomEvent("readme"));

        applicationContext.close();
    }


    static class CustomRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {


            System.err.println("CustomRefreshedListener"+event.getSource());

        }
    }

    static class CustomClosedEventListener implements ApplicationListener<ContextClosedEvent> {

        @Override
        public void onApplicationEvent(ContextClosedEvent event) {

            System.err.println("ClosedEventListener"+event.getSource());
        }
    }

    static class CustomEventListener implements  ApplicationListener<CustomEvent> {

        @Override
        public void onApplicationEvent(CustomEvent event) {

            System.err.println(event.getSource());

        }
    }


    static class CustomEvent extends ApplicationEvent {

        public CustomEvent(Object source) {

            super(source);
        }

        @Override
        public Object getSource() {
            return super.getSource();
        }
    }


}
