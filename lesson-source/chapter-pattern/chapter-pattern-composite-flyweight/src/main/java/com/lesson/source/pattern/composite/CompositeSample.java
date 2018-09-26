package com.lesson.source.pattern.composite;

import java.io.File;
import java.util.stream.Stream;

/**
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public class CompositeSample {

    public static void main(String[] args){


        CompositeFile compositeFile = new CompositeFile();

        File file = new File("F:\\tmp");
        File[] files = file.listFiles();
        Stream.of(files).forEach(item->{
            if (item.isDirectory()){
                compositeFile.addFile(new Folder(item));
            } else {
                compositeFile.addFile(new Document(item));
            }
        });

        compositeFile.getPath().forEach(System.out::println);

    }
}
