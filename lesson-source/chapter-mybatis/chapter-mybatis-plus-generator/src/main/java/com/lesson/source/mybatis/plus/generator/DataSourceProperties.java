package com.lesson.source.mybatis.plus.generator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

/**
 * @author zhengshijun
 * @version created on 2019/2/28.
 */
public class DataSourceProperties {


    public static DataSourceConfig getDataSourceConfig() {


//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://10.0.20.4:3306/test1?useUnicode=true&useSSL=false&characterEncoding=utf8");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("123456");


        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("12345");
        return dsc;
    }
}
