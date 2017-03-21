package com.lee.codegen.generator.def;

import com.google.common.collect.Maps;
import com.lee.codegen.definition.ColumnDefinition;

import java.util.Map;

/**
 * 变量定义类
 * 
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午2:01
 */
public class FieldGenerateDefinition {
	private ColumnDefinition columnDefinition;
	/**
	 * 是否是主键
	 */
	private boolean identify;
	/**
	 * Java类型
	 */
	private String javaType;
	/**
	 * 数据库类型
	 */
	private String jdbcType;
	/**
	 * 字段名称
	 */
	private String fieldName;
	/**
	 * 字段编码
	 */
	private String fieldCode;
	/**
	 *
	 */
	private String command;
	/**
	 * 是否为空
	 */
	private boolean nullable;
	/**
	 * 表生成的运行属性
	 */
	private Map<String, Object> attributes = Maps.newHashMap();
	/**
	 * 文件生成插件的运行属性
	 */
	private Map<String, Object> pluginAttributes = Maps.newHashMap();

	public FieldGenerateDefinition(ColumnDefinition columnDefinition) {
		this.columnDefinition = columnDefinition;
		this.fieldCode = columnDefinition.getCode();
		this.fieldName = columnDefinition.getName();
		this.command = columnDefinition.getComment();
		if (this.command == null || this.command.length() < 1) {
			this.command = this.fieldName;
		}
		this.nullable = columnDefinition.isNullable();
		this.identify = columnDefinition.isIdentify();
	}

	public ColumnDefinition getColumnDefinition() {
		return columnDefinition;
	}

	public void setColumnDefinition(ColumnDefinition columnDefinition) {
		this.columnDefinition = columnDefinition;
	}

	public boolean isIdentify() {
		return identify;
	}

	public void setIdentify(boolean identify) {
		this.identify = identify;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Map<String, Object> getPluginAttributes() {
		return pluginAttributes;
	}

	public void setPluginAttributes(Map<String, Object> pluginAttributes) {
		this.pluginAttributes = pluginAttributes;
	}

	public void addAttributes(String key, Object value) {
		this.attributes.put(key, value);
	}

	public void clearAttributes() {
		this.attributes.clear();
	}

	public void addPluginAttributes(String key, String value) {
		this.pluginAttributes.put(key, value);
	}

	public void clearPluginAttributes() {
		this.pluginAttributes.clear();
	}
}
