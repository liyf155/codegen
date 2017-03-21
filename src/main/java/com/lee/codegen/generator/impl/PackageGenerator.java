package com.lee.codegen.generator.impl;

import com.lee.codegen.definition.PackageDefinition;
import com.lee.codegen.definition.TableDefinition;
import com.lee.codegen.generator.BaseGenerator;
import com.lee.codegen.generator.CodeGenerateContext;
import com.lee.codegen.generator.filter.GenerateFilter;
import com.lee.codegen.setting.GenerateSetting;
import com.lee.codegen.template.FreemarkHandler;

import java.io.File;
import java.util.List;

/**
 * 生成包
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午4:03
 */
public class PackageGenerator implements BaseGenerator<PackageDefinition> {
	private TableGenerator tableGenerator;
	private String templateName = "package.ftl";
	private String packageHtmlName = "package.html";

	public TableGenerator getTableGenerator() {
		return tableGenerator;
	}

	public void setTableGenerator(TableGenerator tableGenerator) {
		this.tableGenerator = tableGenerator;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getPackageHtmlName() {
		return packageHtmlName;
	}

	public void setPackageHtmlName(String packageHtmlName) {
		this.packageHtmlName = packageHtmlName;
	}

	@Override
	public void generate(PackageDefinition packageDefinition) {
		CodeGenerateContext context = CodeGenerateContext.getInstance();
		context.pushPackageName(packageDefinition.getCode());
		GenerateSetting setting = GenerateSetting.getInstance();

		GenerateFilter filter = GenerateFilter.getInstance();
		int result = filter.checkPackageGen(context.getCurrentPackageName());

		String path = context.getCurrentPackageDirPath();
		if (setting.isBasePackageGenerate() == false && filter.isPackageGenerate(result)) {
			File file = new File(path);
			if ((false == file.exists()) && (false == file.isDirectory())) {
				file.mkdirs();
			}
		}

		if (filter.isPackageTableGenerate(result)) {// 当前包定义可生成
			/* 生成包注释package.html */
			if (setting.isBasePackageGenerate() == false && packageDefinition.getCommand() != null && packageDefinition.getCommand().length() > 0) {
				FreemarkHandler handler = FreemarkHandler.getInstance();
				handler.handle(this.templateName, path + File.separator + this.packageHtmlName, packageDefinition);
			}
			/* 生成当前包下的表定义 */
			List<TableDefinition> tableList = packageDefinition.getTables();
			if (tableList.isEmpty() == false) {
				int i = tableList.size() - 1;
				TableDefinition tableDef;
				do {
					tableDef = tableList.get(i);
					this.tableGenerator.generate(tableDef);
					i--;
				} while (i >= 0);
			}
		}

		List<PackageDefinition> packageList = packageDefinition.getPackages();
		if (packageList.isEmpty() == false) {
			int i = packageList.size() - 1;
			PackageDefinition packageDef2;
			do {
				packageDef2 = packageList.get(i);
				this.generate(packageDef2);
				i--;
			} while (i >= 0);
		}

		context.popPackageName();
	}
}
