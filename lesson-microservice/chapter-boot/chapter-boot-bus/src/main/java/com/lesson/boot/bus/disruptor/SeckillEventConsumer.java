package com.lesson.boot.bus.disruptor;

import com.lesson.boot.bus.WorkEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @author zhengshijun
 * @version created on 2019/9/13.
 */
public class SeckillEventConsumer implements EventHandler<WorkEvent> {


    @Override
    public void onEvent(WorkEvent seckillEvent, long seq, boolean bool) throws Exception {


        System.out.println(seckillEvent);
        System.out.println(seq+"++"+bool);

    }
}

