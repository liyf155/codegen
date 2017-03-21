package com.lee.codegen.rules;

import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

/**
 * 包的解析规则
 * Model/o:RootObject/c:Children/o:Model/c:Packages
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/17 下午7:01
 */
public class DynamicPackageRule extends Rule {
    private String prefixPattern;

    public DynamicPackageRule(String prefixPattern){
        this.prefixPattern = prefixPattern;
    }

    @Override
    public void begin(String namespace, String name, Attributes attributes) throws Exception {

        this.digester.addRuleSet(new PackageRuleSet(this.prefixPattern));

    }
}
