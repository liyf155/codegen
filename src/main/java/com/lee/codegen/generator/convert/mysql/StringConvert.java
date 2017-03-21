package com.lee.codegen.generator.convert.mysql;

import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.generator.convert.FieldConvert;
import com.lee.codegen.generator.def.FieldGenerateDefinition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类型名称 显示长度 数据库类型 JAVA类型 JDBC类型索引 VARCHAR L+N VARCHAR java.lang.String 12 CHAR
 * N CHAR java.lang.String 1 TEXT 65535 VARCHAR java.lang.String -1
 * 
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午3:33
 */
public class StringConvert implements FieldConvert {
	private static final Pattern pattern = Pattern.compile("varchar(\\(\\d+\\))*|text|char(\\(\\d+\\))*");

	@Override
	public boolean isEnable(ColumnDefinition columnDefinition) {
		String jdbcType = columnDefinition.getDataType().trim().toLowerCase();
		Matcher matcher = pattern.matcher(jdbcType);
		if (matcher.matches())
			return true;
		return false;
	}

	@Override
	public void convert(ColumnDefinition columnDefinition, FieldGenerateDefinition fieldGenerateDefinition) {
		fieldGenerateDefinition.setJavaType("String");
	}
}
