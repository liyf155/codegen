package com.lee.codegen.definition;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 数据库定义类
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/13 下午10:19
 */
public class DatabaseDefinition extends BaseDefinition {
	/**
	 * 数据库类型名 Model/o:RootObject/c:Children/o:Model/c:DBMS/o:Shortcut/a:Name
	 */
	private String dbname;
	/**
	 * 数据库类型代码 Model/o:RootObject/c:Children/o:Model/c:DBMS/o:Shortcut/a:Code
	 */
	private String dbcode;
	/**
	 * 项目名 Model/o:RootObject/c:Children/o:Model/a:Name
	 */
	private String projectName;
	/**
	 * 项目代码 Model/o:RootObject/c:Children/o:Model/a:Code
	 */
	private String projectCode;
	/**
	 * Model/o:RootObject/c:Children/o:Model/c:Packages/o:Package
	 */
	private List<PackageDefinition> packages = Lists.newArrayList();
	/**
	 * Model/o:RootObject/c:Children/o:Model/c:Tables/o:Table
	 */
	private List<TableDefinition> tables = Lists.newArrayList();

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbcode() {
		return dbcode;
	}

	public void setDbcode(String dbcode) {
		this.dbcode = dbcode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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

	/**
	 * 解析规则里需要调用的方法
	 * 
	 * @param packageDef
	 */
	public void addPackages(PackageDefinition packageDef) {
		this.packages.add(packageDef);
	}

	/**
	 * 解析规则里需要调用的方法
     * TableRuleSet: digester.addSetNext(table_pattern, "addTables");
	 * 
	 * @param tableDef
	 */
	public void addTables(TableDefinition tableDef) {
		this.tables.add(tableDef);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(128);
		sb.append("dbname:");
		sb.append(this.dbname);
		sb.append("\ndbcode:");
		sb.append(this.dbcode);
		sb.append("\nprojectName:");
		sb.append(this.projectName);
		sb.append("\nprojectCode:");
		sb.append(this.projectCode);
		sb.append("\ntables:");
		sb.append(this.tables);
		sb.append("\n");
		sb.append(this.packages);
		return sb.toString();
	}
}
