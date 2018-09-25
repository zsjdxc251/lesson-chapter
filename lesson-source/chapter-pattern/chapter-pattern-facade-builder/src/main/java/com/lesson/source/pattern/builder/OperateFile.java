package com.lesson.source.pattern.builder;

import java.io.File;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public interface OperateFile {

    /**
     *  删除
     * @param file 文件
     * @return 是否成功
     */
    boolean delete(File file);
}
