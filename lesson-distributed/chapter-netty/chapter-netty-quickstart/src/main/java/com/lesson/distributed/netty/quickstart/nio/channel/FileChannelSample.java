package com.lesson.distributed.netty.quickstart.nio.channel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zhengshijun
 * @version created on 2018/11/5.
 */
public class FileChannelSample {


    public static void main(String[] args) throws Exception{

        FileOutputStream inputStream = new FileOutputStream("F:\\tmp\\新建文本文档.txt");

        FileChannel fc = inputStream.getChannel();


        ByteBuffer bb = ByteBuffer.allocate(1024);


        bb.put("hello".getBytes());

        bb.flip();

        fc.write(bb);

        bb.clear();

        bb.put("world".getBytes());

        bb.flip();

        fc.write(bb);



        bb.clear();
        fc.close();
        inputStream.close();



        /********************************************************************************/

        Path path = Paths.get("F:\\资料\\File\\微位\\周报.txt");

        FileChannel fileChannel = FileChannel.open(path);

        ByteBuffer byteBuffer = ByteBuffer.allocate((int)fileChannel.size()+1);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(byteBuffer);
        System.out.println(charBuffer.toString());
        byteBuffer.clear();
        fileChannel.close();





    }
}
