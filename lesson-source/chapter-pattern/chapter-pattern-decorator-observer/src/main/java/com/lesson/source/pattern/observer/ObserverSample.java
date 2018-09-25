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
        mouseEventMulticaster.multicastEvent(new MouseClickEvent<>("zsj"));

    }
}
