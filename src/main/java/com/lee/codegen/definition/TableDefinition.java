package com.lee.codegen.definition;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 表格定义类
 *
 * @author kevinlee
 * @version 1.0
 * @date 2017/3/13 下午11:18
 */
public class TableDefinition extends BaseDefinition {
	/**
	 * 指令
	 */
	private String command;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 字段
	 */
	private Map<String, ColumnDefinition> columns = Maps.newHashMap();
	/**
	 * 外键
	 */
	private Map<String, KeyDefinition> keys = Maps.newHashMap();
	/**
	 * 主键集合
	 */
	private List<ColumnDefinition> primaryKeys = Lists.newArrayList();

	private PackageDefinition refPackage;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Map<String, ColumnDefinition> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, ColumnDefinition> columns) {
		this.columns = columns;
	}

	public Map<String, KeyDefinition> getKeys() {
		return keys;
	}

	public void setKeys(Map<String, KeyDefinition> keys) {
		this.keys = keys;
	}

	public List<ColumnDefinition> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<ColumnDefinition> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public PackageDefinition getRefPackage() {
		return refPackage;
	}

	/**
	 * 与TableRuleSet中的对应
	 * 	digester.addRule(table_pattern, new CheckedSetTopRule("setRefPackage", PackageDefinition.class));
	 * @param refPackage
	 */
	public void setRefPackage(PackageDefinition refPackage) {
		this.refPackage = refPackage;
	}

	/**
	 * TableRuleSet:
	 * digester.addSetNext(table_pattern + "/c:Columns/o:Column", "addColumnDefinitions");
	 * @param columnDef
	 */
	public void addColumnDefinitions(ColumnDefinition columnDef){
		this.columns.put(columnDef.getId(), columnDef);
	}

	/**
	 * TableRuleSet:
	 * digester.addSetNext(table_pattern+"/c:Keys/o:Key", "addKeyDefinitions");
	 * @param keyDef
	 */
	public void addKeyDefinitions(KeyDefinition keyDef){
		this.keys.put(keyDef.getId(), keyDef);
	}

	/**
	 * TableRuleSet:
	 * digester.addRule(table_pattern+"/c:PrimaryKey/o:Key", new AttributeSetRule("addPrimaryKeys", new String[]{"Ref"}));
	 * @param ref
	 */
	public void addPrimaryKeys(String ref) {
		KeyDefinition keyDef = this.keys.get(ref);
		List<String> columnRefIds = keyDef.getRefColumnIds();
		ColumnDefinition columnDef;
		for (String columnRef : columnRefIds) {
			columnDef = this.columns.remove(columnRef);
			columnDef.setIdentify(true);
			this.primaryKeys.add(columnDef);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(128);
		sb.append("refPackage:");
		sb.append(this.refPackage);
		sb.append("; id:");
		sb.append(this.getId());
		sb.append("; name:");
		sb.append(this.getName());
		sb.append("; code:");
		sb.append(this.getCode());
		sb.append("; command:");
		sb.append(this.command);
		sb.append("; columns:");
		sb.append(this.columns);
		sb.append("; keys:");
		sb.append(this.keys);
		sb.append("; primaryKeys:");
		sb.append(this.primaryKeys);
		return sb.toString();
	}
}
