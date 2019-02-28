package com.lesson.source.mybatis.generator.common;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhengshijun
 * @version created on 2019/2/13.
 */
@NoArgsConstructor
public class PropertyColumn {
	private String column;

	private String property;

	private String alias;

	public PropertyColumn(String column, String property,String alias) {
		this.column = column;
		this.property = property;
		this.alias = alias;
	}

	public String getColumn() {
		return column;
	}

	public String getProperty() {
		return property;
	}

	public String getAlias() {
		if (StringUtils.isNotBlank(alias)){
			return alias.concat(".");
		}
		return alias;
	}
}
