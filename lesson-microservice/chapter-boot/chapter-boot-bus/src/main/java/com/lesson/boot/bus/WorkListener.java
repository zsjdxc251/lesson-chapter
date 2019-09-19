package com.lesson.boot.bus;

import java.util.EventListener;

/**
 * @author zhengshijun
 * @version created on 2019/9/13.
 */
public interface WorkListener<E extends WorkEvent> extends EventListener {


    void onWorkEvent(E event);
}

