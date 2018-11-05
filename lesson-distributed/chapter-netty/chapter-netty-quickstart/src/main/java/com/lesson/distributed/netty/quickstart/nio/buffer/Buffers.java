package com.lesson.distributed.netty.quickstart.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author zhengshijun
 * @version created on 2018/11/5.
 */
public class Buffers {


    private ByteBuffer readBuffer;
    private ByteBuffer writeBuffer;

    public Buffers(int readCapacity, int writeCapacity){
        readBuffer = ByteBuffer.allocate(readCapacity);
        writeBuffer = ByteBuffer.allocate(writeCapacity);
    }

    public ByteBuffer getReadBuffer(){
        return readBuffer;
    }

    public ByteBuffer getWriteBuffer(){
        return writeBuffer;
    }

}
