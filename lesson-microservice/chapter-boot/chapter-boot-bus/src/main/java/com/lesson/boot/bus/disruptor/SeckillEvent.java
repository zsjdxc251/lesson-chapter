package com.lesson.boot.bus.disruptor;

import com.lesson.boot.bus.WorkEvent;

import java.io.Serializable;

/**
 * @author zhengshijun
 * @version created on 2019/9/13.
 */
public class SeckillEvent extends WorkEvent {
    private static final long serialVersionUID = 1L;
    private long seckillId;
    private long userId;

    public SeckillEvent(){
        super("");

    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

