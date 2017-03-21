package com.lee.codegen;

import com.lee.codegen.definition.DatabaseDefinition;
import com.lee.codegen.generator.impl.DatabaseGenerator;
import com.lee.codegen.parse.PdmParser;
import com.lee.codegen.setting.GenerateController;
import com.lee.codegen.setting.GenerateSetting;
import com.lee.codegen.template.FreemarkHandler;
import sun.java2d.loops.GeneralRenderer;

import java.io.*;

/**
 * @author kevinlee
 * @version V1.0
 * @date 2017/3/19 下午4:05
 */
public class GenerateCodeStart {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        System.out.println(path);
        InputStream in = null;

        try {
            in = new FileInputStream(path + "/src/main/resources/codegen.setting.xml");
            // 配置文件解析
            GenerateSetting setting = GenerateSetting.getInstance();
            setting.parseConfigFile(in);

            // 解析Freemarke模板
            FreemarkHandler.getInstance().init();
            File outputDir = new File(setting.getOutputDir());
            if (outputDir.exists() == false || outputDir.isDirectory() == false) {
               outputDir.mkdirs();
            }

            DatabaseDefinition databaseDef = null;
            String[] pdmFiles = setting.getPdmPath();
            // 加载pdm文件，可加载多个,用";"分开
            if (pdmFiles != null && pdmFiles.length > 0) {
                for (String pdmFile : pdmFiles) {
                    databaseDef = PdmParser.parse(new File(pdmFile)); // 数据库定义
                    String databaseType = setting.getDatabaseType(); // 数据库类型
                    if (databaseType == null || databaseType.length() < 1) {
                        databaseType = databaseDef.getDbcode();
                        setting.setDatabaseType(databaseType);
                    }

                    // 生成代码
                    GenerateController controller = GenerateController.getInstance();
                    DatabaseGenerator generator = controller.getDatabaseGenerator(databaseType);

                    generator.generate(databaseDef);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
