package com.chapter.microservice.application.listener;

import org.springframework.context.ApplicationEvent;

import java.util.EventObject;

/**
 * @author zhengshijun
 * @version created on 2019/9/7.
 */
public class SimpleEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SimpleEvent(Object source) {
        super(source);
    }
}
