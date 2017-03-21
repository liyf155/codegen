package com.lee.codegen.rules;

import com.lee.codegen.definition.PackageDefinition;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * 包的规则集合
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/18 上午10:44
 */
public class PackageRuleSet extends RuleSetBase {
	protected String prefix = null;

	public PackageRuleSet() {
		this("");
	}

	public PackageRuleSet(String prefix) {
		super();
		this.namespaceURI = null;
		this.prefix = prefix;
	}

	@Override
	public void addRuleInstances(Digester digester) {
		String packet_pattern = this.prefix + "/o:Package";
		digester.addObjectCreate(packet_pattern, PackageDefinition.class);
		digester.addSetNext(packet_pattern, "addPackages");
		digester.addSetProperties(packet_pattern, "Id", "id");
		digester.addCallMethod(packet_pattern + "/a:Name", "setCode", 0);
		digester.addCallMethod(packet_pattern + "/a:Code", "setName", 0);
		digester.addCallMethod(packet_pattern + "/a:Comment", "setCommand", 0);
		digester.addRuleSet(new TableRuleSet(packet_pattern + "/c:Tables"));
		digester.addRule(packet_pattern + "/c:Packages", new DynamicPackageRule(packet_pattern + "/c:Packages"));

	}
}
