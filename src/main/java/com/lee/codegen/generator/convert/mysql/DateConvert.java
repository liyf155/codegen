package com.lee.codegen.generator.convert.mysql;

import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.generator.convert.FieldConvert;
import com.lee.codegen.generator.def.FieldGenerateDefinition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类型名称 显示长度 数据库类型 JAVA类型 JDBC类型索引
 * DATE 		10 		DATE 		java.sql.Date 		91
 * TIME 		8 		TIME 		java.sql.Time 		92
 * DATETIME 	19 		DATETIME 	java.sql.Timestamp 	93
 * TIMESTAMP 	19 		TIMESTAMP 	java.sql.Timestamp 	93
 * YEAR 		4 		YEAR 		java.sql.Date
 * 
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午3:35
 */
public class DateConvert implements FieldConvert {
	private static final Pattern pattern = Pattern.compile("date|time|datetime|year(\\(\\d+\\))*|timestamp(\\(\\d+\\))*");

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
		fieldGenerateDefinition.setJavaType("java.util.Date");
	}
}
