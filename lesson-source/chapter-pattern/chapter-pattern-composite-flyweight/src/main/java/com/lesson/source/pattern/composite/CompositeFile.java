package com.lesson.source.pattern.composite;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public class CompositeFile extends AbstractFile {

    private List<AbstractFile> files;

    public CompositeFile() {

    }
    public CompositeFile(List<AbstractFile> files) {
        this.files = files;
    }

    public void addFile(AbstractFile file){
        if (files == null){
            files = Lists.newArrayList();
        }
        files.add(file);
    }

    @Override
    public List<String> getPath() {
        List<String> paths = Lists.newArrayList();
        files.forEach(file->{
            List<String> childPath = file.getPath();
            paths.addAll(childPath);
        });
        return paths;
    }
}
