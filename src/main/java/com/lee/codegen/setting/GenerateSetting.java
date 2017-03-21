package com.lee.codegen.setting;

import com.google.common.collect.Maps;
import org.apache.commons.digester.Digester;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午2:22
 */
public class GenerateSetting {
	/**
	 * 单例模式（饿汉）
	 */
	private static GenerateSetting instance = new GenerateSetting();

	private String outputDir; // 输出文件夹路径
	private String[] pdmPath; // pdm文件路径，支持多个pdm文件
	private String databaseType; // 数据库类型
	private String freemarkTemplateDir; // 模板文件路径
	private String freemarkCharset = "UTF-8"; // 模板文件编码
	private String includePackage; // 包含的包
	private String excludePackage; // 需过滤掉的包
	private String includeTable; // 需生成代码的表
	private String excludeTable; // 不需要生成代码的表
	private String basePackage; // 包的根名称
	private String basePackagePath; // 包根路径
	private Map<String, String> meta = Maps.newHashMap(); // 存放元数据

	private GenerateSetting() {
	}

	/**
	 * 供外部调用的实例化方法
	 * 
	 * @return
	 */
	public static GenerateSetting getInstance() {
		if (instance == null) {
			instance = new GenerateSetting();
		}
		return instance;
	}

	public void parseConfigFile(InputStream in) {
		if (in == null)
			return;
		Digester digester = new Digester();
		try {
			digester.push(this);
			digester.setValidating(false);
			digester.addCallMethod("CodeGen/outputDir", "setOutputDir", 0);
			digester.addCallMethod("CodeGen/pdmPath", "setPdmPath", 0);
			digester.addCallMethod("CodeGen/databaseType", "setDatabaseType", 0);
			digester.addCallMethod("CodeGen/freemarkTemplateDir", "setFreemarkTemplateDir", 0);
			digester.addCallMethod("CodeGen/freemarkCharset", "setFreemarkCharset", 0);

			digester.addCallMethod("CodeGen/includePackage", "setIncludePackage", 0);
			digester.addCallMethod("CodeGen/excludePackage", "setExcludePackage", 0);
			digester.addCallMethod("CodeGen/includeTable", "setIncludeTable", 0);
			digester.addCallMethod("CodeGen/excludeTable", "setExcludeTable", 0);
			digester.addCallMethod("CodeGen/basePackage", "setBasePackage", 0);

			digester.addCallMethod("CodeGen/GenMeta/property", "addMeta", 2, new Class<?>[] { String.class, String.class });
			digester.addCallParam("CodeGen/GenMeta/property", 0, "key");
			digester.addCallParam("CodeGen/GenMeta/property", 1, "value");

			digester.parse(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			digester.clear();
		}
		String dateStr = new SimpleDateFormat("yyyy年MM月dd日 aHH:mm:ss").format(new Date());
		this.meta.put("date", dateStr);
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String[] getPdmPath() {
		return pdmPath;
	}

	public void setPdmPath(String pdmPath) {
//		this.pdmPath = pdmPath;
        if (pdmPath != null && pdmPath.length() > 0) {
        	this.pdmPath = pdmPath.split(";");
		}
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public String getFreemarkTemplateDir() {
		return freemarkTemplateDir;
	}

	public void setFreemarkTemplateDir(String freemarkTemplateDir) {
		this.freemarkTemplateDir = freemarkTemplateDir;
	}

	public String getFreemarkCharset() {
		return freemarkCharset;
	}

	public void setFreemarkCharset(String freemarkCharset) {
		this.freemarkCharset = freemarkCharset;
	}

	public String getIncludePackage() {
		return includePackage;
	}

	public void setIncludePackage(String includePackage) {
		this.includePackage = includePackage;
	}

	public String getExcludePackage() {
		return excludePackage;
	}

	public void setExcludePackage(String excludePackage) {
		this.excludePackage = excludePackage;
	}

	public String getIncludeTable() {
		return includeTable;
	}

	public void setIncludeTable(String includeTable) {
		this.includeTable = includeTable;
	}

	public String getExcludeTable() {
		return excludeTable;
	}

	public void setExcludeTable(String excludeTable) {
		this.excludeTable = excludeTable;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getBasePackagePath() {
		return basePackagePath;
	}

	public void setBasePackagePath(String basePackagePath) {
		this.basePackagePath = basePackagePath;
	}

	public Map<String, String> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}

	public void addMeta(String name, String value) {
		this.meta.put(name, value);
	}

	/**
	 * 初始化
	 */
	public void initBasePackage() {
		if (this.basePackage != null && this.basePackage.trim().length() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(this.outputDir);

			String[] pathArray = this.basePackage.trim().split("\\.");
			for (String path : pathArray) {
				sb.append(File.separator);
				sb.append(path);
			}

			File file = new File(sb.toString());
			// 目录不存在则进行创建
			if (file.exists() == false && file.isDirectory() == false) {
				file.mkdirs();
			}
			this.basePackagePath = sb.toString();
		}
	}

	public boolean isBasePackageGenerate() {
		return this.basePackage != null && this.basePackage.trim().length() > 0;
	}
}
