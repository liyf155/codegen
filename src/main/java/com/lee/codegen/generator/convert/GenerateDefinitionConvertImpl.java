package com.lee.codegen.generator.convert;

import com.lee.codegen.definition.TableDefinition;
import com.lee.codegen.generator.def.TableGenerateDefinition;
import com.lee.codegen.generator.plugin.GenerateDefinitionConvertPlugin;

import java.util.List;

/**
 * 数据定义的转换实现
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午4:43
 */
public class GenerateDefinitionConvertImpl implements GenerateDefinitionConvet {
	private List<GenerateDefinitionConvertPlugin> convertPluginList;

	public List<GenerateDefinitionConvertPlugin> getConvertPluginList() {
		return convertPluginList;
	}

	public void setConvertPluginList(List<GenerateDefinitionConvertPlugin> convertPluginList) {
		this.convertPluginList = convertPluginList;
	}

	@Override
	public void convert(TableDefinition tableDefinition, TableGenerateDefinition tableGenerateDefinition) {
		if (this.convertPluginList != null && this.convertPluginList.size() > 0) {
			int i = this.convertPluginList.size() - 1;
			do {
				this.convertPluginList.get(i).convert(tableDefinition, tableGenerateDefinition);
				i--;
			} while (i >= 0);
		}
	}
}
