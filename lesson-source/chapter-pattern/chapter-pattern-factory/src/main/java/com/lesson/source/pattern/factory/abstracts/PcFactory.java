package com.lesson.source.pattern.factory.abstracts;

import com.lesson.source.pattern.factory.model.Keybo;
import com.lesson.source.pattern.factory.model.Mouse;

/**
 * @author zhengshijun
 * @version created on 2018/9/24.
 */
public interface PcFactory {

    Mouse createMouse();

    Keybo createKeyBo();
}
