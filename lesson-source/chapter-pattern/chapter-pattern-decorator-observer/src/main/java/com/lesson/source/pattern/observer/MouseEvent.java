package com.lesson.source.pattern.observer;

import java.util.EventObject;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public abstract class MouseEvent extends EventObject {
    public MouseEvent(Object source) {
        super(source);
    }
}
