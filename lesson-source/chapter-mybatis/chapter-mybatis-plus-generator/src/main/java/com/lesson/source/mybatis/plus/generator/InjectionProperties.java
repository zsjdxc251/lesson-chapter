package com.lesson.source.mybatis.plus.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2019/2/28.
 */
public class InjectionProperties {


    public static InjectionConfig getInjectionConfig(String moduleName) {


        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing

                getConfig().getTemplate().setEntity("/templates/custom.entity.java");


            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return GlobalProperties.PROJECT_PATH + "/src/main/resources/mapper/" + moduleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        cfg.setFileCreate(((configBuilder, fileType, filePath) -> {


            File file = new File(filePath);


            if (file.exists() && fileType != FileType.ENTITY) {

                return Boolean.FALSE;


            }
            return Boolean.TRUE;
        }));
        return cfg;
    }


}
