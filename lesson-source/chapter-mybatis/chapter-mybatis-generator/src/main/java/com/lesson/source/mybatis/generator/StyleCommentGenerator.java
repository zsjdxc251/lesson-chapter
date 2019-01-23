package com.lesson.source.mybatis.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;


/**
 * @author zhengshijun
 * @version created on 2019/1/22.
 */
public class StyleCommentGenerator implements CommentGenerator {

	private Properties properties = new Properties();
	private boolean suppressDate = false;
	private boolean suppressAllComments = false;
	private boolean addRemarkComments = false;
	private SimpleDateFormat dateFormat;

	/**
	 * 开始的分隔符，例如mysql为`，sqlserver为[
	 */
	private String beginningDelimiter = "";
	/**
	 * 结束的分隔符，例如mysql为`，sqlserver为]
	 */
	private String endingDelimiter = "";

	public StyleCommentGenerator() {
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);
		this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));
		this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
		this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
		String dateFormatString = properties.getProperty("dateFormat");
		if (StringUtility.stringHasValue(dateFormatString)) {
			this.dateFormat = new SimpleDateFormat(dateFormatString);
		}

		String beginningDelimiter = properties.getProperty("beginningDelimiter");
		if (StringUtility.stringHasValue(beginningDelimiter)) {
			this.beginningDelimiter = beginningDelimiter;
		}
		String endingDelimiter = properties.getProperty("endingDelimiter");
		if (StringUtility.stringHasValue(endingDelimiter)) {
			this.endingDelimiter = endingDelimiter;
		}
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {


		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			field.addJavaDocLine("/**");
			StringBuilder sb = new StringBuilder();
			sb.append(" * ");
			sb.append(introspectedColumn.getRemarks());
			field.addJavaDocLine(sb.toString());
			field.addJavaDocLine(" */");
		}
	}


	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

	}


	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean b) {

	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {

	}

	@Override
	public void addComment(XmlElement xmlElement) {

	}

	@Override
	public void addRootComment(XmlElement xmlElement) {

	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

		field.addAnnotation("@Column(name = \"" + field.getName() + "\")");
	}

	@Override
	public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

	}
}
