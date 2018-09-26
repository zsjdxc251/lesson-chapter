package com.lesson.source.pattern.composite;

import com.google.common.collect.ImmutableList;

import java.io.File;
import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public class Document extends AbstractFile {

    private File file;

    public Document(File file) {
        this.file = file;
    }

    @Override
    public List<String> getPath() {
        return ImmutableList.of(file.getPath());
    }
}
