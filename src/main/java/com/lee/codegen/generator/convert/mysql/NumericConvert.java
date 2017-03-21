package com.lee.codegen.generator.convert.mysql;

import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.generator.convert.FieldConvert;
import com.lee.codegen.generator.def.FieldGenerateDefinition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字类型的转换
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午3:42
 */
public class NumericConvert implements FieldConvert {
	private static final Pattern pattern = Pattern.compile("numeric.*");

	@Override
	public boolean isEnable(ColumnDefinition columnDefinition) {
		String jdbcType = columnDefinition.getDataType().trim().toLowerCase();
		Matcher matcher = pattern.matcher(jdbcType);
		if (matcher.matches())
			return true;
		return false;
	}

	@Override
	public void convert(ColumnDefinition columnDefinition, FieldGenerateDefinition fieldGenDef) {
		String dataType = columnDefinition.getDataType();
		int idx1 = dataType.indexOf("(");
		int idx2 = dataType.indexOf(")");
		String numDef = "9";
		if (idx1 != -1) {
			numDef = dataType.substring(idx1 + 1, idx2);
		}
		idx1 = numDef.indexOf(",");
		if (idx1 == -1) {// 整数
			int p = Integer.valueOf(numDef.trim());
			if (p < 3) {
				fieldGenDef.setJavaType("byte");
				fieldGenDef.setJdbcType("Types.TINYINT");
			} else if (p < 5) {
				fieldGenDef.setJavaType("short");
				fieldGenDef.setJdbcType("Types.SMALLINT");
			} else if (p < 10) {
				fieldGenDef.setJavaType("int");
				fieldGenDef.setJdbcType("Types.INTEGER");
			} else if (p <= 18) {
				fieldGenDef.setJavaType("long");
				fieldGenDef.setJdbcType("Types.BIGINT");
			} else {
				fieldGenDef.setJavaType("java.math.BigDecimal");
				fieldGenDef.setJdbcType("Types.DECIMAL");
			}
		} else {// 浮点数
			int p = Integer.valueOf(numDef.substring(0, idx1).trim());
			if (p <= 5) {
				fieldGenDef.setJavaType("float");
				fieldGenDef.setJdbcType("Types.FLOAT");
			} else if (p <= 14) {
				fieldGenDef.setJavaType("double");
				fieldGenDef.setJdbcType("Types.DOUBLE");
			} else {
				fieldGenDef.setJavaType("java.math.BigDecimal");
				fieldGenDef.setJdbcType("Types.DECIMAL");
			}
		}
	}
}
