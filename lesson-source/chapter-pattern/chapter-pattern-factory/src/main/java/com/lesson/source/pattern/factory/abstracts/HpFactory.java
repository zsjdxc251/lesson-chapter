package com.lesson.source.pattern.factory.abstracts;

import com.lesson.source.pattern.factory.model.HpKeybo;
import com.lesson.source.pattern.factory.model.HpMouse;
import com.lesson.source.pattern.factory.model.Keybo;
import com.lesson.source.pattern.factory.model.Mouse;

/**
 * @author zhengshijun
 * @version created on 2018/9/24.
 */
public class HpFactory implements PcFactory {

    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public Keybo createKeyBo() {
        return new HpKeybo();
    }
}
