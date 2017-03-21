package com.lee.codegen.generator.convert;

import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.generator.def.FieldGenerateDefinition;

/**
 * 驼峰命名，并把数据库中字段的下划线去掉
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/21 上午10:42
 */
public class FieldNameConvert implements FieldConvert {
	@Override
	public boolean isEnable(ColumnDefinition columnDefinition) {
		return true;
	}

	@Override
	public void convert(ColumnDefinition columnDefinition, FieldGenerateDefinition fieldGenerateDefinition) {
		try {
			String fieldCode = columnDefinition.getCode();
			if (fieldCode != null) {
				String[] codeSegments = fieldCode.toLowerCase().split("_");
				StringBuilder sb = new StringBuilder(fieldCode.length() - (codeSegments.length - 1));
				for (int i = 0; i < codeSegments.length; i++) {
					if (i == 0) {
						sb.append(codeSegments[i]);
					} else {
						sb.append(codeSegments[i].substring(0, 1).toUpperCase());
						sb.append(codeSegments[i].substring(1));
					}
				}
                fieldGenerateDefinition.setFieldCode(sb.toString());
			}
		} catch (Throwable e) {
			throw new RuntimeException("转换字段" + columnDefinition.getCode() + "发生异常", e);
		}
	}
}
