package com.lesson.source.pattern.observer;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class MouseClickListener implements MouseListener<MouseClickEvent> {

    @Override
    public void onMouseEvent(MouseClickEvent event) {
        System.out.println(event);
    }
}
