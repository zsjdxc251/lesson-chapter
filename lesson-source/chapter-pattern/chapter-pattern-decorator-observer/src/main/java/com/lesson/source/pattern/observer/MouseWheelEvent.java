package com.lesson.source.pattern.observer;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class MouseWheelEvent<T> extends MouseEvent {
    public MouseWheelEvent(T source) {
        super(source);
    }
}
