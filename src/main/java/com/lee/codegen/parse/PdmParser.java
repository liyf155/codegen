package com.lee.codegen.parse;

import com.lee.codegen.definition.DatabaseDefinition;
import com.lee.codegen.rules.DynamicPackageRule;
import com.lee.codegen.rules.DynamicTableRule;
import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.*;

/**
 * 数据库设计文件PDM的解析工具
 *
 * @author kevinlee
 * @date 2017/3/13 下午10:07
 * @version V1.0
 */
public class PdmParser {
	// 日志记录类
	private static final Logger logger = LoggerFactory.getLogger(PdmParser.class);

	/**
	 * pdm文件的解析
	 * 
	 * @param pdmFile
	 * @return
	 */
	public static DatabaseDefinition parse(File pdmFile) {
		if (pdmFile.exists() == false) {
			logger.info("pdm文件路径不对");
			return null;
		}
		DatabaseDefinition definition = null;
		Digester digester = null;

		try (InputStream in = new FileInputStream(pdmFile)) {
			definition = new DatabaseDefinition();
			digester = createDigester();
			digester.setValidating(false);
			digester.push(definition);
			definition = (DatabaseDefinition) digester.parse(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		return definition;
	}

	/**
	 * 通过Digester来解析pdm文件，pdm实际上就是个XML文件(用sublime text打开就可看到文件内容)
	 *
	 * @return
	 */
	private static Digester createDigester() {
		Digester digester = new Digester();
		digester.addCallMethod("Model/o:RootObject/c:Children/o:Model/c:DBMS/o:Shortcut/a:Name", "setDbname", 0);
		digester.addCallMethod("Model/o:RootObject/c:Children/o:Model/c:DBMS/o:Shortcut/a:Code", "setDbcode", 0);

		digester.addCallMethod("Model/o:RootObject/c:Children/o:Model/a:Name", "setProjectName", 0);
		digester.addCallMethod("Model/o:RootObject/c:Children/o:Model/a:Code", "setProjectCode", 0);
		digester.addRule("Model/o:RootObject/c:Children/o:Model/c:Tables", new DynamicTableRule("Model/o:RootObject/c:Children/o:Model/c:Tables"));
		digester.addRule("Model/o:RootObject/c:Children/o:Model/c:Packages", new DynamicPackageRule("Model/o:RootObject/c:Children/o:Model/c:Packages"));

		return digester;
	}

//	public static void main(String[] args) {
//		File file = new File("/Users/kevinlee/Desktop/test.pdm");
//		DatabaseDefinition definition = parse(file);
//		System.out.println("解析成功！" + definition.toString());
//	}
}
