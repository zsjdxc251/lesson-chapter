package com.lesson.source.pattern.observer;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class MouseEventMulticaster {

    private final Set<MouseListener<? extends MouseEvent>> mouseListeners;

    public MouseEventMulticaster() {
        mouseListeners = new HashSet<>();
    }

    @SuppressWarnings("unchecked")
    public void multicastEvent(MouseEvent mouseEvent) {

        Set<MouseListener<? extends MouseEvent>> waitNotice = new HashSet<>();
        for (MouseListener<?> mouseListener : mouseListeners) {

            waitNotice.add(mouseListener);
        }

        for (MouseListener mouseListener : waitNotice) {
            mouseListener.onMouseEvent(mouseEvent);
        }
    }

    public void addMouseListener(MouseListener<? extends MouseEvent> mouseListener) {
        mouseListeners.add(mouseListener);
    }


}
