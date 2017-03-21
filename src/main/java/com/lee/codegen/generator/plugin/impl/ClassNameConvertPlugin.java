package com.lee.codegen.generator.plugin.impl;

import com.lee.codegen.definition.TableDefinition;
import com.lee.codegen.generator.def.TableGenerateDefinition;
import com.lee.codegen.generator.plugin.GenerateDefinitionConvertPlugin;

/**
 * 名称的转换插件
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午6:18
 */
public class ClassNameConvertPlugin implements GenerateDefinitionConvertPlugin {
	@Override
	public void convert(TableDefinition tableDefinition, TableGenerateDefinition tableGenerateDefinition) {
		String tableCode = tableDefinition.getCode().toLowerCase();
		String[] codeSegment = tableCode.split("_");
		StringBuilder sb = new StringBuilder(tableCode.length() - (codeSegment.length - 1));
		int i = 0;
		if (codeSegment.length > 1) {
			i = 1;
		}
		do {
			sb.append(codeSegment[i].substring(0, 1).toUpperCase());
			sb.append(codeSegment[i].substring(1));
			i++;
		} while (i < codeSegment.length);
		tableGenerateDefinition.setBeanName(sb.toString());
	}
}
