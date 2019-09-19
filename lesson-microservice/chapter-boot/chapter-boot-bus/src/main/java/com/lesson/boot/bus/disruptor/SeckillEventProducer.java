package com.lesson.boot.bus.disruptor;

import com.lesson.boot.bus.WorkEvent;
import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;

/**
 * @author zhengshijun
 * @version created on 2019/9/13.
 */
public class SeckillEventProducer {
    private final static EventTranslatorVararg<WorkEvent> translator = new EventTranslatorVararg<WorkEvent>() {
        public void translateTo(WorkEvent seckillEvent, long seq, Object... objs) {

        }
    };

    private final RingBuffer<WorkEvent> ringBuffer;

    public SeckillEventProducer(RingBuffer<WorkEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void seckill(long seckillId, long userId){

        this.ringBuffer.publishEvent(translator, seckillId, userId);



    }
}

