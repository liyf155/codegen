package com.lee.codegen.generator.convert.mysql;

import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.generator.convert.FieldConvert;
import com.lee.codegen.generator.def.FieldGenerateDefinition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 类型名称 显示长度 数据库类型 JAVA类型 JDBC类型索引 TINYINT 3 TINYINT UNSIGNED java.lang.Integer
 * -6 SMALLINT 5 SMALLINT UNSIGNED java.lang.Integer 5 MEDIUMINT 8 MEDIUMINT
 * UNSIGNED java.lang.Integer 4
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午3:27
 */
public class ByteConvert implements FieldConvert {

	private static final Pattern pattern = Pattern.compile("tinyint");

	@Override
	public void convert(ColumnDefinition columnDefinition, FieldGenerateDefinition fieldGenDef) {
		fieldGenDef.setJavaType("byte");
	}

	@Override
	public boolean isEnable(ColumnDefinition columnDefinition) {
		String jdbcType = columnDefinition.getDataType().trim().toLowerCase();
		Matcher matcher = pattern.matcher(jdbcType);
		if (matcher.matches())
			return true;
		return false;
	}
}
