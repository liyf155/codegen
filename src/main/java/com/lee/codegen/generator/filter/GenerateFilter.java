package com.lee.codegen.generator.filter;

import com.lee.codegen.definition.TableDefinition;
import com.lee.codegen.setting.GenerateSetting;

import java.util.HashSet;
import java.util.Set;

/**
 * 过滤器
 * 
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午5:23
 */
public class GenerateFilter {
	public static final int PACKAGE_FILTER_RESULT_GEN_PACKAGE = 1;
	public static final int PACKAGE_FILTER_RESULT_GEN_PACKAGE_TABLE = 2;
	public static final int PACKAGE_FILTER_RESULT_GEN_ALL = 3;
	private static final GenerateFilter inst = new GenerateFilter();
	private boolean init = false;
	private Set<String> includePackage;
	private Set<String> excludePackage;
	private Set<String> includeTable;
	private Set<String> excludeTable;

	private GenerateFilter() {
	}

	public static GenerateFilter getInstance() {
		return inst;
	}

	private void init() {
		if (this.init)
			return;
		this.init = true;
		GenerateSetting setting = GenerateSetting.getInstance();
		String string = setting.getIncludePackage();
		String[] stringArray;
		if (string != null) {
			string = string.trim();
			if (string.length() > 0) {
				stringArray = string.split(";");
				this.includePackage = new HashSet<String>(stringArray.length);
				for (int i = 0; i < stringArray.length; i++) {
					this.includePackage.add(stringArray[i]);
				}
			}
		}

		string = setting.getExcludePackage();
		if (string != null) {
			string = string.trim();
			if (string.length() > 0) {
				stringArray = string.split(";");
				this.excludePackage = new HashSet<String>(stringArray.length);
				for (int i = 0; i < stringArray.length; i++) {
					this.excludePackage.add(stringArray[i]);
				}
			}
		}

		string = setting.getIncludeTable();
		if (string != null) {
			string = string.trim();
			if (string.length() > 0) {
				stringArray = string.split(";");
				this.includeTable = new HashSet<String>(stringArray.length);
				for (int i = 0; i < stringArray.length; i++) {
					this.includeTable.add(stringArray[i]);
				}
			}
		}

		string = setting.getExcludeTable();
		if (string != null) {
			string = string.trim();
			if (string.length() > 0) {
				stringArray = string.split(";");
				this.excludeTable = new HashSet<String>(stringArray.length);
				for (int i = 0; i < stringArray.length; i++) {
					this.excludeTable.add(stringArray[i]);
				}
			}
		}
	}

	public int checkPackageGen(String packageName) {
		this.init();

		if (this.includePackage == null) {
			if (this.excludePackage == null) {
				return PACKAGE_FILTER_RESULT_GEN_ALL;
			} else {
				if (packageName == null)
					return PACKAGE_FILTER_RESULT_GEN_ALL;
				for (String string : this.excludePackage) {
					if (packageName.startsWith(string)) {
						return 0;
					}
				}
				return PACKAGE_FILTER_RESULT_GEN_ALL;
			}
		} else {
			if (packageName == null)
				return 0;
			for (String string : this.includePackage) {
				if (packageName.length() < string.length()) {
					if (string.startsWith(packageName)) {
						return PACKAGE_FILTER_RESULT_GEN_PACKAGE;
					}
				} else {
					if (packageName.startsWith(string)) {
						return PACKAGE_FILTER_RESULT_GEN_ALL;
					}
				}
			}
			return 0;
		}
	}

	/**
	 * 是否生成包
	 *
	 * @param packageGenResult
	 * @return
	 */
	public boolean isPackageGenerate(int packageGenResult) {
		return (packageGenResult & PACKAGE_FILTER_RESULT_GEN_PACKAGE) == PACKAGE_FILTER_RESULT_GEN_PACKAGE;
	}

	public boolean isPackageTableGenerate(int packageGenResult) {
		return (packageGenResult & PACKAGE_FILTER_RESULT_GEN_PACKAGE_TABLE) == PACKAGE_FILTER_RESULT_GEN_PACKAGE_TABLE;
	}

	public boolean isGeneratable(TableDefinition tableDefinition) {
		this.init();
		String tableName = tableDefinition.getCode();
		if (this.includeTable == null) {
			if (this.excludeTable == null) {
				return true;
			} else {
				return (false == this.excludeTable.contains(tableName));
			}
		} else {
			return this.includeTable.contains(tableName);
		}
	}
}
