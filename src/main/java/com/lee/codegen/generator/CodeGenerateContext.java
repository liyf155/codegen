package com.lee.codegen.generator;

import com.lee.codegen.setting.GenerateSetting;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 生成代码的上下文
 *
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/20 下午4:15
 */
public class CodeGenerateContext {
	private static CodeGenerateContext instance = new CodeGenerateContext();

	private List<String> pathUnit = new ArrayList<>();
	private HashMap<String, Object> processData = new HashMap<>();

	private CodeGenerateContext() {
	}

	public static CodeGenerateContext getInstance() {
		if (instance == null) {
			instance = new CodeGenerateContext();
		}
		return instance;
	}

	public HashMap<String, Object> getProcessData() {
		return processData;
	}

	public void setProcessData(HashMap<String, Object> processData) {
		this.processData = processData;
	}

	/**
	 * 存放包的名字
	 *
	 * @param packageName
	 */
	public void pushPackageName(String packageName) {
		this.pathUnit.add(packageName);
	}

	/**
	 * 把最后存入的报名移除
	 */
	public void popPackageName() {
		this.pathUnit.remove(this.pathUnit.size() - 1);
	}

	/**
	 * 获取当前系统的项目路径
	 * 
	 * @return
	 */
	public String getCurrentPackageDirPath() {
		StringBuilder sb = new StringBuilder();
		sb.append(GenerateSetting.getInstance().getOutputDir());
		int size = this.pathUnit.size();
		for (int i = 0; i < size; i++) {
			sb.append(File.separator);
			sb.append(this.pathUnit.get(i));
		}
		return sb.toString();
	}

	/**
	 * 获取包的名称
	 *
	 * @return
	 */
	public String getCurrentPackageName() {
		StringBuilder sb = new StringBuilder();
		int size = this.pathUnit.size();
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				sb.append(".");
			}
			sb.append(this.pathUnit.get(i));
		}
		return sb.toString();
	}

    /**
     * 获取项目包的路径
     *
     * @return
     */
	public String getCurrentPackageProjectPath() {
		StringBuilder sb = new StringBuilder();
		int size = this.pathUnit.size();
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				sb.append("/");
			}
			sb.append(this.pathUnit.get(i));
		}
		return sb.toString();
	}
}
