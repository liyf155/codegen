package com.lee.codegen.generator.plugin.impl;

import com.lee.codegen.generator.CodeGenerateContext;
import com.lee.codegen.generator.def.FieldGenerateDefinition;
import com.lee.codegen.generator.def.TableGenerateDefinition;
import com.lee.codegen.generator.plugin.TableGeneratorPlugin;
import com.lee.codegen.setting.GenerateSetting;
import com.lee.codegen.template.FreemarkHandler;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 实体类生成插件
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午6:08
 */
public class EntityVoGeneratorPlugin implements TableGeneratorPlugin {
	private String templateName; // 模板名称
	private String voSuffix = "VO";
	private String subPackageName = "vo";

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getVoSuffix() {
		return voSuffix;
	}

	public void setVoSuffix(String voSuffix) {
		this.voSuffix = voSuffix;
	}

	public String getSubPackageName() {
		return subPackageName;
	}

	public void setSubPackageName(String subPackageName) {
		this.subPackageName = subPackageName;
	}

	@Override
	public void generate(TableGenerateDefinition definition) {
		try {
			CodeGenerateContext context = CodeGenerateContext.getInstance();
			GenerateSetting setting = GenerateSetting.getInstance();
			String pakcageName;

			String dirpath;
			if (setting.isBasePackageGenerate()) {
				pakcageName = setting.getBasePackage() + "." + this.subPackageName;

				File file = new File(setting.getBasePackagePath() + File.separator + this.subPackageName);
				if ((false == file.exists()) && (false == file.isDirectory())) {
					file.mkdirs();
				}
				dirpath = file.getAbsolutePath();
			} else {
				String currentPackageName = context.getCurrentPackageName();
				if (currentPackageName == null || currentPackageName.length() < 1) {
					pakcageName = this.subPackageName;
				} else {
					pakcageName = currentPackageName + "." + this.subPackageName;
				}
				dirpath = context.getCurrentPackageDirPath() + File.separator + this.subPackageName;
				File file = new File(dirpath);
				if ((false == file.exists()) && (false == file.isDirectory())) {
					file.mkdirs();
				}
			}

			definition.addPluginAttribute("packageName", pakcageName);

			// 类名
			String beanName = definition.getBeanName() + this.voSuffix;
			definition.addPluginAttribute("voSuffix", this.voSuffix);

			// 包的名称
			definition.addAttribute(TableGenerateDefinition.SYS_BEAN_FULLNAME, pakcageName + "." + beanName);
			// 类的名称
			definition.addAttribute(TableGenerateDefinition.SYS_BEAN_SIMPLENAME, beanName);

			Set<String> importClasses = new HashSet<>();
			Set<String> fieldSet = definitionProcess(definition.getIdentifyFields());
			Set<String> propertySet = definitionProcess(definition.getPropertyFields());
			importClasses.addAll(fieldSet);
			importClasses.addAll(propertySet);

			if (importClasses.size() > 0) {
				definition.addPluginAttribute("importClass", importClasses);
			}

			// 生成代码
			FreemarkHandler handler = FreemarkHandler.getInstance();
			handler.handle(this.templateName, dirpath + File.separator + beanName + ".java", definition);
		} catch (Throwable e) {
			throw new RuntimeException("生成表" + definition.getTableDefinition().getCode() + "发生异常", e);
		}
	}

	/**
	 * 数据定义的处理
	 *
	 * @param defs
	 * @return
	 */
	private Set<String> definitionProcess(List<FieldGenerateDefinition> defs) {
		Set<String> importClasses = new HashSet<String>();
		FieldGenerateDefinition fieldGenDef;
		if (defs != null && defs.size() > 0) {
			int i = defs.size() - 1;
			do {
				fieldGenDef = defs.get(i);

				if (fieldGenDef.getJavaType().lastIndexOf(".") != -1) {
					importClasses.add(fieldGenDef.getJavaType());
				}
				i--;
			} while (i >= 0);

		}
		return importClasses;
	}
}
