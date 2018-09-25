package com.lesson.source.pattern.observer;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class MouseClickEvent<T> extends MouseEvent {
    public MouseClickEvent(T source) {
        super(source);
    }
}
