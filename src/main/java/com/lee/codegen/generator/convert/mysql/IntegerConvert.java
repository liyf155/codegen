package com.lee.codegen.generator.convert.mysql;

import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.generator.convert.FieldConvert;
import com.lee.codegen.generator.def.FieldGenerateDefinition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * int 类型的转换
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午3:39
 */
public class IntegerConvert implements FieldConvert {
	// int类型的正则表达式
	private static final Pattern pattern = Pattern.compile("int(\\(\\d+\\))*|mediumint(\\(\\d+\\))*|smallint(\\(\\d+\\))*");

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
		fieldGenerateDefinition.setJavaType("int");
	}
}
