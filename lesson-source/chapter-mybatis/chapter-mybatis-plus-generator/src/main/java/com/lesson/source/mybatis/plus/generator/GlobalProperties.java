package com.lesson.source.mybatis.plus.generator;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;

/**
 * @author zhengshijun
 * @version created on 2019/2/28.
 */
public class GlobalProperties {

//    public static final String PROJECT_PATH =
//            "D:\\workspace\\githome\\github\\lesson-chapter\\lesson-source\\chapter-mybatis\\chapter-mybatis-plus";


    public static final String PROJECT_PATH =
            "D:\\workspace\\githome\\20171217\\lesson-chapter\\lesson-source\\chapter-mybatis\\chapter-mybatis-plus";

    public static GlobalConfig getGlobalConfig(){

        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(PROJECT_PATH + "/src/main/java");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setSwagger2(false);
        gc.setAuthor("zhengshijun");

        return gc;
    }
}
