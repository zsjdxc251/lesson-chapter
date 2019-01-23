package com.lesson.source.mybatis.generator;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.config.TableConfiguration;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2019/1/23.
 */
public class IntrospectedTableMyBatis3Impl extends org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl {


	@Override
	public TableConfiguration getTableConfiguration() {

		if (tableConfiguration != null){
			tableConfiguration.setDomainObjectName("C"+tableConfiguration.getDomainObjectName());
		}


		return super.getTableConfiguration();
	}


	@Override
	protected AbstractJavaClientGenerator createJavaClientGenerator() {
		if (tableConfiguration != null){
			tableConfiguration.setDomainObjectName("C"+tableConfiguration.getDomainObjectName());
		}
		return super.createJavaClientGenerator();
	}
}
