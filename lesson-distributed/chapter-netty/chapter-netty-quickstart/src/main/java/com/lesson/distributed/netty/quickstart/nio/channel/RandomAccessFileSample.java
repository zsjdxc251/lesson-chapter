package com.lesson.distributed.netty.quickstart.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhengshijun
 * @version created on 2020/6/3.
 */
public class RandomAccessFileSample {


    public static void main(String[] args)throws Exception {

        loadIndexHeader();
    }

    public static void loadIndexHeader() throws IOException {
        RandomAccessFile file = new RandomAccessFile("F:\\workspace\\home\\store\\index\\20200603105057054","r");
        MappedByteBuffer mbb = file.getChannel().map(FileChannel.MapMode.READ_ONLY,0,1024);

        int beginTimestampIndex = 0;
        int endTimestampIndex = 8;
        int beginPhyoffsetIndex = 16;
        int endPhyoffsetIndex = 24;
        int hashSlotcountIndex = 32;
        int indexCountIndex = 36;
        ByteBuffer byteBuffer = mbb.slice();
        System.out.println(byteBuffer.getLong(beginTimestampIndex));
        System.out.println(byteBuffer.getLong(endTimestampIndex));
        System.out.println(byteBuffer.getLong(beginPhyoffsetIndex));
        System.out.println(byteBuffer.getLong(endPhyoffsetIndex));
        System.out.println(byteBuffer.getInt(hashSlotcountIndex));
        System.out.println(byteBuffer.getInt(indexCountIndex));


        file.close();

    }


    public static void testForce() throws IOException {

        //FileOutputStream inputStream = new FileOutputStream("D:\\temp\\文件.txt");
        // FileChannel fileChannel = FileChannel.open(Paths.get("D:\\temp\\文件.txt"), StandardOpenOption.WRITE);

        RandomAccessFile file = new RandomAccessFile("D:\\temp\\文件.txt","rw");
        MappedByteBuffer mbb = file.getChannel().map(FileChannel.MapMode.READ_WRITE,0,1024);



        System.out.println(mbb.position());
        System.out.println(mbb.limit());
        ByteBuffer bb = ByteBuffer.allocate(100);

        bb.put("hello4343433434342323333333333333333333333".getBytes());

        // bb.putInt(22);

        bb.flip();

        //fc.write(bb);

//        bb.clear();
//        bb.putInt(66);
//        bb.putInt(55);

        //bb.flip();


        //file.getChannel().write(bb);

        mbb.put(bb);

        System.out.println(mbb.position());
        System.out.println(mbb.limit());
        // mbb.flip();


        // mbb.force();

        //bb.clear();

        //System.out.println(mbb.capacity());
        // file.close();



        /********************************************************************************/

//        Path path = Paths.get("F:\\资料\\File\\微位\\周报.txt");
//
//        FileChannel fileChannel = FileChannel.open(path);
//
//        ByteBuffer byteBuffer = ByteBuffer.allocate((int)fileChannel.size()+1);
//        fileChannel.read(byteBuffer);
//        byteBuffer.flip();
//        Charset charset = Charset.forName("UTF-8");
//        CharBuffer charBuffer = charset.decode(byteBuffer);
//        System.out.println(charBuffer.toString());
//        byteBuffer.clear();
//        fileChannel.close();



    }
}
