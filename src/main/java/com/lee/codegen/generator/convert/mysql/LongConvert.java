package com.lee.codegen.generator.convert.mysql;

import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.generator.convert.FieldConvert;
import com.lee.codegen.generator.def.FieldGenerateDefinition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Long类型的转换
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午3:41
 */
public class LongConvert implements FieldConvert {
	private static final Pattern pattern = Pattern.compile("bigint(\\(\\d+\\))*");

	@Override
	public void convert(ColumnDefinition columnDefinition, FieldGenerateDefinition fieldGenDef) {
		fieldGenDef.setJavaType("long");
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
