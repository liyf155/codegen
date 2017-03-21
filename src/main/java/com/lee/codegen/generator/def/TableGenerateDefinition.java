package com.lee.codegen.generator.def;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lee.codegen.definition.TableDefinition;
import com.lee.codegen.setting.GenerateSetting;

import java.util.List;
import java.util.Map;

/**
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午2:11
 */
public class TableGenerateDefinition {
	public static final String SYS_BEAN_FULLNAME = "SYS_BEAN_FULLNAME";
	public static final String SYS_BEAN_SIMPLENAME = "SYS_BEAN_SIMPLENAME";
	private TableDefinition tableDefinition;
	private String beanName; // 对象名称
	private String command; // 命令

	/**
	 * 标识字段集合
	 */
	private List<FieldGenerateDefinition> identifyFields = Lists.newArrayList();
	/**
	 * 属性集合
	 */
	private List<FieldGenerateDefinition> propertyFields = Lists.newArrayList();
	/**
	 * 所有集合
	 */
	private List<FieldGenerateDefinition> allFields = Lists.newArrayList();
	/**
	 * 表生成的运行属性
	 */
	private Map<String, Object> attributes = Maps.newHashMap();
	/**
	 * 插件属性
	 */
	private Map<String, Object> pluginAttributes = Maps.newHashMap();
	/**
	 * 元数据
	 */
	private Map<String, String> meta = Maps.newHashMap();

	/**
	 * 构造函数
	 * 
	 * @param tableDefinition
	 */
	public TableGenerateDefinition(TableDefinition tableDefinition) {
		this.tableDefinition = tableDefinition;
		this.beanName = this.tableDefinition.getCode();
		this.command = this.tableDefinition.getCommand();
		if (this.command == null || this.command.length() < 1) {
			this.command = this.tableDefinition.getName();
		}
		this.meta.putAll(GenerateSetting.getInstance().getMeta());
		if (meta.containsKey("author") == false) {
			meta.put("author", this.tableDefinition.getCreator());
		}
	}

	public TableDefinition getTableDefinition() {
		return tableDefinition;
	}

	public void setTableDefinition(TableDefinition tableDefinition) {
		this.tableDefinition = tableDefinition;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public List<FieldGenerateDefinition> getIdentifyFields() {
		return identifyFields;
	}

	public void setIdentifyFields(List<FieldGenerateDefinition> identifyFields) {
		this.identifyFields = identifyFields;
	}

	public List<FieldGenerateDefinition> getPropertyFields() {
		return propertyFields;
	}

	public void setPropertyFields(List<FieldGenerateDefinition> propertyFields) {
		this.propertyFields = propertyFields;
	}

	public void setAllFields(List<FieldGenerateDefinition> allFields) {
		this.allFields = allFields;
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

	public Map<String, String> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}

	public void addIdentifyField(FieldGenerateDefinition fieldGenDef) {
		this.identifyFields.add(fieldGenDef);
	}

	public void addPluginAttribute(String name, Object value) {
		this.pluginAttributes.put(name, value);
	}

	public void addAttribute(String name, Object value) {
		this.attributes.put(name, value);
	}

	/**
	 * 清除属性
	 */
	public void clearAttribute() {
		this.attributes.clear();
		if (this.identifyFields != null && this.identifyFields.size() > 0) {
			int i = this.identifyFields.size() - 1;
			do {
				this.identifyFields.get(i).clearAttributes();
				i--;
			} while (i >= 0);
		}
		if (this.propertyFields != null && this.propertyFields.size() > 0) {
			int i = this.propertyFields.size() - 1;
			do {
				this.propertyFields.get(i).clearAttributes();
				i--;
			} while (i >= 0);
		}

	}

	/**
	 * 清除插件的属性
	 */
	public void clearPluginAttribute() {
		this.pluginAttributes.clear();
		if (this.identifyFields != null && this.identifyFields.size() > 0) {
			int i = this.identifyFields.size() - 1;
			do {
				this.identifyFields.get(i).clearPluginAttributes();
				i--;
			} while (i >= 0);
		}
		if (this.propertyFields != null && this.propertyFields.size() > 0) {
			int i = this.propertyFields.size() - 1;
			do {
				this.propertyFields.get(i).clearPluginAttributes();
				i--;
			} while (i >= 0);
		}
	}

	/**
	 * 获取所有的属性
	 * 
	 * @return
	 */
	public List<FieldGenerateDefinition> getAllFields() {
		if (allFields.isEmpty()) {
			this.allFields.addAll(this.identifyFields);
			this.allFields.addAll(this.propertyFields);
		} else {
			int allSize = this.allFields.size();
			int identifySize = this.identifyFields.size();
			int propertySize = this.propertyFields.size();
			if (allSize != (identifySize + propertySize)) {
				this.allFields.clear();
				this.allFields.addAll(this.identifyFields);
				this.allFields.addAll(this.propertyFields);
			}
		}
		return allFields;
	}
}
