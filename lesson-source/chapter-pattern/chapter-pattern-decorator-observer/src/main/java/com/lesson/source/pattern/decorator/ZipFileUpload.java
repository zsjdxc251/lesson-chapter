package com.lesson.source.pattern.decorator;

/**
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class ZipFileUpload implements IFileUpload {


    private IFileUpload fileUpload;

    public ZipFileUpload(IFileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    @Override
    public void upload(String fileName) {

        System.out.println("现在上传的是zip文件");
        fileUpload.upload(fileName);


    }
}
