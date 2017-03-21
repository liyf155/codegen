package com.lee.codegen.generator.impl;

import com.lee.codegen.definition.DatabaseDefinition;
import com.lee.codegen.definition.PackageDefinition;
import com.lee.codegen.definition.TableDefinition;
import com.lee.codegen.generator.BaseGenerator;
import com.lee.codegen.generator.CodeGenerateContext;
import com.lee.codegen.generator.ContextGenerator;
import com.lee.codegen.generator.filter.GenerateFilter;
import com.lee.codegen.setting.GenerateSetting;

import java.util.List;

/**
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午3:46
 */
public class DatabaseGenerator implements BaseGenerator<DatabaseDefinition> {
	private List<ContextGenerator> contextGeneratorList;
	private PackageGenerator packageGenerator;
	private TableGenerator tableGenerator;

	public List<ContextGenerator> getContextGeneratorList() {
		return contextGeneratorList;
	}

	public void setContextGeneratorList(List<ContextGenerator> contextGeneratorList) {
		this.contextGeneratorList = contextGeneratorList;
	}

	public PackageGenerator getPackageGenerator() {
		return packageGenerator;
	}

	public void setPackageGenerator(PackageGenerator packageGenerator) {
		this.packageGenerator = packageGenerator;
	}

	public TableGenerator getTableGenerator() {
		return tableGenerator;
	}

	public void setTableGenerator(TableGenerator tableGenerator) {
		this.tableGenerator = tableGenerator;
	}

	@Override
	public void generate(DatabaseDefinition databaseDef) {
		GenerateSetting setting = GenerateSetting.getInstance();
		setting.initBasePackage();

		/* 表定义模板生成 */
		List<TableDefinition> tableList = databaseDef.getTables();
		if (tableList.isEmpty() == false) {
			GenerateFilter filter = GenerateFilter.getInstance();
			int result = filter.checkPackageGen(null);
			if (filter.isPackageTableGenerate(result)) {
				int i = tableList.size() - 1;
				TableDefinition tableDef;
				do {
					tableDef = tableList.get(i);
					this.tableGenerator.generate(tableDef);
					i--;
				} while (i >= 0);
			}
		}
		/* 包定义模板生成 */
		List<PackageDefinition> packageList = databaseDef.getPackages();
		if (packageList.isEmpty() == false) {
			int i = packageList.size() - 1;
			PackageDefinition packageDef;
			do {
				packageDef = packageList.get(i);
				this.packageGenerator.generate(packageDef);
				i--;
			} while (i >= 0);
		}
		/* 上下文生成 */
		if (this.contextGeneratorList != null && this.contextGeneratorList.size() > 0) {
			int i = this.contextGeneratorList.size() - 1;
			CodeGenerateContext context = CodeGenerateContext.getInstance();
			do {
				this.contextGeneratorList.get(i).generate(context);
				i--;
			} while (i >= 0);
		}
	}
}
