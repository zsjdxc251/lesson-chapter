package com.lesson.boot.bus.disruptor;

import com.lesson.boot.bus.WorkEvent;
import com.lmax.disruptor.EventFactory;

/**
 * @author zhengshijun
 * @version created on 2019/9/13.
 */
public class SeckillEventFactory implements EventFactory<WorkEvent> {
    @Override
    public WorkEvent newInstance() {
        return new SeckillEvent();
    }


}

