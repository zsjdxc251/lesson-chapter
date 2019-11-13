package com.lesson.boot.bus;

import java.util.EventObject;

/**
 * @author zhengshijun
 * @version created on 2019/9/13.
 */
public abstract class WorkEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public WorkEvent(Object source) {
        super(source);
    }
}

