package com.lee.codegen.generator.impl;

import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.definition.TableDefinition;
import com.lee.codegen.generator.BaseGenerator;
import com.lee.codegen.generator.convert.GenerateDefinitionConvet;
import com.lee.codegen.generator.def.TableGenerateDefinition;
import com.lee.codegen.generator.filter.GenerateFilter;
import com.lee.codegen.generator.plugin.TableGeneratorPlugin;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午3:56
 */
public class TableGenerator implements BaseGenerator<TableDefinition> {
	private static final Pattern TABLE_NAME_PATTERN = Pattern.compile("[_A-Za-z]+");
	private List<TableGeneratorPlugin> plugins;
	private GenerateDefinitionConvet genDefConverter;

	@Override
	public void generate(TableDefinition tableDefinition) {
		if (this.isGeneratable(tableDefinition)) {
			TableGenerateDefinition genDef = new TableGenerateDefinition(tableDefinition);
			this.genDefConverter.convert(tableDefinition, genDef);
			if (this.plugins != null && this.plugins.size() > 0) {
				int i = this.plugins.size() - 1;
				do {
					this.plugins.get(i).generate(genDef);
					genDef.clearPluginAttribute();
					i--;
				} while (i >= 0);
			}
			genDef.clearAttribute();
		}
	}

	private boolean isGeneratable(TableDefinition tableDef) {
		GenerateFilter filter = GenerateFilter.getInstance();
		if (filter.isGeneratable(tableDef) == false)
			return false;
		/* 表名合法性校验 */
		if (TABLE_NAME_PATTERN.matcher(tableDef.getCode()).matches() == false) {
            return false;
        }
		/* 表数据类型如没定义也不生成 */
		Map<String, ColumnDefinition> columnDefinitions = tableDef.getColumns();
		for (Map.Entry<String, ColumnDefinition> entry : columnDefinitions.entrySet()) {
			if (entry.getValue().getDataType() == null) {
                return false;
            }
		}
		return true;
	}

	public List<TableGeneratorPlugin> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<TableGeneratorPlugin> plugins) {
		this.plugins = plugins;
	}

	public GenerateDefinitionConvet getGenDefConverter() {
		return genDefConverter;
	}

	public void setGenDefConverter(GenerateDefinitionConvet genDefConverter) {
		this.genDefConverter = genDefConverter;
	}
}
