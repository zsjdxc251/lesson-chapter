package com.lesson.source.pattern.factory.function;

import com.lesson.source.pattern.factory.model.DellMouse;
import com.lesson.source.pattern.factory.model.Mouse;

/**
 * @author zhengshijun
 * @version created on 2018/9/24.
 */
public class DellFactory implements PcFactory {

    @Override
    public Mouse create() {
        return new DellMouse();
    }
}
