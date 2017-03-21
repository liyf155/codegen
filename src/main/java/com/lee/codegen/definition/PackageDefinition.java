package com.lee.codegen.definition;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 包名定义类
 *
 * @author kevinlee
 * @date 2017/3/13 下午10:49
 * @version V1.0
 */
public class PackageDefinition extends BaseDefinition {

	/**
	 * 指令
	 */
	private String command;
	/**
	 * 所有包
	 */
	private List<PackageDefinition> packages = Lists.newArrayList();
	/**
	 * 所有表格信息
	 */
	private List<TableDefinition> tables = Lists.newArrayList();

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public List<PackageDefinition> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageDefinition> packages) {
		this.packages = packages;
	}

	public List<TableDefinition> getTables() {
		return tables;
	}

	public void setTables(List<TableDefinition> tables) {
		this.tables = tables;
	}

	public void addPackages(PackageDefinition packageDef) {
		this.packages.add(packageDef);
	}

	public void addTables(TableDefinition tableDef) {
		this.tables.add(tableDef);
	}
}
