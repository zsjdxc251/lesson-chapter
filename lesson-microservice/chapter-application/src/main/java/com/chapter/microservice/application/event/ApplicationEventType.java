package com.chapter.microservice.application.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.ResolvableType;

/**
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public class ApplicationEventType {

    public static void main(String[] args){


        ResolvableType resolvableType = ResolvableType.forInstance(new ApplicationEventSample.CustomEvent<Integer>(12));


//        Class<? extends ApplicationEvent> eventClass = (Class<? extends ApplicationEvent>) resolvableType.resolve();
//        System.out.println(eventClass);
        //System.out.println(JSON.toJSONString(resolvableType,true));

        CustomRefreshedListener customRefreshedListener = new CustomRefreshedListener();



        ResolvableType resolvableType1 = ResolvableType.forClass(customRefreshedListener.getClass()).as(ApplicationListener.class);
        //System.out.println(resolvableType);
        System.out.println(resolvableType1.toString());




//        GenericApplicationListenerAdapter genericApplicationListenerAdapter = new GenericApplicationListenerAdapter(new CustomRefreshedListener());
//
//        System.out.println(genericApplicationListenerAdapter.supportsEventType(resolvableType));
    }

    static class CustomRefreshedListener implements ApplicationListener<ApplicationEventSample.CustomEvent> {

        @Override
        public void onApplicationEvent(ApplicationEventSample.CustomEvent event) {

        }
    }

    static class CustomContextClosedEvent implements ApplicationListener<ContextClosedEvent> {
        @Override
        public void onApplicationEvent(ContextClosedEvent event) {

        }
    }
}
