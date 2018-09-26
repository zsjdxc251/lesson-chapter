package com.lesson.source.pattern.composite;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public class Folder extends AbstractFile {

    private File file;

    public Folder(File file) {
        this.file = file;
    }

    @Override
    public List<String> getPath() {
        List<String> result = Lists.newArrayList();
        File[] files = file.listFiles();
        if (files != null){
            Stream.of(files).forEach(item->result.add(item.getPath()));
        }
        return result;
    }
}
