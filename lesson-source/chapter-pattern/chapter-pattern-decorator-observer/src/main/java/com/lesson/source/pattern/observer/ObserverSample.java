package com.lesson.source.pattern.observer;

/**
 *
 *    *Listener
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class ObserverSample {

    public static void main(String[] args){
        MouseEventMulticaster mouseEventMulticaster = new MouseEventMulticaster();
        mouseEventMulticaster.addMouseListener(new MouseClickListener());
        mouseEventMulticaster.addMouseListener(event -> {
            System.out.println(event);
        });

        mouseEventMulticaster.addMouseListener(new MouseWheelListener());

        mouseEventMulticaster.multicastEvent(new MouseClickEvent<>("zsj"));

        mouseEventMulticaster.multicastEvent(new MouseWheelEvent<>("鼠标滑动"));

    }
}
