package com.lesson.source.pattern.observer;

import java.util.EventListener;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public interface MouseListener<E extends MouseEvent> extends EventListener {

    /**
     *  监听
     * @param event 事件类型
     */
    void onMouseEvent(E event);
}
