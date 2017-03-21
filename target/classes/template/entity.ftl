package ${pluginAttributes.packageName};

<#if pluginAttributes.importClass?exists&&pluginAttributes.importClass?size gt 0>
<#list pluginAttributes.importClass as className>
import ${className};
</#list>
</#if>

/**
 * ${command}类
 * @tableName ${tableDefinition.code}
 * @Title ${beanName}${pluginAttributes.voSuffix}.java
 * @Package ${pluginAttributes.packageName}
 * @Description 
 * Copyright: Copyright (c) 2017
 * Company:
 *
 * @author <a href="mailto:${meta.email}">${meta.author}</a>
 * @date ${meta.date}
 * @version V1.0
 */
public class ${beanName}${pluginAttributes.voSuffix} {
<#if identifyFields?size gt 0>
	<#list identifyFields as field>
	/**
	 * ${field.command!""}
	 * @label ${field.fieldName}
	 * @code ${field.columnDefinition.code}
	 * @ColumnDataType ${field.columnDefinition.dataType}
	 * @javaType ${field.javaType}
	 * @IsOID ${field.identify?string}
	 * @required ${field.nullable?string}
	 */
	private ${field.javaType?substring((field.javaType?last_index_of('.'))+1)} ${field.fieldCode};
	</#list>
</#if>
<#if propertyFields?size gt 0>
	<#list propertyFields as field>
	/**
	 * ${field.command!""}
	 * @label ${field.fieldName}
	 * @code ${field.columnDefinition.code}
	 * @ColumnDataType ${field.columnDefinition.dataType}
	 * @javaType ${field.javaType}
	 * @IsOID ${field.identify?string}
	 * @required ${field.nullable?string}
	 */
	private ${field.javaType?substring((field.javaType?last_index_of('.'))+1)} ${field.fieldCode};
	</#list>
</#if>
	
	/**
	 * 默认构造函数
	 */
	public ${beanName}${pluginAttributes.voSuffix}(){}
<#if identifyFields?size gt 0>
	<#list identifyFields as field>
	
	/**
	 * ${field.command!""}
	 * @return<br/>
	 *  	${field.javaType?substring((field.javaType?last_index_of('.'))+1)} : ${field.fieldName}
	 */
	public ${field.javaType?substring((field.javaType?last_index_of('.'))+1)} get${field.fieldCode?cap_first}() {
		return this.${field.fieldCode};
	}
	/**
	 * ${field.command!""}
	 * @param ${field.fieldCode}<br/>
	 * 		  ${field.fieldName}
	 */
	public void set${field.fieldCode?cap_first}(${field.javaType?substring((field.javaType?last_index_of('.'))+1)} ${field.fieldCode}) {
		this.${field.fieldCode}=${field.fieldCode};
	}
	</#list>
</#if>
<#if propertyFields?size gt 0>
	<#list propertyFields as field>
	
	/**
	 * ${field.command!""}
	 *  @return<br/>
	 * 		${field.javaType?substring((field.javaType?last_index_of('.'))+1)} : ${field.fieldName}
	 */
	public ${field.javaType?substring((field.javaType?last_index_of('.'))+1)} get${field.fieldCode?cap_first}() {
		return this.${field.fieldCode};
	}
	/**
	 * ${field.command!""}
	 * @param ${field.fieldCode}<br/>
	 * 		  ${field.fieldName}
	 */
	public void set${field.fieldCode?cap_first}(${field.javaType?substring((field.javaType?last_index_of('.'))+1)} ${field.fieldCode}) {
		this.${field.fieldCode}=${field.fieldCode};
	}
	</#list>
</#if>
}