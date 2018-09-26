package com.lesson.source.pattern.observer;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class MouseWheelListener implements MouseListener<MouseWheelEvent> {
    @Override
    public void onMouseEvent(MouseWheelEvent event) {

        System.out.println("MouseWheelListener:"+event);

    }
}
