package com.lesson.distributed.netty.quickstart.nio.channel;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author zhengshijun
 * @version created on 2018/11/5.
 */
public class FileChannelSample {


    public static void consumequeueForSchedule() throws Exception {
        FileChannel fileChannel = new RandomAccessFile("D:\\workspace\\home\\sample-bb\\store\\consumequeue\\sample"
            + "-topic-2\\0\\00000000000000000000", "r").getChannel();
        MappedByteBuffer buffer =  fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, 6000000);
        ByteBuffer byteBuffer = buffer.slice();
        byteBuffer.position(20);
        //byteBuffer.flip();

        System.out.println("po:"+byteBuffer.position());
        System.out.println("limit:"+byteBuffer.limit());
        while (true) {
            long offset = byteBuffer.getLong();
            int size = byteBuffer.getInt();
            if (offset == 0 && size ==0 ){
                break;
            }
            System.out.println("offset="+offset);
            System.out.println("size="+size);
            System.out.println("long="+byteBuffer.getLong());
            System.out.println("position="+byteBuffer.position());
        }
    }

    public static void consumequeue() throws Exception {

        FileChannel fileChannel = new RandomAccessFile("D:\\workspace\\home\\store\\consumequeue\\TopicTest\\1\\00000000000000000000", "r").getChannel();
        MappedByteBuffer buffer =  fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, 6000000);
        ByteBuffer byteBuffer = buffer.slice();
      // byteBuffer.position(253);
     //byteBuffer.flip();

        System.out.println(byteBuffer.position());
       System.out.println(byteBuffer.limit());
        while (true) {
            long offset = byteBuffer.getLong();
            int size = byteBuffer.getInt();
            if (offset == 0 && size ==0 ){
                break;
            }
            System.out.println(offset);
            System.out.println(size);
            System.out.println(byteBuffer.getLong());
            System.out.println(byteBuffer.position());
        }
    }


    public static void main(String[] args) throws Exception {
        consumequeueForSchedule();
    }


    public static void commitlog() throws Exception{

//        FileInputStream inputStream = new FileInputStream("F:\\workspace\\home\\store\\commitlog\\00000000000000000000");
//
//        FileChannel fc = inputStream.getChannel();

        FileChannel fileChannel = new RandomAccessFile("D:\\workspace\\home\\store\\commitlog\\00000000000000000000", "r").getChannel();
        //FileChannel fileChannel = new RandomAccessFile("F:\\workspace\\home\\store\\consumequeue\\SCHEDULE_TOPIC_XXXX\\1\\00000000000000000000", "r").getChannel();
        MappedByteBuffer buffer =  fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, 1024*1024*1024);



       // buffer.limit(60);
       // buffer.position(21);
        ByteBuffer byteBuffer = buffer.slice();
        byteBuffer.position(415324);

//
        int msgLen = byteBuffer.getInt();

        int msgIcCode =  byteBuffer.getInt();

        int bodyCrc = byteBuffer.getInt();

        int queueId = byteBuffer.getInt();
        System.out.println(queueId);

        int flag = byteBuffer.getInt();

        long queueOffset =  byteBuffer.getLong();
        System.out.println(queueOffset);

        long PHYSICALOFFSET = byteBuffer.getLong();
        System.out.println(PHYSICALOFFSET);

        int sysFlag = byteBuffer.getInt();
        System.out.println("sysFlag:"+sysFlag);

        long BORNTIMESTAMP = byteBuffer.getLong();

        ByteBuffer BORNHOST =  byteBuffer.get(new byte[8]);

        long STORETIMESTAMP = byteBuffer.getLong();

        byte[] bytesStore = new byte[8];
        ByteBuffer STOREHOSTADDRESS =  byteBuffer.get(bytesStore);
        System.out.println(Base64.getEncoder().encodeToString(bytesStore));

        int RECONSUMETIMES = byteBuffer.getInt();

        long PreparedOffset = byteBuffer.getLong();

        int bodyLength = byteBuffer.getInt();
        byte[] bytes = new byte[bodyLength];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));

        byte topicLength = byteBuffer.get();
        bytes =  new byte[topicLength];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));


        short propertiesLength =  byteBuffer.getShort();

        if (propertiesLength > 0){
            ByteBuffer properties = byteBuffer.get(new byte[propertiesLength]);
        }



        // consumqueue
//        while (true) {
//            long offset = byteBuffer.getLong();  偏移量
//            int size = byteBuffer.getInt();   msg 大小
//            if (!(offset >= 0 && size > 0)){
//                break;
//            }
//            System.out.println(offset);
//            System.out.println(size);
//            System.out.println(byteBuffer.getLong());
//        }



        fileChannel.close();





//        ByteBuffer bb = ByteBuffer.allocate(1024);
//
//       // bb.put("hello".getBytes());
//
//        bb.putInt(22);
//
//        bb.flip();
//
//        fc.write(bb);
//
//        bb.clear();
//        bb.putInt(66);
//        bb.putInt(55);
//        System.out.println(bb.limit());
//        System.out.println(bb.position());
//        bb.flip();
//        System.out.println(bb.limit());
//        System.out.println(bb.position());
//
//        System.out.println(bb.getInt());
//        System.out.println(bb.getInt());
//        System.out.println(bb.limit());
//        System.out.println(bb.position());
//        fc.write(bb);
//
//
//
//        bb.clear();
//        fc.close();
//        inputStream.close();



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
