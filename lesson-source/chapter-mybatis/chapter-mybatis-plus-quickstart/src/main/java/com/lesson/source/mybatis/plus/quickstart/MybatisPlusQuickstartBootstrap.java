package com.lesson.source.mybatis.plus.quickstart;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.SerializedLambda;

/**
 *
 *  properties {@link MybatisPlusProperties}
 *
 *  {@link TableInfoHelper#initTableInfo(org.apache.ibatis.builder.MapperBuilderAssistant, java.lang.Class)}
 *
 *  {@link SerializedLambda}
 * @author zhengshijun
 * @version created on 2019/6/13.
 */
@Slf4j
@SpringBootApplication
public class MybatisPlusQuickstartBootstrap {

	public static void main(String[] args) {

		SpringApplication.run(MybatisPlusQuickstartBootstrap.class,args);
	}
}
