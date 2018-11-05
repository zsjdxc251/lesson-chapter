package com.lesson.distributed.netty.quickstart.nio.buffer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 *
 *    Buffer
 *
 *    capacity  容量
 *
 *    position 位置
 *
 *    limit 限制
 *
 *    写模型
 *
 *    capacity = limit
 *
 *    position 写入最后的位置
 *
 *    读模型
 *
 *   position 从哪个位置开始读
 *
 *   limit 读到哪个位置
 *
 *
 *   {@link ByteBuffer}
 *   {@link java.nio.IntBuffer}
 *   {@link CharBuffer}
 *   {@link java.nio.LongBuffer}
 *   {@link java.nio.DoubleBuffer}
 *   {@link java.nio.ShortBuffer}
 *   {@link java.nio.FloatBuffer}
 *
 *
 *
 *
 * @author zhengshijun
 * @version created on 2018/11/5.
 */
public class BufferSample {

    public static void decode(String info) throws UnsupportedEncodingException {

        // 分配大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 写
        byteBuffer.put(info.getBytes("UTF-8"));

        byteBuffer.flip();

        Charset charset = Charset.forName("UTF-8");

        CharBuffer charBuffer =  charset.decode(byteBuffer);

        char[] charArr = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
        System.out.println(charArr);
    }

    public static void encode(String info){
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.append(info);

        charBuffer.flip();

        Charset charset = Charset.forName("UTF-8");

        ByteBuffer byteBuffer = charset.encode(charBuffer);
        byte[] byteArr = Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
        System.out.println(Arrays.toString(byteArr));
    }


    /**
     *
     *    在ByteBuffer 中使用 mark 标记 mark = position
     *
     *    然后在使用 reset 调用完之后使用 reset 来还原会 mark 标记的位置    position = mark
     *
     *
     */
    public static void makeSimple(){

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("demo".getBytes());
        byteBuffer.flip();

        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(byteBuffer);


        charBuffer.mark();
        System.out.println(charBuffer.get());

        charBuffer.reset();
        System.out.println(charBuffer.get());

    }

    /**
     *
     *   在Buffer 中 rewind 还原
     *   position =0
     *   mark = -1
     *
     */
    public static void rewindSimple(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("rewindSimple".getBytes());
        byteBuffer.flip();

        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(byteBuffer);

        System.out.println(charBuffer.get());
        charBuffer.rewind();
        System.out.println(charBuffer.get());

        System.out.println(charBuffer.array());
        charBuffer.append("-");

        System.out.println(charBuffer.array());
    }

    public static void cleanSimple(){

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("cleanSimple".getBytes());
        byteBuffer.flip();

        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(byteBuffer);
        System.out.println(charBuffer.get());
        charBuffer.clear();

        System.out.println(charBuffer.get());
    }

    public static void main(String[] args){

        rewindSimple();









    }
}
