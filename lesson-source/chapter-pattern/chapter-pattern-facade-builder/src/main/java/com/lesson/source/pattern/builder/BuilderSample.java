package com.lesson.source.pattern.builder;

import java.io.File;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class BuilderSample {

    public static void main(String[] args){
        OperateFile operateFile =
                OperateFileFactory.builder().authorization("zsj","123").connect("127.0.0.1",8080).namespace("/").builder();

        operateFile.delete(new File(""));

    }
}
