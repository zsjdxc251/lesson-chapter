package com.lesson.distributed.netty.quickstart.nio.buffer;

import com.example.common.utils.StringTools;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.IntStream;

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

        Charset charset = StandardCharsets.UTF_8;
        CharBuffer charBuffer = charset.decode(byteBuffer);


        charBuffer.mark();
        System.out.println(charBuffer.get());

        charBuffer.reset();
        System.out.println(charBuffer.get());

    }

    /**
     *
     *   在Buffer 中 rewind 还原 重新读
     *   position =0
     *   mark = -1
     *
     */
    public static void rewindSimple(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("rewindSimple".getBytes());
        byteBuffer.flip();
        byteBuffer.get();
        byteBuffer.rewind();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());

    }

    /**
     *
     *   把 position 还原为 0 可以重新写
     *
     */
    public static void cleanSimple(){

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("cleanSimple".getBytes());
        byteBuffer.flip();


        System.out.println(byteBuffer.get());


        byteBuffer.clear();

        byteBuffer.put("l".getBytes());
        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array()));
    }


    /**
     *
     *   判断是否有可读数据，需要先调用 {@link ByteBuffer#flip()}
     *
     */
    public static void remainingSimple(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("remainingSimple".getBytes());

        byteBuffer.flip();

        // 只读缓冲区
        byteBuffer.asReadOnlyBuffer();
        System.out.println(byteBuffer.remaining());

    }

    /**
     *    {@link ByteBuffer#compact()}
     *    完成
     *    position 到 数组长度 值
     *    limit 到 capacity 值
     *
     *    刚好和 {@link Buffer#flip()} 相反
     *
     *
     *    而 {@link Buffer#clear()} 是把 position 设置为初始值，limit 设置为原来的值，便于重新写入
     *
     */
    private static void compactSimple(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("compactSimple".getBytes());



        byteBuffer.flip();

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());

        byteBuffer.clear();

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
    }


    /**
     *    只读缓冲区
     *
     *    在可变缓冲区中分生出来的 Buffer
     *
     *    如果可变Buffer 改变了，只读缓冲区也会随之改变
     *
     *    如果容器数量增多了则不会需要调整 只读缓冲区
     *
     *
     */
    private static void readonlySimple(){
        ByteBuffer buffer = ByteBuffer.allocate( 11 );

        IntStream.range(0,10).forEach(i->{
            buffer.put((byte)i);
        });
        buffer.flip();
        System.out.println("可变原数据");
        while (buffer.remaining()>0) {
            System.out.print(buffer.get()+"\t");
        }

        ByteBuffer readonlyBuffer = buffer.asReadOnlyBuffer();
        readonlyBuffer.flip();
        System.out.println("\n只读原数据");
        while (readonlyBuffer.remaining()>0) {
            System.out.print(readonlyBuffer.get()+"\t");
        }




        buffer.rewind();
        readonlyBuffer.rewind();

        // 修改可变原数据
        for (int i=0;i<buffer.limit();i++){
            byte b = buffer.get(i);
            // 原来数据基础乘以10
            b*=10;
            buffer.put(i,b);
        }
        buffer.compact();
        buffer.put((byte)10);
        buffer.flip();
        System.out.println("\n可变改变后数据");
        while (buffer.remaining()>0) {
            System.out.print(buffer.get()+"\t");
        }

        // 如果增加了数据需要调整 只读缓冲区limit 大小才能读的到添加元素
        readonlyBuffer.limit(readonlyBuffer.limit()+1);
        System.out.println("\n只读改变后数据");
        while (readonlyBuffer.remaining()>0) {
            System.out.print(readonlyBuffer.get()+"\t");
        }
    }


    /**
     *    创建
     *
     *
     */
    public static void createSimple(){

        ByteBuffer buffer = ByteBuffer.allocate( 11 );

        byte[] bytes = new byte[1024];
        buffer = ByteBuffer.wrap(bytes);


        buffer = ByteBuffer.allocateDirect(1024);




    }


    /**
     *
     *   分隔
     *
     */
    public static void sliceSimple(){
        ByteBuffer buffer = ByteBuffer.allocate( 1024 );
        IntStream.range(0,10).forEach(i->{
            buffer.put((byte)i);
        });
        buffer.flip();
        System.out.println("原数据");
        while (buffer.remaining()>0) {
            System.out.print(buffer.get()+"\t");
        }

        // 创建新分隔
        buffer.position(2);
        buffer.limit(4);
        ByteBuffer sliceBuffer = buffer.slice();

        System.out.println("\n分隔数据");
        while (sliceBuffer.remaining()>0) {
            System.out.print(sliceBuffer.get()+"\t");
        }
    }


    /**
     *
     *
     *
     */
    private static void mappedBufferSimple() throws IOException {
        RandomAccessFile file = new RandomAccessFile( "F:\\tmp\\test.txt", "rw" );

        FileChannel channel = file.getChannel();

        System.out.println(channel.size());
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE,0,channel.size());

        for (long i=0;i<channel.size();i++){
            mappedByteBuffer.put((byte)(i*10));
        }

        channel.close();
        file.close();
    }


    public static void main(String[] args) throws IOException {

        mappedBufferSimple();



        System.out.println(StringTools.toBinaryString(~SelectionKey.OP_WRITE&(SelectionKey.OP_READ|SelectionKey.OP_WRITE)));
        System.out.println(StringTools.toBinaryString(SelectionKey.OP_READ|SelectionKey.OP_WRITE));

        System.out.println(StringTools.toBinaryString((SelectionKey.OP_READ|SelectionKey.OP_WRITE)^SelectionKey.OP_WRITE));
        System.out.println();
        System.out.println(StringTools.toBinaryString(SelectionKey.OP_READ));
        System.out.println(StringTools.toBinaryString(SelectionKey.OP_WRITE));
        System.out.println(StringTools.toBinaryString(SelectionKey.OP_CONNECT));
        System.out.println(StringTools.toBinaryString(SelectionKey.OP_ACCEPT));




    }
}
