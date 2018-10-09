package com.lesson.source.mybatis.generator.plugins;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
public class GeneratorPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return false;
    }
}
