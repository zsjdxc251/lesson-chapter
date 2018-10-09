package com.lesson.source.mybatis.generator;

import org.apache.ibatis.io.Resources;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
public class GeneratorSample {

    public static void main(String[] args) throws Exception{

        List<String> warnings = new ArrayList<>();
        // 是否覆盖
        boolean overwrite = true;
        InputStream inputStream = Resources.getResourceAsStream("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);

        Configuration config = cp.parseConfiguration(inputStream);

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

    }
}
