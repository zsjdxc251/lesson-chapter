package com.lesson.source.pattern.observer;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

        Set<MouseListener> waitNotice = new HashSet<>();

        mouseListeners.stream().filter(mouseListener -> supportsEventType(mouseListener,mouseEvent)).forEach(waitNotice::add);

        waitNotice.forEach(mouseListener -> mouseListener.onMouseEvent(mouseEvent));
    }

    private boolean supportsEventType(MouseListener<?> mouseListener, MouseEvent mouseEvent){
        Type[] types = mouseListener.getClass().getGenericInterfaces();

        for (Type type : types){

            if (type.getClass() == Class.class){
                return true;
            }


            if (!isSuper(type.getClass(),ParameterizedType.class)){

                return false;
            }
            ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
            Type[] itemTypes = parameterizedType.getActualTypeArguments();
            for (Type itemType : itemTypes) {
                if (itemType.getTypeName().equals(mouseEvent.getClass().getName())){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSuper(Class<?> source , Class<?> target){
        Class<?>[] superClass = source.getInterfaces();
        if (ArrayUtils.isEmpty(superClass)){
            superClass = new Class[1];
            superClass[0] = source.getSuperclass();
        }
        for (Class<?> item :superClass){
            if (item == target){
                return true;
            }
        }
        return false;
    }

    public void addMouseListener(MouseListener<? extends MouseEvent> mouseListener) {
        mouseListeners.add(mouseListener);
    }


}
