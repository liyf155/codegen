package com.lee.codegen.rules;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.digester.Rule;

/**
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/17 下午6:42
 */
public class CheckedSetTopRule extends Rule {
    /**
     * 方法名称
     */
	private String methodName;
    /**
     * 参数类
     */
	private Class paramClass;

	public CheckedSetTopRule(String methodName, Class paramClass) {
		this.methodName = methodName;
		this.paramClass = paramClass;
	}

	public void end() throws Exception {
		// Identify the objects to be used
		Object child = digester.peek(0);
		Object parent = digester.peek(1);

		// Call the specified method
		Class paramTypes[] = new Class[1];
		if (paramClass != null) {
			if (!parent.getClass().isAssignableFrom(paramClass))
				return;
			paramTypes[0] = paramClass;
		} else {
			paramTypes[0] = parent.getClass();
		}

		MethodUtils.invokeMethod(child, methodName, new Object[] { parent }, paramTypes);
	}
}
