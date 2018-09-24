package com.lesson.source.pattern.factory.abstracts;

import com.lesson.source.pattern.factory.model.DellKeybo;
import com.lesson.source.pattern.factory.model.DellMouse;
import com.lesson.source.pattern.factory.model.Keybo;
import com.lesson.source.pattern.factory.model.Mouse;

/**
 * @author zhengshijun
 * @version created on 2018/9/24.
 */
public class DellFactory implements PcFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keybo createKeyBo() {
        return new DellKeybo();
    }
}
