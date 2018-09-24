package com.lesson.source.pattern.factory.simple;

import com.lesson.source.pattern.factory.model.DellMouse;
import com.lesson.source.pattern.factory.model.HpMouse;
import com.lesson.source.pattern.factory.model.Mouse;

/**
 * @author zhengshijun
 * @version created on 2018/9/24.
 */
public class MouseFactory {

    public Mouse create(int i){

        if (i == 0) {
            return new HpMouse();
        }  else if (i== 1) {
            return new DellMouse();
        }
        throw new NullPointerException();
    }
}
