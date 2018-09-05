package com.chapter.common.io;

import com.google.common.io.ByteProcessor;
import com.google.common.io.ByteStreams;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author zhengshijun
 * @version created on 2018/9/5.
 */
public class LargeFileReaderTest {

    public static void main(String[] args){


        LargeFileReaderTest largeFileReaderTest = new LargeFileReaderTest();

        largeFileReaderTest.exampleGuavaIO("F:\\workspace\\githome\\custom\\lesson-chapter\\lesson-common\\pom.xml");


    }

    /**
     *
     *   Java BufferReader
     *
     */
    public void exampleBufferReader(String fileName){



        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String content;
            while ((content = bufferedReader.readLine()) != null) {
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    /**
     *
     *   Java 8
     *
     */
    public void exampleJava8(String fileName){

        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     *  Java Scanner
     * @param fileName
     */
    public void exampleJavaScanner(String fileName) {

        try (Scanner scanner = new Scanner(new FileInputStream(fileName),StandardCharsets.UTF_8.name())){
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param fileName
     */
    public void exampleCommonIO(String fileName){

        try {
            LineIterator lineIterator = FileUtils.lineIterator(new File(fileName));
            while (lineIterator.hasNext()) {
                System.out.println(lineIterator.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param fileName
     */
    public void exampleGuavaIO(String fileName) {

        try {
            List<String> lines = com.google.common.io.Files.readLines(new File(fileName), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            InputStream inputStream = new FileInputStream(fileName);
//
//            ByteStreams.readBytes(inputStream, new ByteProcessor<String>() {
//
//                private String lines = null;
//
//
//                @Override
//                public boolean processBytes(byte[] bytes, int i, int i1) throws IOException {
//                    lines = new String(bytes);
//                    return false;
//                }
//
//                @Override
//                public String getResult() {
//
//                    System.out.println(lines);
//                    return lines;
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }





}
