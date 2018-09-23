package com.lesson.source.pattern.decorator;

/**
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class FileUpload implements IFileUpload {

    @Override
    public void upload(String fileName) {

        System.out.println("执行上传文件："+fileName);
    }
}
