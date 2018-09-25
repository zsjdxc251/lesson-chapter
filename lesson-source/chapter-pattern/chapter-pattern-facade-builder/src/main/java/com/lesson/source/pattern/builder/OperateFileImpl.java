package com.lesson.source.pattern.builder;

import java.io.File;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class OperateFileImpl implements OperateFile {

    private String authorization;

    private String connectString;

    private String namespace;

    public OperateFileImpl(OperateFileFactory.Builder builder) {
        this.authorization = builder.getUsername()+":"+builder.getPassword();
        this.connectString = builder.getHost()+":"+builder.getPort();
        this.namespace = builder.getPrefix();

    }
    @Override
    public boolean delete(File file) {
        return false;
    }
}
