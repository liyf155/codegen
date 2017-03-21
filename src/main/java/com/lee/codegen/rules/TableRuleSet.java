package com.lee.codegen.rules;

import com.lee.codegen.definition.ColumnDefinition;
import com.lee.codegen.definition.KeyDefinition;
import com.lee.codegen.definition.PackageDefinition;
import com.lee.codegen.definition.TableDefinition;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * Table的解析规则集合
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/17 下午6:33
 */
public class TableRuleSet extends RuleSetBase {
	private String prefixPattern;

	public TableRuleSet() {
		this("");
	}

	public TableRuleSet(String prefixPattern) {
		super();
		this.namespaceURI = null;
		this.prefixPattern = prefixPattern;
	}

	@Override
	public void addRuleInstances(Digester digester) {
		String table_pattern = this.prefixPattern + "/o:Table";
		/* 表的基本信息 */
		digester.addObjectCreate(table_pattern, TableDefinition.class);
		digester.addSetNext(table_pattern, "addTables");
		digester.addSetProperties(table_pattern, "Id", "id");
		digester.addCallMethod(this.prefixPattern + "/o:Table/a:Name", "setName", 0);
		digester.addCallMethod(this.prefixPattern + "/o:Table/a:Code", "setCode", 0);
		digester.addCallMethod(this.prefixPattern + "/o:Table/a:Comment", "setCommand", 0);
		digester.addCallMethod(this.prefixPattern + "/o:Table/a:Creator", "setCreator", 0);
		digester.addRule(table_pattern, new CheckedSetTopRule("setRefPackage", PackageDefinition.class));

		/* 字段信息 */
		digester.addObjectCreate(table_pattern + "/c:Columns/o:Column", ColumnDefinition.class);
		digester.addSetProperties(table_pattern + "/c:Columns/o:Column", "Id", "id");
		digester.addCallMethod(table_pattern + "/c:Columns/o:Column/a:Name", "setName", 0);
		digester.addCallMethod(table_pattern + "/c:Columns/o:Column/a:Code", "setCode", 0);
		digester.addCallMethod(table_pattern + "/c:Columns/o:Column/a:DataType", "setDataType", 0);
		digester.addCallMethod(table_pattern + "/c:Columns/o:Column/a:Comment", "setComment", 0);
		digester.addCallMethod(table_pattern + "/c:Columns/o:Column/a:Mandatory", "setMandatory", 0);
		digester.addSetNext(table_pattern + "/c:Columns/o:Column", "addColumnDefinitions");

		/*表健信息*/
		digester.addObjectCreate(table_pattern+"/c:Keys/o:Key", KeyDefinition.class);
		digester.addSetNext(table_pattern+"/c:Keys/o:Key", "addKeyDefinitions");
		digester.addSetProperties(table_pattern+"/c:Keys/o:Key",  "Id", "id");
		digester.addCallMethod(table_pattern+"/c:Keys/o:Key/a:Name", "setName", 0);
		digester.addCallMethod(table_pattern+"/c:Keys/o:Key/a:Code", "setCode", 0);
		digester.addCallMethod(table_pattern+"/c:Keys/o:Key/c:Key.Columns/o:Column", "addRefColumnIds", 1);
		digester.addCallParam(table_pattern+"/c:Keys/o:Key/c:Key.Columns/o:Column", 0, "Ref");

		/*主键信息*/
		digester.addRule(table_pattern+"/c:PrimaryKey/o:Key", new AttributeSetRule("addPrimaryKeys", new String[]{"Ref"}));
	}

}
