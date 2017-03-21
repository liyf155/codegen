package com.lee.codegen.rules;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

/**
 * 动态解析Table的规则，继承自org.apache.commons.digester.Rule;
 * Model/o:RootObject/c:Children/o:Model/c:Tables
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/17 下午6:29
 */
public class DynamicTableRule extends Rule {
    private String prefixPattern; // 字符串匹配

    public DynamicTableRule() {}

    public DynamicTableRule(String prefixPattern) {
        this.prefixPattern = prefixPattern;
    }

    @Override
    public void begin(String namespace, String name, Attributes attributes) throws Exception {
        this.digester.addRuleSet(new TableRuleSet(this.prefixPattern));
    }
}
