package com.lesson.source.mybatis.generator.plugins;

import com.lesson.source.mybatis.generator.common.BaseExample;
import com.lesson.source.mybatis.generator.common.PropertyColumn;
import com.lesson.source.mybatis.spring.model.OrderByEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
public class GeneratorPlugin extends PluginAdapter {

	private boolean useBaseMapper = true;


	public String[] javaDocLines = {
			"/**",
			"* @author Mybatis Generator",
			"* @version created on " + DateFormatUtils.format(new Date(), "yyyy/MM/dd."),
			"*/"};

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}


	/**
	 * 生成 Model
	 * {@link DefaultCommentGenerator#addModelClassComment(TopLevelClass, IntrospectedTable)}
	 *
	 * @param topLevelClass
	 * @param introspectedTable
	 * @return
	 */
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		//添加domain的import

		topLevelClass.addImportedType("lombok.Data");
		topLevelClass.addImportedType("lombok.Builder");
		topLevelClass.addImportedType("lombok.NoArgsConstructor");
		topLevelClass.addImportedType("lombok.AllArgsConstructor");

		topLevelClass.addImportedType("javax.persistence.Column");
		topLevelClass.addImportedType("javax.persistence.Id");
		topLevelClass.addImportedType("javax.persistence.Table");
		topLevelClass.addImportedType("javax.persistence.GeneratedValue");
		topLevelClass.addImportedType("javax.validation.constraints.NotNull");
		topLevelClass.addImportedType("org.apache.ibatis.type.Alias");

		//添加domain的注解
		topLevelClass.addAnnotation("@Table(name = \"" + introspectedTable.getFullyQualifiedTable() + "\")");
		topLevelClass.addAnnotation("@Alias(\"" + toLowerCaseFirstOne(introspectedTable.getTableConfiguration().getDomainObjectName()) + "\")");
		topLevelClass.addAnnotation("@Data");
		topLevelClass.addAnnotation("@Builder");
		topLevelClass.addAnnotation("@NoArgsConstructor");
		topLevelClass.addAnnotation("@AllArgsConstructor");

		//添加domain的注释
		Stream.of(javaDocLines).forEach(topLevelClass::addJavaDocLine);

		return true;
	}

	/**
	 * 首字母转小写
	 *
	 * @param s 字符串
	 * @return 结果
	 */
	private static String toLowerCaseFirstOne(String s) {
		if (StringUtils.isNotBlank(s)) {
			s = StringUtils.substringAfterLast(s, ".");
		}
		if (Character.isLowerCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}

	}


	/**
	 * {@link DefaultCommentGenerator#addFieldComment(Field, IntrospectedTable, IntrospectedColumn)}
	 */
	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {

		if (!introspectedColumn.isNullable()) {
			field.addAnnotation("@NotNull");
		}
		//添加注解
		if (field.isTransient()) {
			//@Column
			field.addAnnotation("@Transient");
		}

		List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
		for (IntrospectedColumn col : primaryKeyColumns) {
			if (col.getActualColumnName().equals(introspectedColumn.getActualColumnName())) {
				field.addAnnotation("@Id");

				if (col.isAutoIncrement()) {
					field.addAnnotation("@GeneratedValue");
				}
			}


		}
		field.addAnnotation("@Column(name = \"" + introspectedColumn.getActualColumnName() + "\")");
		return true;
	}


	/**
	 * 生成 Mapper
	 *
	 * @param interfaze
	 * @param topLevelClass
	 * @param introspectedTable
	 * @return
	 */
	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		if (useBaseMapper) {
			/**
			 * 主键默认采用java.lang.Long
			 */
			FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseMapper<"
					+ introspectedTable.getBaseRecordType() + ","
					+ introspectedTable.getExampleType() + ","
					+ introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType().getFullyQualifiedName() + ">");
			FullyQualifiedJavaType imp = new FullyQualifiedJavaType(
					"com.lesson.commons.mapper.BaseMapper");
			/**
			 * 添加 extends MybatisBaseMapper
			 */
			interfaze.addSuperInterface(fqjt);

			/**
			 * 添加BaseMapper
			 */
			interfaze.addImportedType(imp);
			/**
			 * 方法不需要
			 */
			interfaze.getMethods().clear();
			interfaze.getAnnotations().clear();
		}
		Stream.of(javaDocLines).forEach(interfaze::addJavaDocLine);
		return true;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		//不生成getter
		return false;
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		//不生成setter
		return false;
	}


	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		// XML 覆盖
		sqlMap.setMergeable(false);
		return true;
	}


	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		// /设置主键返回策略
		element.addAttribute(new Attribute("keyProperty", introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));
		element.addAttribute(new Attribute("keyColumn", introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName()));
		element.addAttribute(new Attribute("useGeneratedKeys", "true"));
		return true;
	}

	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {// 设置主键返回策略
		element.addAttribute(new Attribute("keyProperty", introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty()));
		element.addAttribute(new Attribute("keyColumn", introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName()));
		element.addAttribute(new Attribute("useGeneratedKeys", "true"));
		return true;
	}


	/**
	 * 生成 Example
	 *
	 * @param topLevelClass
	 * @param introspectedTable
	 * @return
	 */
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		Stream.of(javaDocLines).forEach(topLevelClass::addJavaDocLine);


		addOrder(topLevelClass, introspectedTable);


		addLimit(topLevelClass, introspectedTable, "limitStart");

		addLimit(topLevelClass, introspectedTable, "limitEnd");

		addLimit(topLevelClass, introspectedTable, "limit");
		topLevelClass.getMethods().clear();
		topLevelClass.getInnerClasses().clear();
		topLevelClass.getFields().clear();
		topLevelClass.getImportedTypes().clear();

		addField(topLevelClass, introspectedTable);
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	private void addField(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType(PropertyColumn.class.getName());
		topLevelClass.addImportedType(BaseExample.class.getName());
		topLevelClass.setSuperClass(new FullyQualifiedJavaType(BaseExample.class.getName()));
		String alias = introspectedTable.getTableConfiguration().getAlias() != null ? introspectedTable.getTableConfiguration().getAlias() : StringUtils.EMPTY;

		Stream.concat(introspectedTable.getBaseColumns().stream(), introspectedTable.getPrimaryKeyColumns()
				.stream())
				.forEach(column -> {
					Field field = new Field();
					field.setVisibility(JavaVisibility.PUBLIC);
					field.setStatic(true);
					String name = column.getJavaProperty();
					field.setName(name);
					field.setType(new FullyQualifiedJavaType(PropertyColumn.class.getName()));

					field.addJavaDocLine("/**");
					field.addJavaDocLine(" *" + column.getRemarks());
					field.addJavaDocLine(" */");

					// field.setInitializationString("new PropertyColumn(\"" + MyBatis3FormattingUtilities.getAliasedActualColumnName(column) + "\",\"" + name + "\",\"" + alias + "\")");

					field.setInitializationString("new PropertyColumn(\"" + column.getActualColumnName() + "\",\"" + name + "\",\"" + alias + "\")");

					topLevelClass.addField(field);

				});
	}

	private void addOrder(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Field field = new Field("orderBuffer", new FullyQualifiedJavaType(StringBuilder.class.getName()));
		field.setVisibility(JavaVisibility.PROTECTED);
		topLevelClass.addField(field);

		Stream.concat(introspectedTable.getBaseColumns().stream(), introspectedTable.getPrimaryKeyColumns()
				.stream())
				.filter(column ->
						StringUtils.equalsAnyIgnoreCase(column.getFullyQualifiedJavaType().getFullyQualifiedName(), Long.class.getName(), Integer.class.getName())
				)
				.forEach(column -> {
					String name = column.getJavaProperty();
					char c = name.charAt(0);
					String camel = Character.toUpperCase(c) + name.substring(1);


					Method method = new Method();
					method.setVisibility(JavaVisibility.PUBLIC);


					method.setName("addOrder" + camel);

					topLevelClass.addImportedType(StringUtils.class.getName());
					topLevelClass.addImportedType(OrderByEnum.class.getName());

					method.addParameter(new Parameter(new FullyQualifiedJavaType(OrderByEnum.class.getName()), "orderType"));

					method.addBodyLine("if (orderBuffer == null) {");
					method.addBodyLine("orderBuffer = new StringBuilder();");
					method.addBodyLine("} else {");
					method.addBodyLine("orderBuffer.append(\",\");");
					method.addBodyLine("}");
					method.addBodyLine("orderBuffer.append(\"" + column.getTableAlias() + "." + column.getActualColumnName() + " \"+orderType.name());");
//			method.addBodyLine("orderByClause = orderByClause.concat((orderByClause != null?\",\":StringUtils.EMPTY)+ \""+column.getTableAlias()+"."+column.getActualColumnName()+" \"+orderType.name());");

					commentGenerator.addGeneralMethodComment(method, introspectedTable);

					topLevelClass.addMethod(method);
				});

		topLevelClass.getMethods().stream().filter(method -> StringUtils.equals(method.getName(), "getOrderByClause")).forEach(method -> {

			method.getBodyLines().set(0, "return orderBuffer != null ? orderBuffer.toString() : null; ");

		});
	}

	private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {

		CommentGenerator commentGenerator = context.getCommentGenerator();


		Field field = new Field();

		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(FullyQualifiedJavaType.getIntInstance());

		field.setName(name);
		field.setInitializationString("-1");

		commentGenerator.addFieldComment(field, introspectedTable);

		topLevelClass.addField(field);

		char c = name.charAt(0);

		String camel = Character.toUpperCase(c) + name.substring(1);

		Method method = new Method();

		method.setVisibility(JavaVisibility.PUBLIC);

		method.setName("set" + camel);

		method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), name));

		method.addBodyLine("this." + name + "=" + name + ";");

		commentGenerator.addGeneralMethodComment(method, introspectedTable);

		topLevelClass.addMethod(method);

		method = new Method();

		method.setVisibility(JavaVisibility.PUBLIC);

		method.setReturnType(FullyQualifiedJavaType.getIntInstance());

		method.setName("get" + camel);

		method.addBodyLine("return " + name + ";");

		commentGenerator.addGeneralMethodComment(method, introspectedTable);

		topLevelClass.addMethod(method);

	}


	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		XmlElement isNotNullElement = new XmlElement("if");

		isNotNullElement.addAttribute(new Attribute("test", "limitStart != null and limitStart >=0"));
		isNotNullElement.addElement(new TextElement("limit #{limitStart},#{limitEnd}"));
		element.addElement(isNotNullElement);


		//$NON-NLS-1$
		isNotNullElement = new XmlElement("if");
		//$NON-NLS-1$ //$NON-NLS-2$
		isNotNullElement.addAttribute(new Attribute("test", "limit != null and limit >=0"));
		isNotNullElement.addElement(new TextElement("limit #{limit}"));
		element.addElement(isNotNullElement);
		return super.sqlMapExampleWhereClauseElementGenerated(element, introspectedTable);
	}


	@Override
	public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		String alias = introspectedTable.getTableConfiguration().getAlias() != null ? introspectedTable.getTableConfiguration().getAlias() : StringUtils.EMPTY;

		if (StringUtils.isNotBlank(alias) && !ObjectUtils.isEmpty(element.getElements())) {
			// 补充 deleteByExample 删除 bug -->  `delete from t_user_bank_info ubi` updated `delete ubi from t_user_bank_info ubi`
			Element e;
			for (int i = 0; i < element.getElements().size(); i++) {
				e = element.getElements().get(i);
				if (e instanceof TextElement) {
					String content = TextElement.class.cast(e).getContent();
					if (StringUtils.isNotBlank(content)) {
						String newContent = StringUtils.replace(content, "delete", "delete " + alias);

						element.getElements().set(i, new TextElement(newContent));
					}

				}
			}
		}
		return super.sqlMapDeleteByExampleElementGenerated(element, introspectedTable);
	}
}
