package com.lee.codegen.rules;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

/**
 * 字段属性规则
 * 
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/17 下午6:56
 */
public class AttributeSetRule extends Rule {
	private String[] attributes;
	private String methodName;

	public AttributeSetRule(String methodName, String[] attributes) {
		this.methodName = methodName;
		this.attributes = attributes;
	}

	@Override
	public void begin(String namespace, String name, Attributes attributes) throws Exception {
		Object bean = this.digester.peek();
		String[] values = new String[this.attributes.length];
		for (int i = 0; i < this.attributes.length; i++) {
			values[i] = attributes.getValue(this.attributes[i]);
		}

		MethodUtils.invokeMethod(bean, this.methodName, values);
	}

}
